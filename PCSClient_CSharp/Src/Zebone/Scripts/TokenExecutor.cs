using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace Zebone.Scripts
{
    /// <summary>
    /// 用于进行符号对象的执行
    /// </summary>
    internal class TokenExecutor
    {
        private static readonly object MissingValue = new object();  //在无法处理指定符号时，返回的对象，后续判断返回值是否为该对象来判断是否正确处理了符号

        private ScriptExecuteContext context;
        private Token token;

        internal TokenExecutor(ScriptExecuteContext context, Token token)
        {
            this.context = context;
            this.token = token;
        }

        /// <summary>
        /// 执行符号
        /// </summary>
        /// <returns></returns>
        internal object Execute()
        {
            switch (token.TokenType)
            {
                case TokenType.Text: return token.Content;
                case TokenType.Variable: return ExecuteVariable();
                case TokenType.Function: return ExecuteFunction();
                default: throw new ZeboneException("无法处理的符号类型：" + token.TokenType.ToString());
            }
        }

        /// <summary>
        /// 执行变量符号
        /// </summary>
        /// <returns></returns>
        private object ExecuteVariable()
        {
            //变量中不存在，尝试按照获取对象属性的语法进行处理
            if (!context.VariableExists(token.Content) && token.Content.IndexOf('.') != -1)
            {
                var result = GetPropertyValue(token.Content);
                if (result != MissingValue) return result;
            }

            return context.GetVariable(token.Content);
        }

        /// <summary>
        /// 执行函数符号
        /// </summary>
        /// <returns></returns>
        private object ExecuteFunction()
        {
            //获取函数所在的对象名称、方法名称、参数列表
            string objectName = null;
            string methodName = null;
            List<string> parameters = null;
            SplitFunction(token.Content, out objectName, out methodName, out parameters);

            //获取函数调用时，需要进行查找的对象列表
            var searchingObjects = GetObjects(objectName);

            //从对象列表中找到符合调用参数的方法，只支持不同参数个数的重载
            object invokingObject = null;
            MethodInfo invokingMethod = null;
            GetMethodInfo(searchingObjects, methodName, parameters.Count, out invokingObject, out invokingMethod);

            //解析调用参数
            var invokingParameters = new List<object>();
            foreach (var methodParameter in invokingMethod.GetParameters())
            {
                //处理可变方法列表
                if (methodParameter.ParameterType.IsArray && methodParameter.GetCustomAttributes(typeof(ParamArrayAttribute), false) != null)
                {
                    var array = Activator.CreateInstance(methodParameter.ParameterType, parameters.Count) as Array;
                    for (var i = 0; i < parameters.Count; i++)
                    {
                        array.SetValue(EvalMethodParameter(parameters[i], methodParameter.ParameterType.GetElementType()), i);
                    }

                    invokingParameters.Add(array);
                }
                else
                {
                    invokingParameters.Add(EvalMethodParameter(parameters[0], methodParameter.ParameterType));
                    parameters.RemoveAt(0);
                }
            }

            return invokingMethod.Invoke(invokingMethod.IsStatic ? null : invokingObject, invokingParameters.ToArray());
        }

        /// <summary>
        /// 从指定的属性调用字符串中获取对象值
        /// </summary>
        /// <param name="content"></param>
        /// <returns></returns>
        private object GetPropertyValue(string content)
        {
            var segments = content.Split(new[] { '.' }, StringSplitOptions.RemoveEmptyEntries);

            //处理第一个属性     
            object result = MissingValue;
            foreach (var obj in context.GetObjects(segments[0]))  //segments数组中的第一个为对象名称，后续为属性名称
            {
                result = GetPropertyValue(obj.Value, segments[1]);
                if (result != MissingValue) break;
            }

            //处理后续属性
            for (var i = 2; i < segments.Length; i++)
            {
                if (result == MissingValue) break;  //前一步符号未能正确处理，整个处理过程结束
                result = GetPropertyValue(result, segments[i]);
            }

            return result;
        }

        /// <summary>
        /// 从指定的对象中获取指定属性的值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="propertyName"></param>
        /// <returns></returns>
        private object GetPropertyValue(object obj, string propertyName)
        {
            var isStatic = obj is Type;  //表示需要处理静态属性
            var objectType = obj is Type ? (Type)obj : obj.GetType();
            var property = objectType.GetProperty(propertyName, BindingFlags.Public | BindingFlags.Instance | BindingFlags.Static | BindingFlags.IgnoreCase);

            if (property != null) return property.GetValue(isStatic ? null : obj, null);

            return MissingValue;
        }

        /// <summary>
        /// 从函数中分离对象名称、方法名称、方法参数列表
        /// </summary>
        private void SplitFunction(string content, out string objectName, out string methodName, out List<string> parameters)
        {
            //分离函数体与函数参数
            var index = content.IndexOf('(');
            var body = content.Substring(0, index);
            parameters = SplitFunctionParameter(content.Substring(index + 1, content.Length - index - 2));

            //分离函数体中的对象名与方法名称
            objectName = string.Empty;
            methodName = body.Trim();

            index = body.LastIndexOf('.');
            if (index != -1)
            {
                objectName = body.Substring(0, index).Trim();
                methodName = body.Substring(index + 1).Trim();
            }
        }

        /// <summary>
        /// 将表示函数参数部分的字符串进行分隔
        /// </summary>
        /// <param name="parameter"></param>
        /// <returns></returns>
        private List<string> SplitFunctionParameter(string parameter)
        {
            var items = new List<string>();
            var stack = new Stack<char>();   //用于进行括号匹配
            var isStringPart = false;        //当前解析的部分是否为字符串
            var stringStartChar = '"';       //当前字符串开始的字符
            var content = string.Empty;

            foreach (var c in parameter)
            {
                switch (c)
                {
                    //参数分隔符
                    case ',':
                        if (stack.Count > 0 || isStringPart) //表示内部嵌套函数中的参数或字符串中的逗号
                        {
                            content = content + c.ToString();
                        }
                        else
                        {
                            items.Add(content.Trim());
                            content = string.Empty;
                        }
                        break;

                    //字符串开始或结束标记
                    case '\'':
                    case '\"':
                        content = content + c.ToString();
                        if (isStringPart)
                        {
                            if (c == stringStartChar)
                            {
                                isStringPart = false;
                            }
                        }
                        else
                        {
                            isStringPart = true;
                            stringStartChar = c;
                        }
                        break;

                    case '(':
                        stack.Push(c);
                        content = content + c.ToString();
                        break;

                    case ')':
                        stack.Pop();
                        content = content + c.ToString();
                        break;

                    default:
                        content = content + c.ToString();
                        break;
                }
            }

            if (isStringPart) throw new ZeboneException("字符串未正确闭合");
            if (!string.IsNullOrEmpty(content)) items.Add(content.Trim());

            return items;
        }

        /// <summary>
        /// 通过处理表示对象引用的字符串获取对象列表
        /// </summary>
        /// <param name="objectName"></param>
        /// <returns></returns>
        private List<object> GetObjects(string objectName)
        {
            var objects = new List<object>();
            if (objectName.IndexOf('.') == -1)  //直接从对象引用中获取
            {
                objects.AddRange(context.GetObjects(objectName).Select(o => o.Value));
            }
            else
            {
                objects.Add(GetPropertyValue(objectName));  //通过属性调用获取
            }

            return objects;
        }

        /// <summary>
        /// 在指定的对象列表中，查找具有具有指定参数个数的方法
        /// </summary>
        private void GetMethodInfo(List<object> searchingObjects, string methodName, int parameterCount, out object invokingObject, out MethodInfo invokingMethod)
        {
            invokingObject = null;
            invokingMethod = null;

            foreach (var obj in searchingObjects)
            {
                var objectType = obj is Type ? obj as Type : obj.GetType();
                var methods = objectType.GetMethods().Where(m => MethodFilter(m, methodName, parameterCount)).ToList();
                if (methods.Count == 1)
                {
                    if (invokingMethod != null) throw new ZeboneException(string.Format("在当前执行环境中找个多个名称为 “{0}”，且具有 {1} 个参数的方法。", methodName, parameterCount));

                    invokingMethod = methods[0];
                    invokingObject = obj;
                }
                else if (methods.Count > 1)
                {
                    throw new ZeboneException(string.Format("在当前执行环境中找个多个名称为 “{0}”，且具有 {1} 个参数的方法。", methodName, parameterCount));
                }
            }

            if (invokingMethod == null) throw new ZeboneException(string.Format("未在执行环境中找到方法 “{0}” 的定义。", methodName));
        }

        /// <summary>
        /// 用于匹配指定的方法是否符合要求
        /// </summary>
        /// <returns></returns>
        private bool MethodFilter(MethodInfo methodInfo, string methodName, int parameterCount)
        {
            //判断方法名称是否匹配
            if (string.Compare(methodInfo.Name, methodName, true) != 0) return false;

            var parameters = methodInfo.GetParameters();
            if (parameters.Length == parameterCount) return true;  //参数个数匹配

            //检查方法参数是否为可变参数(params T[]....)
            if (parameters.Length == 1)
            {
                var p = parameters[0];
                if (p.ParameterType.IsArray && p.GetCustomAttributes(typeof(ParamArrayAttribute), false) != null)
                {
                    return true;
                }
            }

            return false;
        }

        /// <summary>
        /// 解析表示参数的字符串，并按照需要的类型返回
        /// </summary>
        /// <param name="parameter"></param>
        /// <param name="resultType"></param>
        /// <returns></returns>
        private object EvalMethodParameter(string parameter, Type resultType)
        {
            //处理字面量
            if (string.Compare(parameter, "null", true) == 0) return null;
            if (string.Compare(parameter, "true", true) == 0 && resultType == typeof(bool)) return true;
            if (string.Compare(parameter, "false", true) == 0 && resultType == typeof(bool)) return false;

            //处理字符串字面量，如果引号内的符号计算结果为null，则返回空字符串
            if (parameter.Length > 1 && (parameter.StartsWith("'") || parameter.StartsWith("\"")) && parameter[0] == parameter[parameter.Length - 1])
            {
                if (resultType != typeof(string)) throw new ZeboneException(string.Format("无法将字符串 {0} 转换成类型 {1}", parameter, resultType.FullName));
                var value = ScriptUtils.Execute(context, parameter.Substring(1, parameter.Length - 2));
                return ScriptUtils.ToString(value);
            }

            //直接计算参数值，并转换成需要的类型
            var result = ScriptUtils.Execute(context, parameter);
            if (result == null) return null;
            if (resultType.IsAssignableFrom(result.GetType())) return result;

            return Convert.ChangeType(result, resultType);
        }
    }
}
