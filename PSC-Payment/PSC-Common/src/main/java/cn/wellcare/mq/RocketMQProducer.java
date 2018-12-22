package cn.wellcare.mq;

import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import cn.wellcare.core.utils.CommonUtils;

/**
 * 生产者
 * 
 * @author zhaihl
 *
 */
public class RocketMQProducer {
	private static final Logger logger = Logger.getLogger(RocketMQProducer.class);

	private DefaultMQProducer defaultMQProducer;
	private String producerGroup;
	private String namesrvAddr;
	private String instanceName;
	private int retryTimes;

	public void init() throws MQClientException {
		this.defaultMQProducer = new DefaultMQProducer(this.producerGroup);
		this.defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
		if (!CommonUtils.isNull(this.instanceName)) {
			this.defaultMQProducer.setInstanceName(this.instanceName);
		}
		if (!CommonUtils.isNull(this.retryTimes)) {
			this.defaultMQProducer.setRetryTimesWhenSendFailed(this.retryTimes);
		}
		this.defaultMQProducer.start();
		logger.info("rocketMQ初始化生产者完成[producerGroup：" + this.producerGroup + "，namesrvAddr：" + this.namesrvAddr + "]");
	}

	public void destroy() {
		this.defaultMQProducer.shutdown();
	}

	public DefaultMQProducer getDefaultMQProducer() {
		return this.defaultMQProducer;
	}

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
}
