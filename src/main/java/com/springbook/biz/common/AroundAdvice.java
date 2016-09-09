package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;


public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[BEFORE]: 비즈니스 메소드 수행 전에 처리할 내용...");
		Object returnedObj = pjp.proceed();
		System.out.println("[AFTER]: 비즈니스 메소드 수행 후에 처리할 내용...");
		return returnedObj;
	}
}
