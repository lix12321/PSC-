using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

using Zebone.Common;

namespace Zebone.JavaService
{
    public class EnumConverter : StringEnumConverter
    {
        private static Dictionary<Type, EnumDescriptor> mappings = new Dictionary<Type, EnumDescriptor>();

        public override void WriteJson(JsonWriter writer, object value, JsonSerializer serializer)
        {
            //将枚举值转换成对应的编码
            if (value != null)
            {
                var enumType = value.GetType();
                if (enumType.IsGenericType && enumType.GetGenericTypeDefinition() == typeof(Nullable<>))
                {
                    enumType = enumType.GetGenericArguments()[0];
                }
                BuildMappings(enumType);
                if (mappings.ContainsKey(enumType))
                {
                    var result = mappings[enumType].Items.FirstOrDefault(m => m.Value.Equals(value));
                    if (result != null)
                    {
                        writer.WriteValue(result.Code);
                        return;
                    }
                }
            }

            base.WriteJson(writer, value, serializer);
        }

        public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
        {
            //将枚举对应的编码转换成枚举值
            if (reader.Value != null)
            {
                if (objectType.IsGenericType && objectType.GetGenericTypeDefinition() == typeof(Nullable<>))
                {
                    objectType = objectType.GetGenericArguments()[0];
                }
                BuildMappings(objectType);

                if (mappings.ContainsKey(objectType))
                {
                    var result = mappings[objectType].Items.FirstOrDefault(m => m.Code == reader.Value.ToString());
                    if (result != null) return result.Value;
                }
            }

            return base.ReadJson(reader, objectType, existingValue, serializer);
        }

        private void BuildMappings(Type enumType)
        {
            if (mappings.ContainsKey(enumType)) return;            
            mappings.Add(enumType, EnumUtils.GetDescriptor(enumType));
        }
    }
}
