import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String id = req.getParameter("id");
		int id1 = Integer.parseInt(id);
		String name = req.getParameter("name");
		
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/frontendmiddlewarebackenddb?user=postgres&password=root123");
			
			PreparedStatement ps = con.prepareStatement("select * from customer where id = ? and name = ?");
			
			ps.setInt(1, id1);
			ps.setString(2, name);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				res.getWriter().print("<html><body><h2>Login Successful..!!</h2></body></html>");
			}else {
				res.getWriter().print("<html><body><h2>Invalid input!!</h2></body></html>");
			}
				
			
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