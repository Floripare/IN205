package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDao implements IEmpruntDao{
	
	private static EmpruntDao instance;
	
	private EmpruntDao() {}
	
	public static EmpruntDao getInstance() {
		if (instance == null) {
			instance = new EmpruntDao();
		}
		return instance;
	}
	
	
	public List<Emprunt> getList() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour\r\n"
					+ "FROM emprunt AS e\r\n" + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\r\n" + "ORDER BY dateRetour DESC;");
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Emprunt> listEmprunt = new ArrayList<Emprunt>();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				int idLivre = rs.getInt("idLivre");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				
				if (rs.getDate("dateRetour") != null) {
					LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
					listEmprunt.add(new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour));
				}
				else
				{
					listEmprunt.add(new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, null));
				}
		
			}
			
			return listEmprunt;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public List<Emprunt> getListCurrent() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour\r\n"
					+ "FROM emprunt AS e\r\n" + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\r\n" + "WHERE dateRetour IS NULL;");
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Emprunt> listEmprunt = new ArrayList<Emprunt>();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				int idLivre = rs.getInt("idLivre");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				
				listEmprunt.add(new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, null));
		
			}
			
			return listEmprunt;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour\r\n"
					+ "FROM emprunt AS e\r\n" + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\r\n" + "WHERE dateRetour IS NULL AND membre.id = ?;");
			
			pstmt.setInt(1,  idMembre);
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Emprunt> listEmprunt = new ArrayList<Emprunt>();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				int idLivre = rs.getInt("idLivre");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				
				listEmprunt.add(new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, null));
		
			}
			
			return listEmprunt;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour\r\n"
					+ "FROM emprunt AS e\r\n" + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\r\n" + "WHERE dateRetour IS NULL AND livre.id = ?;");
			
			pstmt.setInt(1, idLivre);
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Emprunt> listEmprunt = new ArrayList<Emprunt>();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				
				listEmprunt.add(new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, null));
		
			}
			
			return listEmprunt;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public Emprunt getById(int id) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour\r\n"
					+ "FROM emprunt AS e\r\n" + "INNER JOIN membre ON membre.id = e.idMembre\r\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\r\n" + "WHERE e.id = ?;");

			pstmt.setInt(1, id);
			
			ResultSet rs =  pstmt.executeQuery();
			
			rs.next();
			
			int idMembre = rs.getInt("idMembre");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
			int idLivre = rs.getInt("idLivre");
			String titre = rs.getString("titre");
			String auteur = rs.getString("auteur");
			String isbn = rs.getString("isbn");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();

			if (rs.getDate("dateRetour") != null) {
				LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
				Emprunt emprunt = new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour);
				return emprunt;
			}
			else
			{
				Emprunt emprunt = new Emprunt(id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, null);
				return emprunt;
			}
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\r\n" + "VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, idMembre);
			pstmt.setInt(2, idLivre);
			pstmt.setDate(3, java.sql.Date.valueOf(dateEmprunt));
			pstmt.setDate(4, java.sql.Date.valueOf(dateRetour));
			pstmt.executeUpdate();
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public void update(Emprunt emprunt) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("UPDATE emprunt\r\n" + "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?,\r\n"
					+ "WHERE id = ?;");
			
			pstmt.setInt(1, emprunt.getIdMembre());
			pstmt.setInt(2, emprunt.getIdLivre());
			pstmt.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
			pstmt.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetour()));
			pstmt.setInt(5, emprunt.getId());
			pstmt.executeUpdate();
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public int count() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT COUNT(id) AS count FROM emprunt");

			ResultSet rs =  pstmt.executeQuery();
			
			rs.next();

			int res = rs.getInt("count");
			
			return res;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
}
