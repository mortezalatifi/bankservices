package com.example.bank.bankservices.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.bank.bankservices.model.TransaktionsTyp;

@XmlRootElement
public class Transaktion{
	private TransaktionsTyp typ;
	private int betrag;
	
	public Transaktion() {
		
	}

	public Transaktion(TransaktionsTyp typ, int betrag) {
		super();
		this.typ = typ;
		this.betrag = betrag;
	}

	public TransaktionsTyp getTyp() {
		return typ;
	}

	public void setTyp(TransaktionsTyp typ) {
		this.typ = typ;
	}

	public int getBetrag() {
		return betrag;
	}

	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}
	
	
	
}
