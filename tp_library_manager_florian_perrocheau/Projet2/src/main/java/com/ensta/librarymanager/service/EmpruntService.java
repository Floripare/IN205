package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntService implements IEmpruntService{
	
	private static EmpruntService instance;
	
	private EmpruntService() {}
	
	public static EmpruntService getInstance() {
		if (instance == null) {
			instance = new EmpruntService();
		}
		return instance;
	}

	private EmpruntDao empruntDao = EmpruntDao.getInstance();
	
	
	public List<Emprunt> getList() throws ServiceException {
			
		try {
			return empruntDao.getList();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public List<Emprunt> getListCurrent() throws ServiceException {
		
		try {
			return empruntDao.getListCurrent();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		
		try {
			return empruntDao.getListCurrentByMembre(idMembre);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		
		try {
			return empruntDao.getListCurrentByLivre(idLivre);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public Emprunt getById(int id) throws ServiceException {
		
		try {
			return empruntDao.getById(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) throws ServiceException {
		
		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt, dateRetour);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public void returnBook(int id) throws ServiceException {
		
		try {
			Emprunt emprunt = empruntDao.getById(id);
			emprunt.setDateRetour(LocalDate.now());
			empruntDao.update(emprunt);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public int count() throws ServiceException {
		
		try {
			return empruntDao.count();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		
		try {
			List<Emprunt> listEmprunt = getListCurrent();
			for (Emprunt emprunt : listEmprunt) {
				if (emprunt.getIdLivre() == idLivre) return false;
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		
		try {
			return getListCurrentByMembre(membre.getId()).size() < membre.getAbonnement().getLimit();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
}
