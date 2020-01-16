package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Goods;
import net.sf.json.JSONObject;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import util.DateCal;

@WebServlet("/ActionScan")
public class ActionScan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodserv = new GoodsServiceImpl();
	DateCal datecal = new DateCal();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取门店编号
		String markid = request.getParameter("markid");
		if(markid==null) {
			response.sendRedirect("login.jsp");
		}
		//再次存入application中
		this.getServletContext().setAttribute("markid", markid);
		//创建返回的json对象
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		//获取传入的条形码后半部分的值
		String[] qrcode = request.getParameter("qrcode").split(",");
		//查询商品
		Goods good = goodserv.findgoods(qrcode[1],markid);
		if (good == null) {
			//查询失败则返回0
			jsonObject.put("msg", "0");
			response.getWriter().write(jsonObject.toString());
		}else {
			//查询成功则返回1，以及商品信息;
			jsonObject = JSONObject.fromObject(goodserv.toMap(good));
			jsonObject.put("day", datecal.calDate((String)jsonObject.get("validTime")));
			jsonObject.put("msg", "1");
			System.out.println("yes");
			response.getWriter().write(jsonObject.toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建返回的json对象
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		//创建要存储的商品对象
		Goods good = new Goods();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		String day = request.getParameter("day");
		if(id=="" || id==null) {
			jsonObject.put("msg", "0");
			System.out.println(0);
			response.getWriter().write(jsonObject.toString());
			return;
		}
		good.setId(id);
		good.setName(name);
		good.setSize(size);
		good.setValidTime(request.getParameter("validTime"));
		if(day=="" || day == null) {
			good.setDay(datecal.calDate(good.getValidTime())+"");
		}else {
			good.setDay(day);
		}
		good.setUnit(request.getParameter("unit"));
		
		String markid = (String) this.getServletContext().getAttribute("markid");
		this.getServletContext().setAttribute("markid", markid);
		System.out.println(good.toString());
		if (goodserv.updategoods(good,markid)<1) {
			if (goodserv.addgoods(good,markid)<1){
				jsonObject.put("msg", "0");
				System.out.println(0);
				response.getWriter().write(jsonObject.toString());
				return;
			}
			else {
				jsonObject.put("msg", "1");
				System.out.println(1);
				response.getWriter().write(jsonObject.toString());
				return;
			}
		}else {
			jsonObject.put("msg", "1");
			System.out.println(1);
			response.getWriter().write(jsonObject.toString());
			return;
		}
	}

}
