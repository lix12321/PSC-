using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 表示服务调用结果
    /// </summary>
    [Serializable]
    public class ServiceResult
    {
        /// <summary>
        /// 构造服务调用结果
        /// </summary>
        public ServiceResult() : this(true, string.Empty, string.Empty) { }

        /// <summary>
        /// 构造服务调用结果
        /// </summary>
        /// <param name="success">服务调用是否成功</param>
        /// <param name="statusCode">服务返回的状态码</param>
        /// <param name="message">服务返回的信息</param>
        public ServiceResult(bool success, string statusCode, string message)
        {
            this.Success = success;
            this.StatusCode = statusCode;
            this.Message = message;
        }

        /// <summary>
        /// 用于表示服务调用是否成功
        /// </summary>
        public bool Success { get; private set; }

        /// <summary>
        /// 服务返回的状态码
        /// </summary>
        public string StatusCode { get; private set; }

        /// <summary>
        /// 服务返回的消息，例如：异常信息，对于状态码的说明信息等
        /// </summary>
        public string Message { get; private set; }
    }
}
