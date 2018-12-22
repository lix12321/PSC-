using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 对于ServiceResult进行后续操作
    /// </summary>
    public static class ServiceResultExtensions
    {
        /// <summary>
        /// 执行服务调用成功后的操作
        /// </summary>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <returns></returns>
        public static ServiceResult Then(this ServiceResult serviceResult, Action onSuccess)
        {
            if (serviceResult.Success)
            {
                onSuccess();
            }
            else
            {

            }

            return serviceResult;
        }

        /// <summary>
        /// 执行服务调用结束后的操作
        /// </summary>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <param name="onError">服务调用失败后执行的操作，该操作有一个输入参数，表示错误信息</param>
        /// <returns></returns>
        public static ServiceResult Then(this ServiceResult serviceResult, Action onSuccess, Action<string> onError)
        {
            if (serviceResult.Success)
            {
                onSuccess();
            }
            else
            {
                onError(serviceResult.Message);
            }

            return serviceResult;
        }

        /// <summary>
        /// 执行服务调用结束后的操作
        /// </summary>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <param name="onError">服务调用失败后执行的操作，该操作有两个输入参数，第一个参数表示错误状态码，第二个参数表示错误信息</param>
        /// <returns></returns>
        public static ServiceResult Then(this ServiceResult serviceResult, Action onSuccess, Action<string, string> onError)
        {
            if (serviceResult.Success)
            {
                onSuccess();
            }
            else
            {
                onError(serviceResult.StatusCode, serviceResult.Message);
            }

            return serviceResult;
        }

        /// <summary>
        /// 执行服务调用成功后的操作
        /// </summary>
        /// <typeparam name="T">服务调用结果类型</typeparam>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <returns></returns>
        public static ServiceResult<T> Then<T>(this ServiceResult<T> serviceResult, Action<T> onSuccess)
        {
            if (serviceResult.Success)
            {
                onSuccess(serviceResult.Value);
            }
            else
            {

            }

            return serviceResult;
        }

        /// <summary>
        /// 执行服务调用结束后的操作
        /// </summary>
        /// <typeparam name="T">服务调用结果类型</typeparam>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <param name="onError">服务调用失败后执行的操作，该操作有一个输入参数，表示错误信息</param>
        /// <returns></returns>
        public static ServiceResult<T> Then<T>(this ServiceResult<T> serviceResult, Action<T> onSuccess, Action<string> onError)
        {
            if (serviceResult.Success)
            {
                onSuccess(serviceResult.Value);
            }
            else
            {
                onError(serviceResult.Message);
            }

            return serviceResult;
        }

        /// <summary>
        /// 执行服务调用结束后的操作
        /// </summary>
        /// <typeparam name="T">服务调用结果类型</typeparam>
        /// <param name="serviceResult">服务调用结果</param>
        /// <param name="onSuccess">服务调用成功后执行的操作</param>
        /// <param name="onError">服务调用失败后执行的操作，该操作有两个输入参数，第一个参数表示错误状态码，第二个参数表示错误信息</param>
        /// <returns></returns>
        public static ServiceResult<T> Then<T>(this ServiceResult<T> serviceResult, Action<T> onSuccess, Action<string, string> onError)
        {
            if (serviceResult.Success)
            {
                onSuccess(serviceResult.Value);
            }
            else
            {
                onError(serviceResult.StatusCode, serviceResult.Message);
            }

            return serviceResult;
        }
    }
}
