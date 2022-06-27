package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet{
	
	private MembreService membreService = MembreService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_delete.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("id") == null) {
				throw new ServletException();
			}
			else
			{
				membreService.delete(Integer.parseInt(request.getParameter("id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/membre_list");
		
	}

}
