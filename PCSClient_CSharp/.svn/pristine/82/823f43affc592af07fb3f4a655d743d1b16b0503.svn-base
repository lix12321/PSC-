using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Logging
{
    /// <summary>
    /// 日志基类
    /// </summary>
    public abstract class Logger : IDisposable
    {
        private const int LevelIndentLength = 4;  //每个层级的缩进数量

        protected Logger() { }

        /// <summary>
        /// 记录调试级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        public void Debug(string message)
        {
            Write(LogLevel.Debug, message, null);
        }

        /// <summary>
        /// 记录调试级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="args"></param>
        public void Debug(string format, params object[] args)
        {
            Debug(string.Format(format, args));
        }

        /// <summary>
        /// 记录信息级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        public void Info(string message)
        {
            Write(LogLevel.Info, message, null);
        }

        /// <summary>
        /// 记录信息级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="args"></param>
        public void Info(string format, params object[] args)
        {
            Info(string.Format(format, args));
        }

        /// <summary>
        /// 记录警告级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        public void Warn(string message)
        {
            Write(LogLevel.Warn, message, null);
        }

        /// <summary>
        /// 记录警告级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="args"></param>
        public void Warn(string format, params object[] args)
        {
            Warn(string.Format(format, args));
        }

        /// <summary>
        /// 记录错误级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        public void Error(string message)
        {
            Write(LogLevel.Error, message, null);
        }

        /// <summary>
        /// 记录错误级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        /// <param name="exception"></param>
        public void Error(string message, Exception exception)
        {
            Write(LogLevel.Error, message, exception);
        }

        /// <summary>
        /// 记录错误级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="args"></param>
        public void Error(string format, params object[] args)
        {
            Error(string.Format(format, args));
        }

        /// <summary>
        /// 记录错误级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="exception"></param>
        /// <param name="args"></param>
        public void Error(string format, Exception exception, params object[] args)
        {
            Error(string.Format(format, args), exception);
        }

        /// <summary>
        /// 记录严重错误级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        public void Fatal(string message)
        {
            Write(LogLevel.Fatal, message, null);
        }

        /// <summary>
        /// 记录严重错误级别的日志信息
        /// </summary>
        /// <param name="message"></param>
        /// <param name="exception"></param>
        public void Fatal(string message, Exception exception)
        {
            Write(LogLevel.Fatal, message, exception);
        }

        /// <summary>
        /// 记录严重错误级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="args"></param>
        public void Fatal(string format, params object[] args)
        {
            Fatal(string.Format(format, args));
        }

        /// <summary>
        /// 记录严重错误级别的日志信息
        /// </summary>
        /// <param name="format"></param>
        /// <param name="exception"></param>
        /// <param name="args"></param>
        public void Fatal(string format, Exception exception, params object[] args)
        {
            Fatal(string.Format(format, args), exception);
        }

        public virtual void Dispose()
        {

        }

        /// <summary>
        /// 将异常信息转储为字符串
        /// </summary>
        /// <param name="exception"></param>
        /// <returns></returns>
        protected string DumpException(Exception exception)
        {
            if (exception == null) return string.Empty;

            StringBuilder sb = new StringBuilder();
            DumpExceptioin(exception, sb, 0);
            return sb.ToString();
        }

        private void Write(LogLevel level, string message, Exception exception)
        {
            WriteCore(level, message, exception);
        }

        /// <summary>
        /// 记录日志信息
        /// </summary>
        /// <param name="level">日志级别</param>
        /// <param name="message">日志信息</param>
        /// <param name="exception">与日志相关联的异常信息</param>
        protected abstract void WriteCore(LogLevel level, string message, Exception exception);

        private void DumpExceptioin(Exception exception, StringBuilder sb, int level)
        {
            var items = exception.ToString().Split(new[] { Environment.NewLine, "\r", "\n" }, StringSplitOptions.None);
            foreach (var s in items)
            {
                sb.Append(new string(' ', level * LevelIndentLength));
                sb.AppendLine(s);
            }

            if (exception.InnerException != null)
            {
                sb.AppendLine();

                sb.Append(new string(' ', level * LevelIndentLength));
                sb.AppendLine("InnerException：");

                DumpExceptioin(exception.InnerException, sb, level + 1);
            }
        }
    }
}
