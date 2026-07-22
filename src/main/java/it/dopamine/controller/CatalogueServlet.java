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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	    
	    String categoria = request.getParameter("categoria");
	    String prezzoMinStr = request.getParameter("prezzoMin");
	    String prezzoMaxStr = request.getParameter("prezzoMax");

	    Double prezzoMin = (prezzoMinStr != null && !prezzoMinStr.isEmpty()) ? Double.parseDouble(prezzoMinStr) : null;
	    Double prezzoMax = (prezzoMaxStr != null && !prezzoMaxStr.isEmpty()) ? Double.parseDouble(prezzoMaxStr) : null;

	    ProdottoDAO dao = new ProdottoDAO();
	    Map<String, List<Prodotto>> catalogo = dao.getProdottiFiltrati(categoria, prezzoMin, prezzoMax);

	    request.setAttribute("catalogo", catalogo);
	    request.getRequestDispatcher("/WEB-INF/views/navigation/catalogo.jsp").forward(request, response);
	}
}