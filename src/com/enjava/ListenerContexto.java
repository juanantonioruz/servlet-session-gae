package com.enjava;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class ListenerContexto implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ListenerContexto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("iniciando usuarios");
		arg0.getServletContext().setAttribute("usuarios", new HashMap());

		
	}
	
}
