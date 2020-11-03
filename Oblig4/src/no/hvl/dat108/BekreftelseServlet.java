package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bekreftelse")
public class BekreftelseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAOMemory deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);

		if (sesjon == null || sesjon.getAttribute("deltager") == null) {
			response.sendRedirect("innlogging?unauthorized");
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/bekreftelse.jsp")
			.forward(request, response);
		}
	}

}
