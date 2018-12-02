package com.example.bank.bankservices.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Kunde {
	private long id;
	private String name;
	private String adresse;
	private List<Konto> konten = new ArrayList<>();
	
	public Kunde() {
		
	}
	
	public Kunde(String name, String adresse) {
		super();
		this.name = name;
		this.adresse = adresse;
	}

	public Kunde(long id, String name, String adresse) {
		super();
		this.id = id;
		this.name = name;
		this.adresse = adresse;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Konto> getKonten() {
		return konten;
	}

	public void setKonten(List<Konto> konten) {
		this.konten = konten;
	}
	
}

