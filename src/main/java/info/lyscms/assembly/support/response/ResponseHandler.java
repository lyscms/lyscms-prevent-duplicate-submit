package info.lyscms.assembly.support.response;


import jakarta.servlet.http.HttpServletResponse;

/**
 * 拦截响应处理
 *
 * @author sunkl
 * @version 2020/3/18 17:44
 */
public interface ResponseHandler {

    /**
     * 处理响应
     *
     * @param response
     * @throws Exception
     * @author sunkl
     * @version 2020/3/18 17:45
     */
    void handlerResponse(HttpServletResponse response) throws Exception;
}
