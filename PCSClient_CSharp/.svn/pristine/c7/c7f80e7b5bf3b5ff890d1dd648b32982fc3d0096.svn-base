using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.MisPOSPayer.Models
{
    class InputData
    {
        public string posid;		/* 收银机号(8字节,必填，左对齐,不足部分补空格)*/
        public char[] posidBytes
        {
            get
            {
                char[] temp = new string(' ', 8).ToArray();
                copyArrayToArrayBottom(posid.ToArray(), temp);
                return temp;
            }
        }

        public string operid; 	/* 操作员号(8字节,必填，左对齐,不足部分补空格)*/
        public char[] operidBytes
        {
            get
            {
                char[] temp = new string(' ', 8).ToArray();
                copyArrayToArrayBottom(operid.ToArray(), temp);
                return temp;
            }
        }

        public string trans;		/* 交易类型: '00'消费'01'撤销'02'退货'03'查余 '04'重打印'05'签到'06'结算’61’银行卡签约	*/
        public char[] transBytes
        {
            get
            {
                char[] temp = new string(' ', 2).ToArray();
                copyArrayToArrayBottom(trans.ToArray(), temp);
                return temp;
            }
        }

        public string amount;	/* 金额(12字节,无小数点,左补0,单位:分)*/
        public char[] amountBytes
        {
            get
            {
                char[] temp = new string('0', 12).ToArray();
                copyArrayToArrayBottom(amount.ToArray(), temp);
                return temp;
            }
        }

        public string old_date;	/* 原交易日期(8字节,必填，yyyymmdd格式,隔日退货时用)*/
        public char[] old_dateBytes
        {
            get
            {
                char[] temp = new string(' ', 8).ToArray();
                copyArrayToArrayBottom(old_date.Length > 8 ? old_date.Substring(0, 8).ToArray() : old_date.ToArray(), temp);
                return temp;
            }
            set
            {
                copyArrayToArrayBottom(value.ToArray(), old_dateBytes);
            }
        }

        public string old_reference; /* 原交易参考号（撤销或退货原消费的流水号，撤销或退货必填）*/
        public char[] old_referenceBytes
        {
            get
            {
                char[] temp = new string(' ', 12).ToArray();
                copyArrayToArrayBottom(old_reference.ToArray(), temp);
                return temp;
            }
        }

        public string old_trace;	/* 流水号(6字节,右对齐,左补0,退货或重打印等用)，必填*/
        public char[] old_traceBytes
        {
            get
            {
                char[] temp = new string('0', 6).ToArray();
                copyArrayToArrayBottom(old_trace.ToArray(), temp);
                return temp;
            }
        }

        public string szOrderTrace;	// 收银流水(订单)号,HIS唯一流水号
        public char[] szOrderTraceBytes
        {
            get
            {
                char[] temp = new string(' ', 20).ToArray();
                copyArrayToArrayBottom(szOrderTrace.ToArray(), temp);
                return temp;
            }
        }

        public string opername; 	/* 操作员姓名*/
        public char[] opernameBytes
        {
            get
            {
                char[] temp = new string(' ', 20).ToArray();
                copyArrayToArrayBottom(opername.ToArray(), temp);
                return temp;
            }
        }


        public string CredentialsType; /*证件类型 15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；*/
        public char[] CredentialsTypeBytes
        {
            get
            {
                char[] temp = new string(' ', 2).ToArray();
                copyArrayToArrayBottom(CredentialsType.ToArray(), temp);
                return temp;
            }
        }

        public string CredentialsNum;  /*证件号码*/
        public char[] CredentialsNumBytes
        {
            get
            {
                char[] temp = new string(' ', 40).ToArray();
                copyArrayToArrayBottom(CredentialsNum.ToArray(), temp);
                return temp;
            }
        }

        public string Name;       /*客户姓名*/
        public char[] NameBytes
        {
            get
            {
                char[] temp = new string(' ', 40).ToArray();
                copyArrayToArrayBottom(Name.ToArray(), temp);
                return temp;
            }
        }

        public string Sex;         /*客户性别 0-男性；1-女性；2-不详*/
        public char[] SexBytes
        {
            get
            {
                char[] temp = new string(' ', 1).ToArray();
                copyArrayToArrayBottom(Name.ToArray(), temp);
                return temp;
            }
        }

        public string PhoneNum;   /*客户手机号*/
        public char[] PhoneNumBytes
        {
            get
            {
                char[] temp = new string(' ', 11).ToArray();
                copyArrayToArrayBottom(Name.ToArray(), temp);
                return temp;
            }
        }

        public string Nation;      /*客户民族*/
        public char[] NationBytes
        {
            get
            {
                char[] temp = new string(' ', 4).ToArray();
                copyArrayToArrayBottom(Name.ToArray(), temp);
                return temp;
            }
        }

        public string birthDay;	 /*客户出生日期*/
        public char[] birthDayBytes
        {
            get
            {
                char[] temp = new string(' ', 8).ToArray();
                copyArrayToArrayBottom(birthDay.ToArray(), temp);
                return temp;
            }
        }

        public string age;		/*客户年龄*/
        public char[] ageBytes
        {
            get
            {
                char[] temp = new string(' ', 4).ToArray();
                copyArrayToArrayBottom(age.ToArray(), temp);
                return temp;
            }
        }

        public string Address;     /*客户家庭住址 （可能为空）*/
        public char[] AddressBytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(Address.ToArray(), temp);
                return temp;
            }
        }

        public string szRsv;	// 保留字段 
        public char[] szRsvBytes
        {
            get
            {
                char[] temp = new string(' ', 500).ToArray();
                copyArrayToArrayBottom(szRsv.ToArray(), temp);
                return temp;
            }
        }

        public string szExtend1;	// 保留字段 
        public char[] szExtend1Bytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(szExtend1.ToArray(), temp);
                return temp;
            }
        }

        public string szExtend2;	// 保留字段
        public char[] szExtend2Bytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(szExtend2.ToArray(), temp);
                return temp;
            }
        }

        public string szExtend3;	// 保留字段
        public char[] szExtend3Bytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(szExtend3.ToArray(), temp);
                return temp;
            }
        }

        public string szExtend4;	// 保留字段
        public char[] szExtend4Bytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(szExtend4.ToArray(), temp);
                return temp;
            }
        }

        public string szExtend5;	// 保留字段
        public char[] szExtend5Bytes
        {
            get
            {
                char[] temp = new string(' ', 100).ToArray();
                copyArrayToArrayBottom(szExtend5.ToArray(), temp);
                return temp;
            }
        }

        public string szPrefer;	// 优惠券
        public char[] szPreferBytes
        {
            get
            {
                char[] temp = new string(' ', 50).ToArray();
                copyArrayToArrayBottom(szPrefer.ToArray(), temp);
                return temp;
            }
        }

        public string szQRCode;	//消费时传入二维码的条码数据,退货时传入退货的订单号
        public char[] szQRCodeBytes
        {
            get
            {
                char[] temp = new string(' ', 300).ToArray();
                copyAyyayToArrayTop(szQRCode.ToArray(), temp);
                return temp;
            }
        }

        /// <summary>
        /// 填充
        /// </summary>
        /// <param name="scr"></param>
        /// <param name="des"></param>
        private void copyArrayToArrayBottom(char[] scr, char[] des)
        {
            Array.Copy(scr, 0, des, des.Length - scr.Length, scr.Length);
        }

        private void copyAyyayToArrayTop(char[] scr, char[] des)
        {
            Array.Copy(scr, 0, des, 0, scr.Length);
        }

        public InputData(string translatecode)
        {
            posid = string.Empty;
            operid = string.Empty;
            trans = string.Empty;
            amount = string.Empty;
            old_date = string.Empty;
            old_reference = string.Empty;
            old_trace = string.Empty;
            szOrderTrace = string.Empty;
            opername = string.Empty;
            CredentialsType = string.Empty;
            CredentialsNum = string.Empty;
            Name = string.Empty;
            Sex = string.Empty;
            PhoneNum = string.Empty;
            Nation = string.Empty;
            birthDay = string.Empty;
            age = string.Empty;
            Address = string.Empty;
            szRsv = string.Empty;
            szExtend1 = string.Empty;
            szExtend2 = string.Empty;
            szExtend3 = string.Empty;
            szExtend4 = string.Empty;
            szExtend5 = string.Empty;
            szPrefer = string.Empty;
            szQRCode = string.Empty;

            trans = translatecode;
        }

        public static byte[] Serlize(InputData serlizeObj)
        {
            try
            {
                char seprateChar = '|';
                StringBuilder sb = new StringBuilder();
                sb.Append(serlizeObj.posidBytes).Append(seprateChar);
                sb.Append(serlizeObj.operidBytes).Append(seprateChar);
                sb.Append(serlizeObj.transBytes).Append(seprateChar);
                sb.Append(serlizeObj.amountBytes).Append(seprateChar);
                sb.Append(serlizeObj.old_dateBytes).Append(seprateChar);
                sb.Append(serlizeObj.old_referenceBytes).Append(seprateChar);
                sb.Append(serlizeObj.old_traceBytes).Append(seprateChar);
                sb.Append(serlizeObj.szOrderTraceBytes).Append(seprateChar);
                sb.Append(serlizeObj.opernameBytes).Append(seprateChar);
                sb.Append(serlizeObj.CredentialsTypeBytes).Append(seprateChar);
                sb.Append(serlizeObj.CredentialsNumBytes).Append(seprateChar);
                sb.Append(serlizeObj.NameBytes).Append(seprateChar);
                sb.Append(serlizeObj.SexBytes).Append(seprateChar);
                sb.Append(serlizeObj.PhoneNumBytes).Append(seprateChar);
                sb.Append(serlizeObj.NationBytes).Append(seprateChar);
                sb.Append(serlizeObj.birthDayBytes).Append(seprateChar);
                sb.Append(serlizeObj.ageBytes).Append(seprateChar);
                sb.Append(serlizeObj.AddressBytes).Append(seprateChar);
                sb.Append(serlizeObj.szRsvBytes).Append(seprateChar);
                sb.Append(serlizeObj.szExtend1Bytes).Append(seprateChar);
                sb.Append(serlizeObj.szExtend2Bytes).Append(seprateChar);
                sb.Append(serlizeObj.szExtend3Bytes).Append(seprateChar);
                sb.Append(serlizeObj.szExtend4Bytes).Append(seprateChar);
                sb.Append(serlizeObj.szExtend5Bytes).Append(seprateChar);
                sb.Append(serlizeObj.szPreferBytes).Append(seprateChar);
                sb.Append(serlizeObj.szQRCodeBytes).Append(seprateChar);

                //POS汉字编码库为GBK
                Encoding gbk = Encoding.GetEncoding("GB18030");
                return gbk.GetBytes(sb.ToString());
            }
            catch (Exception ex)
            {
                throw new POSSerlizeException(ex);
            }
        }
    }

    class POSSerlizeException : Exception
    {
        static readonly string ErrorMsg = "构造入参异常";
        public POSSerlizeException(Exception ex) : base(ErrorMsg, ex) { }
    }
}
