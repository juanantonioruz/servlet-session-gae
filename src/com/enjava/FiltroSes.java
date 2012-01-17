package com.enjava;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class FiltroSes implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		String host = req.getHeader("Host");
		String userAgent = req.getHeader("User-Agent");
		String id=host+"-"+userAgent;
		System.out.println(id);
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		HashMap<String, Map> usuarios = (HashMap<String, Map>) servletContext.getAttribute("usuarios");
		if(usuarios.containsKey(id)){
			Map<String, Object> map = usuarios.get(id);
			for(String key:map.keySet()){
				session.setAttribute(key, map.get(key));
			}
		}else{
			usuarios.put(id, new HashMap());
		}
		arg2.doFilter(arg0, arg1);
		Map<String,Object> m=usuarios.get(id);
		Enumeration names=session.getAttributeNames();
		while(names.hasMoreElements()){
			String keySession = (String) names.nextElement();
			Object valueSession = session.getAttribute(keySession);
			m.put(keySession, valueSession);
			System.out.println(valueSession);
		}
		
//		Enumeration headerNames = req.getHeaderNames();
//		while(headerNames.hasMoreElements()){
//			String header = (String) headerNames.nextElement();
//			System.out.println("header: "+header+":"+req.getHeader(header));
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
