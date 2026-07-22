package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.dopamine.dao.AdminDAO;
import it.dopamine.model.Utente;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO = new AdminDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utenteLoggato");

        if (utente == null || !utente.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("prodotti", adminDAO.getAllProdotti());
        request.setAttribute("utenti", adminDAO.getAllUtenti());
        request.setAttribute("ordini", adminDAO.getAllOrdini());

        request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utenteLoggato");

        if (utente == null || !utente.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("saveProdotto".equals(action)) {
            adminDAO.salvaProdotto(
                request.getParameter("id"),
                request.getParameter("nome"),
                request.getParameter("descrizione"),
                Double.parseDouble(request.getParameter("prezzo")),
                Integer.parseInt(request.getParameter("stock")),
                request.getParameter("url_immagine")
            );
        } else if ("deleteProdotto".equals(action)) {
            adminDAO.eliminaProdotto(Integer.parseInt(request.getParameter("id")));
        } else if ("toggleAdmin".equals(action)) {
            adminDAO.toggleAdminUtente(
                Integer.parseInt(request.getParameter("id")),
                Boolean.parseBoolean(request.getParameter("currentAdmin"))
            );
        } else if ("deleteUtente".equals(action)) {
            adminDAO.eliminaUtente(Integer.parseInt(request.getParameter("id")));
        } else if ("updateStatoOrdine".equals(action)) {
            adminDAO.aggiornaStatoOrdine(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("stato")
            );
        } else if ("deleteOrdine".equals(action)) {
            adminDAO.eliminaOrdine(Integer.parseInt(request.getParameter("id")));
        }

        response.sendRedirect(request.getContextPath() + "/adminDashboard");
    }
}