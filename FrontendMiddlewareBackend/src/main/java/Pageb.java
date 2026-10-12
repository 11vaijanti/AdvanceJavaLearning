import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pageb")
public class Pageb extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String mobno =req.getParameter("mobno");
		String email = req.getParameter("email");
		
		PrintWriter writer = resp.getWriter();
		writer.print(name);
		writer.print(mobno);
		writer.print(name);
		
		resp.getWriter().print("<html><body><h2>pageb has been executed successfully...!!</html></body></h2>");
	}
}
