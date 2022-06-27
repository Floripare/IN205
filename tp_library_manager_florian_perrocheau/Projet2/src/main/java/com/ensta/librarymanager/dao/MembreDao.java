package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDao implements IMembreDao{

	private static MembreDao instance;
	
	private MembreDao() {}
	
	public static MembreDao getInstance() {
		if (instance == null) {
			instance = new MembreDao();
		}
		return instance;
	}
	
	
	public List<Membre> getList() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement\r\n"
					+ "FROM membre\r\n" + "ORDER BY nom, prenom");
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Membre> listMembre = new ArrayList<Membre>();
			
			while(rs.next())
			{
				if (rs.getString("abonnement") != null) {
					Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
					listMembre.add(new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement));
				}
				else
				{
					listMembre.add(new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), null));
				}
		
			}
			
			return listMembre;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public Membre getById(int id) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT nom, prenom, adresse, email, telephone, abonnement\r\n"
					+ "FROM membre\r\n" + "WHERE id = ?");

			pstmt.setInt(1, id);
			
			ResultSet rs =  pstmt.executeQuery();
			
			rs.next();

			if (rs.getString("abonnement") != null) {
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				Membre membre = new Membre(id, rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), abonnement);
				return membre;
			}
			else
			{
				Membre membre = new Membre(id, rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), null);
				return membre;
			}
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement)\r\n" + "VALUES (?, ?, ?, ?, ?, NULL)",
					Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setString(3, adresse);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			pstmt.executeUpdate();
			
			ResultSet rs =  pstmt.getGeneratedKeys();
			
			rs.next();
			
			int id = rs.getInt(1);
			
			return id;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public void update(Membre membre) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("UPDATE membre\r\n" + "SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?,\r\n"
					+ "abonnement = ?\r\n" + "WHERE id = ?;");
			
			pstmt.setString(1, membre.getNom());
			pstmt.setString(2, membre.getPrenom());
			pstmt.setString(3, membre.getAdresse());
			pstmt.setString(4, membre.getEmail());
			pstmt.setString(5, membre.getTelephone());
			pstmt.setString(6, membre.getAbonnement().name());
			pstmt.setInt(7, membre.getId());
			pstmt.executeUpdate();
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public void delete(int id) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("DELETE FROM membre WHERE id = ?");

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public int count() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT COUNT(id) AS count FROM membre");

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
