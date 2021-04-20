package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InnloggingServlet")
public class InnloggingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String correctPassword;
	int intervall;

	@Override
	public void init() {
		correctPassword = getServletConfig().getInitParameter("password");
		intervall = Integer.parseInt(getServletContext().getInitParameter("sek"));
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = request.getParameter("error");
		
		if(error!=null) {
			//Vis feilmelding i JSP
		}
		
		
		request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String passord = request.getParameter("passord");
		
		if(passord != correctPassword) {
			System.out.println("Feil passord " + passord);
			response.sendRedirect("InnloggingServlet" + "?error");
		}
		
		
	}

}
