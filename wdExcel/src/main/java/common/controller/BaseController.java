package common.controller;

import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class BaseController {

    protected void writeResponse(String data, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("text/xml;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p><b>Title:</b> receiveReq</p>
     * <p><b>Description:</b> 请求参数中获取xml流</p>
     *
     * @param request
     * @return
     * @throws IOException
     * @author douzi
     */
    protected String receiveReq(HttpServletRequest request) throws IOException {
        StringBuffer xmlSf = new StringBuffer();
        ServletInputStream inStream = request.getInputStream();
        BufferedInputStream buf = new BufferedInputStream(inStream);
        byte[] buffer = new byte[1024];
        int iRead;
        while ((iRead = buf.read(buffer)) != -1) {
            xmlSf.append(new String(buffer, 0, iRead, StandardCharsets.UTF_8));
        }
        return xmlSf.toString();
    }

    /**
     * <p><b>Title:</b> getOptLog</p>
     * <p><b>Description:</b> 获取操作日志</p>
     *
     * @param request      请求
     * @param modelName    模块名称
     * @param resource     类名.方法名  eg:RollChannelListController.imei
     * @param additionInfo 附加信息 可为空
     * @return
     * @author douzi
     */
    public String getOptLog(HttpServletRequest request, String modelName,
                            String resource, String code, String additionInfo) {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            ip = request.getLocalName();
        }

        return ip + ":" + request.getLocalPort() +
                "|admin|IE|" + getRemoteAddr(request) + "||HTTP|" + modelName +
                "|" + resource + "|" + ("1".equals(code) ? "Success" : "Failed") + "|" + additionInfo;
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (!StringUtils.isEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
}
