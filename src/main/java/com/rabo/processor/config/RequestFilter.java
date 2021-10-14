package com.rabo.processor.config;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class RequestFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!req.getRequestURI().startsWith("/statement/api") || Objects.equals(req.getMethod(), HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
            return;
        }
        String key = req.getHeader("api-key") == null ? "" : req.getHeader("api-key");
        //todo implement a proper authentication; API keys never to be hardcoded.
        if (key.equals("ItsTheApikeyTOConsumeThevalidateService")) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            String error = "Invalid API KEY";
            resp.reset();
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentLength(error.length());
            response.getWriter().write(error);
        }
    }
}
