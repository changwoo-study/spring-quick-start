package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

public class AfterReturningAdvice {
	public void afterLog(JoinPoint jp, Object returnedObj) {
		
		String method = jp.getSignature().getName();
		
		if(returnedObj instanceof UserVO) {
			UserVO user = (UserVO)returnedObj;
			if(user.getRole().equals("Admin")) {
				System.out.println(user.getName() + " 로그인(Admin)");
			}
		}
		
		System.out.println("[사후 처리] " + method + "() 메소드 리턴값: " + returnedObj.toString());
	}
}
