using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zebone.Services
{
    /// <summary>
    /// 服务基类
    /// </summary>
    public abstract class Service : IService
    {
        public ServiceRegistrationInfo ServiceInfo { get; set; }

        public virtual void Initialize() { }
    }
}
