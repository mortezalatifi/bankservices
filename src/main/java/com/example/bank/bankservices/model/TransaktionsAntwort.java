package com.example.bank.bankservices.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransaktionsAntwort {
	private String kundenName;
	private int neuerStand;
	
	public TransaktionsAntwort() {
		
	}
	

	public TransaktionsAntwort(String kundenName, int neuerStand) {
		super();
		this.kundenName = kundenName;
		this.neuerStand = neuerStand;
	}


	public String getKundenName() {
		return kundenName;
	}

	public void setKundenName(String kundenName) {
		this.kundenName = kundenName;
	}

	public int getNeuerStand() {
		return neuerStand;
	}

	public void setNeuerStand(int neuerStand) {
		this.neuerStand = neuerStand;
	}
	
	
}
