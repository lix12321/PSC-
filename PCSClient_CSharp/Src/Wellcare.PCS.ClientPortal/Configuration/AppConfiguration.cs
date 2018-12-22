using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;

namespace Wellcare.PCS.ClientPortal.Configuration
{
    class AppConfiguration
    {
        internal AppConfiguration()
        {
            this.Services = new ServiceRegistrationCollection();
        }

        /// <summary>
        /// 获取外部接口服务配置信息
        /// </summary>
        public ServiceRegistrationCollection Services { get; private set; }
    }
}
