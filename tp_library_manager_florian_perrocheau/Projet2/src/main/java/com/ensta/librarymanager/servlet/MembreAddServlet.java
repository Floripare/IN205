package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet{
	
	private MembreService membreService = MembreService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("nom") == null || request.getParameter("prenom") == null 
					|| request.getParameter("adresse") == null || request.getParameter("email") == null
					|| request.getParameter("telephone") == null || request.getParameter("abonnement") == null) {
				throw new ServletException();
			}
			else
			{
				int id = membreService.create(request.getParameter("nom"), 
						request.getParameter("prenom"), request.getParameter("adresse"),
						request.getParameter("email"), request.getParameter("telephone"), Abonnement.valueOf(request.getParameter("abonnement")));
				response.sendRedirect(request.getContextPath() + "/membre_details?id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
