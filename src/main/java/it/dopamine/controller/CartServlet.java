package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.dopamine.dao.CarrelloDAO;
import it.dopamine.dao.ProdottoDAO;
import it.dopamine.model.CarrelloItem;
import it.dopamine.model.Prodotto;
import it.dopamine.model.Utente;

public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarrelloDAO carrelloDAO = new CarrelloDAO();
    private ProdottoDAO prodottoDAO = new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");
        List<CarrelloItem> itemsCarrello = new ArrayList<>();

        if (utenteLoggato != null) {
            itemsCarrello = carrelloDAO.getCarrello(utenteLoggato.getId());
        } else {
            Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
            if (carrelloOspite != null) {
                for (Map.Entry<Integer, Integer> entry : carrelloOspite.entrySet()) {
                    Prodotto p = prodottoDAO.getProdottoById(entry.getKey());
                    if (p != null) {
                        itemsCarrello.add(new CarrelloItem(p, entry.getValue()));
                    }
                }
            }
        }

        double totaleGenerale = 0;
        for (CarrelloItem item : itemsCarrello) {
            totaleGenerale += item.getSubtotale();
        }

        request.setAttribute("itemsCarrello", itemsCarrello);
        request.setAttribute("totaleGenerale", totaleGenerale);
        
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");

        if ("add".equals(action)) {
            int idProdotto = Integer.parseInt(request.getParameter("id"));
            int qty = Integer.parseInt(request.getParameter("qty"));

            Prodotto prod = prodottoDAO.getProdottoById(idProdotto);
            if (prod == null || prod.getStock() < qty) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Quantità richiesta non disponibile a magazzino.");
                return;
            }

            if (utenteLoggato != null) {
                carrelloDAO.aggiungiProdotto(utenteLoggato.getId(), idProdotto, qty);
            } else {
                Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
                if (carrelloOspite == null) {
                    carrelloOspite = new HashMap<>();
                }
                int attualeQty = carrelloOspite.getOrDefault(idProdotto, 0);
                
                if (attualeQty + qty > prod.getStock()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Impossibile aggiungere: superato lo stock disponibile.");
                    return;
                }
                
                carrelloOspite.put(idProdotto, attualeQty + qty);
                session.setAttribute("carrelloOspite", carrelloOspite);
            }
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Prodotto aggiunto correttamente.");

        } else if ("remove".equals(action)) {
            int idProdotto = Integer.parseInt(request.getParameter("id"));

            if (utenteLoggato != null) {
                carrelloDAO.rimuoviProdotto(utenteLoggato.getId(), idProdotto);
            } else {
                Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
                if (carrelloOspite != null) {
                    carrelloOspite.remove(idProdotto);
                    session.setAttribute("carrelloOspite", carrelloOspite);
                }
            }

            List<CarrelloItem> itemsCarrello = new ArrayList<>();
            if (utenteLoggato != null) {
                itemsCarrello = carrelloDAO.getCarrello(utenteLoggato.getId());
            } else {
                Map<Integer, Integer> carrelloOspite = (Map<Integer, Integer>) session.getAttribute("carrelloOspite");
                if (carrelloOspite != null) {
                    for (Map.Entry<Integer, Integer> entry : carrelloOspite.entrySet()) {
                        Prodotto p = prodottoDAO.getProdottoById(entry.getKey());
                        if (p != null) itemsCarrello.add(new CarrelloItem(p, entry.getValue()));
                    }
                }
            }

            double nuovoTotale = 0;
            for (CarrelloItem item : itemsCarrello) {
                nuovoTotale += item.getSubtotale();
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.format("%.2f", nuovoTotale));
        }
    }
}