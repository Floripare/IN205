package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreService implements ILivreService{
	
	private static LivreService instance;
	
	private LivreService() {}
	
	public static LivreService getInstance() {
		if (instance == null) {
			instance = new LivreService();
		}
		return instance;
	}
	
	private LivreDao livreDao = LivreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	public List<Livre> getList() throws ServiceException{
		
		try {
			return livreDao.getList();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public List<Livre> getListDispo() throws ServiceException {
		
		try {
			List<Livre> listLivre = this.getList();
			List<Livre> listLivresDispo = new ArrayList<Livre>();
			for (Livre livre : listLivre) {
				if (empruntService.isLivreDispo(livre.getId())) listLivresDispo.add(livre);
			}
			return listLivresDispo;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	
	public Livre getById(int id) throws ServiceException {
		
		try {
			return livreDao.getById(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		
		try {
			return livreDao.create(titre, auteur, isbn);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public void update(Livre livre) throws ServiceException {
		
		try {
			livreDao.update(livre);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public void delete(int id) throws ServiceException {
		
		try {
			livreDao.delete(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public int count() throws ServiceException {
		
		try {
			return livreDao.count();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
