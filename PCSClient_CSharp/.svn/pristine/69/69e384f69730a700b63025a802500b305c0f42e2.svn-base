using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Scripts
{
    /// <summary>
    /// 对于指定的字符串进行符号化处理
    /// </summary>
    public class Tokenizer
    {
        private string content;
        private List<Token> tokens;

        public Tokenizer(string content)
        {
            this.content = content;
            this.tokens = new List<Token>();
        }

        public List<Token> Execute()
        {
            if (string.IsNullOrEmpty(content)) return new List<Token>();

            var stack = new Stack<char>();  //用于进行符号开始与结束标记的匹配处理
            var beginIndex = -1;
            var endIndex = -1;
            var lastIndex = 0;

            var index = 0;
            while (index < content.Length)
            {
                var c = content[index++];
                switch (c)
                {
                    case Token.BeginFlag:
                        stack.Push(c);
                        if (stack.Count == 1)
                        {
                            if (index < content.Length && content[index] == Token.BeginFlag)  //连续两个开始标记，认为是对开始标记字符的转义
                            {
                                index++;
                                stack.Pop();
                            }
                            else
                            {
                                //符号开始
                                beginIndex = index - 1;  //记录符号开始的位置
                                if (beginIndex > 0)
                                {
                                    //保存当前符号前的字符串为文本符号
                                    tokens.Add(new Token(content.Substring(lastIndex, beginIndex - lastIndex), TokenType.Text));
                                }
                            }
                        }

                        break;

                    case Token.EndFlag:
                        if (stack.Count > 0)
                        {
                            stack.Pop();
                            if (stack.Count == 0)
                            {
                                endIndex = index - 1;      //符号结束位置
                                lastIndex = endIndex + 1;

                                var code = content.Substring(beginIndex + 1, endIndex - beginIndex - 1).Trim();
                                tokens.Add(new Token(code, code.IndexOf("(") == -1 ? TokenType.Variable : TokenType.Function));
                            }
                        }

                        break;

                    default: break;
                }
            }

            if (lastIndex < content.Length)
            {
                tokens.Add(new Token(content.Substring(lastIndex, content.Length - lastIndex), TokenType.Text));
            }

            return tokens;
        }
    }
}
