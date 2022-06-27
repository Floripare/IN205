package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet{
	
	private LivreService livreService = LivreService.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("livreId", request.getParameter("id"));
			request.setAttribute("livreAuteur", this.livreService.getById(Integer.parseInt(request.getParameter("id"))).getAuteur());
			request.setAttribute("livreTitre", this.livreService.getById(Integer.parseInt(request.getParameter("id"))).getTitre());
			request.setAttribute("livreIsbn", this.livreService.getById(Integer.parseInt(request.getParameter("id"))).getIsbn());
			request.setAttribute("livreEmprunts", this.empruntService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id"))));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("titre") == null || request.getParameter("auteur") == null || request.getParameter("isbn") == null) {
				throw new ServletException();
			}
			else
			{
				Livre livre = livreService.getById(Integer.parseInt(request.getParameter("id")));
				livre.setTitre(request.getParameter("titre"));
				livre.setAuteur(request.getParameter("auteur"));
				livre.setIsbn(request.getParameter("isbn"));
				livreService.update(livre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/livre_details?id=" + request.getParameter("id"));
		
	}
}
