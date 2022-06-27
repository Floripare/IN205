package com.ensta.librarymanager.modele;

public enum Abonnement {
	BASIC(2), PREMIUM(5), VIP(20);
	
	private int limite;
	
	private Abonnement(int limite) {
		this.limite = limite;
	}
	
	public int getLimit() {
		return this.limite;
	}
}
