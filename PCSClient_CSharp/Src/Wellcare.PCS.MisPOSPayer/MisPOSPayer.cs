using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;
using Wellcare.PCS.MisPOSPayer.Models;
using Wellcare.PCS.MisPOSPayer.Configuration;
using Wellcare.PCS.MisPOSPayer.Services;
using Zebone.Logging;

namespace Wellcare.PCS.MisPOSPayer
{
    public class MisPOSPayer
    {
        private static Logger log = new FileLogger("MisPOSPayer_" + DateTime.Now.ToString(Constant.POSDateFormat) + ".txt", true);
        private static readonly string logFormat;
        /// <summary>
        /// 应用程序配置信息
        /// </summary>
        internal static ConfigurationManager Configuration { get; private set; }

        /// <summary>
        /// POS调用接口
        /// </summary>
        private POSInvoker invoker = new POSInvoker();

        public static MisPOSPayer Instance = new MisPOSPayer();

        static MisPOSPayer()
        {
            var builder = new StringBuilder();
            builder.AppendLine(string.Empty);
            builder.AppendLine("====================================================================================================================");
            builder.AppendLine("异步模式：{0}");
            builder.AppendLine("错误编码：{1}");
            builder.AppendLine("错误类型：{2}");
            builder.AppendLine("错误消息：{3}");
            builder.AppendLine("交易号：{4}");
            builder.AppendLine("入参数据：{5}");
            builder.AppendLine("出参数据：{6}");
            logFormat = builder.ToString();

        }

        private MisPOSPayer()
        {
            Configuration = new ConfigurationManager();
            Configuration.Load();

            //初始化服务
            ServiceFactory.ServiceError += ServiceFactory_ServiceError;
            ServiceFactory.InvokeProgressChanged += ServiceFactory_InvokeProgressChanged;
            ServiceFactory.RegisterServices(Configuration.Application.Services);
        }
        private void ServiceFactory_ServiceError(object sender, ServiceErrorEventArgs e)
        {
            log.Error(logFormat, e.Exception, e.AsyncMode, e.ErrorCode, e.ErrorType, e.ErrorMessage
                , e.TransactionCode, e.Request, e.Response);
        }

        private void ServiceFactory_InvokeProgressChanged(object sender, EventArgs e)
        {
        }

        public ServiceResult<POSConsumeResult> Consume(POSConsumeParam param)
        {
            var ret = invoker.Consume(param);
            if (ret.Success)
            {
                var retSave = ServiceFactory.CreateService<IPCSService>().SavePOSOrder(ret.Value);
                if (retSave.Success)
                {
                    return new ServiceResult<POSConsumeResult>(new POSConsumeResult()
                    {
                        payInfo = retSave.Value,
                        bankName = Constant.GetBankName(ret.Value.bankCode),
                        cardNo = ret.Value.cardNo
                    });
                }
                else
                {
                    var retRedund = invoker.Refund(ret.Value);
                    if (retRedund.Success)
                    {
                        return new ServiceResult<POSConsumeResult>(false, retSave.StatusCode, "收款成功，支付中心保存数据失败！已退款给用户");
                    }
                    else
                    {
                        return new ServiceResult<POSConsumeResult>(false, retSave.StatusCode, "收款成功，支付中心保存数据失败！退款失败，请联系管理员");
                    }
                }
            }
            else
            {
                return new ServiceResult<POSConsumeResult>(false, ret.StatusCode, ret.Message);
            }
        }
    }
}
