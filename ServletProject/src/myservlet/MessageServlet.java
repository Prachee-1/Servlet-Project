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
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
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
		String em,msg;
		em=request.getParameter("em");
		msg=request.getParameter("msg");
		HttpSession hs=request.getSession(false);
		String sem=(String) hs.getAttribute("emailid");
		try {
			Connection cn=ConnectionMaster.getConnection();
			PreparedStatement pst=cn.prepareStatement("insert into msg values(?,?,?)");
			pst.setString(1, msg);
			pst.setString(2, sem);
			pst.setString(3, em);
			int ans=pst.executeUpdate();
			if(ans!=0) {
				pw.print("<script>alert('Message send');window.location='HomeServlet'</script>");
			}
			else {
				pw.print("<font color=red>Problem Occured</font>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
