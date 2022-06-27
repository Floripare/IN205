package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet{
	
	private LivreService livreService = LivreService.getInstance();
	private MembreService membreService = MembreService.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("livresDispo", this.livreService.getListDispo());
			request.setAttribute("membresDispo", this.membreService.getListMembreEmpruntPossible());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null) {
				throw new ServletException();
			}
			else
			{
				empruntService.create(Integer.parseInt(request.getParameter("idMembre")), 
						Integer.parseInt(request.getParameter("idLivre")), LocalDate.now(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/emprunt_list");
	}

}
