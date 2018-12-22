using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Validation
{
    public class MaxWordsValidator : Validator
    {
        int maxWordsLength;
        public MaxWordsValidator(string messageTemplate,int wordLength) : base(messageTemplate)
        {
            maxWordsLength = wordLength;
        }

        protected override string DefaultMessageTemplate
        {
            get { return string.Format("文本最大长度为{0}！", maxWordsLength); }
        }
        protected override bool ValidateCore(object target, ref string message)
        {
            if (target == null) return true;
            if (target.ToString() == string.Empty) return true;
            if (target.ToString().Length > maxWordsLength)
            {
                message = GetMessage();
                return false;
            }
            return true;
        }
    }
}
