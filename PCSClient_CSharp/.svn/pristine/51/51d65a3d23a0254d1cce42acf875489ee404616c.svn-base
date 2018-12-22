using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Scripts
{
    /// <summary>
    /// 用于表示脚本中的一个符号信息
    /// </summary>
    public class Token
    {
        /// <summary>
        /// 符号开始标记
        /// </summary>
        public const char BeginFlag = '{';

        /// <summary>
        /// 符号结束标记
        /// </summary>
        public const char EndFlag = '}';

        /// <summary>
        /// 将指定的字符串处理为符号
        /// </summary>
        /// <param name="s"></param>
        /// <returns></returns>
        public static string MakeToken(string s)
        {
            return BeginFlag + s + EndFlag;
        }

        public Token(string content, TokenType tokenType)
        {
            this.Content = content;
            this.TokenType = tokenType;
        }

        /// <summary>
        /// 符号内容
        /// </summary>
        public string Content { get; private set; }

        /// <summary>
        /// 符号类型
        /// </summary>
        public TokenType TokenType { get; private set; }
    }
}
