package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.dopamine.dao.UtenteDAO;
import it.dopamine.model.Utente;

public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/changePassword.jsp").forward(request, response);	
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

	    String oldPassword = request.getParameter("oldPassword");
	    String newPassword = request.getParameter("newPassword");
	    String email = ((Utente)session.getAttribute("utenteLoggato")).getEmail();

	    UtenteDAO utenteDAO = new UtenteDAO();

        if (utenteDAO.cambiaPassword(email, oldPassword, newPassword)) {
            String json = "{\"success\": true}";
            response.getWriter().write(json);
        } else {
            String json = "{\"success\": false, \"errore\": \"La password attuale non è corretta.\"}";
            response.getWriter().write(json);
        }
        
	}
}
