package myservlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		RequestDispatcher rd1=request.getRequestDispatcher("Header.html");
		rd1.include(request, response);
		HttpSession hs=request.getSession(false);
		String em="";
		if(hs!=null) {
			em=(String)hs.getAttribute("emailid");
			try {
				Connection cn=ConnectionMaster.getConnection();
				Statement st= (Statement) cn.createStatement();
				ResultSet rs=((java.sql.Statement) st).executeQuery("select * from msg where receiver_email='"+em+"'");
				pw.print("<ul class='nav nav-pills col-sm-offset-8 col-sm-4'>");
				pw.print("<li class='active'><a href=send.html>Send Message</a></li>");
				pw.print("<li class='active'><a href=LogoutServlet>Logout</a></li></ul>");
				pw.print("<br><br><div class=jumbotron>");
				pw.print("<h1>Message For You</h1></div>");
				while(rs.next()) {
					String m=rs.getString("msg");
					pw.print("<div class=panel>");
					pw.print(m+"<br>");
					pw.print("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
