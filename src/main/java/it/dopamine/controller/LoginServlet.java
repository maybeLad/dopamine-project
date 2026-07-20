package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import it.dopamine.dao.UtenteDAO;
import it.dopamine.dao.CarrelloDAO; 
import it.dopamine.model.Utente;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

	    if ("logout".equals(request.getParameter("type"))) {
	        HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("utenteLoggato") != null) {
	            session.invalidate();
	        }
	        response.sendRedirect(request.getContextPath() + "/login");
	        return; 
	    }

	    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	UtenteDAO utenteDAO = new UtenteDAO();
    	Utente u = utenteDAO.checkLogin(email, password);
    	
    	if(u != null) {
    		HttpSession session = request.getSession();
            session.setAttribute("utenteLoggato", u);
            
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
            if (carrelloOspite != null && !carrelloOspite.isEmpty()) {
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                carrelloDAO.unisciCarrelloOspite(u.getId(), carrelloOspite);
                session.removeAttribute("carrelloOspite");
            }
            
            request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
    	} else {
    		request.setAttribute("errore", "Credenziali errate!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    	}
    }
}