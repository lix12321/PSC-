using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Scripts
{
    /// <summary>
    /// 脚本处理单元
    /// </summary>
    public static class ScriptUtils
    {
        /// <summary>
        /// 对于指定的脚本进行符号化处理
        /// </summary>
        /// <param name="script">需要进行符号化的脚本</param>
        /// <returns></returns>
        public static List<Token> Tokenizer(string script)
        {
            return new Tokenizer(script).Execute();
        }

        /// <summary>
        /// 执行指定脚本
        /// </summary>
        /// <param name="context">脚本执行上下文</param>
        /// <param name="script">需要执行的脚本</param>
        /// <returns></returns>
        public static object Execute(ScriptExecuteContext context, string script)
        {
            try
            {
                var tokens = Tokenizer(script);
                if (tokens.Count == 1) return Execute(context, tokens[0]);

                var sb = new StringBuilder();
                foreach (var token in tokens)
                {
                    var result = Execute(context, token);
                    sb.Append(ToString(result));
                }

                return sb.ToString();
            }
            catch (Exception exception)
            {
                throw new ZeboneException(string.Format("在执行脚本 {0} 时发生错误：{1}", script, exception.Message), exception);
            }
        }

        /// <summary>
        /// 执行指定符号
        /// </summary>
        /// <param name="context">脚本执行上下文</param>
        /// <param name="token">需要执行的符号</param>
        /// <returns></returns>
        public static object Execute(ScriptExecuteContext context, Token token)
        {
            return new TokenExecutor(context, token).Execute();
        }

        /// <summary>
        /// 处理脚本中的符号，返回处理后的脚本
        /// </summary>
        /// <param name="context">脚本执行上下文</param>
        /// <param name="script">需要进行处理的脚本</param>
        /// <returns></returns>
        public static string ReplaceTokens(ScriptExecuteContext context, string script)
        {
            return ToString(Execute(context, script));
        }

        internal static string ToString(object value)
        {
            if (value == null) return string.Empty;
            if (value.GetType() == typeof(DateTime)) return ((DateTime)value).ToString("yyyy-MM-dd HH:mm:ss");

            return value.ToString();
        }
    }
}
