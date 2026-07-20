package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import it.dopamine.dao.UtenteDAO;
import it.dopamine.dao.CarrelloDAO;
import it.dopamine.model.Utente;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String email = request.getParameter("email");
    	String tel = request.getParameter("mobilenumber");
    	String password = request.getParameter("password");
    	
    	String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    	
    	Utente u = new Utente();
    	u.setNome(nome);
    	u.setCognome(cognome);
    	u.setEmail(email);
    	u.setTelefono(tel);
    	u.setPassword(hashPassword);
    	
    	UtenteDAO utenteDAO = new UtenteDAO();
    	utenteDAO.registraUtente(u);
    	
    	Utente utenteSalvato = utenteDAO.checkLogin(email, password);
    	
    	HttpSession session = request.getSession();
        session.setAttribute("utenteLoggato", utenteSalvato != null ? utenteSalvato : u);

        if (utenteSalvato != null) {
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
            if (carrelloOspite != null && !carrelloOspite.isEmpty()) {
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                carrelloDAO.unisciCarrelloOspite(utenteSalvato.getId(), carrelloOspite);
                session.removeAttribute("carrelloOspite");
            }
        }
        
        response.sendRedirect(request.getContextPath());
    }
}