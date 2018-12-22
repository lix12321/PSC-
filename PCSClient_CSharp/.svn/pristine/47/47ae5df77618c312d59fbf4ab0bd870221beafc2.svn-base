using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Validation
{
    public class MobileValidator : Validator
    {
        public MobileValidator(string messageTemplate) : base(messageTemplate) { }

        protected override string DefaultMessageTemplate
        {
            get { return "请输入有效的手机号码！"; }
        }
        protected override bool ValidateCore(object target, ref string message)
        {
            if (target == null) return true;
            if (target.ToString() == string.Empty) return true;
            //if (!System.Text.RegularExpressions.Regex.IsMatch(target.ToString(), @"^[1]+[3,5,8]+\d{9}"))
            if (!System.Text.RegularExpressions.Regex.IsMatch(target.ToString(), @"^1[0-9]{10}$"))
            {
                message = GetMessage();
                return false;
            }
            return true;
        }
    }
}
