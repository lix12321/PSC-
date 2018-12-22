using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 用于标记指定的服务为登录类服务
    /// </summary>
    [AttributeUsage(AttributeTargets.Interface)]
    public sealed class LoginServiceAttribute : Attribute
    {
        public LoginServiceAttribute() { }
    }
}
