package com.example.diary.interceptor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerMoveTimeInterceptor implements HandlerInterceptor {
	
	long startTime;
	long endTime;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		log.info("コントローラ：{} メソッド名：{} スレッドID：{} 現在時刻：{}",
				handlerMethod.getBeanType().getName(),
				request.getMethod(),
				Thread.currentThread(),
				LocalTime.now().format(formatter)
		);
		
		startTime = System.currentTimeMillis();
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	        throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		endTime = System.currentTimeMillis();
		
		log.info("コントローラ：{} メソッド名：{} スレッドID：{} 現在時刻：{} 処理時間：{} ミリ秒",
				handlerMethod.getBeanType().getName(),
				request.getMethod(),
				Thread.currentThread(),
				LocalTime.now().format(formatter),
				endTime - startTime
		);
		
	}
}
