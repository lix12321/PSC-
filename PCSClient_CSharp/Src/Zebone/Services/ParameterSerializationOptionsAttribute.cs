using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 用于指定对服务方法中参数进行序列化时的配置信息
    /// </summary>
    public class ParameterSerializationOptionsAttribute : Attribute
    {
        public ParameterSerializationOptionsAttribute() { }

        public ParameterSerializationOptionsAttribute(ParameterNameVisibility parameterNameVisibility)
        {
            this.ParameterNameVisibility = parameterNameVisibility;
        }

        /// <summary>
        /// 用于指定参数名称的可见性
        /// </summary>
        public ParameterNameVisibility ParameterNameVisibility { get; set; }
    }

    /// <summary>
    /// 表示在对参数进行序列化时，参数名是否包含在最终的序列化结果中
    /// </summary>
    public enum ParameterNameVisibility
    {
        /// <summary>
        /// 自动判断，但只有一个参数，且参数为引用类型（不包括string类型）时，参数名称不包含在序列化结果中；
        /// 否则参数名称包含在序列化结果中
        /// </summary>
        Default,

        /// <summary>
        /// 参数名称包含在最终的序列化结果中
        /// </summary>
        Visible,

        /// <summary>
        /// 参数名称不包含在最终的序列化结果中
        /// </summary>
        Hidden
    }
}
