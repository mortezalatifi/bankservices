package com.example.bank.bankservices.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Konto {
	private int stand;
	private int nummer;
	private List<Transaktion> transaktionen = new ArrayList<>();
	
	public Konto() {
		
	}
	
	
	public Konto(Kunde kunde, int stand, int nummer) {
		super();
		this.stand = stand;
		this.nummer = nummer;
	}


	public int getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public List<Transaktion> getTransaktionen() {
		return transaktionen;
	}
	
	public void setTransaktionen(List<Transaktion> transaktionen) {
		this.transaktionen = transaktionen;
	}
	
	
}
