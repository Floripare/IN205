package com.ensta.librarymanager.modele;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private String isbn;
	
	public Livre(int id, String titre, String auteur, String ISBN){
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = ISBN;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
