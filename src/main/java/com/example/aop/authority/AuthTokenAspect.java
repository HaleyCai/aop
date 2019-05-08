package com.example.aop.authority;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthTokenAspect {
    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
     * 后面的增强均是围绕此切入点来完成的
     * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
     * 匹配包及其子包下的所有类的所有方法
     */
    @Pointcut("@annotation(auth)")
    public void executeService(Auth auth){
    }

    @Around("executeService(auth)")
    public Object doBeforeAdvice(ProceedingJoinPoint pjp,Auth auth) throws Throwable {
        ModelAndView mv = new ModelAndView();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String account = request.getParameter("account");
        System.out.println("account = "+account);
        // 获取访问该方法所需的role_name信息
        String[] role_name = auth.role_name();
        if (role_name == null || role_name.length == 0) {
            // 只需登录，验证是否具有account即可。
            /**
             * 此处使用短路与，若id==null直接会执行if体，不会继续判断
             * 若不等于null，则去验证后面的条件，但是也不会出现因为id为null而出现的空指针异常
             * 所以这样写也是安全的。
             */
            if (account != null && !account.equals("")) {
                // 已登录，执行原方法并返回即可。
                return pjp.proceed();
            }
            // 未登录，不执行方法，直接返回错误信息
            mv.setViewName("loginPage");
            System.out.println("未登录");
            return mv;
        }
        else {
            // 需要验证身份
            String role = request.getParameter("role");
            if(role.split(",").length == 2 && role_name.length == 1){
                String[] roles = role.split(",");
                for(int i=0;i<roles.length;i++){
                    if(roles[i].equals(role_name[0])){
                        //其中一项权限符合
                        System.out.println("1");
                        return pjp.proceed();
                    }
                }
                System.out.println("2");
                mv.setViewName("error");
                return mv;
            }

            role = role.replace(",","");
            String role_name_all = "";
            for(int i=0; i<role_name.length;i++){
                role_name_all += role_name[i];
            }
            if(role.equals(role_name_all)){
                //所有权限符合
                System.out.println("3");
                return pjp.proceed();
            }
            System.out.println("4");
            mv.setViewName("error");
            return mv;
        }
    }
}
