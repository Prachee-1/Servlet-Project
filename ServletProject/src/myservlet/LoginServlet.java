package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String e,p;
		e=request.getParameter("em1");
		p=request.getParameter("pwd1");
		pw.println("email"+e);
		pw.println("password:"+p);
		
		try {
			Connection cn=ConnectionMaster.getConnection();
			PreparedStatement pst=cn.prepareStatement("select * from user where email=? and password=?");
			pst.setString(1,e);
			pst.setString(2,p);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				HttpSession hs=request.getSession(true);
				hs.setAttribute("emailid",e);
				response.sendRedirect("HomeServlet");
			}
			else {
				pw.print("<font color=red> Invalid username and password</font>");
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
