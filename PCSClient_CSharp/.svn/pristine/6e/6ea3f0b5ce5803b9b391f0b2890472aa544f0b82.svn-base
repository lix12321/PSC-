using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zebone.Services
{
    /// <summary>
    /// 接口服务代理类
    /// </summary>
    public abstract class ServiceProxy : Service, IDisposable
    {
        protected ServiceProxy() { }

        public abstract IService Service { get; }

        public void Dispose()
        {
            var disposable = this.Service as IDisposable;
            if (disposable != null) disposable.Dispose();
        }

        public override void Initialize()
        {
            base.Initialize();

            this.Service.ServiceInfo = this.ServiceInfo;
            this.Service.Initialize();
        }

        protected ServiceResult GetDefaultServiceResult()
        {
            return new ServiceResult();
        }

        protected ServiceResult GetDefaultServiceResultGeneric<T>()
        {
            return new ServiceResult<T>(default(T));
        }

        protected ServiceResult GetErrorServiceResult(IService service, Exception exception)
        {
            return new ServiceResult(false, string.Empty, exception.Message);
        }

        protected ServiceResult<T> GetErrorServiceResultGeneric<T>(IService service, Exception exception)
        {
            return new ServiceResult<T>(false, string.Empty, exception.Message);
        }
    }
}
