package com.book.manager.security.impl;

import com.book.manager.security.service.CustomFilterSecurityInterceptor;
import com.book.util.StringUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**  
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。 
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。 
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中， 
 * 供Spring Security使用，用于权限校验。 
 *  
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: CustomFilterSecurityInterceptorImpl
 * @author Johnny_L_Q
 */
@Service("customFilterSecurityInterceptor")
public class CustomFilterSecurityInterceptorImpl extends
        AbstractSecurityInterceptor implements CustomFilterSecurityInterceptor {

    @Resource
    @Qualifier("customInvocationSecurityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;


    @Resource
    @Qualifier("customAccessDecisionManager")
    @Override
    public void setAccessDecisionManager(
            AccessDecisionManager accessDecisionManager) {
        // TODO Auto-generated method stub
        super.setAccessDecisionManager(accessDecisionManager);
    }
/*    @Resource
    @Qualifier("customAccessDecisionManager")
    public AccessDecisionManager accessDecisionManager;*/
    
/*    @Resource
    @Qualifier("authenticationManager")
    public AuthenticationManager authenticationManager;*/
    
    
    @Resource
    @Qualifier("authenticationManager")
    @Override
    public void setAuthenticationManager(AuthenticationManager newManager) {
        super.setAuthenticationManager(newManager);
    }

    /*
     * <p>Title: doFilter</p>
     * <p>Description: </p>
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession() ;
//        session.setMaxInactiveInterval(3);

        //请求参数打印
//        logger.info("uri: " + httpServletRequest.getRequestURI());
//        Map map = request.getParameterMap();
//        if (map != null && !map.isEmpty()) {
//            Set<String> keySet = map.keySet();
//            for (String key : keySet) {
//                String[] values = (String[]) map.get(key);
////                for (String value : values) {
//                    logger.info("> " + key + "=" + values[0]);
////                }
//            }
//        }
        infoke(fi);
        String username = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
        String url = httpServletRequest.getRequestURI() ;
        String[] urls = url.split(";") ;
        if(StringUtil.isEmpty(username) && !urls[0].equals("/logout") && !urls[0].equals("/user/index")){
            httpServletRequest.getRequestDispatcher("/user/userOut").forward(request,response);  // 请求转发
            return ;
        }
    }

    /**
     * TODO(这里用一句话描述这个方法的作用).
     * @param fi 
     * @throws ServletException 
     * @throws IOException 
     */
    private void infoke(FilterInvocation fi) throws IOException, ServletException {
        //在执行doFilter()之前，进行权限的检查，而具体的实现已经交给AccessDecisionManager了
        InterceptorStatusToken token = super.beforeInvocation(fi);
        if (token==null){
            throw new AccessDeniedException("Access Denied");
        }
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
        
    }

    /*
     * <p>Title: init</p>
     * <p>Description: </p>
     * @param arg0
     * @throws ServletException
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    /*
     * <p>Title: getSecureObjectClass</p>
     * <p>Description: </p>
     * @return
     * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#getSecureObjectClass()
     */
    @Override
    public Class<?> getSecureObjectClass() {
        // TODO Auto-generated method stub
        return FilterInvocation.class;
    }

    /*
     * <p>Title: obtainSecurityMetadataSource</p>
     * <p>Description: </p>
     * @return
     * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#obtainSecurityMetadataSource()
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        // TODO Auto-generated method stub
        return this.securityMetadataSource;
    }
    
    /*
     * <p>Title: destroy</p>
     * <p>Description: </p>
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }
    

    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

   

}
