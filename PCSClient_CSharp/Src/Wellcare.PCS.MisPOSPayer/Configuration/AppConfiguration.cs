using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;

namespace Wellcare.PCS.MisPOSPayer.Configuration
{
    class AppConfiguration
    {
        internal AppConfiguration()
        {
            this.Services = new ServiceRegistrationCollection();
            this.ProductName = "西安佑君HIS系统 V1.0";
        }

        /// <summary>
        /// 获取或设置产品名称
        /// </summary>
        public string ProductName { get; set; }

        /// <summary>
        /// 获取或设置自动更新服务地址
        /// </summary>
        public string AutoUpdateUrl { get; set; }

        /// <summary>
        /// 获取或设置应用服务地址
        /// </summary>
        public string ServiceUrl { get; set; }

        /// <summary>
        /// 获取或设置应用服务提供程序
        /// </summary>
        public string ServiceProvider { get; set; }

        /// <summary>
        /// 获取或设置消息服务地址
        /// </summary>
        public string MessageServiceUrl { get; set; }

        /// <summary>
        /// 获取或设置病历归档文件存放目录
        /// </summary>
        public string MedicalRecordArchivePath { get; set; }

        public string MessageServiceUser { get; set; }

        public string MessageServicePwd { get; set; }

        /// <summary>
        /// 获取或设置病历归档路径服务地址
        /// </summary>
        public string MedicalRecordArchiveIP { get; set; }

        /// <summary>
        /// 获取或设置病历归档路径服务地址
        /// </summary>
        public string MedicalRecordArchiveUser { get; set; }

        /// <summary>
        /// 获取或设置病历归档路径服务地址
        /// </summary>
        public string MedicalRecordArchivePwd { get; set; }

        /// <summary>
        /// 归档纸质文件扫面路径
        /// </summary>
        public string ArchiveImagePaths { get; set; }

        /// <summary>
        /// 获取外部接口服务配置信息
        /// </summary>
        public ServiceRegistrationCollection Services { get; private set; }

        /// <summary>
        /// PAGS的exe路径
        /// </summary>
        public string PAGSProcessPath { get; set; }

        /// <summary>
        /// 获取或设置本地POS机的编号
        /// </summary>
        public string LocalPOSID { get; set; }

        /// <summary>
        /// 获取或设置心电报告查看地址
        /// </summary>
        public string ECGViewReportUrl { get; set; }

        /// <summary>
        /// 是否允许点击LOGO，返回首页
        /// </summary>
        public bool ClickLogoToHome { get; set; }

        /// <summary>
        /// 获取或设置应用分类代码
        /// </summary>
        public string AbuTypeCode { get; set; }

        public List<string> ConstDept { get; set; }

        /// <summary>
        /// 获取或设置 灵璧农合主键
        /// </summary>
        public string PkInsuLBNH { get; set; }
    }
}
