package cn.ncss.jym.messagebox.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ***********************
 * 
 *  登录权限过滤
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年3月27日]
 *
 */
public class LoginFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		HttpSession session=request.getSession();
//		Object username=session.getAttribute("username");
//		if(!"kyrin".equals(username)){
//			System.out.println("您还未登录！");
//			 request.setCharacterEncoding("UTF-8");  
//             response.setCharacterEncoding("UTF-8");  
//             PrintWriter out = response.getWriter();  
//             String loginPage = request.getContextPath()+"/";  
//             StringBuilder builder = new StringBuilder();  
//             builder.append("<script type=\"text/javascript\">");  
//             builder.append("alert('please sign in !');");  
//             builder.append("window.top.location.href='");  
//             builder.append(loginPage);  
//             builder.append("';");  
//             builder.append("</script>");  
//             out.print(builder.toString()); 
//             
//		}else{
//			filterChain.doFilter(request, response);
//		}
		
		filterChain.doFilter(request, response);
	}
}
