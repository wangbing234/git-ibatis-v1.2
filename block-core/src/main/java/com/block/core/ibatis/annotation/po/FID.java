package com.block.core.ibatis.annotation.po;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个表的主键
 * @author bing.wang
 * @time 2017年8月2日14:39:37
 * @email test.qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FID {
	String name();
}
