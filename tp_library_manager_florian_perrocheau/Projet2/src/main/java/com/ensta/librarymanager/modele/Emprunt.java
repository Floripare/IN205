package com.ensta.librarymanager.modele;

import java.time.LocalDate;
import com.ensta.librarymanager.modele.Emprunt;

public class Emprunt {
	private int id;
	private int idMembre;
	private Membre membre;
	private int idLivre;
	private Livre livre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt(int id, int idMembre, String nom, String prenom, String adresse, 
			String email,String telephone, Abonnement abonnement, int idLivre, 
			String titre, String auteur, String isbn, LocalDate dateEmprunt, LocalDate dateRetour) {
		
		this.membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);
		this.livre = new Livre(idLivre, titre, auteur, isbn);
		this.id = id;
		this.idMembre = idMembre;
		this.idLivre = idLivre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	
	public Membre getMembre()
	{
		return this.membre;
	}
	
	public Livre getLivre() {
		return this.livre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}

	public int getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
}
