package servlet;

import domain.Goods;
import org.apache.commons.beanutils.BeanUtils;
import service.GoodsService;
import service.impl.GoodsServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 未使用
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Goods goods = new Goods();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String markid = request.getParameter("markid");
		if (markid == null) {
			response.sendRedirect("login.jsp");
			return;
		}        
        //4.调用Service修改
        GoodsService service = new GoodsServiceImpl();
        service.updategoods(goods,markid);

        //5.跳转到查询所有Servlet
        response.sendRedirect(request.getContextPath()+"/findAllServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
