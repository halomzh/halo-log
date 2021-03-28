package com.halo.log.annotation;

import java.lang.annotation.*;

/**
 * @author shoufeng
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HaloLog {

	String action() default "";
	
}
