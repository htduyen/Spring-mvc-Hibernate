package inventory.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import inventory.model.Auth;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.util.Constant;

public class FilterSystem implements HandlerInterceptor{

	Logger logger = Logger.getLogger(FilterSystem.class);
	
//	Sau khi qua reHandle moi vao Controller
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			// TODO Auto-generated method stub
			logger.info("Request URI ="+request.getRequestURI());
			Users users = (Users) request.getSession().getAttribute(Constant.USER_INFO);
			if(users == null) {
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}
			if(users!=null) {
				String url = request.getServletPath();
				if(!hasPermission(url, users)) {
					logger.error("ACCESS DENIED URI ="+request.getRequestURI());
					response.sendRedirect(request.getContextPath()+"/access-denied");
					return false;
				}
			}
			return true;
	}
	
	private boolean hasPermission(String url, Users user) {
		
		if(url.contains("/index") || url.contains("/access-denied") || url.contains("/logout")) {
			return true;
		}
		UserRole userRole =(UserRole) user.getUserRoles().iterator().next();
		Set<Auth> auths = userRole.getRole().getAuths();
		for(Object obj : auths) {
			Auth auth = (Auth) obj;
			if(url.contains(auth.getMenu().getUrl())) {
				return auth.getPermission() ==1 ;
			}
		}
		return false;
		
	}
	
	//Sau khi url đã qua Filter tra ve
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
