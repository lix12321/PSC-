using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Zebone.Logging
{
    /// <summary>
    /// 以文件的形式进行日志内容的保存
    /// </summary>
    public class FileLogger : Logger
    {
        private string fileName;
        private bool appendMode;

        /// <summary>
        /// 
        /// </summary>
        /// <param name="fileName">用于保存日志内容的文件名</param>
        /// <param name="appendMode">当指定的文件已经存在时，是否将日志附加到该文件的后面，该属性为false时，将覆盖文件的内容</param>
        public FileLogger(string fileName, bool appendMode)
            : base()
        {
            this.fileName = fileName;
            this.appendMode = appendMode;
        }

        protected override void WriteCore(LogLevel level, string message, Exception exception)
        {
            using (var stream = new FileStream(fileName, appendMode ? FileMode.Append : FileMode.Create))
            {
                Write(stream, "**************************************************************************************************************************************************************************************************************************");
                Write(stream, "日志级别：" + level.ToString());
                Write(stream, "日志时间：" + DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"));
                Write(stream, "日志内容：" + message);

                if (exception != null)
                {
                    Write(stream, "异常：");
                    Write(stream, DumpException(exception));
                }
            }
        }

        private void Write(Stream stream, string content)
        {
            var buffer = Encoding.UTF8.GetBytes(content + Environment.NewLine);
            stream.Write(buffer, 0, buffer.Length);
        }
    }
}
