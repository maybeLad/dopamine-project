package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import it.dopamine.dao.OrdineDAO;
import it.dopamine.dao.ProdottoDAO;
import it.dopamine.dao.UtenteDAO;
import it.dopamine.model.CarrelloItem;
import it.dopamine.model.Utente;

public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        UtenteDAO utenteDAO = new UtenteDAO();
        utente = utenteDAO.getUtente(utente.getId());
        session.setAttribute("utenteLoggato", utente);

        boolean haIndirizzo = utente.getIndirizzo() != null && !utente.getIndirizzo().trim().isEmpty();
        boolean haPagamento = utente.getMetodo_pagamento() != null && !utente.getMetodo_pagamento().trim().isEmpty();

        if (!haIndirizzo) {
            request.setAttribute("redirectReason", "Manca l'indirizzo di spedizione per proseguire con l'ordine.");
            request.getRequestDispatcher("WEB-INF/views/user.jsp").forward(request, response); 
            return;
        }

        if (!haPagamento) {
            request.setAttribute("redirectReason", "Manca un metodo di pagamento per completare l'ordine.");
            request.getRequestDispatcher("WEB-INF/views/user.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("WEB-INF/views/checkout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        List<CarrelloItem> carrello = (List<CarrelloItem>) session.getAttribute("carrello");

        if (utente == null || carrello == null || carrello.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        double totale = 0;
        for (CarrelloItem item : carrello) {
            totale += item.getSubtotale();
        }

        OrdineDAO ordineDAO = new OrdineDAO();
        int idOrdine = ordineDAO.creaOrdine(utente.getId(), totale, utente.getIndirizzo(), utente.getMetodo_pagamento());

        if (idOrdine != -1) {
            ProdottoDAO prodottoDAO = new ProdottoDAO();

            for (CarrelloItem item : carrello) {
                ordineDAO.aggiungiDettaglioOrdine(idOrdine, item.getProdotto().getId(), item.getQuantita(), item.getProdotto().getPrezzo());
                prodottoDAO.scaricaStock(item.getProdotto().getId(), item.getQuantita());
            }

            session.removeAttribute("carrello");

            request.setAttribute("successMessage", "Ordine #" + idOrdine + " effettuato con successo!");
            request.getRequestDispatcher("WEB-INF/views/user.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Errore durante il completamento dell'ordine. Riprova.");
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}