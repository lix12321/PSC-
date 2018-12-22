using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.MisPOSPayer.Models
{
    public class POSConsumeResult
    {
        //支付中心订单号
        public string payInfo { get; set; }
        //银行名称 
        public string bankName { get; set; }
        //银行卡号 
        public string cardNo { get; set; }
    }
}
