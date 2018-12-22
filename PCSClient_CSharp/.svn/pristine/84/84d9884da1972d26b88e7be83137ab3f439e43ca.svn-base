using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Zebone.Common;

namespace Zebone.Scripts
{
    /// <summary>
    /// 脚本执行上下文信息
    /// </summary>
    public class ScriptExecuteContext
    {
        private Dictionary<string, object> variables = new Dictionary<string, object>(StringComparer.OrdinalIgnoreCase);
        private List<ObjectReference> objectReferences = new List<ObjectReference>();

        public ScriptExecuteContext()
        {
            this.IgnoreVariablesMissingError = false;
        }

        /// <summary>
        /// 获取或设置是否忽略变量缺失错误，忽略该错误时，如果指定的变量不存在，则将变量内容原样返回
        /// </summary>
        public bool IgnoreVariablesMissingError { get; set; }

        /// <summary>
        /// 添加变量到执行上下文
        /// </summary>
        /// <param name="name">变量名称</param>
        /// <param name="value">变量值</param>
        public void AddVariable(string name, object value)
        {
            variables.AddOrSetValue(name, value);
        }

        /// <summary>
        /// 添加变量到执行上下文，如果传入的值为值类型或字符串类型，则以value作为参数名称；否则，以对象中的属性作为参数名称
        /// </summary>
        /// <param name="value"></param>
        public void AddVariables(object value)
        {
            var valueType = value.GetType();
            if (!valueType.IsClass || valueType == typeof(string))
            {
                AddVariable("value", value);
            }
            else
            {
                foreach (var p in valueType.GetProperties().Where(p => p.CanRead))
                {
                    AddVariable(p.Name, p.GetValue(value, null));
                }
            }
        }

        /// <summary>
        /// 添加变量到执行上下文
        /// </summary>
        /// <param name="variables"></param>
        public void AddVariables(IDictionary<string, string> variables)
        {
            foreach (var variable in variables)
            {
                AddVariable(variable.Key, variable.Value);
            }
        }

        /// <summary>
        /// 添加对象引用到执行上下文
        /// </summary>
        /// <param name="value">需要添加的对象</param>
        public void AddObject(object value)
        {
            AddObject(string.Empty, value);
        }

        /// <summary>
        /// 添加对象引用到执行上下文
        /// </summary>
        /// <param name="name">对象名称</param>
        /// <param name="value">需要添加的对象</param>
        public void AddObject(string name, object value)
        {
            objectReferences.Add(new ObjectReference(name ?? string.Empty, value));
        }

        /// <summary>
        /// 检查指定的变量是否存在
        /// </summary>
        /// <param name="name">变量名称</param>
        /// <returns></returns>
        public bool VariableExists(string name)
        {
            return variables.ContainsKey(name);
        }

        /// <summary>
        /// 获取指定变量的值
        /// </summary>
        /// <param name="name">变量名称</param>
        /// <returns></returns>
        public object GetVariable(string name)
        {
            object value;
            if (variables.TryGetValue(name, out value)) return value;

            //忽略错误时，返回变量名称
            if (this.IgnoreVariablesMissingError) return Token.BeginFlag + name + Token.EndFlag;

            throw new ZeboneException(string.Format("无法确定变量 {0} 的值。", name));
        }

        /// <summary>
        /// 指定名称的对象引用是否存在
        /// </summary>
        /// <param name="name">对象名称</param>
        /// <returns></returns>
        public bool ObjectExists(string name)
        {
            return objectReferences.Exists(o => string.Compare(o.Name, name, true) == 0);
        }

        /// <summary>
        /// 获取指定名称的对象列表
        /// </summary>
        /// <param name="name">对象名称</param>
        /// <returns></returns>
        public List<ObjectReference> GetObjects(string name)
        {
            return objectReferences.Where(o => string.Compare(o.Name, name, true) == 0).ToList();
        }
    }
}
