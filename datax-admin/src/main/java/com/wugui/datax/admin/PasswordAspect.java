package com.wugui.datax.admin;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author DongJiaQi
 * @create 2022/7/29 13:47
 * @Description: 密码代理
 *
 */
@Aspect
@Component
//切记不可提交！！！
public class PasswordAspect {


    @Pointcut("execution(* org.springframework.security.crypto.password.PasswordEncoder.matches(..))")
    public void password(){}





    /**
     * 针对 security 框架 ,调试方便,不可提交至生产
     * 代理密码校验,直接返回 true 。 省的问密码  直接跳过密码校验
     * 有其他校验的东西烦的可以同理干掉
     * 切记不可提交！！！
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @Around("password()")
    public Boolean passwordAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("代理密码");
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        //Object result = joinPoint.proceed(args);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return true;
    }





}
