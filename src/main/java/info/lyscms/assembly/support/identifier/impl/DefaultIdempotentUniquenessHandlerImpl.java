package info.lyscms.assembly.support.identifier.impl;

import info.lyscms.assembly.support.identifier.IdempotentUniquenessHandler;
import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletRequest;

public class DefaultIdempotentUniquenessHandlerImpl implements IdempotentUniquenessHandler {

    /**
     * 幂等唯一id
     *
     * @param request 请求对象
     * @param method  方法
     * @return
     * @author sunkl
     * @version 2020/3/18 17:23
     */
    @Override
    public String idempotentId(HttpServletRequest request, HandlerMethod method) {
        return request.getSession().getId() + "_" + request.getMethod() + "_" + request.getRequestURI();
    }
}
