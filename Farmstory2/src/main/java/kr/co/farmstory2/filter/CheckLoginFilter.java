package kr.co.farmstory2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.UserDTO;

public class CheckLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserDTO sessUser = (UserDTO) ((HttpServletRequest) request).getSession().getAttribute("sessUser");

		if (sessUser != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/Farmstory2/user/login.do?success=101");
		}
	}
}