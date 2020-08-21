package com.lyscms.assembly.support.qualifier;


import com.lyscms.assembly.annotation.ExcludePreventRepeat;
import com.lyscms.assembly.annotation.PreventRepeat;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 定义阻止限定
 */
public class PreventQualifierHandler {

    @Resource
    private PreventQualifier preventQualifier;

    /**
     * 是否需要拦截
     *
     * @return
     * @author sunkl
     * @date 2020/3/19 18:34
     */
    public boolean globalIsPrevent(HttpServletRequest request, HandlerMethod handlerMethod) {
        if (handlerMethod.hasMethodAnnotation(ExcludePreventRepeat.class)) {
            return false;
        }

        if (handlerMethod.hasMethodAnnotation(PreventRepeat.class)) {
            return true;
        }
        return preventQualifier.isPrevent(request, handlerMethod);
    }
}
