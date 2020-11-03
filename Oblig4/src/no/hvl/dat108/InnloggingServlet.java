package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/innlogging")
public class InnloggingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAOMemory deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("feilmelding") != null) {
			request.setAttribute("feilmelding", "-");
		} else if (request.getParameter("unauthorized") != null) {
			request.setAttribute("unauthorized", "-");
		} else if (request.getParameter("finnesAllerede") != null) {
			request.setAttribute("finnesAllerede", "-");
		}

		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");

		Deltager deltager = deltagerDAO.hentDeltager(mobil);

		if (deltager != null) {
			
			String salt = deltager.getPassordhash().getPwd_salt();
			String passordhash = deltager.getPassordhash().getPwd_hash();
			boolean valid = Passord.validerMedSalt(passord, salt, passordhash);
			
			if (valid) {
				
				HttpSession sesjon = nySesjon(request);
				sesjon.setAttribute("deltager", deltager);
				response.sendRedirect("deltagerliste");
				
			} else {
				response.sendRedirect("innlogging?feilmelding");
			}
		} else {
			response.sendRedirect("innlogging?feilmelding");
		}

	}
	
	
	public HttpSession nySesjon(HttpServletRequest request) {
		
		HttpSession sesjon = request.getSession(false);
        if (sesjon != null) {
            sesjon.invalidate();
        }
        sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(10);

		return sesjon;
	}

}
