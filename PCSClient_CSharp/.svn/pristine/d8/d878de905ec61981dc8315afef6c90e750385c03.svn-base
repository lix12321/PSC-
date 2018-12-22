using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zebone.Services
{
    /// <summary>
    /// 用于进行应用服务的创建
    /// </summary>
    public interface IApplicationServiceFactory : IService
    {
        /// <summary>
        /// 创建指定的应用服务
        /// </summary>
        /// <param name="serviceType">应用服务类型</param>
        /// <returns></returns>
        IServiceBase CreateService(Type serviceType);
    }
}
