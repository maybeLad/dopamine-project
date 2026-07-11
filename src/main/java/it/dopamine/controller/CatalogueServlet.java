package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.dopamine.dao.ProdottoDAO;
import it.dopamine.model.Prodotto;

public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, List<Prodotto>> catalogo = (new ProdottoDAO()).getProdottiRaggruppati();
		request.setAttribute("catalogo", catalogo);

		request.getRequestDispatcher("/WEB-INF/views/catalogo.jsp").forward(request, response);
	}
}