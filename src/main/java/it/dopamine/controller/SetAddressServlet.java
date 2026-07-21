package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import it.dopamine.dao.UtenteDAO;
import it.dopamine.model.Utente;
import it.dopamine.util.Connector;


public class SetAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/setAddress.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("utenteLoggato") == null) {
			String json = "{\"success\": false, \"errore\": \"Sessione scaduta, effettua di nuovo il login.\"}";
			response.getWriter().write(json);
			return;
		}

		Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");

		String via = request.getParameter("via");

		if (via != null) {
			via = via.trim();
			if (via.toLowerCase().startsWith("via ")) {
				via = via.substring(4).trim();
			}
		}

		final String address = via + ", " + request.getParameter("civico") + ", " + request.getParameter("citta") + ", " + request.getParameter("cap");

		UtenteDAO utenteDAO = new UtenteDAO();

		boolean esito = utenteDAO.changeAddress(address, utenteLoggato.getId());

		if (esito) {
			utenteLoggato.setIndirizzo(address);
			String json = "{\"success\": true}";
			response.getWriter().write(json);
		} else {
			String json = "{\"success\": false, \"errore\": \"Impossibile salvare l'indirizzo, riprova più tardi.\"}";
			response.getWriter().write(json);
		}
	}

}
