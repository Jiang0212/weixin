package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActionScan")
public class ActionScan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qrCode = request.getParameter("qrCode");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String[] code = qrCode.split(",");
		System.out.println("qrcodetype=" + code[0]);
		System.out.println("qrcodevalue=" + code[1]);
		Map<String, String> product = new HashMap<String, String>();
		product.put("code", code[1]);
		product.put("id", "1215646555");
		product.put("name", "脆脆鲨");
		product.put("price", "14.5元/个");
		product.put("begintime", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
		System.out.println(LocalDateTime.now());
		product.put("overtime", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
		request.setAttribute("product", product);
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}

}
