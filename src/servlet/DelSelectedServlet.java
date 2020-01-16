package servlet;



import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	GoodsService service = new GoodsServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String markid = (String) this.getServletContext().getAttribute("markid");
		this.getServletContext().setAttribute("markid", markid);
		//1.获取所有id
        String ids = request.getParameter("ids");
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
			System.out.println(idstr[i]);
		}
        //2.调用service删除
       service.delSelectedgoods(idstr,markid);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
