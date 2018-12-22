using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 表示服务调用结果
    /// </summary>
    /// <typeparam name="T"></typeparam>
    [Serializable]
    public class ServiceResult<T> : ServiceResult
    {
        public static implicit operator ServiceResult<T>(T value)
        {
            return new ServiceResult<T>(value);
        }

        public static implicit operator T(ServiceResult<T> serviceResult)
        {
            return serviceResult.Value;
        }

        /// <summary>
        /// 构造服务调用结果
        /// </summary>
        /// <param name="value">服务返回的数据</param>
        public ServiceResult(T value) : this(true, string.Empty, string.Empty, value, 0) { }

        /// <summary>
        /// 构造服务调用结果
        /// </summary>
        /// <param name="success">服务调用是否成功</param>
        /// <param name="statusCode">服务返回的状态码</param>
        /// <param name="message">服务返回的信息</param>
        public ServiceResult(bool success, string statusCode, string message)
            : base(success, statusCode, message)
        {
        }

        /// <summary>
        /// 构造服务调用结果
        /// </summary>
        /// <param name="success">服务调用是否成功</param>
        /// <param name="statusCode">服务返回的状态码</param>
        /// <param name="message">服务返回的信息</param>
        /// <param name="value">服务返回的数据</param>
        /// <param name="total">分页时，总的数据笔数</param>
        public ServiceResult(bool success, string statusCode, string message, T value, int total)
            : base(success, statusCode, message)
        {
            this.Value = value;
            this.Total = total;
        }

        /// <summary>
        /// 获取服务调用成功后，所返回的数据
        /// </summary>
        public T Value { get; private set; }

        /// <summary>
        /// 获取在分页模式下，服务调用成功后返回总的数据笔数
        /// </summary>
        public int Total { get; private set; }
    }
}
