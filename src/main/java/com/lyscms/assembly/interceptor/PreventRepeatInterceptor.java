package com.lyscms.assembly.interceptor;

import com.lyscms.assembly.support.identifier.IdempotentUniquenessHandler;
import com.lyscms.assembly.support.lock.LockHandler;
import com.lyscms.assembly.support.qualifier.PreventQualifierHandler;
import com.lyscms.assembly.support.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 阻止拦截器Handler
 *
 * @author sunkl
 * @date 2020/3/18 14:32
 */


@Slf4j
public class PreventRepeatInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private PreventQualifierHandler preventQualifier;

    @Resource
    private IdempotentUniquenessHandler idempotentUniquenessHandler;

    @Resource
    private LockHandler lockHandler;

    @Resource
    private ResponseHandler responseHandler;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            //判断是否需要防止重复提交
            if (!preventQualifier.globalIsPrevent(request, handlerMethod)) {
                return true;
            }
            log.info("========================执行拦截器了...==========================");

            //获取幂等唯一标识
            String idempotentId = idempotentUniquenessHandler.idempotentId(request, handlerMethod);

            //获取锁
            boolean lock = lockHandler.lock(idempotentId);

            log.info("[Lyscms Prevent Repeat] url={}，method={}，idempotentId={}，lock={}",
                    request.getRequestURI(), request.getMethod(), idempotentId, lock);

            //对获取锁失败的进行打回处理
            if (!lock) {
                log.error("========================执行拦截器结束==========================");
                responseHandler.handlerResponse(response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //判断是否需要防止重复提交
            if (!preventQualifier.globalIsPrevent(request, handlerMethod)) {
                return;
            }

            //获取幂等唯一标识
            String idempotentId = idempotentUniquenessHandler.idempotentId(request, handlerMethod);

            //释放锁
            boolean unLock = lockHandler.unLock(idempotentId);

            log.info("[Lyscms Prevent Repeat] url={}，method={}，idempotentId={}，unLock={}",
                    request.getRequestURI(), request.getMethod(), idempotentId, unLock);
            log.info("========================执行拦截器成功结束==========================");
        }
    }
}