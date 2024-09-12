package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		HttpSession hs=request.getSession();
		String n,e,c,p;
		n=request.getParameter("nm");
		e=request.getParameter("em");
		c=request.getParameter("contact");
		p=request.getParameter("pwd");
		try {
			Connection cn=ConnectionMaster.getConnection();
			PreparedStatement pst =cn.prepareStatement("insert into user values(?,?,?,?)");
			pst.setString(1, n);
			pst.setString(2, c);
			pst.setString(3, e);
			pst.setString(4, p);
			int ans=pst.executeUpdate();
			PrintWriter pw = null;
			if(ans!=0) {
				pw.print("<script>alert('Registration Successful');history.back()</script>");
			}
			else {
				pw.print("<script>alert('Problem Occured!');history.back()</script>");
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

