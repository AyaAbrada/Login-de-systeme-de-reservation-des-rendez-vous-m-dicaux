package com.rendezVous.controller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter({"/patientHome","/success"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        if (req.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
        }
        else {
            res.sendRedirect(req.getContextPath() + "/connecter.jsp");
        }

    }
}