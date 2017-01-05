package com.jiuyi.jyplat.authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jiuyi.jyplat.util.Constant;

@Retention(RetentionPolicy.RUNTIME)
//指定该注解是在运行期进行
@Target({ ElementType.METHOD })
//指定该注解要在方法上使用
public @interface AuthName {

	String[] value() default { Constant.AUTH_PASS };

}
