using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

using Zebone.Common;
using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 表示服务方法信息
    /// </summary>
    internal class ServiceMethodInfo
    {
        private Type serviceType;
        private MethodInfo serviceMethod;

        internal ServiceMethodInfo(Type serviceType, MethodInfo serviceMethod)
        {
            this.serviceType = serviceType;
            this.serviceMethod = serviceMethod;
            this.Parameters = new List<ParameterInfo>();
        }

        /// <summary>
        /// 读取服务方法信息
        /// </summary>
        internal void Read()
        {
            ReadTransactionCode();
            ReadParameters();
            ReadParameterSerializationOptions();

            if (this.IsAsyncMode) CheckAsyncMethodDefinition();
            else CheckMethodDefinition();
        }

        /// <summary>
        /// 获取服务方法信息
        /// </summary>
        internal MethodInfo ServiceMethod
        {
            get { return serviceMethod; }
        }

        /// <summary>
        /// 获取方法外部系统名称
        /// </summary>
        internal string SysName { get; private set; }
        /// <summary>
        /// 获取方法交易码
        /// </summary>
        internal string TransactionCode { get; private set; }

        /// <summary>
        /// 获取方法中的参数在序列化为JSON时，是否需要将参数名称包含在序列化结果中
        /// </summary>
        internal ParameterNameVisibility ParameterNameVisibility { get; private set; }

        /// <summary>
        /// 获取服务方法的参数列表
        /// </summary>
        internal List<ParameterInfo> Parameters { get; private set; }

        /// <summary>
        /// 获取是否使用异步模式进行服务方法调用
        /// </summary>
        internal bool IsAsyncMode { get; private set; }

        /// <summary>
        /// 获取在服务调用成功时的回调，异步模式下有效
        /// </summary>
        internal ParameterInfo OnSuccessCallback { get; private set; }

        /// <summary>
        /// 获取在服务调用失败时的回调，异步模式下有效
        /// </summary>
        internal ParameterInfo OnErrorCallback { get; private set; }

        /// <summary>
        /// 读取交易号信息
        /// </summary>
        /// <returns></returns>
        private void ReadTransactionCode()
        {
            //读取方法上的交易号属性，进行代理对象的生成
            var transactionAttribute = ReflectionUtils.GetAttribute<TransactionCodeAttribute>(serviceMethod, false);
            if (transactionAttribute == null)
            {
                throw new ZeboneException(string.Format("服务 {0} 中的方法 {1} 需要使用 {2} 属性指定其交易号。",
                    serviceType.FullName,
                    serviceMethod.Name,
                    typeof(TransactionCodeAttribute).FullName));
            }

            this.TransactionCode = transactionAttribute.TransactionCode;
            this.SysName = transactionAttribute.SysName;
        }

        /// <summary>
        /// 读取服务方法中的参数，在进行序列化时的配置信息
        /// </summary>
        /// <param name="serviceMethod"></param>
        /// <returns></returns>
        private void ReadParameterSerializationOptions()
        {
            //读取序列化配置信息
            var options = ReflectionUtils.GetAttribute<ParameterSerializationOptionsAttribute>(serviceMethod, false);
            if (options == null) options = new ParameterSerializationOptionsAttribute();

            //读取方法中的参数信息 
            if (this.Parameters.Count == 1)
            {
                var parameterType = this.Parameters[0].ParameterType;

                //确定序列化结果中是否包含参数名称
                if (options.ParameterNameVisibility == ParameterNameVisibility.Default)
                {
                    if (parameterType.IsClass && parameterType != typeof(string))  //引用类型默认不包含参数名称
                    {
                        options.ParameterNameVisibility = ParameterNameVisibility.Hidden;
                    }
                    else
                    {
                        options.ParameterNameVisibility = ParameterNameVisibility.Visible;
                    }
                }
            }
            else
            {
                //有多个参数时，参数名称始终要包含在序列化结果中
                options.ParameterNameVisibility = ParameterNameVisibility.Visible;
            }

            this.ParameterNameVisibility = options.ParameterNameVisibility;
        }

        /// <summary>
        /// 读取方法参数
        /// </summary>
        private void ReadParameters()
        {
            this.Parameters.AddRange(serviceMethod.GetParameters());

            //判断参数列表中的后一个或两个参数，是否为回调函数，如果为回调函数，则认为方法需要进行异步调用
            //先判断最后一个参数，是否为回调函数，如果为回调函数，认为其为服务调用成功时的回调
            if (this.Parameters.Count > 0 && IsCallbackParameter(this.Parameters.Last()))
            {
                this.IsAsyncMode = true;
                this.OnSuccessCallback = this.Parameters.Last();
                this.Parameters.RemoveAt(this.Parameters.Count - 1);  //将表示回调函数的参数，从参数列表中移除

                //移除最后一个参数后，再判断最后一个参数是否为回调函数，
                //如果为回调函数，则表示参数列表中，最后两个参数均为回调函数，此时，认为第一个回调为函数执行成功时的回调函数，第二个回调为函数执行失败时的回调函数
                if (this.Parameters.Count > 0 && IsCallbackParameter(this.Parameters.Last()))
                {
                    this.OnErrorCallback = this.OnSuccessCallback;
                    this.OnSuccessCallback = this.Parameters.Last();
                    this.Parameters.RemoveAt(this.Parameters.Count - 1);  //将表示回调函数的参数，从参数列表中移除
                }
            }
        }

        /// <summary>
        /// 检查同步方法定义是否符合要求
        /// </summary>
        private void CheckMethodDefinition()
        {
            //判断方法的返回值是否符合要求
            if (!typeof(ServiceResult).IsAssignableFrom(serviceMethod.ReturnType))
            {
                throw new ZeboneException(string.Format("服务 {0} 中的方法 {1} 的返回值为 {2}，无法生成代理对象。只有返回值类型为 {3} 及其子类的方法才可以生成代理对象。",
                    serviceType.FullName,
                    serviceMethod.Name,
                    serviceMethod.ReturnType.FullName,
                    typeof(ServiceResult).FullName));
            }
        }

        /// <summary>
        /// 检查异步方法定义是否符合要求
        /// </summary>
        private void CheckAsyncMethodDefinition()
        {
            //异步方法的方法名称，强制以 Async 结尾
            if (!serviceMethod.Name.EndsWith("Async"))
            {
                throw new ZeboneException(string.Format("服务 {0} 中的方法 {1} 为异步方法，对于异步方法，方法名称需要以 Async 结尾。",
                    serviceType.FullName,
                    serviceMethod.Name));
            }

            //异步方法的返回值，必须为void
            if (serviceMethod.ReturnType != typeof(void))
            {
                throw new ZeboneException(string.Format("服务 {0} 中的方法 {1} 为异步方法，其返回值必须为 void。",
                    serviceType.FullName,
                    serviceMethod.Name));
            }

            //用于处理错误信息的回调，必须为Action<string>类型
            if (this.OnErrorCallback != null && this.OnErrorCallback.ParameterType != typeof(Action<string>))
            {
                throw new ZeboneException(string.Format("服务 {0} 中的方法 {1} 为异步方法，用于处理错误状态回调的函数，必须为 Action<string> 类型。",
                    serviceType.FullName,
                    serviceMethod.Name));
            }
        }

        /// <summary>
        /// 检查指定的类型是否为回调函数
        /// </summary>
        /// <param name="parameter"></param>
        private bool IsCallbackParameter(ParameterInfo parameter)
        {
            return parameter.ParameterType == typeof(Action) ||
                   (parameter.ParameterType.IsGenericType && parameter.ParameterType.GetGenericTypeDefinition() == typeof(Action<>));
        }
    }
}
