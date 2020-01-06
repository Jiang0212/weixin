package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.wxService;
import util.WxJSUtil;

@WebServlet("/Redirect")
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		url = URLDecoder.decode(url, "UTF-8" );
		Map<String, String> ret = WxJSUtil.sign(url);
		JSONObject jsonObject = JSONObject.fromObject(ret);
		jsonObject.put("appId",wxService.APPID);
		response.getWriter().write(jsonObject.toString());
	}

}
