import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pagea")
public class Pagea extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String name = req.getParameter("name");
			String mobno = req.getParameter("mobno");
			String email = req.getParameter("email");
					
			RequestDispatcher rd = req.getRequestDispatcher("pageb");
			rd.forward(req,resp);
			
			resp.getWriter().print("<html><body><h2>pagea has been executed successfully...!!</html></body></h2>");
		}
}
