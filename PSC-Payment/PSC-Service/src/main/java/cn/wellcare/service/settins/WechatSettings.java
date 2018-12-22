package cn.wellcare.service.settins;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.entity.payset.PaySettingWechat;
import cn.wellcare.model.modules.payset.PaySettingWechatModel;
import cn.wellcare.pojo.wechat.WXPayConfig;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WechatSettings implements WXPayConfig {
	private Logger log = Logger.getLogger(this.getClass());

	private byte[] certData;
	private String appID;
	private String mchID;
	private String key;
	private int httpConnectTimeoutMs;
	private int httpReadTimeoutMs;
	private boolean sandboxnewOpen;
	@Resource
	private PaySettingWechatModel paySettingWechatModel;

	public void init(Integer orgId) {
		log.debug("初始化微信配置。。。");
		if (orgId == null) {
			log.error("没有机构，无法获取设置信息");
		}

		PaySettingWechat setting = paySettingWechatModel.getByOrg(orgId);

		String certPath = "apiclient_cert.p12";
		this.sandboxnewOpen = false;
		this.appID = setting.getAppid();
		this.mchID = setting.getMchid();
		this.key = setting.getKey();

		this.httpConnectTimeoutMs = Constants.HTTP_TIMEOUT_MS;
		this.httpReadTimeoutMs = Constants.HTTP_TIMEOUT_MS;
		InputStream certStream = WechatSettings.class.getClassLoader().getResourceAsStream(certPath);
		log.debug("证书流：" + certStream);
		try {
			this.certData = new byte[certStream.available()];
			certStream.read(this.certData);
			certStream.close();
		} catch (IOException var6) {
			var6.printStackTrace();
		}

	}

	@Override
	public String getAppID() {
		return this.appID;
	}

	@Override
	public String getMchID() {
		return this.mchID;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return this.httpConnectTimeoutMs;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return this.httpReadTimeoutMs;
	}

	public boolean isSandboxnewOpen() {
		return this.sandboxnewOpen;
	}
}
