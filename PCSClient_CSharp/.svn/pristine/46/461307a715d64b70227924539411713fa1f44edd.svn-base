#define DEBUGPOS
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;
using System.Runtime.InteropServices;
using Zebone.Logging;
using Wellcare.PCS.MisPOSPayer.Models;


namespace Wellcare.PCS.MisPOSPayer
{
    class POSInvoker
    {
        public static readonly string CancelTrans = "0";
        public static readonly string InnerError = "-99";

        static readonly string BankTransSuccessCode = "00";
        static Encoding Encode = Encoding.GetEncoding("GB18030");
        static Logger log = new FileLogger("POSInvoker_" + DateTime.Now.ToString(Constant.POSDateFormat) + ".txt", true);
        static readonly string logFormat;

        /// <summary>
        /// 微信扫码的前两位
        /// </summary>
        public static readonly List<string> listWeChatCode = new List<string>() { "10", "11", "12", "13", "14", "15" };
        /// <summary>
        /// 支付宝扫码的前两位
        /// </summary>
        public static readonly List<string> listAliCode = new List<string>() { "25", "26", "27", "28", "29", "30" };


        [DllImport(@"..\landiccbmispos\MisPos.dll", EntryPoint = "BankTrans")]
        public static extern Int32 BankTrans(byte[] ptrIn, byte[] ptrOut);


        static POSInvoker()
        {
            var builder = new StringBuilder();
            builder.AppendLine(string.Empty);
            builder.AppendLine("====================================================================================================================");
            builder.AppendLine("交易类型：{0}");
            builder.AppendLine("返回值：{1}");
            builder.AppendLine("入参原始数据：{2}");
            builder.AppendLine("出参原始数据：{3}");
            logFormat = builder.ToString();

        }

        /// <summary>
        /// 有返回类型的POS接口调用
        /// </summary>
        /// <param name="inputdata"></param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> BankTrans(InputData inputdata)
        {
            int output = -1;
            byte[] inputDataBytes = null;
            byte[] outputDataBytes = new byte[1000];
            try
            {
                //序列化入参
                inputDataBytes = InputData.Serlize(inputdata);

                //调用POS机接口
                outputDataBytes = new byte[1000];
                output = BankTrans(inputDataBytes, outputDataBytes);

                //反序列化出参
                OutPutData outputData = new OutPutData();
                OutPutData.Deserlize(outputDataBytes, ref outputData);

#if DEBUGPOS
                output = 0;
                outputData.resp_code = "00";
                outputData.bank_code = "0308";
                outputData.card_no = "111111111111111111111";
                outputData.expr = "";
                outputData.amount = inputdata.amount;
                outputData.trace = DateTime.Now.ToString("HHmmss");
                outputData.refer = DateTime.Now.ToString("yyyyMMddHHmmss");
                outputData.auth = "";
                outputData.batch = "";
                outputData.date = DateTime.Now.ToString("yyyyMMddHHmmss");
                outputData.userno = "";
                outputData.terno = "00000815";
                outputData.old_terno = "";
                outputData.szOrderTrace = DateTime.Now.ToString("HHmmssyyyyMMdd");
                outputData.resp_chin = "";
                outputData.CredentialsType = "";
                outputData.CredentialsNum = "";
                outputData.Name = "";
                outputData.birthDay = "";
                outputData.age = "";
                outputData.Address = "";
                outputData.PhoneNum = "";
                outputData.Nation = "";
                outputData.szExtend1 = "";
                outputData.szExtend2 = "";
                outputData.szExtend3 = "";
                outputData.szExtend4 = "";
                outputData.szExtend5 = "";
                outputData.OrderNo = DateTime.Now.ToString("HHmmssyyyyMMdd");
#endif

                if (output == 0 && outputData.resp_code == BankTransSuccessCode)
                {
                    //正常交易后记录日志
                    log.Info(logFormat,
                        POSTranslateCode.GetDisplayString(inputdata.trans),
                        output,
                        Encode.GetString(inputDataBytes),
                        Encode.GetString(outputDataBytes));
                    return new ServiceResult<POSReturn>(new POSReturn(outputData));
                }
                else
                {
                    throw new Exception(outputData.resp_chin.ToString());
                }

            }
            catch (POSSerlizeException ex)
            {
                log.Error(logFormat,
                    ex,
                    POSTranslateCode.GetDisplayString(inputdata.trans),
                    output,
                    string.Empty,
                    string.Empty);
                return new ServiceResult<POSReturn>(false, InnerError, ex.Message);
            }
            catch (POSDerlizeException ex)
            {
                log.Error(logFormat,
                    ex,
                    POSTranslateCode.GetDisplayString(inputdata.trans),
                    output,
                    Encode.GetString(inputDataBytes),
                    string.Empty);
                return new ServiceResult<POSReturn>(false, InnerError, ex.Message);

            }
            catch (Exception ex)
            {
                log.Error(logFormat,
                    ex,
                    POSTranslateCode.GetDisplayString(inputdata.trans),
                    output,
                    Encode.GetString(inputDataBytes),
                    Encode.GetString(outputDataBytes));
                return new ServiceResult<POSReturn>(false, InnerError, ex.Message);
            }
        }

        /// <summary>
        /// 签到
        /// </summary>
        /// <returns></returns>
        private static ServiceResult SignIn()
        {
            InputData inputdata = new InputData(POSTranslateCode.SIGNIN);

            return BankTrans(inputdata);
        }

        /// <summary>
        /// 结算
        /// </summary>
        /// <returns></returns>
        private static ServiceResult Settle()
        {
            InputData inputdata = new InputData(POSTranslateCode.SETTLE);

            return BankTrans(inputdata);
        }

        /// <summary>
        /// 消费
        /// </summary>
        /// <param name="balance">消费金额</param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> Consume(decimal balance)
        {
            InputData inputdata = new InputData(POSTranslateCode.CONSUME);
            inputdata.amount = Math.Round(balance * 100m).ToString();

            var ret = BankTrans(inputdata);
            if (ret.Success && ret.Value.amount / 100m != balance)
            {
                return new ServiceResult<POSReturn>(false, InnerError
                    , string.Format("POS机缴费金额与应扣金额不一致！{0}应为{1}现为{2}"
                    , Environment.NewLine, balance, ret.Value.amount / 100m));
            }
            if (ret.Success)
            {
                ret.Value.dtPayMode = Constant.PayWays.BankCard;
            }
            return ret;
        }

        /// <summary>
        /// 扫码消费
        /// </summary>
        /// <param name="balance">消费金额</param>
        /// <param name="codeQR">微信支付宝支付码</param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> SweepConsume(UnionPayInput1 param)
        {
            InputData inputdata = new InputData(POSTranslateCode.SWEEPCONSUME);
            inputdata.amount = Math.Round(param.Number * 100m).ToString();
            inputdata.szQRCode = param.Str1;

            //POS机微信/支付宝扫码支付使用支付方式作为银行名称代码，方便界面显示
            var retSweepConsume = BankTrans(inputdata);
            if (retSweepConsume.Success && retSweepConsume.Value.amount / 100m != param.Number)
            {
                return new ServiceResult<POSReturn>(false, InnerError
                    , string.Format("POS机缴费金额与应扣金额不一致！{0}应为{1}现为{2}"
                    , Environment.NewLine, param.Number, retSweepConsume.Value.amount / 100m));
            }
            if (retSweepConsume.Success)
            {
                if (listWeChatCode.Contains(param.Str1.Substring(0, 2)))
                {
                    retSweepConsume.Value.dtPayMode = Constant.PayWays.WeChat;
                }
                else
                {
                    if (listAliCode.Contains(param.Str1.Substring(0, 2)))
                    {
                        retSweepConsume.Value.dtPayMode = Constant.PayWays.Ali;
                    }
                }
            }
            return retSweepConsume;
        }

        /// <summary>
        /// 撤销交易
        /// </summary>
        /// <param name="balance">金额</param>
        /// <param name="translateSerialCode">原交易流水号</param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> CancelConsume(UnionPayInput1 param)
        {
            InputData inputdata = new InputData(POSTranslateCode.CANCELCONSUME);
            inputdata.amount = Math.Round(param.Number * 100m).ToString();
            inputdata.old_trace = param.Str1;
            var ret = BankTrans(inputdata);
            return ret;
        }

        /// <summary>
        /// 退货
        /// </summary>
        /// <param name="balance">退货金额</param>
        /// <param name="tranlateDate">交易日期YYYYMMDD格式</param>
        /// <param name="translateCode">交易参考号</param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> Refund(UnionPayInput2 param)
        {
            InputData inputdata = new InputData(POSTranslateCode.REFUND);
            inputdata.amount = Math.Round(param.Number * 100m).ToString();
            inputdata.old_date = param.Str1;
            inputdata.old_reference = param.Str2;

            var ret = BankTrans(inputdata);
            if (ret.Success && ret.Value.amount / 100m != param.Number)
            {
                ret = new ServiceResult<POSReturn>(false, InnerError,
                    string.Format("POS机退费金额与应退金额不一致！{0}应为{1}元，现为{2}元",
                    Environment.NewLine, param.Number, ret.Value.amount / 100m));
            }
            return ret;
        }

        /// <summary>
        /// 扫码退货
        /// </summary>
        /// <param name="balance">退货金额</param>
        /// <param name="translateCode">订单号</param>
        /// <returns></returns>
        private static ServiceResult<POSReturn> SweepRefund(UnionPayInput1 param)
        {
            InputData inputdata = new InputData(POSTranslateCode.SWEEPREFUND);
            inputdata.amount = Math.Round(param.Number * 100m).ToString();
            inputdata.szQRCode = param.Str1;
            var ret = BankTrans(inputdata);
            if (ret.Success && ret.Value.amount / 100m != param.Number)
            {
                ret = new ServiceResult<POSReturn>(false, InnerError,
                    string.Format("POS机退费金额与应退金额不一致！{0}应为{1}元，现为{2}元",
                    Environment.NewLine, param.Number, ret.Value.amount / 100m));
            }
            return ret;
        }

        /// <summary>
        /// 查询余额
        /// </summary>
        /// <returns></returns>
        private static ServiceResult QueryBalance()
        {
            InputData inputdata = new InputData(POSTranslateCode.QUERYBALANCE);

            return BankTrans(inputdata);
        }

        /// <summary>
        /// 重打印
        /// </summary>
        /// <param name="translateSerialCode">六个0代表最后一笔</param>
        /// <returns></returns>
        private static ServiceResult RePrint(string translateSerialCode)
        {
            InputData inputdata = new InputData(POSTranslateCode.REPRINT);
            inputdata.old_trace = translateSerialCode;
            return BankTrans(inputdata);
        }


        /// <summary>
        /// 打印扫码支付的异常订单
        /// </summary>
        /// <param name="serialCode">为空则打印所有异常订单</param>
        /// <returns></returns>
        private static ServiceResult SweepQueryInfo(string serialCode)
        {
            InputData inputdata;
            if (!string.IsNullOrEmpty(serialCode))
            {
                inputdata = new InputData(POSTranslateCode.SWEEPQUERYINFO);
                inputdata.szQRCode = serialCode;
            }
            else
            {
                inputdata = new InputData(POSTranslateCode.SWEEPQUERYINFOS);
            }

            return BankTrans(inputdata);
        }


        /// <summary>
        /// 银行卡交易结算
        /// </summary>
        /// <returns></returns>
        public ServiceResult BankSettle()
        {
            return Settle();
        }

        /// <summary>
        /// 签到
        /// </summary>
        /// <returns></returns>
        public ServiceResult BankReg()
        {
            return SignIn();
        }

        /// <summary>
        /// 重打印
        /// </summary>
        /// <param name="translateSerialCode">交易流水号,000000代表最后一笔</param>
        /// <returns></returns>
        public ServiceResult BankReprint(string translateSerialCode)
        {
            return RePrint(translateSerialCode);
        }

        /// <summary>
        /// 打印扫码支付的异常订单
        /// </summary>
        /// <param name="translateSerialCode">空位打印所有异常订单</param>
        /// <returns></returns>
        public ServiceResult BankSweepQueryInfo(string serialCode)
        {
            return SweepQueryInfo(serialCode);
        }

        /// <summary>
        /// 查询余额
        /// </summary>
        /// <param name="translateSerialCode"></param>
        /// <returns></returns>
        public ServiceResult BankQuery()
        {
            return QueryBalance();
        }

        /// <summary>
        /// 消费
        /// </summary>
        /// <param name="payerBalance">消费金额，单位为元</param>
        /// <param name="codePayMode">消费附属信息</param>
        /// <returns></returns>
        public ServiceResult<POSReturn> Consume(POSConsumeParam paramPay)
        {
            ServiceResult<POSReturn> ret = null;
            if (paramPay.dtPayMode == Constant.PayWays.Ali || paramPay.dtPayMode == Constant.PayWays.WeChat)
            {
                ret = SweepConsume(new UnionPayInput1() { Number = paramPay.payBalance, Str1 = paramPay.sweepCode });

            }
            else
            {
                if (paramPay.dtPayMode == Constant.PayWays.BankCard)
                {
                    ret = Consume(paramPay.payBalance);
                }
                else
                {
                    ret = new ServiceResult<POSReturn>(false, InnerError, "支付方式不支持！");
                }
            }
            return ret;
        }

        /// <summary>
        /// 当业务失败时，使用消费参数退款
        /// </summary>
        /// <param name="consumeResult"></param>
        /// <returns></returns>
        public ServiceResult<POSReturn> Refund(POSReturn consumeResult)
        {
            ServiceResult<POSReturn> resultRefund = null;
            if (consumeResult != null)
            {
                if (consumeResult.dtPayMode == Constant.PayWays.Ali || consumeResult.dtPayMode == Constant.PayWays.WeChat)
                {
                    resultRefund = SweepRefund(new UnionPayInput1() { Number = consumeResult.amount, Str1 = consumeResult.szOrderTrace });
                }
                else
                {
                    resultRefund = CancelConsume(new UnionPayInput1() { Number = consumeResult.amount, Str1 = consumeResult.trace });
                }
                return resultRefund;
            }
            else
            {
                resultRefund = new ServiceResult<POSReturn>(false, InnerError, "自动退款执行失败，消费参数为空！");
            }
            return resultRefund;
        }

        /// <summary>
        /// 退款
        /// </summary>
        /// <param name="refundBalance">退款金额，单位为元</param>
        /// <param name="paramRefund">退款附加信息</param>
        /// <returns></returns>
        public ServiceResult<POSReturn> Refund(decimal refundBalance, POSReturn paramRefund)
        {
            if (paramRefund == null)
            {
                return new ServiceResult<POSReturn>(false, InnerError, "退款信息参数为空");
            }

            string refundType = null;
            refundBalance = Math.Abs(refundBalance);

            #region 获取退费方式
            if (paramRefund.dtPayMode == Constant.PayWays.Ali || paramRefund.dtPayMode == Constant.PayWays.WeChat)
            {
                refundType = POSTranslateCode.SWEEPREFUND;
            }
            else
            {
                if (paramRefund.settleStatus == Constant.POSSettleStatus.Settled)
                {
                    refundType = POSTranslateCode.REFUND;
                }
                if (paramRefund.settleStatus == Constant.POSSettleStatus.NotSettle)
                {
                    refundType = POSTranslateCode.CANCELCONSUME;
                }
            }
            #endregion

            ServiceResult<POSReturn> retRefund = null;
            //银行卡取消交易退款，不需要传递金额
            if ((POSTranslateCode.CANCELCONSUME == refundType) || (POSTranslateCode.CANCELCONSUME != refundType && refundBalance != 0))
            {
                switch (refundType)
                {
                    case POSTranslateCode.CANCELCONSUME:
                        retRefund = CancelConsume(new UnionPayInput1() { Number = refundBalance, Str1 = paramRefund.trace });
                        break;
                    case POSTranslateCode.REFUND:
                        retRefund = Refund(new UnionPayInput2() { Number = refundBalance, Str1 = paramRefund.date.ToString(Constant.POSDateFormat), Str2 = paramRefund.refer });
                        break;
                    case POSTranslateCode.SWEEPREFUND:
                        retRefund = SweepRefund(new UnionPayInput1() { Number = refundBalance, Str1 = paramRefund.szOrderTrace });
                        break;
                }
                if (retRefund.Success)
                {
                    retRefund.Value.dtPayMode = paramRefund.dtPayMode;
                }
            }
            else
            {
                retRefund = new ServiceResult<POSReturn>(false, InnerError, "POS机退费金额异常！退费金额为0元");
            }

            return retRefund;
        }

        /// <summary>
        /// 校验预结算退款参数
        /// </summary>
        /// <param name="refundParam">预结算退款参数</param>
        /// <returns></returns>
        public ServiceResult ValidateRefundData(decimal refundBalance, POSReturn refundParam)
        {
            if (refundParam == null)
            {
                return new ServiceResult(false, InnerError, "无法进行退款！退款参数为空");
            }

            if (refundBalance == 0)
            {
                return new ServiceResult(false, InnerError, "无法进行退款！退款金额为0元");
            }

            if (refundParam.amount == 0m)
            {
                return new ServiceResult(false, InnerError, "无法进行退款！原消费订单可退金额为0元");
            }

            if (Math.Abs(refundBalance) > Math.Round(refundParam.amount))
            {
                return new ServiceResult(false, InnerError, "无法进行退款！退款金额大于交款订单可退金额");
            }

            //POS机的微信支付宝扫码支付
            if (refundParam.dtPayMode == Constant.PayWays.Ali || refundParam.dtPayMode == Constant.PayWays.WeChat)
            {
                if (string.IsNullOrEmpty(refundParam.szOrderTrace))
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机微信支付宝退款！收银流水号为空");
                }
            }
            else
            {
                //退款只能针对同一种支付方式
                if (refundParam.dtPayMode != Constant.PayWays.BankCard)
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机退款！退款订单支付类型不支持");
                }

                if (string.IsNullOrEmpty(refundParam.refer))
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机退款！交易参考号为空");
                }
                if (string.IsNullOrEmpty(refundParam.trace))
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机退款！银行流水号为空");
                }

                if (string.IsNullOrEmpty(refundParam.settleStatus))
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机退款！银联结算状态为空");
                }
                if (string.IsNullOrEmpty(refundParam.terno))
                {
                    return new ServiceResult(false, InnerError, "无法进行POS机退款！银联收款POS机编号为空");
                }

                if (refundParam.refer.Length > 20)
                {
                    return new ServiceResult(false, InnerError, string.Format("无法进行POS机退款！交易参考号长度异常（{0}）\r\n请联系管理员查看问题", refundParam.refer));
                }
                if (refundParam.trace.Length > 6)
                {
                    return new ServiceResult(false, InnerError, string.Format("无法进行POS机退款！银行流水号长度异常（{0}）\r\n请联系管理员查看问题", refundParam.trace));
                }

                if (refundParam.settleStatus == Constant.POSSettleStatus.Settled)
                {
                    if (refundParam.date.AddDays(30) < DateTime.Now)
                    {
                        return new ServiceResult(false, InnerError, "退款订单已过期！退货订单离消费日期不能超过30天");
                    }
                }
                else
                {
                    if (refundParam.settleStatus == Constant.POSSettleStatus.NotSettle)
                    {
                        if (Math.Abs(refundParam.amount) != Math.Abs(refundBalance))
                        {
                            return new ServiceResult(false, InnerError, "POS机消费未结算，部分退费只能在POS机结算后退费");
                        }
                        else
                        {
                            if (MisPOSPayer.Configuration.Application == null || string.IsNullOrEmpty(MisPOSPayer.Configuration.Application.LocalPOSID))
                            {
                                return new ServiceResult(false, InnerError, "POS机本地配置为空，请检查本地配置");
                            }

                            if (refundParam.terno != MisPOSPayer.Configuration.Application.LocalPOSID)
                            {
                                return new ServiceResult(false, InnerError
                                    , string.Format("撤销交易只能在消费POS机上,请在原消费窗口撤销或在原消费窗口POS机结算后退费。\r\n原消费POS机编号为{0}", refundParam.terno));
                            }
                            else
                            {
                                if (!refundParam.date.Date.Equals(DateTime.Now.Date))
                                {
                                    return new ServiceResult(false, InnerError, "POS机撤销退款，只能针对当天的交易！请结算后再进行退款");
                                }
                            }
                        }
                    }
                }
            }
            return new ServiceResult();
        }
    }
}
