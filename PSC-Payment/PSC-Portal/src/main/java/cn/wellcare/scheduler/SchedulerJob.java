package cn.wellcare.scheduler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.payment.modules.order.IPayOrderService;
import cn.wellcare.pojo.common.RpcResult;


/**
 * 系统定时任务
 * 
 * @author zhaihl
 *
 */
public class SchedulerJob {
	private static final Logger log = LogManager.getLogger(SchedulerJob.class);

	/**
	 * 系统自动取消24小时没有付款订单
	 * 
	 * @return
	 */
	public void cancelOrder() {
		log.info("cancelOrder() start");

		IPayOrderService ordersService = (IPayOrderService) ServiceLocator.getInstance().getBean("payOrderService");
		RpcResult<Boolean> jobResult = ordersService.jobSystemCancelOrder();
		if (!jobResult.isSuccess() || jobResult.getData() == null || !jobResult.getData()) {
			log.error("[SchedulerJob][cancelOrder] 定时任务系统自动取消24小时没有付款订单时失败："
					+ jobResult.getMsgInfo());
		}
		log.info("cancelOrder() end");
	}

}
