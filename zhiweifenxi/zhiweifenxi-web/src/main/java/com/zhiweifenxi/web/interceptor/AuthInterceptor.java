package com.zhiweifenxi.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhiweifenxi.web.util.Global;
import com.zhiweifenxi.web.util.StringUtil;

/**
 * 权限过滤
 * @author kyrin
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private Logger logger=Logger.getLogger(AuthInterceptor.class);
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 if (!(handler instanceof HandlerMethod)) {
			 return true;
			 }
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute(Global.AUTH_USERNAME);
		if(StringUtil.isEmpty(username)){
			logger.warn("user is not login !");
			System.out.println("user is not logoin !");
			response.sendRedirect(request.getContextPath());
			return false;
		}
		return true;
	}
}
