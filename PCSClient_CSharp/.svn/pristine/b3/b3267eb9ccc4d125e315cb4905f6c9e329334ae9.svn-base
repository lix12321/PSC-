using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 表示Java服务调用结果
    /// </summary>
    /// <typeparam name="T"></typeparam>
    internal class JavaServiceResult<T>
    {
        /// <summary>
        /// 状态
        /// </summary>
        public int status { get; set; }

        /// <summary>
        /// 描述
        /// </summary>
        public string desc { get; set; }

        /// <summary>
        /// 错误信息
        /// </summary>
        public string errorMessage { get; set; }

        /// <summary>
        /// 数据
        /// </summary>
        public T data { get; set; }

        /// <summary>
        /// 获取或设置服务错误信息
        /// </summary>
        public ServiceErrorEventArgs ErrorInfo { get; set; }

        public int total { get; set; }

        /// <summary>
        /// 是否成功
        /// </summary>
        [JsonIgnore]
        public bool Success { get; set; }

        public ServiceResult<T> ToServiceResult()
        {
            return new ServiceResult<T>(this.Success, this.status.ToString(), this.desc, this.data, this.total);
        }
    }
}
