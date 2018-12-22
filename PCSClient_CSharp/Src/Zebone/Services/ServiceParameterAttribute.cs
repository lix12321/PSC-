using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 表示其修饰的属性为外部传入的参数
    /// </summary>
    [AttributeUsage(AttributeTargets.Property)]
    public class ServiceParameterAttribute : Attribute
    {
    }
}
