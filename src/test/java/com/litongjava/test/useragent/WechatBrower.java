package com.litongjava.test.useragent;

import org.junit.Test;

import com.litongjava.tio.utils.http.useragent.UserAgent;
import com.litongjava.tio.utils.http.useragent.UserAgentUtil;
import com.litongjava.tio.utils.json.JsonUtils;

public class WechatBrower {

  @Test
  public void testParseWechat() {
    String str = "Mozilla/5.0 (Linux; Android 13; IN2017 Build/RKQ1.211119.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/122.0.6261.120 Mobile Safari/537.36 XWEB/1220133 MMWEBSDK/20240501 MMWEBID/9296 MicroMessenger/8.0.49.2685(0x28003145) WeChat/arm64 Weixin GPVersion/1 NetType/WIFI Language/en ABI/arm64";
    UserAgent parse = UserAgentUtil.parse(str);
    System.out.println(JsonUtils.toJson(parse));
  }

  @Test
  public void testParseQQ() {
    String str = "Mozilla/5.0 (Linux; Android 13; IN2017 Build/RKQ1.211119.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.86 MQQBrowser/6.2 TBS/047101 Mobile Safari/537.36 V1_AND_SQ_9.0.50_6258_YYB_D QQ/9.0.50.16545 NetType/WIFI WebP/0.3.0 AppId/537217916 Pixel/1080 StatusBarHeight/107 SimpleUISwitch/0 QQTheme/1103 StudyMode/1 CurrentMode/2 CurrentFontScale/1.0 GlobalDensityScale/0.90000004 AllowLandscape/false InMagicWin/0";
    UserAgent parse = UserAgentUtil.parse(str);
    System.out.println(JsonUtils.toJson(parse));
  }

}
