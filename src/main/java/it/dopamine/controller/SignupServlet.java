package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import it.dopamine.dao.UtenteDAO;
import it.dopamine.model.Utente;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/user/signup.jsp").forward(request, response);
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
    	
    	(new UtenteDAO()).registraUtente(u);
    	
    	HttpSession session = request.getSession();
        session.setAttribute("utenteLoggato", u);
        response.sendRedirect(request.getContextPath());
    	
    }
}