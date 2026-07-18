package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import it.dopamine.dao.ProdottoDAO;
import it.dopamine.model.Prodotto;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query = request.getParameter("query");

		if (query != null && !query.trim().isEmpty()) {
			List<Prodotto> risultati = (new ProdottoDAO()).cercaProdotti(query.trim());
			request.setAttribute("risultati", risultati);
			request.setAttribute("query", query.trim());
		}

		request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);
	}
}