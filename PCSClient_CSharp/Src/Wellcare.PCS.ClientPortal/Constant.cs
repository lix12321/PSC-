using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.ClientPortal
{
    class Constant
    {
        public class ErrorCode
        {
            public const string Cancel = "0";
        }

        /// <summary>
        /// 支付方式
        /// </summary>
        public class PayWays
        {
            /// <summary>
            /// 现金
            /// </summary>
            public const string Cash = "1";
            /// <summary>
            /// 支票
            /// </summary>
            public const string Cheque = "2";
            /// <summary>
            /// 银行卡
            /// </summary>
            public const string BankCard = "3";
            /// <summary>
            /// 患者账户
            /// </summary>
            public const string PatiAccount = "4";
            /// <summary>
            /// 内部转账
            /// </summary>
            public const string InnerTrans = "5";
            /// <summary>
            /// 单位记账
            /// </summary>
            public const string UnitAccount = "6";

            /// <summary>
            /// 微信支付
            /// </summary>
            public const string WeChat = "7";

            /// <summary>
            /// 支付宝
            /// </summary>
            public const string Ali = "8";

            /// <summary>
            /// 微信支付宝二维码聚合支付
            /// </summary>
            public const string Aggregate = "9";

            /// <summary>
            /// 其他
            /// </summary>
            public const string Other = "99";

            /// <summary>
            /// 界面能选择的支付方式：现金，银行卡，微信，支付宝
            /// </summary>
            public static List<string> CanSelectPayWay = new List<string> { Cash, BankCard, WeChat, Ali };
        }
    }
}
