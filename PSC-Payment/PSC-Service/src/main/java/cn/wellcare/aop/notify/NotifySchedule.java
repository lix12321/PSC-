package cn.wellcare.aop.notify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.SystemConfig;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.HttpClientUtil;
import cn.wellcare.entity.notify.PayNotify;

@Component
public class NotifySchedule {

	private ScheduledExecutorService threadPool = Executors
			.newScheduledThreadPool(SystemConfig.CONSUME_THREAD_POOL_SIZE);
	private Logger log = Logger.getLogger(this.getClass());

	public boolean scheduledRun(PayNotify pn) {
		try {
			final String jobID = Thread.currentThread().getName();
			final AtomicInteger count = new AtomicInteger(1);
			final Map<String, Future<?>> futures = new HashMap<>();
			final CountDownLatch countDownLatch = new CountDownLatch(1);

			ScheduledFuture<?> scheduledFuture = threadPool.scheduleWithFixedDelay(() -> {

				String url = pn.getBackUrl();
				log.debug("启动消息推送，推送地址：" + url);

				try {
					log.debug("订单[" + pn.getOuterOrderSn() + "]第" + count.get() + "次通知，当前运行线程名："
							+ Thread.currentThread().getName());

					// 有url的通知
					if (!CommonUtils.isNull(url)) {
						boolean notify = false;
						if (count.get() < SystemConfig.DEFAULT_NOTIFY_COUNTS && !notify) {
							Map<String, Object> msgBody = CommonUtils.obj2Map(pn);
							String returnmsg = HttpClientUtil.doPost(url, msgBody, SystemConfig.HTTP_DEFAULT_CHARSET);
							returnmsg = CommonUtils.isNull(returnmsg) ? "" : returnmsg.toLowerCase();
							log.debug("通知已推送，地址：" + url + "，返回消息：" + returnmsg);

							if (Arrays.asList(SystemConfig.DEFAULT_NOTIFY_RETURN_MSG.split("\\|"))
									.contains(returnmsg)) {
								notify = true;

								stopScheduled(pn, futures, jobID, countDownLatch);
								log.debug("订单[" + pn.getOuterOrderSn() + "]已通达业务系统，通知终止");
								return;
							}
							log.debug("未收到业务服务返回的消息，继续发送");
							count.getAndIncrement();
						} else {
							// 超过次数或已通知
							stopScheduled(pn, futures, jobID, countDownLatch);
							log.debug("订单[" + pn.getOuterOrderSn() + "]通知已达" + (SystemConfig.DEFAULT_NOTIFY_COUNTS)
									+ "次或当前订单已通知，终止");

						}
						if (!notify) {
							// 未通知到
							log.warn("警告：订单[" + pn.getOuterOrderSn() + "]未通知");
							// TODO
						}
					} else {
						log.debug("当前订单不需要通知，退出线程");
						stopScheduled(pn, futures, jobID, countDownLatch);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}, 0, 1, TimeUnit.MINUTES);

			futures.put(jobID, scheduledFuture);
			log.debug("futures:" + futures);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void stopScheduled(PayNotify pn, Map<String, Future<?>> futures, String jobID,
			CountDownLatch countDownLatch) {
		Future<?> future = futures.get(jobID);
		if (future != null)
			future.cancel(true);
		countDownLatch.countDown();
		log.debug("已终止当前线程执行");
	}

}
