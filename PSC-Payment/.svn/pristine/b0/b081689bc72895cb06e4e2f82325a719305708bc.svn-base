package cn.wellcare.service.transaction.payment.alipay.base;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl.ClientBuilder;

import cn.wellcare.entity.payset.PaySettingAlipay;
import cn.wellcare.model.modules.payset.PaySettingAlipayModel;

@Component
public class AlipayTradeBuilder extends ClientBuilder {
	@Resource
	private PaySettingAlipayModel paySettingAlipayModel;
	private String gatewayUrl = null;
	private String appid = null;
	private String privateKey = null;
	private String format = null;
	private String charset = null;
	private String alipayPublicKey = null;
	private String signType = null;
	private Integer busid = null;

	private static String mcloudApiDomain; // 支付宝mcloudmonitor域名
	private static String pid; // 商户partner id
	private static int maxQueryRetry; // 最大查询次数
	private static long queryDuration; // 查询间隔（毫秒）

	private static int maxCancelRetry; // 最大撤销次数
	private static long cancelDuration; // 撤销间隔（毫秒）

	private static long heartbeatDelay; // 交易保障线程第一次调度延迟（秒）
	private static long heartbeatDuration; // 交易保障线程调度间隔（秒）

	@Override
	public AlipayTradeServiceImpl build() {
		Assert.notNull(busid);
		// 支付设置
		PaySettingAlipay setting = paySettingAlipayModel.getByOrg(busid);

		if (StringUtils.isEmpty(gatewayUrl)) {
			gatewayUrl = setting.getOpenApiDomain();
		}
		if (StringUtils.isEmpty(appid)) {
			appid = setting.getAppid();
		}
		if (StringUtils.isEmpty(privateKey)) {
			privateKey = setting.getPrivateKey();
		}
		if (StringUtils.isEmpty(format)) {
			format = "json";
		}
		if (StringUtils.isEmpty(charset)) {
			charset = "utf-8";
		}
		if (StringUtils.isEmpty(alipayPublicKey)) {
			alipayPublicKey = setting.getAlipayPublicKey();
		}
		if (StringUtils.isEmpty(signType)) {
			signType = setting.getSignType();
		}

		// 配置初始化
		mcloudApiDomain = setting.getMcloudApiDomain();
		pid = setting.getPid();
		maxQueryRetry = setting.getMaxQueryRetry();
		queryDuration = setting.getQueryDuration();
		maxCancelRetry = setting.getMaxCancelRetry();
		cancelDuration = setting.getCancelDuration();
		heartbeatDelay = setting.getHeartbeatDelay();
		heartbeatDuration = setting.getHeartbeatDuration();

		Configs.setOpenApiDomain(gatewayUrl);
		Configs.setMcloudApiDomain(mcloudApiDomain);
		Configs.setPid(pid);
		Configs.setAppid(appid);
		Configs.setPrivateKey(privateKey);
		Configs.setPublicKey(setting.getPublicKey());
		Configs.setAlipayPublicKey(alipayPublicKey);
		Configs.setSignType(signType);
		Configs.setMaxQueryRetry(maxQueryRetry);
		Configs.setQueryDuration(queryDuration);
		Configs.setMaxCancelRetry(maxCancelRetry);
		Configs.setCancelDuration(cancelDuration);
		Configs.setHeartbeatDelay(heartbeatDelay);
		Configs.setHeartbeatDuration(heartbeatDuration);

		return new AlipayTradeServiceImpl(this);
	}

	public Integer getBusid() {
		return this.busid;
	}

	public void setBusid(Integer busid) {
		this.busid = busid;
	}

	@Override
	public ClientBuilder setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
		return this;
	}

	@Override
	public ClientBuilder setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	@Override
	public ClientBuilder setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	@Override
	public ClientBuilder setFormat(String format) {
		this.format = format;
		return this;
	}

	@Override
	public ClientBuilder setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
		return this;
	}

	@Override
	public ClientBuilder setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
		return this;
	}

	@Override
	public ClientBuilder setSignType(String signType) {
		this.signType = signType;
		return this;
	}

	@Override
	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	@Override
	public String getSignType() {
		return signType;
	}

	@Override
	public String getAppid() {
		return appid;
	}

	@Override
	public String getCharset() {
		return charset;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public String getGatewayUrl() {
		return gatewayUrl;
	}

	@Override
	public String getPrivateKey() {
		return privateKey;
	}

}
