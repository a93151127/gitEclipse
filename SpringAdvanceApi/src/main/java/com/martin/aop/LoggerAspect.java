package com.martin.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * com.martin.controller下面所有的class
	 * 和class裡面所有的方法
	 */
	@Pointcut("execution(* com.martin.controller.*.*(..))")
	public void log() {
	}
	@Pointcut("execution(* com.martin.handler.*.*(..))")
	public void handleLog() {		
	}
	
	@Before("handleLog()")
	public void doHandleBefore() {
		log.info("--inside exception--");
	}
	
	@AfterReturning(returning="result",pointcut="handleLog()")
	public void returningHandleLog(Object result) {
		log.info("==exception result=={}",result);
	}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attribute.getRequest();
		
		
		/*
		 * getDelaringTypeName是取得類的名字
		 * getName是取得方法的名字
		 */
		String classMethod = joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName();
		
		Object[] args=joinPoint.getArgs();
		
		RequestLog requestLog = new RequestLog(request.getRequestURL().toString(),request.getRemoteAddr(),classMethod,args);
		log.info("==Request=={}:",requestLog.toString());
	}
	
	@After("log()")
	public void doAfter() {
		//log.info("after==========");
	}
	
	@AfterReturning(returning="result",pointcut="log()")
	public void returningLog(Object result) {
		log.info("==Result=={}:",result);
	}
	
	
	private class RequestLog{
		private String url;
		private String ip;
		//類和名稱
		private String classMethod;
		//請求參數
		private Object[] arg;
		
		public RequestLog(String url, String ip, String classMethod, Object[] arg) {
			super();
			this.url = url;
			this.ip = ip;
			this.classMethod = classMethod;
			this.arg = arg;
		}

		@Override
		public String toString() {
			return "RequestLog [url=" + url + ", ip=" + ip + ", classMethod=" + classMethod + ", arg="
					+ Arrays.toString(arg) + "]";
		}
		
		
	}

}
