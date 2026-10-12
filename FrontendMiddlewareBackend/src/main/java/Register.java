import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class Register extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		PrintWriter writer = res.getWriter();
		writer.print("executed Successfully....!!");
		
		String id = req.getParameter("id");
		int id1 = Integer.parseInt(id);
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		String mobno = req.getParameter("mobno");
		long mobno1 = Long.parseLong(mobno);
		
		String desc = req.getParameter("desc");
		
		String sal = req.getParameter("sal");
		Double sal1 = Double.parseDouble(sal);
		
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/frontendmiddlewarebackenddb?user=postgres&password=root123");
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?)");
			
			ps.setInt(1, id1);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setLong(4,mobno1);
			ps.setString(5, desc);
			ps.setDouble(6, sal1);
			
			boolean rs = ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
