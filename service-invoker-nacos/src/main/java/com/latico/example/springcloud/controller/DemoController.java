package com.latico.example.springcloud.controller;

import com.latico.commons.common.util.io.FileUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);
    @Value("${server.port}")
    String serverPort;
    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "hello")
    public String hello() {
        //返回字符串，需要包一层JSON
        return "端口" + serverPort + ":服务生产者数据:" + "你好";
    }

    @RequestMapping(value="uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("fileName") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        //设置文件上传路径
        String fileDir = "upload";
        try {
            FileUtils.writeByteArrayToFile(new File(fileDir + "/" + fileName), multipartFile.getBytes());
            return "上传成功";
        } catch (Exception e) {
            LOG.error("", e);
            return "上传失败";
        }
    }
}
