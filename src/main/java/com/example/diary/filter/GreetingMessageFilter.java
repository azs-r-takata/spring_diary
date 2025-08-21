package com.example.diary.filter;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingMessageFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init()：フィルターを初期化します。");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		
		log.info("doFilter()：フィルター処理を開始します。");
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setContentType("text/html; charset=UTF-8");
		
		LocalTime now = LocalTime.now();
		
		if (now.isAfter(LocalTime.of(0, 59)) && now.isBefore(LocalTime.of(2, 00))) {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "メンテナンス中です");
		} else if(now.isAfter(LocalTime.of(3, 59)) && now.isBefore(LocalTime.of(12, 00))) {
			httpResponse.getWriter().write("おはようございます");
		} else if (now.isAfter(LocalTime.of(11, 59)) && now.isBefore(LocalTime.of(17, 00))) {
			httpResponse.getWriter().write("こんにちは");
		} else if (now.isAfter(LocalTime.of(16, 59)) && now.isBefore(LocalTime.of(4, 00))) {
			httpResponse.getWriter().write("こんばんは");
		}
		
		chain.doFilter(request, response);
		
		log.info("doFilter()：フィルター処理を完了しました。");
	}
	
	@Override
	public void destroy() {
		log.info("destroy()：フィルターを破棄します。");
	}
}
