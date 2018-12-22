using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    public static class ServiceExtensions
    {
        /// <summary>
        /// 抑制服务中的错误信息
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="service"></param>
        /// <returns></returns>
        public static T SuppressError<T>(this T service) where T : IServiceBase
        {
            var serviceConfig = service as IServiceConfig;
            if (serviceConfig != null)
            {
                serviceConfig.SuppressError = true;
                serviceConfig.ThrowException = false;
            }

            return service;
        }

        /// <summary>
        /// 是否向服务调用方抛出服务调用时产生的异常信息
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="service"></param>
        /// <returns></returns>
        public static T ThrowException<T>(this T service) where T : IServiceBase
        {
            var serviceConfig = service as IServiceConfig;
            if (serviceConfig != null)
            {
                serviceConfig.SuppressError = false;
                serviceConfig.ThrowException = true;
            }

            return service;
        }
    }
}
