using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;

using Newtonsoft.Json.Linq;

using Zebone.Common;

namespace Zebone.Services
{
    /// <summary>
    /// 用于进行服务的创建
    /// </summary>
    public class ServiceFactory
    {
        /// <summary>
        /// 外部应用配置集合
        /// </summary>
        private static ServiceRegistrationCollection serviceRegistrations = new ServiceRegistrationCollection();
        /// <summary>
        /// 外部应用工厂集合
        /// </summary>
        private static Dictionary<string, IApplicationServiceFactory> applicationServiceFactorys;

        private static readonly string DefaultName = "Default";

        ///// <summary>
        ///// 获取自定义属性，用于传递额外数据，不区分大小写
        ///// </summary>
        //public static Dictionary<string, string> Properties { get; private set; }

        static ServiceFactory()
        {
            //Properties = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);
            applicationServiceFactorys = new Dictionary<string, IApplicationServiceFactory>(StringComparer.OrdinalIgnoreCase);
        }

        /// <summary>
        /// 服务发生错误时触发的事件
        /// </summary>
        public static event EventHandler<ServiceErrorEventArgs> ServiceError;

        /// <summary>
        /// 服务调用进度发送改变时触发的事件
        /// </summary>
        public static event EventHandler InvokeProgressChanged;

        /// <summary>
        /// 创建指定类型的服务
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <returns></returns>
        public static T CreateService<T>() where T : IServiceBase
        {
            return (T)CreateService(typeof(T), null);
        }

        /// <summary>
        /// 根据指定的服务注册名称创建指定类型的服务
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="serviceRegistrationName">服务注册名称</param>
        /// <returns></returns>
        public static T CreateService<T>(string serviceRegistrationName) where T : IService
        {
            return (T)CreateService(typeof(T), serviceRegistrationName);
        }

        ///// <summary>
        ///// 创建指定类型的服务
        ///// </summary>
        ///// <param name="serviceType">服务类型</param>
        ///// <returns></returns>
        //public static IService CreateService(Type serviceType)
        //{
        //    return CreateService(serviceType, null);
        //}

        /// <summary>
        /// 根据指定的服务注册名称创建指定类型的服务
        /// </summary>
        /// <param name="serviceType">服务类型</param>
        /// <param name="serviceRegistrationName">服务注册名称</param>
        /// <returns></returns>
        public static IService CreateService(Type serviceType, string serviceRegistrationName)
        {
            try
            {
                return CreateServiceInternal(serviceType, serviceRegistrationName);
            }
            catch (Exception exception)
            {
                throw new ZeboneException(string.Format("在创建服务 {0} 时发生错误： {1}", serviceType.FullName, exception.Message), exception);
            }
        }

        ///// <summary>
        ///// 创建指定类型的所有服务实现
        ///// </summary>
        ///// <typeparam name="T"></typeparam>
        ///// <returns></returns>
        //public static List<T> CreateServices<T>() where T : IService
        //{
        //    var services = new List<T>();
        //    foreach (var reg in GetServiceRegistrations<T>())
        //    {
        //        if (reg.Enabled)
        //        {
        //            services.Add(CreateService<T>(reg.Name));
        //        }
        //    }

        //    return services;
        //}

        ///// <summary>
        ///// 获取指定服务的注册信息
        ///// </summary>
        ///// <typeparam name="T"></typeparam>
        ///// <returns></returns>
        //public static List<ServiceRegistrationInfo> GetServiceRegistrations<T>() where T : IService
        //{
        //    return GetServiceRegistrations(typeof(T));
        //}

        /// <summary>
        /// 进行服务注册
        /// </summary>
        /// <param name="items">服务注册信息的列表</param>
        public static void RegisterServices(ServiceRegistrationCollection items)
        {
            foreach (var item in items)
            {
                List<ServiceRegistrationInfo> registrations;
                if (!serviceRegistrations.TryGetValue(item.Key, out registrations))
                {
                    registrations = new List<ServiceRegistrationInfo>();
                    serviceRegistrations.Add(item.Key, registrations);
                }

                if (string.Compare(item.Key, "ApplicationServiceFactory", true) == 0)
                {
                    item.Value.ForEach(config => { if (string.IsNullOrEmpty(config.Provider)) config.Provider = "Zebone.JavaService.dll"; });
                }
                registrations.AddRange(item.Value);
            }
        }

        ///// <summary>
        ///// 注册应用服务
        ///// </summary>
        ///// <param name="serviceProvider"></param>
        ///// <param name="serviceUrl"></param>
        //public static void RegisterApplicationService(string serviceProvider, string serviceUrl)
        //{
        //    var config = new JObject();
        //    config["ServiceUrl"] = serviceUrl;

        //    serviceRegistrations.Add(typeof(IApplicationServiceFactory).FullName, new List<ServiceRegistrationInfo>()
        //    {
        //        new ServiceRegistrationInfo()
        //        {
        //            Name="Default",
        //            Provider=serviceProvider,
        //            Config= config
        //        }
        //    });
        //}

        private static IService CreateServiceInternal(Type serviceType, string serviceRegistrationName)
        {
            IService service = null;

            if (typeof(IServiceBase).IsAssignableFrom(serviceType))
            {
                service = CreateApplicationService(serviceType, serviceRegistrationName);
            }
            else
            {
                //获取服务注册信息
                var registrations = GetServiceRegistrations(serviceType).Where(r => string.Compare(r.Name, serviceRegistrationName, true) == 0).ToList();
                //获取默认服务信息
                if (registrations.Count == 0)
                {
                    registrations = GetServiceRegistrations(serviceType).Where(r => string.Compare(r.Name, DefaultName, true) == 0).ToList();
                }
                if (registrations.Count == 0)
                {
                    throw new ZeboneException(string.IsNullOrEmpty(serviceRegistrationName) ?
                       string.Format("未找到服务 {0} 的注册信息。", serviceType.FullName) :
                       string.Format("未找到对于服务 {serviceType0} 且注册名称为 {1} 的注册信息。", serviceType.FullName, serviceRegistrationName));
                }

                if (registrations.Count > 1)
                {
                    throw new ZeboneException(string.IsNullOrEmpty(serviceRegistrationName) ?
                       string.Format("找到多个服务 {0} 的注册信息。", serviceType.FullName) :
                       string.Format("找到多个对于服务 {serviceType0} 且注册名称为 {1} 的注册信息。", serviceType.FullName, serviceRegistrationName));
                }

                service = CreateService(registrations[0]);
            }

            service.Initialize();

            return service;
        }

        #region 应用服务
        /// <summary>
        /// 创建应用服务
        /// </summary>
        /// <param name="serviceType"></param>
        /// <returns></returns>
        private static IService CreateApplicationService(Type serviceType, string serviceRegistrationName)
        {
            if (string.IsNullOrEmpty(serviceRegistrationName))
            {
                serviceRegistrationName = DefaultName;
            }
            //先创建应用服务工厂服务，再使用该服务创建相应的应用服务
            if (!applicationServiceFactorys.ContainsKey(serviceRegistrationName))
            {
                applicationServiceFactorys.Add(serviceRegistrationName, CreateService<IApplicationServiceFactory>(serviceRegistrationName));
            }

            var service = applicationServiceFactorys[serviceRegistrationName].CreateService(serviceType);
            service.ServiceInfo = applicationServiceFactorys[serviceRegistrationName].ServiceInfo;
            return service;
        }

        /// <summary>
        /// 对于服务调用错误信息进行提示
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public static void NotifyError(object sender, ServiceErrorEventArgs e)
        {
            if (ServiceError != null)
            {
                ServiceError(sender, e);
            }
        }

        public static void PerformInvokeProgressChanged(object sender, EventArgs e)
        {
            if (InvokeProgressChanged != null)
            {
                InvokeProgressChanged(sender, e);
            }
        }
        #endregion


        /// <summary>
        /// 获取指定服务的注册信息
        /// </summary>
        /// <param name="serviceType"></param>
        /// <returns></returns>
        public static List<ServiceRegistrationInfo> GetServiceRegistrations(Type serviceType)
        {
            //确定服务名称查找顺序（服务全名 => 服务短名）
            var serviceNames = new List<string>() { serviceType.FullName, serviceType.Name };
            if (serviceType.IsInterface && serviceType.Name.StartsWith("I")) serviceNames.Add(serviceType.Name.Substring(1));  //对于接口类型，去掉前缀I，作为一个查找顺序

            var result = new List<ServiceRegistrationInfo>();

            foreach (var serviceName in serviceNames)
            {
                if (serviceRegistrations.ContainsKey(serviceName))
                {
                    result.AddRange(serviceRegistrations[serviceName].Where(r => r.Enabled));
                    break;
                }
            }

            result.ForEach(item =>
            {
                item.ServiceType = serviceType;
            });

            return result;
        }

        /// <summary>
        /// 根据指定的服务注册信息创建服务
        /// </summary>
        /// <param name="serviceRegistration">服务注册信息</param>
        /// <returns></returns>
        private static IService CreateService(ServiceRegistrationInfo serviceRegistration)
        {
            var service = Activator.CreateInstance(serviceRegistration.GetServiceImplType()) as IService;
            service.ServiceInfo = serviceRegistration;

            ConfigService(service, serviceRegistration.Config);

            return service;
        }

        /// <summary>
        /// 进行服务配置工作
        /// </summary>
        /// <param name="service"></param>
        /// <param name="config"></param>
        private static void ConfigService(IService service, JObject config)
        {
            if (config == null) return;

            var serviceProxy = service as ServiceProxy;
            if (serviceProxy != null && serviceProxy.Service != null) service = serviceProxy.Service;

            foreach (var property in service.GetType().GetProperties().Where(p => p.CanWrite))
            {
                if (ReflectionUtils.GetAttribute<ServiceParameterAttribute>(property, true) == null) continue;

                var jProperty = config.Property(property.Name);
                if (jProperty != null) property.SetValue(service, jProperty.Value.ToObject(property.PropertyType), null);
            }
        }
    }
}
