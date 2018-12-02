package com.example.bank.bankservices.database;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.bank.bankservices.model.Konto;
import com.example.bank.bankservices.model.Kunde;

@XmlRootElement
public class DatabaseClass {
	private static Map<String, Kunde> kunden = new HashMap<>(); 
	private static Map<Integer, Konto> konten = new HashMap<>();
	private static Map<Konto, Kunde> kundenKonten = new HashMap<>();
	
	public static Map<String, Kunde> getKunden() {
		return kunden;
	}
	
	public static Map<Integer, Konto> getKonten() {
		return konten;
	}

	public static Map<Konto, Kunde> getKundenKonten() {
		return kundenKonten;
	}
	
	
}