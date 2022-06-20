/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filters;

import com.entity.Account;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Long
 */
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Account session = (Account) req.getSession().getAttribute("account");
        String url = req.getRequestURI();
        
        if (session == null || !session.isLogged) {
            if (url.indexOf(url) >= 0 || url.indexOf("logout.xhtml") >= 0) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/test.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (url.indexOf("signup.xhtml") >= 0 || url.indexOf("test.xhtml") >= 0) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/loginsuccessfully.xhtml");
            } else if (url.indexOf("logout.xhtml") >= 0) {
                req.getSession().removeAttribute("account");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/test.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }
        
    }
    
    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
