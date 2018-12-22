package cn.wellcare.core.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.wellcare.core.constant.NotifyType;

/**
 * 支付通知注解
 * 
 * @author zhaihl
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Notify {
	// 通知类型
	NotifyType value();
}
