package info.lyscms.assembly.support.response.impl;

import info.lyscms.assembly.support.response.ResponseHandler;

import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DefaultResponseHandlerImpl implements ResponseHandler {
    /**
     * 处理响应
     *
     * @param response
     * @throws Exception
     * @author sunkl
     * @version 2020/3/18 17:45
     */
    @Override
    public void handlerResponse(HttpServletResponse response) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\"code\":1002,\"message\":\"重复请求\",\"data\":null}");
        writer.flush();
        writer.close();
    }
}
