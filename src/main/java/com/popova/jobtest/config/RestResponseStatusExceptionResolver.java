package com.popova.jobtest.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse,
                                              Object o, Exception e) {
        e.printStackTrace();
        return new ModelAndView("error");
    }
}
