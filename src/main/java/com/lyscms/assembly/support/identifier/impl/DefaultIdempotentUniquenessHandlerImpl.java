package com.lyscms.assembly.support.identifier.impl;

import com.lyscms.assembly.support.identifier.IdempotentUniquenessHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

public class DefaultIdempotentUniquenessHandlerImpl implements IdempotentUniquenessHandler {

    /**
     * 幂等唯一id
     *
     * @param request
     * @param method
     * @return
     * @author sunkl
     * @date 2020/3/18 17:23
     */
    @Override
    public String idempotentId(HttpServletRequest request, HandlerMethod method) {
        return request.getSession().getId();
    }
}
