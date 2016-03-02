package br.edu.unoesc.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Data;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/*" })
public @Data class AuthorizationFilter implements Filter {

	private Logger logger;

    public AuthorizationFilter() {
    	logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (	reqURI.contains("login.xhtml" )
                    || (ses != null && ses.getAttribute("usuario") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")){

                chain.doFilter(request, response);

            }
            else{
                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml?erro=true");
            }
        } catch (Exception e) {
        		e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}