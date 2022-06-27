package com.ensta.librarymanager.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDao implements ILivreDao{
	
	private static LivreDao instance;
	
	private LivreDao() {}
	
	public static LivreDao getInstance() {
		if (instance == null) {
			instance = new LivreDao();
		}
		return instance;
	}
	

	public List<Livre> getList() throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT id, titre, auteur, isbn FROM livre;");
			
			ResultSet rs =  pstmt.executeQuery();
			
			List<Livre> listLivre = new ArrayList<Livre>();
			
			while(rs.next())
			{
				listLivre.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn")));
				
			}
			
			return listLivre;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public int create(String titre, String auteur, String isbn) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, titre);
			pstmt.setString(2, auteur);
			pstmt.setString(3, isbn);
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
	
	
	public void update(Livre livre) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?");
			
			pstmt.setString(1, livre.getTitre());
			pstmt.setString(2, livre.getAuteur());
			pstmt.setString(3, livre.getIsbn());
			pstmt.setInt(4, livre.getId());
			pstmt.executeUpdate();
			
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	
	public void delete(int id) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("DELETE FROM livre WHERE id = ?");

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
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT COUNT(id) AS count FROM livre");

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
	

	public Livre getById(int id) throws DaoException {
		try (Connection comm = ConnectionManager.getConnection()){
			
			PreparedStatement pstmt = comm.prepareStatement("SELECT id, titre, auteur, isbn FROM LIVRE WHERE id = ?");

			pstmt.setInt(1, id);
			
			ResultSet rs =  pstmt.executeQuery();
			
			rs.next();

			Livre livre = new Livre(id, rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));
			
			return livre;
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
}
