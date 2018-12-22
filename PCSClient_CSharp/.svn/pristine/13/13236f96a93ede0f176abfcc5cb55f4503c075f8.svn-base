using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 用于表示数据接口错误信息的事件数据
    /// </summary>
    public sealed class ServiceErrorEventArgs : EventArgs
    {
        public ServiceErrorEventArgs(ErrorType errorType, string errorCode, string errorMessage, string transactionCode, string request, string response, Exception exception, bool asyncMode)
        {
            this.AsyncMode = asyncMode;
            this.ErrorType = errorType;
            this.ErrorCode = errorCode;
            this.ErrorMessage = errorMessage;
            this.TransactionCode = transactionCode;
            this.Request = request;
            this.Response = response;
            this.Exception = exception;
        }

        /// <summary>
        /// 获取服务是否以异步方式进行调用
        /// </summary>
        public bool AsyncMode { get; private set; }

        /// <summary>
        /// 获取错误类型
        /// </summary>
        public ErrorType ErrorType { get; private set; }

        /// <summary>
        /// 获取错误代码
        /// </summary>
        public string ErrorCode { get; private set; }

        /// <summary>
        /// 获取异常信息
        /// </summary>
        public string ErrorMessage { get; private set; }

        /// <summary>
        /// 获取请求交易码
        /// </summary>
        public string TransactionCode { get; private set; }

        /// <summary>
        /// 获取用于表示请求数据的字符串
        /// </summary>
        public string Request { get; private set; }

        /// <summary>
        /// 获取用于表示响应数据的字符串
        /// </summary>
        public string Response { get; private set; }

        /// <summary>
        /// 当客户端发生异常时，该字段表示异常的详细信息
        /// </summary>
        public Exception Exception { get; private set; }
    }

    public enum ErrorType
    {
        /// <summary>
        /// 服务端错误
        /// </summary>
        ServerError,

        /// <summary>
        /// 客户端异常
        /// </summary>
        ClientException,

        /// <summary>
        /// 网络异常
        /// </summary>
        NetworkException
    }
}
