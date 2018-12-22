using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Services
{
    /// <summary>
    /// 用于标识方法调用的交易号
    /// </summary>
    [AttributeUsage(AttributeTargets.Method)]
    public class TransactionCodeAttribute : Attribute
    {
        public TransactionCodeAttribute(string sysName,string transactionCode)
        {
            this.TransactionCode = transactionCode;
            this.SysName = sysName;
        }


        /// <summary>
        /// 外部系统名称
        /// </summary>
        public string SysName { get; private set; }

        /// <summary>
        /// 参数交易号
        /// </summary>
        public string TransactionCode { get; private set; }
    }
}
