package servlet;

import domain.Goods;
import net.sf.json.JSONObject;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import util.DateCal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
修改单个商品重显或者查询单个商品
*/

@WebServlet("/findoneServlet")
public class FindOneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	GoodsService goodserv = new GoodsServiceImpl();
	DateCal datecal = new DateCal();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取门店编号
		String markid = request.getParameter("markid");
		if (markid == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		// 再次存入application中
		this.getServletContext().setAttribute("markid", markid);
		// 创建返回的json对象
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		// 获取传入的条形码后半部分的值
		String[] qrcode = request.getParameter("qrcode").split(",");
		// 查询商品
		Goods good = goodserv.findgoods(qrcode[1], markid);
		if (good == null) {
			// 查询失败则返回0
			jsonObject.put("msg", "0");
			response.getWriter().write(jsonObject.toString());
		} else {
			// 查询成功则返回1，以及商品信息;
			jsonObject = JSONObject.fromObject(goodserv.toMap(good));
			jsonObject.put("day", datecal.calDate((String) jsonObject.get("validTime")));
			jsonObject.put("msg", "1");
			System.out.println("yes");
			response.getWriter().write(jsonObject.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
