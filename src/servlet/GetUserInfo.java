package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import util.Utils;

@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取code
		String code = request.getParameter("code");
		//获取accesstoken的地址
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+"wx7bede3a3d797d979"+"&secret=85462b787edd0ce6b9a59bdfe9c9399d&code="+code+"&grant_type=authorization_code";
		String  result = Utils.get(url);
		String at= JSONObject.fromObject(result).getString("access_token");
		String openid= JSONObject.fromObject(result).getString("openid");
		url = "https://api.weixin.qq.com/sns/userinfo?access_token="+at+"&openid="+openid+"&lang=zh_CN";
		result = Utils.get(url);
		System.out.println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
