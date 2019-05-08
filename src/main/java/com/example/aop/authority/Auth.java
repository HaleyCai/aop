package com.example.aop.authority;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    /**
     * 访问所需的身份，默认为空，为登录即可访问，可以多个定义
     * Read权限
     * Update
     * Aggravate权限
     * @return
     */
    String[] role_name() default "";
}
