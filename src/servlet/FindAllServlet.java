package servlet;

import domain.Goods;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.List;
import java.io.IOException;

@WebServlet("/findAllServlet")
public class FindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GoodsService service = new GoodsServiceImpl();
    Gson gson = new Gson();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String markid = (String) this.getServletContext().getAttribute("markid");
		if (markid == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		this.getServletContext().setAttribute("markid", markid);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		List<Goods> goods=service.findall(markid);//调用service查询所有商品
        String jsonstr = gson.toJson(goods);//将商品list转化为json数据
        
        System.out.println(jsonstr);
        response.getWriter().write(jsonstr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
