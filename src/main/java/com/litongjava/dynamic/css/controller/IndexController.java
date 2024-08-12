package com.litongjava.dynamic.css.controller;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.http.useragent.UserAgent;
import com.litongjava.tio.utils.http.useragent.UserAgentUtil;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  public UserAgent index(HttpRequest request) {
    return UserAgentUtil.parse(request.getUserAgent());
  }
}
