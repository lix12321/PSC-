using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json.Serialization;

namespace Zebone.JavaService
{
    public class ZeboneContractResolver : DefaultContractResolver
    {
        public ZeboneContractResolver() : base() { }

        public ZeboneContractResolver(bool shareCache) : base(shareCache) { }

        protected override string ResolvePropertyName(string propertyName)
        {
            if (string.IsNullOrEmpty(propertyName)) return propertyName;

            var result = string.Empty;

            //将首字母小写
            if (propertyName[0] >= 'A' && propertyName[0] <= 'Z')
                return propertyName[0].ToString().ToLowerInvariant() + propertyName.Substring(1);
            else return propertyName;
        }
    }
}
