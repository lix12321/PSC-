package cn.wellcare.core.constant.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.wellcare.core.constant.OpType;
import cn.wellcare.core.constant.PayLogHandler;

/***
 * 交易日志操作
 * 
 * @author zhaihl
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PaymentLog {
	// 支付日志操作
	PayLogHandler value();

	// 业务类型
	OpType type() default OpType.CONSUME;
}
