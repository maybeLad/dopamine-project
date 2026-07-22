package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import it.dopamine.dao.ProdottoDAO;
import it.dopamine.dao.ReviewDAO;
import it.dopamine.model.Prodotto;
import it.dopamine.model.Utente;

public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        List<Prodotto> prodotti = prodottoDAO.getAllProdotti();

        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("/WEB-INF/views/user/createReview.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_prodotto= Integer.parseInt(request.getParameter("prodotto"));
		int value= Integer.parseInt(request.getParameter("voto"));
		String description = (String) request.getParameter("descrizione");
		
		HttpSession session = request.getSession(false);
				
		ReviewDAO reviewDAO = new ReviewDAO();
		boolean esito = reviewDAO.insertReview(((Utente)session.getAttribute("utenteLoggato")).getId(), id_prodotto, value, description);
		
		if(esito) {
			session.setAttribute("reviewConfirm", true);
		} else {
			session.setAttribute("reviewConfirm", false);
		}
		
		response.sendRedirect(request.getContextPath() + "/reviewCompleted");
		
	}

}
