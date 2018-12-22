using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Zebone.JavaService
{
    public class CustomDataTableConverter : DataTableConverter
    {
        public override bool CanConvert(Type valueType)
        {
            return valueType == typeof(DataTable);
        }

        public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
        {
            DataTable dt;

            if (reader.TokenType == JsonToken.PropertyName)
            {
                dt = new DataTable((string)reader.Value);
                reader.Read();
            }
            else
            {
                dt = new DataTable();
            }

            reader.Read();

            while (reader.TokenType == JsonToken.StartObject)
            {
                DataRow dr = dt.NewRow();
                reader.Read();

                while (reader.TokenType == JsonToken.PropertyName)
                {
                    string columnName = (string)reader.Value;

                    reader.Read();

                    if (!dt.Columns.Contains(columnName))
                    {
                        Type columnType = GetColumnDataType(reader.TokenType);
                        dt.Columns.Add(new DataColumn(columnName, columnType));
                    }

                    dr[columnName] = reader.Value ?? DBNull.Value;
                    reader.Read();
                }

                dr.EndEdit();
                dt.Rows.Add(dr);

                reader.Read();
            }

            return dt;
        }

        private static Type GetColumnDataType(JsonToken tokenType)
        {
            switch (tokenType)
            {
                case JsonToken.Integer:
                    //若json返回的数据中，某一列的第一个值为整型，后续的值为浮点型，在转换成DataTable时，该列会被处理成整型列，造成后续行的数据变成了整型，造成精度丢失。
                    //这里通过将整型转换成decimal类型解决该问题
                    //return typeof(int);
                    return typeof(decimal);  
                case JsonToken.Float:
                    return typeof(double);
                case JsonToken.String:
                case JsonToken.Null:
                case JsonToken.Undefined:
                    return typeof(string);
                case JsonToken.Boolean:
                    return typeof(bool);
                case JsonToken.Date:
                    return typeof(DateTime);
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }
}
