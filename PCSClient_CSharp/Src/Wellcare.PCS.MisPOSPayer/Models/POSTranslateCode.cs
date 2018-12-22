using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.MisPOSPayer.Models
{
    class POSTranslateCode
    {
        /// <summary>
        /// 消费
        /// </summary>
        public const string CONSUME = "00";
        /// <summary>
        /// 撤销
        /// </summary>
        public const string CANCELCONSUME = "01";
        /// <summary>
        /// 退货
        /// </summary>
        public const string REFUND = "02";
        /// <summary>
        /// 查询余额
        /// </summary>
        public const string QUERYBALANCE = "03";
        /// <summary>
        /// 重打印
        /// </summary>
        public const string REPRINT = "04";
        /// <summary>
        /// 签到
        /// </summary>
        public const string SIGNIN = "05";
        /// <summary>
        /// POS结算
        /// </summary>
        public const string SETTLE = "06";
        /// <summary>
        /// 补登查询
        /// </summary>
        public const string QUERYINFO = "07";

        /// <summary>
        /// 银行卡签约
        /// </summary>
        public const string BANKCARDSIGN = "61";

        /// <summary>
        /// POS机扫码支付
        /// </summary>
        public const string SWEEPCONSUME = "60";
        /// <summary>
        /// POS机扫码退款
        /// </summary>
        public const string SWEEPREFUND = "62";
        /// <summary>
        /// 单笔聚合支付异常订单查询
        /// </summary>
        public const string SWEEPQUERYINFO = "64";
        /// <summary>
        /// 多笔聚合支付异常订单查询
        /// </summary>
        public const string SWEEPQUERYINFOS = "72";

        public static string GetDisplayString(string code)
        {
            switch (code)
            {
                case POSTranslateCode.CONSUME:
                    return "消费-" + code;
                case POSTranslateCode.CANCELCONSUME:
                    return "撤销-" + code;
                case POSTranslateCode.REFUND:
                    return "退货-" + code;
                case POSTranslateCode.QUERYBALANCE:
                    return "查询余额-" + code;
                case POSTranslateCode.REPRINT:
                    return "重打印-" + code;
                case POSTranslateCode.SIGNIN:
                    return "签到-" + code;
                case POSTranslateCode.SETTLE:
                    return "POS结算-" + code;
                case POSTranslateCode.BANKCARDSIGN:
                    return "银行卡签约-" + code;
                case POSTranslateCode.SWEEPCONSUME:
                    return "POS机扫码支付-" + code;
                case POSTranslateCode.SWEEPREFUND:
                    return "POS机扫码退费-" + code;
                case POSTranslateCode.QUERYINFO:
                    return "补登查询-" + code;
                case POSTranslateCode.SWEEPQUERYINFO:
                    return "单笔聚合支付异常订单查询-" + code;
                case POSTranslateCode.SWEEPQUERYINFOS:
                    return "多笔聚合支付异常订单查询-" + code;
            }
            return string.Empty;
        }
    }

    class UnionPayInput1
    {
        public decimal Number { get; set; }
        public string Str1 { get; set; }
    }
    class UnionPayInput2
    {
        public decimal Number { get; set; }
        public string Str1 { get; set; }
        public string Str2 { get; set; }
    }

    public class POSReturn
    {
        /// <summary>
        /// POS支付方式
        /// </summary>
        public string dtPayMode { get; set; }
        //结算状态（银行卡结算时用）
        public string settleStatus { get; set; }
        //银行行号
        public string bankCode { get; set; }
        //卡号
        public string cardNo { get; set; }
        //金额（无小数点，单位：分）
        public decimal amount { get; set; }
        //POS流水号（银行卡撤销退款时用）
        public string trace { get; set; }
        //POS交易参考号（银行卡退货退款时用）
        public string refer { get; set; }
        //收银流水(订单)号（微信支付宝扫码退款时用）
        public string szOrderTrace { get; set; }
        //终端号（银行卡结算时用）
        public string terno { get; set; }
        //原终端号
        public string oldTerno { get; set; }
        //交易日期
        public DateTime date { get; set; }

        internal POSReturn(OutPutData outputData)
        {
            bankCode = outputData.bank_code;
            cardNo = outputData.card_no;
            amount = string.IsNullOrEmpty(outputData.amount) ? 0m : decimal.Parse(outputData.amount);
            trace = outputData.trace;
            refer = outputData.refer;
            szOrderTrace = outputData.szOrderTrace;
            terno = outputData.terno;
            oldTerno = outputData.old_terno;
            settleStatus = Constant.POSSettleStatus.NotSettle;
            date = DateTime.ParseExact(outputData.date, Constant.POSFullTimeFormt, null);
        }

    }
}
