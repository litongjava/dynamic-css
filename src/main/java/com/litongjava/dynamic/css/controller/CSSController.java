package com.litongjava.dynamic.css.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.http.useragent.UserAgent;
import com.litongjava.tio.utils.http.useragent.UserAgentUtil;

@RequestPath("/css")
public class CSSController {

  private Map<String, String> cssMap = new ConcurrentHashMap<>();

  @RequestPath("/{filename}")
  public HttpResponse index(HttpRequest request, String filename) {
    // 解析 User-Agent，判断请求来自移动端还是桌面端
    UserAgent userAgent = UserAgentUtil.parse(request.getUserAgent());

    // 根据设备类型定义对应的目录
    String directory = userAgent.isMobile() ? "css/mobile/" : "css/pc/";

    String pathname = directory + filename;

    if (EnvUtils.isDev()) {
      // 创建 CSS 文件对象
      File file = new File(pathname);

      // 检查文件是否存在
      if (!file.exists()) {
        // 如果文件不存在，则创建必要的父目录
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
          parentFile.mkdirs(); // 创建目录
        }

        try {
          // 创建新 CSS 文件并写入默认内容（可选）
          Files.write(file.toPath(), "/* Default CSS content */".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
          e.printStackTrace(); // 处理异常
        }
      }
    }

    String cssContent = cssMap.get(pathname);
    if (cssContent == null) {
      // 创建 CSS 文件对象
      File file = new File(pathname);
      try {
        // 读取并返回 CSS 文件的内容
        cssContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        cssMap.put(pathname, cssContent);
      } catch (IOException e) {
        e.printStackTrace(); // 处理异常
        return Resps.css(request, "/* Error loading CSS */");
      }
    }
    return Resps.css(request, cssContent);
  }
}