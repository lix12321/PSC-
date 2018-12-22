using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

using Newtonsoft.Json.Linq;

using Zebone.Common;

namespace Zebone.Services
{
    /// <summary>
    /// 表示服务注册信息
    /// </summary>
    public class ServiceRegistrationInfo
    {
        private Type serviceImplType;

        public ServiceRegistrationInfo()
        {
            this.Enabled = true;
        }

        /// <summary>
        /// 获取或设置服务注册名称
        /// </summary>
        public string Name { get; set; }

        /// <summary>
        /// 获取或设置接口服务提供程序
        /// </summary>
        public string Provider { get; set; }

        /// <summary>
        /// 获取或设置是否服务是否启用
        /// </summary>
        public bool Enabled { get; set; }

        /// <summary>
        /// 获取或设置服务配置信息
        /// </summary>
        public JObject Config { get; set; }

        /// <summary>
        /// 获取服务配置信息，并以指定的类型返回
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <returns></returns>
        public T GetConfig<T>()
        {
            return this.Config.ToObject<T>();
        }

        /// <summary>
        /// 获取或设置服务类型
        /// </summary>
        internal Type ServiceType { get; set; }

        /// <summary>
        /// 获取服务实现类型
        /// </summary>
        /// <returns></returns>
        internal Type GetServiceImplType()
        {
            if (serviceImplType != null) return serviceImplType;

            if (string.IsNullOrEmpty(this.Provider))
            {
                //未定义服务地址，尝试直接创建，此时需要检查需要直接创建的服务是否为接口或抽象类
                if (this.ServiceType.IsInterface || this.ServiceType.IsAbstract)
                {
                    throw new ZeboneException(string.Format("服务 {0} 为接口或抽象类型，不能直接创建，请先注册该服务的实现。", serviceImplType.FullName));
                }

                serviceImplType = this.ServiceType;
            }
            else
            {
                if (this.Provider.Contains(","))
                {
                    serviceImplType = Type.GetType(this.Provider);
                    if (serviceImplType == null) throw new ZeboneException(string.Format("在 {0} 中未发现服务 {1} 的实现。", this.Provider, this.ServiceType.FullName));
                    if (serviceImplType.IsInterface || this.serviceImplType.IsAbstract) throw new ZeboneException(string.Format("服务 {0} 为接口或抽象类型，不能直接创建。", this.ServiceType.FullName));
                }
                else
                {
                    var assembly = Assembly.Load(this.Provider.EndsWith(".dll", StringComparison.OrdinalIgnoreCase) ? this.Provider.Substring(0, this.Provider.Length - 4) : this.Provider);
                    var validTypes = assembly.GetTypes().Where(t => !t.IsAbstract && this.ServiceType.IsAssignableFrom(t)).ToList();
                    if (validTypes.Count == 0) throw new ZeboneException(string.Format("在 {0} 中未发现服务 {1} 的实现。", this.Provider, this.ServiceType.FullName));
                    if (validTypes.Count > 1) throw new ZeboneException(string.Format("在 {0} 的类型 {1} 中均发现服务 {this.ServiceType.FullName} 的实现。", this.Provider, validTypes.ToString(", ")));

                    serviceImplType = validTypes[0];
                }
            }

            if (RequireCreateServiceProxy()) serviceImplType = CreateServiceProxy(serviceImplType);

            return serviceImplType;
        }

        /// <summary>
        /// 检查是否需要为当前服务创建代理对象，仅当需要创建的服务为接口类型，且接口中存在返回值为 <see cref="ServiceResult"/> 类型的服务创建代理对象
        /// </summary>
        /// <returns></returns>
        private bool RequireCreateServiceProxy()
        {
            if (!this.ServiceType.IsInterface) return false;

            foreach (var method in this.ServiceType.GetMethods())
            {
                if (typeof(ServiceResult).IsAssignableFrom(method.ReturnType)) return true;
            }

            return false;
        }

        /// <summary>
        /// 创建服务代理对象
        /// </summary>
        /// <param name="serviceImplType"></param>
        /// <returns></returns>
        private Type CreateServiceProxy(Type serviceImplType)
        {
            var builder = new ServiceProxyBuilder(this.ServiceType, serviceImplType);
            return builder.Build();
        }

    }
}
