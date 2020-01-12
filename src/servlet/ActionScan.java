package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
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
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String[] qrcode = request.getParameter("qrcode").split(",");
		Goods good = goodserv.findgoods(qrcode[1]);
		if (good == null) {
			jsonObject.put("msg", "0");
			response.getWriter().write(jsonObject.toString());
		}else {
			jsonObject = JSONObject.fromObject(goodserv.toMap(good));
			jsonObject.put("day", datecal.calDate((String)jsonObject.get("validTime")));
			jsonObject.put("msg", "1");
			System.out.println("yes");
			response.getWriter().write(jsonObject.toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		Goods good = new Goods();
		good.setId(request.getParameter("id"));
		good.setName(request.getParameter("name"));
		good.setSize(request.getParameter("size"));
		String day = request.getParameter("day");
		good.setValidTime(request.getParameter("validTime"));
		if(day=="" || day == null) {
			good.setDay(datecal.calDate(good.getValidTime())+"");
		}else {
			good.setDay(day);
		}
		good.setUnit(request.getParameter("unit"));
		System.out.println(good.toString());
		if (goodserv.updategoods(good)<1) {
			if (goodserv.addgoods(good)<1){
				jsonObject.put("msg", "0");
				System.out.println(0);
				response.getWriter().write(jsonObject.toString());
			}
			else {
				jsonObject.put("msg", "1");
				System.out.println(1);
				response.getWriter().write(jsonObject.toString());
			}
		}else {
			jsonObject.put("msg", "1");
			System.out.println(1);
			response.getWriter().write(jsonObject.toString());
		}
	}

}
