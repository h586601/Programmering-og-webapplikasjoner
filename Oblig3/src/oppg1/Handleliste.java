package oppg1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/handleliste")
public class Handleliste extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);

		if (sesjon == null) {
			response.sendRedirect("innlogging?requiresLogin");
		} else {

			Liste cart = (Liste) sesjon.getAttribute("liste");

			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();
			htmlhead("Handleliste", out);
			out.println("<body>");
			out.println("<h2>Min handleliste</h2>");
			out.println("<form action=\"" + "handleliste" + "\" method=\"post\">");
			out.println("<input type=\"submit\" value=\"Legg til\" />");
			out.println("<input type=\"text\" name=\"vare\" /></br></br>");
			out.println("</form>");

			for (String item : cart.getItems()) {
				out.println("<form action=\"" + "handleliste" + "\" method=\"post\">");
				out.println("<input type=\"submit\" value=\"Slett\" />");
				out.println("<input type=\"hidden\" id=\"itemid\" name=\"varenavn\" value=" + item + ">");
				out.println(item + "</br></br>");
				out.println("</form>");
			}

			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vare = request.getParameter("vare");
		String varenavn = request.getParameter("varenavn");
		
		HttpSession sesjon = request.getSession(false);

		if (sesjon == null) {
			response.sendRedirect("innlogging?requiresLogin");
		} else {
			Liste liste = (Liste) sesjon.getAttribute("liste");

			if (varenavn == null && !(vare.isBlank() || liste.finnes(vare))) {
				vare = escapeHtml(vare);
				liste.addItem(new String(vare));
			} else if (varenavn != null) {	
				if(varenavn.matches(".*[<>].*")) {
						varenavn = escapeHtml(varenavn);
				}
				liste.deleteItem(varenavn);
			} 

			response.sendRedirect("handleliste");
		}
	}

	public void htmlhead(String tittel, PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>" + tittel + "</title>");
		out.println("</head>");
	}
	
	private String escapeHtml(String s) {
		String resultat = s;
		resultat = resultat.replaceAll("<", "&lt;");
		resultat = resultat.replaceAll(">", "&gt;");
		//...
		return resultat;
	}
}
