package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/ActionScan")
public class ActionScan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String qrCode = request.getParameter("qrcode");
		if("CODE_128,kasjd5465465".equals(qrCode)) {
			jsonObject.put("msg", "1");
			jsonObject.put("id", "1215646555");
			jsonObject.put("name", "脆脆鲨");
			jsonObject.put("price", "14.5元/个");
			jsonObject.put("begintime", "2019-09-01T16:12:00");
			jsonObject.put("overtime", "2020-03-01T16:12:00");
			jsonObject.put("mark", "这是测试商品！");
			response.getWriter().write(jsonObject.toString());
		}else {
			jsonObject.put("msg", "0");
			response.getWriter().write(jsonObject.toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String qrCode = request.getParameter("qrCode");
		 * request.setCharacterEncoding("utf-8");
		 * response.setCharacterEncoding("utf-8"); String[] code = qrCode.split(",");
		 * System.out.println(qrCode); Map<String, String> product = new HashMap<String,
		 * String>(); product.put("id", "1215646555"); product.put("name", "脆脆鲨");
		 * product.put("price", "14.5元/个"); product.put("begintime",
		 * LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
		 * product.put("overtime",
		 * LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)); product.put("mark",
		 * "这是测试商品！"); request.setAttribute("product", product);
		 * request.getRequestDispatcher("scan.jsp").forward(request, response);
		 */
		request.getRequestDispatcher("validateSuccess.jsp").forward(request, response);
	}

}
