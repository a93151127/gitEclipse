package com.martin.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 設置切入點
	 * 定義執行完LogTestApi裡面的log方法時會進入這個method檢查有沒有需要執行的地方
	 * 括號裡面的(..)表示不管參數
	 */
	@Pointcut("execution(* com.martin.controller.LogTestApi.log(..))")
	public void log() {
		log.info("inside LogAspect=========");
	}
	
	/*
	 * 在log方法執行之前先進入下面這個方法
	 * 這裡也可以寫成
	 * @Before("execution(* com.martin.controller.LogTestApi.log(..))")
	 */
	@Before("log()")
	public void logBefore() {
		log.info("1before=========");
	}
	/*
	 * 在log方法執行之前先進入下面這個方法
	 * 這裡也可以寫成
	 * @Before("execution(* com.martin.controller.LogTestApi.log(..))")
	 */
	@After("log()")
    public void doAfter() {
        log.info("2=========");
    }


    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAtfertRturning(Object result) {
        log.info("3=========");
    }
}
