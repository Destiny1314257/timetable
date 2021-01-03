package com.tang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/manage/*","/student/*","/teacher/*"},
        servletNames = {"com.tang.servlet.StudentServlet",
                "com.tang.servlet.TeacherServlet","com.tang.servlet.ManagerServlet"},
        initParams = {@WebInitParam(name="indexPage",value = "index.jsp")},
        dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class LoginFilter implements Filter {
    private String indexPage="index.jsp";
    public void init(FilterConfig fConfig) throws ServletException {
        indexPage=fConfig.getInitParameter("indexPage");
        if(null==indexPage){
            indexPage="login.jsp";
        }
    }

    public void destroy() {
        this.indexPage=null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        HttpSession session=req.getSession();
        //判断被拦截的请求用户是否处于登录状态
        if(session.getAttribute("SESSION_USER")==null){
            //获取被拦截的请求地址及参数
            String requestPath=req.getRequestURI();
            if(req.getQueryString()!=null){
                requestPath+="?"+req.getQueryString();
            }
            //将请求地址保存到request对象中转发到登录页面
            req.setAttribute("requestPath",requestPath);
            ((HttpServletResponse) response).sendRedirect("/"+indexPage);
            //request.getRequestDispatcher("/"+loginPage).forward(request,response);
            //return;
        }else {
            chain.doFilter(req, resp);
        }
    }

}
