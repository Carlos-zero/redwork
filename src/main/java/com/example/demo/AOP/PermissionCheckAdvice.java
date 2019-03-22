package com.example.demo.AOP;

import com.example.demo.Annotation.Role;
import com.example.demo.Been.User;
import com.example.demo.Service.Impl.UserServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.lang.reflect.Method;

@Aspect
@Component
public class PermissionCheckAdvice {
    private static final Logger LOGGER= LoggerFactory.getLogger(PermissionCheckAdvice.class);
    private static User user;
    private static final UserServiceImpl USER_SERVICE = null;
    private static String role;

    //静态初始化用户得到用户role
    static {
        user=USER_SERVICE.getUser();
        role=user.getRole();
    }

    @Pointcut(value = "@annotation(com.example.demo.Annotation.Role)")
    private void permissionAnnotation(){
        //切入点签名
    }

    @Before("permissionAnnotation()")
    public void before(){
        System.out.println("aop--before");
    }

    @After("permissionAnnotation()")
    public void after(){
        System.out.println("aop--after");
    }


    @Around("permissionAnnotation()")
    public Object executeAround(ProceedingJoinPoint jp)throws Throwable{
        //获取用户角色信息
        String user_role = role;
        //验证权限
        Signature signature=jp.getSignature();
        MethodSignature methodSignature=(MethodSignature)signature;
        Method targetMethod=methodSignature.getMethod();

        //根据被代理的类对象获取要执行的方法
        Method realMethod=jp.getTarget().getClass().getDeclaredMethod(signature.getName(),targetMethod.getParameterTypes());
        //如果用户拥有该方法权限时执行该方法
        if (hasPermission(realMethod,user_role)){
            //用户拥有该方法的角色权限时执行方法内容
            return jp.proceed();
        }else {
            throw new AuthenticationException("您的角色权限不足，请您去充Q币");
/*
            if (realMethod.isAnnotationPresent(ResponseBody.class)){}
*/
        }
    }

    /*
    *       判断用户是否拥有权限
    *       @param realMethod   访问当前的方法
    *       @param user_role    用户目前的角色
    *       @return
    * */

    private boolean hasPermission(Method realMethod, String user_role) {
        try {
            if (realMethod.isAnnotationPresent(Role.class)) {
                //获取指定的注解
                Role role = realMethod.getAnnotation(Role.class);
                //获取方法的权限角色
                String need_role = role.need_role();
                if (need_role == user_role) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
