using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zebone.Services
{
    /// <summary>
    /// 表示一个服务接口
    /// </summary>
    public interface IService
    {
        /// <summary>
        /// 获取或设置服务接口注册信息
        /// </summary>
        ServiceRegistrationInfo ServiceInfo { get; set; }

        /// <summary>
        /// 进行服务接口的初始化
        /// </summary>
        void Initialize();
    }
}
