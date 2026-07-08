package it.dopamine.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.dopamine.dao.ProdottoDAO;

/**
 * Servlet implementation class ProductPageServlet
 */
@WebServlet("/ProductPageServlet")
public class ProductPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productName", ProdottoDAO.getProdotto(request.getParameter("productName")));
        request.getRequestDispatcher("WEB-INF/views/productPage.jsp").forward(request, response);
    }

}
