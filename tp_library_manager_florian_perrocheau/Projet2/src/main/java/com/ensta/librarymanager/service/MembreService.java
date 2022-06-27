package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;

public class MembreService implements IMembreService{

	private static MembreService instance;
	
	private MembreService() {}
	
	public static MembreService getInstance() {
		if (instance == null) {
			instance = new MembreService();
		}
		return instance;
	}
	
	private MembreDao membreDao = MembreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	
	public List<Membre> getList() throws ServiceException{
		
		try {
			return membreDao.getList();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		
		try {
			List<Membre> listMembreDispo = new ArrayList<Membre>();
			for(Membre membre : this.getList()) {
				if (empruntService.isEmpruntPossible(membre)) listMembreDispo.add(membre);
			}
			return listMembreDispo;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public Membre getById(int id) throws ServiceException {
		
		try {
			return membreDao.getById(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws ServiceException {
		
		try {
			return membreDao.create(nom, prenom, adresse, email, telephone, abonnement);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public void update(Membre membre) throws ServiceException {
		
		try {
			membreDao.update(membre);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public void delete(int id) throws ServiceException {
		
		try {
			membreDao.delete(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	public int count() throws ServiceException {
		
		try {
			return membreDao.count();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
