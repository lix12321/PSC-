using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 服务代理对象基类
    /// </summary>
    public abstract class JavaServiceProxy : Service, IServiceBase, IServiceConfig
    {
        public bool SuppressError { get; set; }

        public bool ThrowException { get; set; }

        public void Dispose()
        {

        }

        /// <summary>
        /// 获取或设置是否为登录服务
        /// </summary>
        internal bool IsLoginService { get; set; }

        protected ServiceResult Invoke(string sysName, string transactionCode, object parameter, bool fakeMode)
        {
            return CallService<object>(sysName, transactionCode, parameter, fakeMode).ToServiceResult();
        }

        protected ServiceResult<T> InvokeGeneric<T>(string sysName, string transactionCode, object parameter, bool fakeMode)
        {
            return CallService<T>(sysName, transactionCode, parameter, fakeMode).ToServiceResult();
        }

        protected void InvokeAsync(string sysName, string transactionCode, object parameter, bool fakeMode, Action onSuccess, Action<string> onError)
        {
            CallServiceAsync(sysName, transactionCode, parameter, fakeMode, onSuccess, onError);
        }

        protected void InvokeGenericAsync<T>(string sysName, string transactionCode, object parameter, bool fakeMode, Action<T> onSuccess, Action<string> onError)
        {
            CallServiceAsync<T>(sysName, transactionCode, parameter, fakeMode, onSuccess, onError);
        }

        /// <summary>
        /// 调用后台服务
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="transactionCode">交易号</param>
        /// <param name="transactionParameter">交易参数</param>
        /// <param name="fakeMode">是否使用虚拟的值填充返回值，该模式下，从本地JSON文件中读取数据，方便调试</param>
        /// <returns></returns>
        private JavaServiceResult<T> CallService<T>(string sysName, string transactionCode, object transactionParameter, bool fakeMode)
        {
            var ulr = this.ServiceInfo.Config.GetValue(sysName).ToObject<Dictionary<string, string>>()["ServiceUrl"];
            var serviceInvoker = new JavaServiceInvoker(new Uri(ulr), this.IsLoginService, false);
            serviceInvoker.Error += serviceInvoker_Error;
            serviceInvoker.ProgressChanged += serviceInvoker_ProgressChanged;
            return serviceInvoker.Invoke<T>(transactionCode, transactionParameter);
        }

        private void CallServiceAsync(string sysName, string transactionCode, object transactionParameter, bool fakeMode, Action onSuccess, Action<string> onError)
        {
            CallServiceAsync<object>(sysName, transactionCode, transactionParameter, fakeMode, obj =>
            {
                if (onSuccess != null) onSuccess();
            }, onError);
        }

        private void CallServiceAsync<T>(string sysName, string transactionCode, object transactionParameter, bool fakeMode, Action<T> onSuccess, Action<string> onError)
        {
            var backgroundWorker = new BackgroundWorker();

            backgroundWorker.DoWork += (sender, e) =>
            {
                var ulr = this.ServiceInfo.Config.GetValue(sysName).ToObject<Dictionary<string, string>>()["ServiceUrl"];
                var serviceInvoker = new JavaServiceInvoker(new Uri(ulr), this.IsLoginService, true);
                e.Result = serviceInvoker.Invoke<T>(transactionCode, transactionParameter);
            };

            backgroundWorker.RunWorkerCompleted += (sender, e) =>
            {
                var sr = (e.Result as JavaServiceResult<T>).ToServiceResult();
                if (sr.Success)
                {
                    if (onSuccess != null) onSuccess(sr.Value);
                }
                else
                {
                    if (onError != null) onError(sr.Message);  //外界传递错误处理回调，使用错误回调处理
                    else ServiceFactory.NotifyError(null, (e.Result as JavaServiceResult<T>).ErrorInfo);
                }
            };

            backgroundWorker.RunWorkerAsync();
        }

        private void serviceInvoker_Error(object sender, ServiceErrorEventArgs e)
        {
            if (!this.SuppressError)
            {
                ServiceFactory.NotifyError(sender, e);
            }
            else if (this.ThrowException)
            {
                throw e.Exception != null ? e.Exception : new ZeboneException(e.ErrorMessage);
            }
        }

        private void serviceInvoker_ProgressChanged(object sender, EventArgs e)
        {
            ServiceFactory.PerformInvokeProgressChanged(sender, e);
        }
    }
}
