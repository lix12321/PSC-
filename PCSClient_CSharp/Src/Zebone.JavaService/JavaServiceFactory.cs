using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Zebone.Services;

namespace Zebone.JavaService
{
    /// <summary>
    /// 进行Java服务的创建
    /// </summary>
    public class JavaServiceFactory : Service, IApplicationServiceFactory
    {
        private static object lockObject = new object();
        private static Dictionary<Type, Type> proxyTypes = new Dictionary<Type, Type>();

        public IServiceBase CreateService(Type serviceType)
        {
            var proxy = Activator.CreateInstance(GetProxyType(serviceType)) as JavaServiceProxy;
            proxy.IsLoginService = IsLogingService(serviceType);

            return proxy;
        }

        /// <summary>
        /// 创建服务代理类型
        /// </summary>
        /// <param name="serviceType">需要调用的服务类型</param>
        /// <returns></returns>
        private Type GetProxyType(Type serviceType)
        {
            Type proxyType = null;

            if (!proxyTypes.TryGetValue(serviceType, out proxyType))
            {
                lock (lockObject)
                {
                    if (!proxyTypes.TryGetValue(serviceType, out proxyType))
                    {
                        var builder = new JavaServiceProxyBuilder(serviceType);
                        proxyType = builder.Build();

                        proxyTypes.Add(serviceType, proxyType);
                    }
                }
            }

            return proxyType;
        }

        /// <summary>
        /// 指定的服务是否为登录服务
        /// </summary>
        /// <param name="serviceType">需要调用的服务类型</param>
        /// <returns></returns>
        private bool IsLogingService(Type serviceType)
        {
            //如果接口使用LoginServiceAttribute标记，则说明该服务为登录服务
            var attrs = serviceType.GetCustomAttributes(typeof(LoginServiceAttribute), false);
            return attrs != null && attrs.Length > 0;
        }
    }
}
