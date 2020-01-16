package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

@WebServlet("/deleteOneServlet")
public class DeleteOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodService = new GoodsServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取门店编号
		String markid = (String) this.getServletContext().getAttribute("markid");
		this.getServletContext().setAttribute("markid", markid);
		
		//获取商品编号
		String id = request.getParameter("ids");
		System.out.println(id);
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		if (goodService.deleteOnegoods(id,markid) < 1) {
			jsonObject.put("msg", "0");
			System.out.println("删除失败");
			response.getWriter().write(jsonObject.toString());
		} else {
			jsonObject.put("msg", "1");
			System.out.println("删除成功");
			response.getWriter().write(jsonObject.toString());
		}
	}

}
