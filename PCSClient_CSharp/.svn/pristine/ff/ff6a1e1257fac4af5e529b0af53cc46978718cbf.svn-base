using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Wellcare.PCS.MisPOSPayer.Models
{
    class OutPutData
    {
        /*1～2位	返回码	(2字节，"00"交易成功,其他失败)*/
        public string resp_code;
        /*3～6位	银行行号（4字节） 可为4个空格   */
        public string bank_code;
        /*7～36位	卡号:6222809999999994860  */
        public string card_no;
        /*37～40位	有效期	(4字节)  可为4个空格*/
        public string expr;

        /*141～52位  金额（12字节，无小数点，左补0，单位：分）*/
        public string amount;
        /*53～58位  POS流水号  (6字节，左对齐)*/
        public string trace;
        /*59～70位	POS交易参考号*/
        public string refer;
        /*71～76位  POS授权号*/
        public string auth;
        /*77～82位  POS批次号*/
        public string batch;
        /*83～96位	交易日期（14字节,yyyymmddhhmmss格式）*/
        public string date;

        /*97～111位	商户号*/
        public string userno;
        /*112～119位	终端号*/
        public string terno;

        /*120～127位	原终端号*/
        public string old_terno;


        // 收银流水(订单)号,HIS唯一流水号
        public string szOrderTrace;

        /*  错误说明(左对齐，不足部分补空格)*/
        public string resp_chin;


        /*证件类型 15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；*/
        public string CredentialsType;

        /*证件号码*/
        public string CredentialsNum;
        /*客户姓名*/
        public string Name;
        /*客户性别 0-男性；1-女性；2-不详*/
        public string Sex;
        /*客户出生日期*/
        public string birthDay;
        /*客户年龄*/
        public string age;
        /*客户家庭住址 （可能为空）*/
        public string Address;
        public string PhoneNum;
        /*客户民族*/
        public string Nation;
        // 保留字段 
        public string szExtend1;

        // 保留字段
        public string szExtend2;

        // 保留字段
        public string szExtend3;

        // 保留字段
        public string szExtend4;

        // 保留字段
        public string szExtend5;

        // 二维码订单号
        public string OrderNo;

        public static bool Deserlize(byte[] datas, ref OutPutData outPutData)
        {
            try
            {
                //POS汉字编码库为GBK
                Encoding gbk = Encoding.GetEncoding("GB18030");
                string outStr = new string(gbk.GetChars(datas));
                string[] outStrs = outStr.Split('|');

                if (outStrs.Length == 31)
                {
                    //去除返回字段的前补0
                    for (int i = 0; i < outStrs.Length; i++)
                    {
                        outStrs[i] = outStrs[i].Trim(new char[] { ' ' });
                    }
                    outPutData = new OutPutData();
                    outPutData.resp_code = outStrs[0];
                    outPutData.bank_code = outStrs[1];
                    outPutData.card_no = outStrs[2];
                    outPutData.expr = outStrs[3];
                    outPutData.amount = outStrs[4];
                    outPutData.trace = outStrs[5];
                    outPutData.refer = outStrs[6];
                    outPutData.auth = outStrs[7];
                    outPutData.batch = outStrs[8];
                    outPutData.date = outStrs[9];
                    outPutData.userno = outStrs[10];
                    outPutData.terno = outStrs[11];
                    outPutData.old_terno = outStrs[12];
                    outPutData.szOrderTrace = outStrs[13];
                    outPutData.resp_chin = outStrs[14];
                    outPutData.CredentialsType = outStrs[15];
                    outPutData.CredentialsNum = outStrs[16];
                    outPutData.Name = outStrs[17];
                    outPutData.Sex = outStrs[18];
                    outPutData.birthDay = outStrs[19];
                    outPutData.age = outStrs[20];
                    outPutData.Address = outStrs[21];
                    outPutData.PhoneNum = outStrs[22];
                    outPutData.Nation = outStrs[23];
                    outPutData.szExtend1 = outStrs[24];
                    outPutData.szExtend2 = outStrs[25];
                    outPutData.szExtend3 = outStrs[26];
                    outPutData.szExtend4 = outStrs[27];
                    outPutData.szExtend5 = outStrs[28];
                    outPutData.OrderNo = outStrs[29];
                }
                else
                {
                    throw new Exception(string.Format("POS机返回值长度异常！现长度为{0},应为31", outStrs.Length));
                }
                return true;
            }
            catch (Exception ex)
            {
                throw new POSDerlizeException(ex);
            }
        }
    }

    class POSDerlizeException : Exception
    {
        static readonly string ErrorMsg = "反序列化出参异常";
        public POSDerlizeException(Exception ex) : base(ErrorMsg, ex) { }
    }
}
