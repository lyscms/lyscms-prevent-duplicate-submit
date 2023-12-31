package info.lyscms.assembly.support.qualifier.impl;

import info.lyscms.assembly.support.qualifier.PreventQualifier;
import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletRequest;


/**
 * 所有均阻止
 *
 * @author sunkl
 * @version 2020/3/18 16:52
 */
public class AllPreventQualifier implements PreventQualifier {
    /**
     * 判定是否需要阻止提交
     *
     * @param request
     * @param handlerMethod
     * @return
     */
    @Override
    public boolean isPrevent(HttpServletRequest request, HandlerMethod handlerMethod) {
        return true;
    }
}
