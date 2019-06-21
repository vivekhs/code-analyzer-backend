/**
 * 
 */
package com.hackathon.filters.authentication;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;

import com.hackathon.models.analyzerproperties.AnalyzerProperties;
import com.hackathon.services.authentication.AuthService;

/**
 * @author vivekhs
 *
 */
public class AuthenticationFilter implements Filter {

    private AuthService authService;
    private AnalyzerProperties analyzerProps;

    public AuthenticationFilter(AuthService authService, AnalyzerProperties analyzerProps) {
        this.authService = authService;
        this.analyzerProps = analyzerProps;
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // first time configs on application start
        new File(analyzerProps.getAnalysisOutDirectory()).mkdirs();
        new File(analyzerProps.getCodesDirectory()).mkdirs();
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if(HttpMethod.OPTIONS.name().equals(httpRequest.getMethod())) {
        	chain.doFilter(request, response);
        	return;
        }
        String authorization = httpRequest.getHeader("authorization");

        if (authorization == null || authorization == "") {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // todo send response in json format use Gson
            httpResponse.getWriter().print("Unauthorized access");
            return;
        }
        authorization = authorization.trim();
        String access_token = authorization.split(" ")[1];

        if (!authService.verifyToken(access_token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // todo send response in json format use Gson
            httpResponse.getWriter().print("Unauthorized access");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
