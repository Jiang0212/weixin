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

@WebServlet("/addGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodserv = new GoodsServiceImpl();
	DateCal datecal = new DateCal();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
