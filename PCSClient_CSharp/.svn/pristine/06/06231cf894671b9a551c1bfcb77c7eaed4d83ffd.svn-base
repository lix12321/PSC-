using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;

namespace Zebone.Services
{
    /// <summary>
    /// 用于生成服务接口代理类
    /// </summary>
    internal class ServiceProxyBuilder
    {
        private static AssemblyBuilder assemblyBuilder;
        private static ModuleBuilder moduleBuilder;

        private Type serviceType;
        private Type serviceImplType;
        private TypeBuilder typeBuilder;
        private FieldBuilder implServiceField;

        internal ServiceProxyBuilder(Type serviceType, Type serviceImplType)
        {
            this.serviceType = serviceType;
            this.serviceImplType = serviceImplType;
        }

        internal Type Build()
        {
            CheckTypeIsValid();

            if (assemblyBuilder == null)
            {
                assemblyBuilder = AppDomain.CurrentDomain.DefineDynamicAssembly(new AssemblyName("ServiceProxy" + Guid.NewGuid().ToString("N")), AssemblyBuilderAccess.Run);
                moduleBuilder = assemblyBuilder.DefineDynamicModule("ServiceProxy" + Guid.NewGuid().ToString("N"));
            }

            typeBuilder = moduleBuilder.DefineType("ServiceProxy" + Guid.NewGuid().ToString("N"), TypeAttributes.Public, typeof(ServiceProxy), new[] { serviceType });

            implServiceField = typeBuilder.DefineField("implService", serviceImplType, FieldAttributes.Private);  //定义一个字段，用于表示服务实现

            BuildConstructor();
            BuildOverrideServiceProperty();
            BuildServiceMethods();

            return typeBuilder.CreateType();
        }

        private void CheckTypeIsValid()
        {
            if (!serviceType.IsInterface) throw new ZeboneException(string.Format("类型 {0} 需要为接口类型。", serviceType.FullName));

            if (!typeof(IService).IsAssignableFrom(serviceType))
            {
                throw new ZeboneException(string.Format("接口服务 {0} 未实现接口 {1}", serviceType.FullName, typeof(IService).FullName));
            }

            if (!typeof(IService).IsAssignableFrom(serviceImplType))
            {
                throw new ZeboneException(string.Format("类型 {0} 未实现接口 {1}", serviceImplType.FullName, serviceType.FullName));
            }
        }

        private void BuildConstructor()
        {
            var constructorBuilder = typeBuilder.DefineConstructor(MethodAttributes.Public | MethodAttributes.HideBySig | MethodAttributes.SpecialName | MethodAttributes.RTSpecialName, CallingConventions.Standard, Type.EmptyTypes);

            var ilGenerator = constructorBuilder.GetILGenerator();
            ilGenerator.Emit(OpCodes.Ldarg_0);
            ilGenerator.Emit(OpCodes.Call, typeof(ServiceProxy).GetConstructors(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Instance)[0]);

            //初始化服务实例字段
            ilGenerator.Emit(OpCodes.Ldarg_0);
            ilGenerator.Emit(OpCodes.Newobj, serviceImplType.GetConstructor(Type.EmptyTypes));
            ilGenerator.Emit(OpCodes.Stfld, implServiceField);
            ilGenerator.Emit(OpCodes.Ret);
        }

        /// <summary>
        /// 覆盖基类的 Service 属性
        /// </summary>
        private void BuildOverrideServiceProperty()
        {
            var methodBuilder = typeBuilder.DefineMethod("get_Service", MethodAttributes.Public | MethodAttributes.HideBySig | MethodAttributes.SpecialName | MethodAttributes.Virtual, typeof(IService), Type.EmptyTypes);

            var ilGenerator = methodBuilder.GetILGenerator();
            ilGenerator.Emit(OpCodes.Ldarg_0);
            ilGenerator.Emit(OpCodes.Ldfld, implServiceField);
            ilGenerator.Emit(OpCodes.Ret);

            var propertyBuilder = typeBuilder.DefineProperty("Service", PropertyAttributes.None, typeof(IService), Type.EmptyTypes);
            propertyBuilder.SetSetMethod(methodBuilder);
        }

        private void BuildServiceMethods()
        {
            foreach (var methodInfo in serviceType.GetMethods())
            {
                BuildServiceMethod(methodInfo);
            }
        }

        private void BuildServiceMethod(MethodInfo methodInfo)
        {
            var methodBuilder = DefineMethod(methodInfo);
            var ilGenerator = methodBuilder.GetILGenerator();
            var methodParameters = methodInfo.GetParameters();

            var service = ilGenerator.DeclareLocal(serviceImplType);       //用于保存服务实例
            var exception = ilGenerator.DeclareLocal(typeof(Exception));   //用于保存调用过程中的异常信息
            var retLabel = ilGenerator.DefineLabel();

            //存在返回值时，定义一个变量，用于保存服务调用结果
            LocalBuilder sr = null;
            if (methodBuilder.ReturnType != typeof(void)) sr = ilGenerator.DeclareLocal(methodInfo.ReturnType);

            //开始try模块
            ilGenerator.BeginExceptionBlock();

            //获取服务实例
            ilGenerator.Emit(OpCodes.Ldarg_0);
            ilGenerator.Emit(OpCodes.Ldfld, implServiceField);
            ilGenerator.Emit(OpCodes.Stloc, service);

            //调用服务实例中的对应方法
            ilGenerator.Emit(OpCodes.Ldloc, service);
            for (var i = 0; i < methodParameters.Length; i++) ilGenerator.Emit(OpCodes.Ldarg, i + 1);
            ilGenerator.Emit(OpCodes.Callvirt, serviceImplType.GetMethod(methodInfo.Name, methodInfo.GetParameters().Select(p => p.ParameterType).ToArray()));

            //返回调用结果
            if (sr != null)
            {
                ilGenerator.Emit(OpCodes.Stloc, sr);

                //判断返回值是否为null，返回值为null时，新建一个ServiceResult对象返回，这样调用方无需判断接口返回值是否为null
                if (typeof(ServiceResult).IsAssignableFrom(methodBuilder.ReturnType))
                {
                    ilGenerator.Emit(OpCodes.Ldloc, sr);
                    ilGenerator.Emit(OpCodes.Ldnull);
                    ilGenerator.Emit(OpCodes.Ceq);
                    ilGenerator.Emit(OpCodes.Brfalse_S, retLabel);

                    if (methodInfo.ReturnType.IsGenericType)
                    {
                        CallBaseMethod(ilGenerator, "GetDefaultServiceResultGeneric", methodInfo.ReturnType.GetGenericArguments(), new object[] { });
                    }
                    else
                    {
                        CallBaseMethod(ilGenerator, "GetDefaultServiceResult", null, new object[] { });
                    }

                    ilGenerator.Emit(OpCodes.Stloc, sr);
                }
            }

            ilGenerator.Emit(OpCodes.Leave_S, retLabel);

            //处理异常
            ilGenerator.BeginCatchBlock(typeof(Exception));

            //函数返回ServiceResult类型时，将抛出的异常转换成ServiceResult对象
            if (typeof(ServiceResult).IsAssignableFrom(methodBuilder.ReturnType))
            {
                ilGenerator.Emit(OpCodes.Stloc, exception);   //保存异常信息

                if (methodInfo.ReturnType.IsGenericType)
                {
                    CallBaseMethod(ilGenerator, "GetErrorServiceResultGeneric", methodInfo.ReturnType.GetGenericArguments(), service, exception);
                }
                else
                {
                    CallBaseMethod(ilGenerator, "GetErrorServiceResult", null, service, exception);
                }

                ilGenerator.Emit(OpCodes.Stloc, sr);
            }
            else
            {
                ilGenerator.Emit(OpCodes.Throw);   //抛出原异常
            }

            ilGenerator.Emit(OpCodes.Leave_S, retLabel);

            //处理finally块
            ilGenerator.BeginFinallyBlock();

            ilGenerator.EndExceptionBlock();

            ilGenerator.MarkLabel(retLabel);
            if (sr != null) ilGenerator.Emit(OpCodes.Ldloc, sr);
            ilGenerator.Emit(OpCodes.Ret);
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

        /// <summary>
        /// 调用基类（ServiceProxy）中的方法
        /// </summary>
        /// <param name="ilGenerator"></param>
        /// <param name="methodName"></param>
        /// <param name="genericTypeArguments"></param>
        /// <param name="locals"></param>
        private void CallBaseMethod(ILGenerator ilGenerator, string methodName, Type[] genericTypeArguments, params object[] locals)
        {
            ilGenerator.Emit(OpCodes.Ldarg_0);  //this压栈

            //局部变量压栈
            foreach (var local in locals)
            {
                if (local == null) ilGenerator.Emit(OpCodes.Ldnull);
                else if (local is string) ilGenerator.Emit(OpCodes.Ldstr, (string)local);
                else if (local is LocalBuilder) ilGenerator.Emit(OpCodes.Ldloc, (LocalBuilder)local);
                else throw new ZeboneException(string.Format("在生成调用服务代理对象中的方法时，遇到无法处理的数据类型 {0}", local.GetType().FullName));
            }

            //查找需要调用的方法
            var method = typeof(ServiceProxy).GetMethods(BindingFlags.Instance | BindingFlags.NonPublic | BindingFlags.Public).Single(m => m.Name == methodName && m.GetParameters().Length == locals.Length);
            if (genericTypeArguments != null) method = method.MakeGenericMethod(genericTypeArguments);

            ilGenerator.Emit(OpCodes.Call, method);
        }
    }
}
