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

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet{
	
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("empruntListCurrent", this.empruntService.getListCurrent());
			request.setAttribute("empruntList", this.empruntService.getList());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("idMembre") == null ) {
				throw new ServletException();
			}
			else
			{
				empruntService.returnBook(Integer.parseInt(request.getParameter("id"))); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/emprunt_list");
	}

}
