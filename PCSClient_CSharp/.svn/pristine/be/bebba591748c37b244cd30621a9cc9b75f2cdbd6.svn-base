using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading.Tasks;
using Zebone.Services;

using Wellcare.PCS.MisPOSPayer;
using Wellcare.PCS.MisPOSPayer.Models;
using Wellcare.PCS.ClientPortal.Models;
using Wellcare.PCS.ClientPortal.Utils;

namespace Wellcare.PCS.ClientPortal.CommandPay
{
    class CommandPOSPay : ICommandPay
    {
        readonly string payWay;

        public CommandPOSPay(string payType)
        {
            payWay = payType;
        }

        public ServiceResult<ConsumeResult> Consume(decimal banlace)
        {
            var param = new POSConsumeParam() { payBalance = banlace, dtPayMode = payWay };
            if (string.Compare(payWay, Constant.PayWays.WeChat) == 0 || string.Compare(payWay, Constant.PayWays.Ali) == 0)
            {
                var formSweepCode = new FormSweepCode();
                if (formSweepCode.ShowDialog() == DialogResult.OK)
                {
                    param.sweepCode = formSweepCode.Data;
                }
                else
                {
                    return new ServiceResult<ConsumeResult>(false, Constant.ErrorCode.Cancel, string.Empty);
                }
            }

            var task = new Task<ServiceResult<POSConsumeResult>>((state) => MisPOSPayer.MisPOSPayer.Instance.Consume((MisPOSPayer.Models.POSConsumeParam)state), param);
            var ret = task.StartAndShowNotice<ServiceResult<POSConsumeResult>>();
            if (ret.Success)
            {
                var result = new ConsumeResult()
                {
                    payInfo = ret.Value.payInfo,
                    dtPayMode = payWay,
                    bankName = ret.Value.bankName,
                    cardNo = ret.Value.cardNo
                };
                return new ServiceResult<ConsumeResult>(result);
            }
            else
            {
                return new ServiceResult<ConsumeResult>(false, ret.StatusCode, ret.Message);
            }
        }
    }
}
