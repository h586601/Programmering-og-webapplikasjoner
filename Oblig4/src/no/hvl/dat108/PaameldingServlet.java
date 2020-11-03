package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/paamelding")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAOMemory deltagerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/paamelding.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Skjema skjema = new Skjema(request); 
		boolean finnesIkke = deltagerDAO.hentDeltager(skjema.getMobil()) == null;
		
		if (skjema.isAllInputGyldig() && finnesIkke) {

			Deltager deltager = skjema.lagDeltager();
			deltagerDAO.lagreNyDeltager(deltager);

			HttpSession sesjon = nySesjon(request);

			sesjon.setAttribute("deltager", deltager);
			request.getSession().removeAttribute("skjema"); // Fjerner eventuelt objekt knyttet til sesjonen
			response.sendRedirect("bekreftelse");

		} else {

			if(!finnesIkke) {
				response.sendRedirect("innlogging?finnesAllerede");
			} else {
				skjema.settOppFeilmeldinger();

				request.getSession().setAttribute("skjema", skjema);
				response.sendRedirect("paamelding");
			}			
		}
	}

	public HttpSession nySesjon(HttpServletRequest request) {

		HttpSession sesjon = request.getSession(false);

		if (sesjon != null) {
			sesjon.invalidate(); // Gjøre gammel sesjon ugyldig
		}

		sesjon = request.getSession(true); // Generere ny
		sesjon.setMaxInactiveInterval(60);

		return sesjon;
	}

}
