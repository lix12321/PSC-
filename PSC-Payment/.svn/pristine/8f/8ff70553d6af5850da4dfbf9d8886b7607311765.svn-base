// package cn.wellcare.mq.test;
//
// import javax.annotation.Resource;
//
// import cn.wellcare.entity.notify.PayNotify;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.alibaba.rocketmq.client.exception.MQBrokerException;
// import com.alibaba.rocketmq.client.exception.MQClientException;
// import com.alibaba.rocketmq.client.producer.SendResult;
// import com.alibaba.rocketmq.client.producer.SendStatus;
// import com.alibaba.rocketmq.common.message.Message;
// import com.alibaba.rocketmq.remoting.exception.RemotingException;
//
// import cn.wellcare.core.constant.SystemConfig;
// import cn.wellcare.core.utils.CommonUtils;
// import cn.wellcare.mq.RocketMQProducer;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {
// "classpath:spring-config/spring-context.xml" })
// public class MqSendTest {
//
// @Resource
// private RocketMQProducer producer;
//
// @Test
// @Transactional
// public void send() throws MQClientException, RemotingException,
// MQBrokerException, InterruptedException {
// PayNotify pn = new PayNotify();
//
// Message msg = new Message(SystemConfig.ROKET_MQ_TOPIC,
// SystemConfig.ROKET_MQ_TAGS,
// CommonUtils.objectToByte(pn));
// SendResult result = this.producer.getDefaultMQProducer().send(msg);
// System.out.println("成功：" + result);
// // 4.通知日志
// if (!SendStatus.SEND_OK.equals(result.getSendStatus())) {
// // TODO 失败
// System.out.println("发送失败");
// }
// }
//
// }
