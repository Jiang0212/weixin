package manager;

import org.junit.Test;

import service.wxService;
import util.Utils;

public class TemplateMessageManager {
	
	/**
	 * 设置行业
	 */
	@Test
	public void set() {
		String at = wxService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+at;
		String data = "{\r\n" + 
				"    \"industry_id1\":\"1\",\r\n" + 
				"    \"industry_id2\":\"4\"\r\n" + 
				"}";
		String result = Utils.post(url, data);
		System.out.println(result);
	}
	
	/**
	 * 获取行业
	 */
	@Test
	public void get() {
		String at = wxService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+at;
		String result = Utils.get(url);
		System.out.println(result);
	}
	
	/**
	 * 发送模版消息
	 */
	@Test
	public static void sendTemplateMessage() {
		String at = wxService.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
		String data = "{\r\n" + 
				"           \"touser\":\"oeGVqwqHwQr05vt0K42KCyc0DxY4\",\r\n" + 
				"           \"template_id\":\"pfzLw4yrrdIVV_N2iAAYzN80em3eK6kLP4209Ve5_98\",         \r\n" + 
				"           \"data\":{\r\n" +
				"                   \"first\": {\r\n" + 
				"                       \"value\":\"您有新的反馈信息！\",\r\n" + 
				"                       \"color\":\"#1f1f1f\"\r\n" + 
				"                   },\r\n" + 
				"                   \"company\":{\r\n" + 
				"                       \"value\":\"江城公司\",\r\n" + 
				"                       \"color\":\"#2c2c2c\"\r\n" + 
				"                   },\r\n" + 
				"                   \"time\": {\r\n" + 
				"                       \"value\":\"2019-12-25\",\r\n" + 
				"                       \"color\":\"#3c3c3c\"\r\n" + 
				"                   },\r\n" + 
				"                   \"result\": {\r\n" + 
				"                       \"value\":\"面试通过\",\r\n" + 
				"                       \"color\":\"#3c3c3c\"\r\n" + 
				"                   },\r\n" + 
				"                   \"remark\":{\r\n" + 
				"                       \"value\":\"请联系我们人事！\",\r\n" + 
				"                       \"color\":\"#4d4d4d\"\r\n" + 
				"                   }\r\n" + 
				"           }\r\n" + 
				"       }";
		String result = Utils.post(url, data);
		System.out.println(result);		
	}
}
