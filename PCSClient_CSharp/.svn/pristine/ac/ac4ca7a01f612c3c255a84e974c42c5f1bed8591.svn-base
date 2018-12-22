using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Reflection.Emit;
using System.Text;

using Zebone.Common;
using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 用于进行服务代理对象的构造，通过该对象完成对Java服务的调用
    /// </summary>
    internal class JavaServiceProxyBuilder
    {
        private static AssemblyBuilder assemblyBuilder;
        private static ModuleBuilder moduleBuilder;

        private Type serviceType;
        private TypeBuilder typeBuilder;

        public JavaServiceProxyBuilder(Type serviceType)
        {
            this.serviceType = serviceType;
        }

        public Type Build()
        {
            if (!typeof(IServiceBase).IsAssignableFrom(serviceType))
            {
                throw new ZeboneException(string.Format("类型 {0} 不是从 {1} 派生，无法进行代理对象的生成。",
                    serviceType.FullName,
                    typeof(IServiceBase).FullName));
            }

            if (!serviceType.IsPublic)
            {
                throw new ZeboneException(string.Format("服务接口 {0} 必须定义为 public 类型", serviceType.FullName));
            }

            if (serviceType.Name[0] != 'I' || !serviceType.Name.EndsWith("Service"))
            {
                throw new ZeboneException(string.Format("服务接口 {0} 不符合命名规范，服务接口应该以 I 开头，并以 Service 结尾。", serviceType.FullName));
            }

            if (assemblyBuilder == null)
            {
                var assemblyName = new AssemblyName("JavaService" + Guid.NewGuid().ToString("N"));
                assemblyBuilder = AppDomain.CurrentDomain.DefineDynamicAssembly(assemblyName, AssemblyBuilderAccess.RunAndSave);
                moduleBuilder = assemblyBuilder.DefineDynamicModule("JavaServiceBuilder", "JavaServiceProxy.dll");
            }

            //创建代理类型
            typeBuilder = moduleBuilder.DefineType(
                assemblyBuilder.GetName().Name + "." + serviceType.Name + "Proxy" + Guid.NewGuid().ToString("N"),
                TypeAttributes.Public,
                typeof(JavaServiceProxy),
                new[] { serviceType });

            BuildServiceMethods();  //生成服务中的方法

            var proxyType = typeBuilder.CreateType();

            //assemblyBuilder.Save("JavaServiceProxy.dll");

            return proxyType;
        }

        private void BuildServiceMethods()
        {
            foreach (var serviceMethod in serviceType.GetMethods())
            {
                var serviceMethodInfo = new ServiceMethodInfo(serviceType, serviceMethod);
                serviceMethodInfo.Read();

                var methodBuilder = DefineMethod(serviceMethod);
                var ilGenerator = methodBuilder.GetILGenerator();
                BuildServiceMethodBody(ilGenerator, serviceMethodInfo);
            }
        }

        private void BuildServiceMethodBody(ILGenerator ilGenerator, ServiceMethodInfo serviceMethodInfo)
        {
            //确定需要调用的方法
            var invokeMethod = serviceMethodInfo.IsAsyncMode ?
                GetServiceInvokeAsyncMethod(serviceMethodInfo) :
                GetServiceInvokeMethod(serviceMethodInfo);

            //确定方法参数
            var parameter = serviceMethodInfo.ParameterNameVisibility == ParameterNameVisibility.Hidden ?
                GetServiceParameterByObject(ilGenerator, serviceMethodInfo) :
                GetServiceParameterByDictionary(ilGenerator, serviceMethodInfo);

            //进行方法调用
            ilGenerator.Emit(OpCodes.Ldarg_0);
            ilGenerator.Emit(OpCodes.Ldstr, serviceMethodInfo.SysName);
            ilGenerator.Emit(OpCodes.Ldstr, serviceMethodInfo.TransactionCode);
            ilGenerator.Emit(OpCodes.Ldloc, parameter);
            ilGenerator.Emit(IsFakeMode(serviceMethodInfo.ServiceMethod) ? OpCodes.Ldc_I4_1 : OpCodes.Ldc_I4_0);

            if (serviceMethodInfo.IsAsyncMode)
            {
                ilGenerator.Emit(OpCodes.Ldarg, serviceMethodInfo.Parameters.Count + 1);
                if (serviceMethodInfo.OnErrorCallback != null)
                {
                    ilGenerator.Emit(OpCodes.Ldarg, serviceMethodInfo.Parameters.Count + 2);
                }
                else
                {
                    ilGenerator.Emit(OpCodes.Ldnull);
                }
            }

            ilGenerator.Emit(OpCodes.Callvirt, invokeMethod);
            ilGenerator.Emit(OpCodes.Ret);
        }

        /// <summary>
        /// 获取用于进行以同步方式进行服务调用的方法
        /// </summary>
        /// <param name="serviceMethodInfo"></param>
        /// <returns></returns>
        private MethodInfo GetServiceInvokeMethod(ServiceMethodInfo serviceMethodInfo)
        {
            var invokeMethod = typeof(JavaServiceProxy).GetMethod(
                    serviceMethodInfo.ServiceMethod.ReturnType.IsGenericType ? "InvokeGeneric" : "Invoke",
                    BindingFlags.NonPublic | BindingFlags.Instance);

            if (serviceMethodInfo.ServiceMethod.ReturnType.IsGenericType)
            {
                invokeMethod = invokeMethod.MakeGenericMethod(serviceMethodInfo.ServiceMethod.ReturnType.GetGenericArguments());
            }

            return invokeMethod;
        }

        /// <summary>
        /// 获取用于进行以异步方式进行服务调用的方法
        /// </summary>
        /// <param name="serviceMethodInfo"></param>
        /// <returns></returns>
        private MethodInfo GetServiceInvokeAsyncMethod(ServiceMethodInfo serviceMethodInfo)
        {
            var invokeMethod = typeof(JavaServiceProxy).GetMethod(
                    serviceMethodInfo.OnSuccessCallback.ParameterType.IsGenericType ? "InvokeGenericAsync" : "InvokeAsync",
                    BindingFlags.NonPublic | BindingFlags.Instance);

            if (serviceMethodInfo.OnSuccessCallback.ParameterType.IsGenericType)
            {
                invokeMethod = invokeMethod.MakeGenericMethod(serviceMethodInfo.OnSuccessCallback.ParameterType.GetGenericArguments());
            }

            return invokeMethod;
        }

        /// <summary>
        /// 处理只有一个参数，且参数类型为引用类型（除string类型)的方式，此时直接对参数进行序列化
        /// </summary>
        /// <param name="ilGenerator"></param>
        /// <param name="serviceMethodInfo"></param>
        /// <returns></returns>
        private LocalBuilder GetServiceParameterByObject(ILGenerator ilGenerator, ServiceMethodInfo serviceMethodInfo)
        {
            var parameter = ilGenerator.DeclareLocal(serviceMethodInfo.Parameters.First().ParameterType);

            ilGenerator.Emit(OpCodes.Ldarg_1);
            ilGenerator.Emit(OpCodes.Stloc, parameter);

            return parameter;
        }

        /// <summary>
        /// 处理含有任意个参数的方法，此时将所有参数添加到一个字典中，再进行序列化操作
        /// </summary>
        /// <param name="ilGenerator"></param>
        /// <param name="serviceMethodInfo"></param>
        /// <returns></returns>
        private LocalBuilder GetServiceParameterByDictionary(ILGenerator ilGenerator, ServiceMethodInfo serviceMethodInfo)
        {
            var dictType = typeof(Dictionary<string, object>);
            var values = ilGenerator.DeclareLocal(dictType);

            //values = new Dictionary<string, object>();
            ilGenerator.Emit(OpCodes.Newobj, dictType.GetConstructor(Type.EmptyTypes));
            ilGenerator.Emit(OpCodes.Stloc, values);

            //将所有的参数添加到一个字典中
            for (var i = 0; i < serviceMethodInfo.Parameters.Count; i++)
            {
                //values.Add(name, value);
                ilGenerator.Emit(OpCodes.Ldloc, values);
                ilGenerator.Emit(OpCodes.Ldstr, serviceMethodInfo.Parameters[i].Name);
                ilGenerator.Emit(OpCodes.Ldarg, i + 1);

                //对于值类型，进行装箱处理
                if (serviceMethodInfo.Parameters[i].ParameterType.IsValueType)
                {
                    ilGenerator.Emit(OpCodes.Box, serviceMethodInfo.Parameters[i].ParameterType);
                }

                ilGenerator.Emit(OpCodes.Callvirt, dictType.GetMethod("Add"));
            }

            return values;
        }

        /// <summary>
        /// 是否需用模拟方式调用指定方法，该模式下，返回本地虚拟数据，便于客户端独立调试
        /// </summary>
        /// <param name="method"></param>
        /// <returns></returns>
        private bool IsFakeMode(MethodInfo method)
        {
            return ReflectionUtils.GetAttribute<FakeAttribute>(method, false) != null;
        }

        private MethodBuilder DefineMethod(MethodInfo method)
        {
            var builder = typeBuilder.DefineMethod(
                method.Name,
                MethodAttributes.Public | MethodAttributes.HideBySig | MethodAttributes.NewSlot | MethodAttributes.Virtual | MethodAttributes.Final,
                method.CallingConvention,
                method.ReturnType,
                method.GetParameters().Select(p => p.ParameterType).ToArray()
                );

            //进行泛型处理
            if (method.IsGenericMethod)
            {
                var args = method.GetGenericArguments();
                var genericParameters = builder.DefineGenericParameters(args.Select(a => a.Name).ToArray());

                //处理泛型约束
                for (var i = 0; i < args.Length; i++)
                {
                    genericParameters[i].SetGenericParameterAttributes(args[i].GenericParameterAttributes);

                    var constraints = args[i].GetGenericParameterConstraints();
                    foreach (var constraint in constraints)
                    {
                        genericParameters[i].SetBaseTypeConstraint(constraint);
                    }
                }
            }

            //定义方法中的参数
            foreach (var parameter in method.GetParameters())
            {
                builder.DefineParameter(parameter.Position + 1, parameter.Attributes, parameter.Name);
            }

            return builder;
        }
    }
}
