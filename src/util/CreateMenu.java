package util;

import entify.Button;
import entify.ClickButton;
import entify.ScancodePushButton;
import entify.ViewButton;
import net.sf.json.JSONObject;
import service.wxService;

public class CreateMenu {
	public static void main(String[] args) {
		//创建菜单
		Button btn = new Button();
		//第一个一级菜单
		btn.getButton().add(new ViewButton("商品管理", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7bede3a3d797d979&redirect_uri=http://jiang.nat300.top/weixin/Redirect&response_type=code&scope=snsapi_userinfo#wechat_redirect"));
		//转为JSON
		JSONObject jsonObject = JSONObject.fromObject(btn);
		
		//准备URL
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+wxService.getAccessToken();
		System.out.println(url);
		//发送请求
		String result = Utils.post(url, jsonObject.toString());
		System.out.println(jsonObject.toString());
		System.out.println(result);
	}
}
