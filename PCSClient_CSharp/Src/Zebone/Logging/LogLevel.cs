using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Logging
{
    /// <summary>
    /// 日志级别
    /// </summary>
    public enum LogLevel
    {
        /// <summary>
        /// 表示调试的日志级别
        /// </summary>
        Debug,

        /// <summary>
        /// 表示消息的日志级别
        /// </summary>
        Info,

        /// <summary>
        /// 表示警告的日志级别
        /// </summary>
        Warn,

        /// <summary>
        /// 表示错误的日志级别
        /// </summary>
        Error,

        /// <summary>
        /// 表示严重错误的日志级别
        /// </summary>
        Fatal
    }
}
