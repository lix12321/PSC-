using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;
using Newtonsoft.Json;

namespace Wellcare.PCS.ClientPortal.Utils
{
    class Utils
    {
        public static T JsonDeserialize<T>(string data)
        {
            var setting = new JsonSerializerSettings();
            setting.DateFormatString = "yyyyMMddHHmmss";
            setting.NullValueHandling = NullValueHandling.Ignore;

            return JsonConvert.DeserializeObject<T>(data, setting);
        }

        public static string JsonSerialize(object data)
        {
            var setting = new JsonSerializerSettings();
            setting.DateFormatString = "yyyyMMddHHmmss";
            return JsonConvert.SerializeObject(data, setting);
        }
    }
}
