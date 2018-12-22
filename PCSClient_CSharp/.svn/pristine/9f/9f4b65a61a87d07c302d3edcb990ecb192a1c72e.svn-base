using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.MisPOSPayer
{

    public static class Constant
    {
        public static string POSFullTimeFormt = "yyyyMMddHHmmss";
        public static string POSDateFormat = "yyyyMMdd";

        public static string GetBankName(string bankCode)
        {
            switch (bankCode)
            {
                case "0001": return "中国银联";
                case "0100": return "邮政储汇";
                case "0102": return "工商银行";
                case "0103": return "农业银行";
                case "0104": return "中国银行";
                case "0105": return "建设银行";
                case "0301": return "交通银行";
                case "0302": return "中信银行";
                case "0303": return "光大银行";
                case "0304": return "华夏银行";
                case "0305": return "民生银行";
                case "0306": return "广发银行";
                case "0307": return "深发银行";
                case "0308": return "招商银行";
                case "0309": return "兴业银行";
                case "0310": return "浦发银行";
            }
            return "商业银行";
        }

        public static class POSSettleStatus
        {
            public const string NotSettle = "0";
            public const string Settled = "1";
        }

        public static class OutSysName
        {
            public const string PCS = "PCS";
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
        }

    }
}
