using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Newtonsoft.Json;

namespace Wellcare.PCS.MisPOSPayer.Configuration
{
    class ConfigurationManager
    {
        private const string ApplicationConfigFileName = "ConfigMisPOSPayer.json";

        /// <summary>
        /// 获取应用程序相关配置信息
        /// </summary>
        public AppConfiguration Application { get; private set; }

        internal ConfigurationManager()
        {
            this.Application = new AppConfiguration();
        }

        /// <summary>
        /// 加载配置信息
        /// </summary>
        internal void Load()
        {
            LoadApplicationConfiguration();
        }

        /// <summary>
        /// 保存配置信息
        /// </summary>
        internal void Save()
        {
        }

        /// <summary>
        /// 加载应用程序配置信息
        /// </summary>
        private void LoadApplicationConfiguration()
        {
            var fileName = Path.Combine(Path.GetDirectoryName(this.GetType().Assembly.Location), ApplicationConfigFileName);
            if (File.Exists(fileName))
            {
                this.Application = JsonConvert.DeserializeObject<AppConfiguration>(File.ReadAllText(fileName));
            }
        }
    }
}
