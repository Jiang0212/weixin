package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao  userimp =  new  UserDaoImpl();
	GoodsService goodsService = new GoodsServiceImpl();
       
;	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 校验验证码
		 * 1.从session中获取正确的验证码
		 * 2.从表单中获取用户填写的验证码
		 * 3.进行比较！
		 * 4.如果相同，向下运行，否则保存错误信息到request域中，转发到login.jsp
		 * */
		String sessionCode = (String)request.getSession().getAttribute("session_vcode");
		String paramCode = request.getParameter("verifyCode");
		
		if (!paramCode.equalsIgnoreCase(sessionCode)) {
			request.setAttribute("msg", "验证码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else {
			String username = request.getParameter("user");
			String password = request.getParameter("password");
			User user = userimp.checkLogin(username, password);
	        if(user!=null){
	    		ServletContext application = this.getServletContext();
	        	goodsService.updateDay(user.getMarkid());
	        	System.out.println(user.getMarkid());
	            System.out.println("登陆成功");
	            System.out.println(user.getMarket());
	        	application.setAttribute("markid", user.getMarkid());
	            application.setAttribute("market", user.getMarket());
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }else{
	        	System.out.println("用户名或密码错误");
	        	request.setAttribute("msg", "用户名或密码错误！");
	        	request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
			return;
		}
	}

}
