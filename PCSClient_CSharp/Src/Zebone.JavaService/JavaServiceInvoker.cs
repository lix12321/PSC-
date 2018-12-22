using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

using Newtonsoft.Json;

using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 进行Java服务的调用
    /// </summary>
    internal class JavaServiceInvoker
    {
        private static int StatusCode_Exception = 10;
        private static string SessionId = string.Empty;

        private Uri serviceUri;
        private bool isLoginService;
        private bool asyncMode;

        internal JavaServiceInvoker(Uri serviceUri, bool isLoginService, bool asyncMode)
        {
            this.serviceUri = serviceUri;
            this.isLoginService = isLoginService;
            this.asyncMode = asyncMode;
        }

        /// <summary>
        /// 服务发生错误时触发的事件
        /// </summary>
        internal event EventHandler<ServiceErrorEventArgs> Error;

        /// <summary>
        /// 服务调用进度发生改变时触发的事件
        /// </summary>
        internal event EventHandler ProgressChanged;

        internal JavaServiceResult<T> Invoke<T>(string transactionCode, object transactionParameter)
        {
            if (!asyncMode && ProgressChanged != null) ProgressChanged(this, EventArgs.Empty);

            string responseContent = string.Empty;
            string parameter = Serialize(transactionParameter);

            try
            {
                responseContent = PerformRequest(transactionCode, parameter);

                var result = Deserialize<JavaServiceResult<T>>(responseContent);

                result.Success = result.status >= 0;

                if (!result.Success)
                {
                    var message = result.desc;
                    if (string.IsNullOrEmpty(message)) message = "后台错误，错误代码：" + result.status.ToString() + "， 错误信息：" + Environment.NewLine + result.errorMessage;

                    result.ErrorInfo = OnError(result.status, message, transactionCode, parameter, responseContent, asyncMode);
                }

                return result;
            }
            catch (Exception e)
            {
                var errorInfo = OnException(e, transactionCode, parameter, responseContent, asyncMode);

                return new JavaServiceResult<T>()
                {
                    status = StatusCode_Exception,
                    desc = e.Message,
                    ErrorInfo = errorInfo
                };
            }
        }

        private string PerformRequest(string transactionCode, string transactionParameter)
        {
            ////尝试从外界读取单点登录信息
            //if (string.IsNullOrEmpty(SessionId) && ServiceFactory.Properties.ContainsKey("JSESSIONID"))
            //{
            //    SessionId = ServiceFactory.Properties["JSESSIONID"];
            //}

            //确定访问的Uri地址
            var uriString = string.Empty;
            if (isLoginService)
            {
                uriString = serviceUri.ToString() + transactionCode;
            }
            else
            {
                //uriString = serviceUri.ToString() + "proxy/handle.zb?transCode=" + transactionCode;
                uriString = serviceUri.ToString() + transactionCode + "?" + transactionParameter;
            }

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uriString);

#if DEBUG
            request.Timeout = Int32.MaxValue;
#endif

            request.AutomaticDecompression = DecompressionMethods.GZip | DecompressionMethods.Deflate;
            request.Accept = "application/json,text/javascript,*/*;q=0.01";
            request.ContentType = "application/x-www-form-urlencoded";
            //request.Method = "POST";
            request.Method = "GET";
            request.Headers.Add("ProductName", "Zebone.His.exe");

            //设置请求Cookie
            CookieContainer cookieArray = new CookieContainer();
            CookieCollection cookies = new CookieCollection();
            cookies.Add(new Cookie("JSESSIONID", SessionId));
            cookieArray.Add(serviceUri, cookies);
            request.CookieContainer = cookieArray;

            ////对于请求参数进行处理
            //if (transactionParameter != null)
            //{
            //    transactionParameter = transactionParameter.Replace("%", "%25");
            //    transactionParameter = transactionParameter.Replace("+", "%2B");
            //    transactionParameter = transactionParameter.Replace("&", "%26");

            //    byte[] requestBytes = Encoding.UTF8.GetBytes("param=" + transactionParameter);
            //    request.ContentLength = requestBytes.Length;
            //    using (Stream stream = request.GetRequestStream())
            //    {
            //        stream.Write(requestBytes, 0, requestBytes.Length);
            //    }
            //}

            //获取请求调用结果
            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            {
                var responseContent = string.Empty;

                if (response.StatusCode == HttpStatusCode.OK)
                {
                    using (Stream responseStream = response.GetResponseStream())
                    {
                        StreamReader streamReader = new StreamReader(responseStream, Encoding.GetEncoding("utf-8"));
                        responseContent = streamReader.ReadToEnd();

                        if (string.IsNullOrEmpty(responseContent)) throw new ZeboneException(string.Format("在调用交易号为 {0} 的服务后，服务无数据返回。", transactionCode));
                    }

                    if (response.Cookies.Count > 0)
                    {
                        SessionId = response.Cookies[0].Value;
                    }

                    return responseContent;
                }
                else
                {
                    throw new ZeboneException(string.Format("在调用交易号为 {0} 的服务时，访问服务器出错，返回状态码为：{1}[{2}]。",
                        transactionCode,
                        response.StatusCode,
                        response.StatusDescription));
                }
            }
        }

        private T Deserialize<T>(string data)
        {
            var setting = new JsonSerializerSettings();
            setting.DateFormatString = "yyyyMMddHHmmss";
            setting.NullValueHandling = NullValueHandling.Ignore;
            setting.ContractResolver = new ZeboneContractResolver();
            setting.Converters.Add(new CustomDataTableConverter());
            setting.Converters.Add(new EnumConverter());
            setting.Converters.Add(new BooleanConverter());

            return JsonConvert.DeserializeObject<T>(data, setting);
        }

        private string Serialize(object data)
        {
            StringBuilder builder = new StringBuilder();
            Type type = data.GetType();
            foreach (var prop in type.GetProperties())
            {
                if (prop.CanRead)
                {
                    builder.Append(string.Format("{0}={1}", prop.Name, prop.GetValue(data, null))).Append("&");
                }
            }
            builder.Remove(builder.Length - 1, 1);
            return builder.ToString();

            //var setting = new JsonSerializerSettings();
            //setting.DateFormatString = "yyyyMMddHHmmss";
            //setting.ContractResolver = new ZeboneContractResolver();
            //setting.Converters.Add(new EnumConverter());
            //setting.Converters.Add(new BooleanConverter());

            //return JsonConvert.SerializeObject(data, setting);
        }

        private ServiceErrorEventArgs OnError(int statusCode, string errorMessage, string transactionCode, string request, string response, bool asyncMode)
        {
            var args = new ServiceErrorEventArgs(ErrorType.ServerError, statusCode.ToString(), errorMessage, transactionCode, request, response, null, asyncMode);
            if (!asyncMode && Error != null) Error(null, args);

            return args;
        }

        private ServiceErrorEventArgs OnException(Exception exception, string transactionCode, string request, string response, bool asyncMode)
        {
            var args = new ServiceErrorEventArgs(exception is WebException ? ErrorType.NetworkException : ErrorType.ClientException, string.Empty, exception.Message, transactionCode, request, response, exception, asyncMode);
            if (!asyncMode && Error != null) Error(null, args);

            return args;
        }
    }
}
