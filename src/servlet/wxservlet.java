package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import service.wxService;

@WebServlet("/wx")
public class wxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
			timestamp	时间戳
			nonce	随机数
			echostr	随机字符串
		 */
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//验证校验名
		if(wxService.check(timestamp,nonce,signature)) {
			PrintWriter out = response.getWriter();
			out.write(echostr);
			out.flush();
			out.close();
		}else {
			System.out.println("接入失败");
		}
	}
	
	/**
	 * 接收消息和事件推送
	 * 
	 * 接收打印测试
	 * ServletInputStream is = request.getInputStream();
		byte[] b = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len=is.read(b))!=-1) {
			sb.append(new String(b, 0, len));
		}
		System.out.println(sb.toString());
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String, String> requestMap = wxService.parseRequest(request.getInputStream());
		System.out.println(requestMap);
//		String respxml = "<xml>\r\n" + 
//				"  <ToUserName><![CDATA["+requestMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
//				"  <FromUserName><![CDATA["+requestMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
//				"  <CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime>\r\n" + 
//				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
//				"  <Content><![CDATA[why?]]></Content>\r\n" + 
//				"</xml>";
		//准备回复的数据包
		String respxml =wxService.getResponse(requestMap);
		System.out.println(respxml);
		PrintWriter out = response.getWriter();
		//out.write(respxml);
		out.flush();
		out.close();
	}

}
