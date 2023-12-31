package info.lyscms.assembly.support.qualifier;

import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 定义阻止限定
 */
public interface PreventQualifier {

    /**
     * 判定是否需要阻止提交
     *
     * @param request
     * @param handlerMethod
     * @return
     */
    boolean isPrevent(HttpServletRequest request, HandlerMethod handlerMethod);
}
