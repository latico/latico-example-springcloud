package com.latico.example.springcloud.controller;

import com.latico.commons.common.util.collections.CollectionUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.system.SystemUtils;
import com.latico.commons.common.util.version.VersionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.List;

/**
 * <PRE>
 * 默认主页
 * </PRE>
 *
 * @author: latico
 * @date: 2019-03-15 10:34:32
 * @version: 1.0
 */
@RestController
@Configuration
@Api("主页API")
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    /**
     * 拿到端口配置
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 拿到项目上下文路径
     */
    @Value("${server.servlet.context-path}")
    private String serverContextPath;

    @RequestMapping("")
    @ApiOperation("默认主页API")
    public String home(@Context HttpServletRequest httpServletRequest) {
        String requestUri = httpServletRequest.getRequestURI();
        String remoteHost = httpServletRequest.getRemoteHost();
        LOG.info("调用的客户端信息,地址:{} 请求路径:{}", remoteHost, requestUri);

        String serverContextPath = this.serverContextPath;
        final String slashStr = "/";
        if (!serverContextPath.startsWith(slashStr)) {
            serverContextPath =slashStr + serverContextPath;
        }
        if (!serverContextPath.endsWith(slashStr)) {
            serverContextPath = serverContextPath + slashStr;
        }

        String ip = "localhost";
        List<InetAddress> allPhysicsInetAddress = SystemUtils.getAllPhysicsInetAddress();
        if (CollectionUtils.isNotEmpty(allPhysicsInetAddress)) {
            InetAddress inetAddress = allPhysicsInetAddress.get(0);
            ip = inetAddress.getHostAddress();
        }
        String swaggerUrl = "http://" + ip + ":" + serverPort + serverContextPath + "swagger-ui.html";
        String swaggerLink = "<a href=\"" + swaggerUrl + "\" target=\"_blank\" title=\"Swagger Restful API\">" + swaggerUrl + "</a>";

        String druidUrl = "http://" + ip + ":" + serverPort + serverContextPath + "druid";
        String druidLink = "<a href=\"" + druidUrl + "\" target=\"_blank\" title=\"druid数据源监控\">" + druidUrl + "</a>";

        String indexUrl = "http://" + ip + ":" + serverPort + serverContextPath + "view/index.html";
        String indexLink = "<a href=\"" + indexUrl + "\" target=\"_blank\" title=\"索引页面\">" + indexUrl + "</a>";

        StringBuilder sb = new StringBuilder();
        sb.append("Spring Boot Home!<hr/>");
        sb.append("1、Rest API调测界面: " + swaggerLink + "<hr/>");
        sb.append("2、Druid监控界面: " + druidLink + "<hr/>");
        sb.append("3、索引界面: " + indexLink + "<hr/>");
        return sb.toString();
    }

    @RequestMapping(value = "version")
    @ApiOperation("查询程序版本信息，默认格式")
    public String version() {
        return VersionUtils.getVersionInfosToMarkdown();
    }

    @RequestMapping(value = "version/md")
    @ApiOperation("查询程序版本信息，markdown格式")
    public String versionMd() {
        return VersionUtils.getVersionInfosToMarkdown();
    }

    @RequestMapping(value = "version/html")
    @ApiOperation("查询程序版本信息，HTML表格式")
    public String versionHtml() {
        return VersionUtils.getVersionInfosToHtml();
    }

}
