package no.hvl.dat108;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deltagerliste")
public class DeltagerlisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAOMemory deltagerDAO;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesjon = request.getSession(false);
		
		if(sesjon == null || sesjon.getAttribute("deltager") == null) {
			response.sendRedirect("innlogging?unauthorized");
		} else {
			
			// Functions for getting first and last names from an Deltager
			Function<Deltager, String> byFirstName = Deltager::getFornavn;
			Function<Deltager, String> byLastName = Deltager::getEtternavn;

			// Comparator for comparing Deltager by first name then last name
			Comparator<Deltager> firstThenLast = Comparator.comparing(byFirstName).thenComparing(byLastName);
		      
			List<Deltager> deltagere = deltagerDAO.hentAlle()
					.stream()
					.sorted(firstThenLast)
					.collect(Collectors.toList());
			
			sesjon.setAttribute("deltagere", deltagere);
			request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp")
			.forward(request, response);
			
		}

		
	}

}
