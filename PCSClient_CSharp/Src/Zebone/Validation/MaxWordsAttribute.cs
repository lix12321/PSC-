using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Zebone.Validation
{
    public class MaxWordsAttribute : ValidateAttribute
    {
        int maxWordsLength = 8;
        public int WordsLength
        {
            set { maxWordsLength = value; }
            get { return maxWordsLength; }
        }

        public MaxWordsAttribute() : base() { }

        public MaxWordsAttribute(string messageTemplate) : base(messageTemplate) { }

        public override Validator CreateValidator()
        {
            return new MaxWordsValidator(this.MessageTemplate, maxWordsLength);
        }
    }
}
