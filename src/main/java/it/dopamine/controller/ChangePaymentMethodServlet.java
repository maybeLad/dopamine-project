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


public class ChangePaymentMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePaymentMethodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/changePaymentMethod.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
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
	    String metodoPagamento = request.getParameter("metodoPagamento");
	    String numeroCarta = request.getParameter("numeroCarta");
	    String scadenzaCarta = request.getParameter("scadenzaCarta");

	    String ultimeCifre = null;

	    if ("Carta".equalsIgnoreCase(metodoPagamento)) {
	        if (numeroCarta != null) {
	            numeroCarta = numeroCarta.replaceAll("\\s+", "");

	            if (numeroCarta.length() >= 4) {
	                ultimeCifre = numeroCarta.substring(numeroCarta.length() - 4);
	            }
	        }
	    } else {
	        scadenzaCarta = null;
	    }

	    UtenteDAO utenteDAO = new UtenteDAO();

	    boolean esito = utenteDAO.changePaymentMethod(metodoPagamento, ultimeCifre, scadenzaCarta, utenteLoggato.getId());
	    if (esito) {
	        utenteLoggato.setMetodo_pagamento(metodoPagamento);
	        utenteLoggato.setCarta_ultime_4_cifre(ultimeCifre);
	        utenteLoggato.setScadenza_carta(scadenzaCarta);

	        String json = "{\"success\": true}";
	        response.getWriter().write(json);
	    } else {
	        String json = "{\"success\": false, \"errore\": \"Impossibile aggiornare il metodo di pagamento.\"}";
	        response.getWriter().write(json);

	    }
	}

}
