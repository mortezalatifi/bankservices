package com.example.bank.bankservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.bank.bankservices.database.DatabaseClass;
import com.example.bank.bankservices.model.Konto;
import com.example.bank.bankservices.model.Kunde;
import com.example.bank.bankservices.model.Transaktion;
import com.example.bank.bankservices.model.TransaktionsAntwort;
import com.example.bank.bankservices.model.TransaktionsTyp;

public class KontoService {
	Map<String, Kunde> kunden = DatabaseClass.getKunden();
	Map<Integer, Konto> kontos = DatabaseClass.getKonten();
	Map<Konto, Kunde> kundenKonten = DatabaseClass.getKundenKonten();
	
	public int addKonto(String kundenName, String stand) {
		Kunde kunde = kunden.get(kundenName);
		
		Konto neuesKonto = new Konto();
		//neuesKonto.setKunde(kunde);
		neuesKonto.setStand(Integer.parseInt(stand));
		neuesKonto.setNummer(kontos.size()+1);
		
		List<Konto> kundenKontos = kunde.getKonten();
		kundenKontos.add(neuesKonto);
				
		kunde.setKonten(kundenKontos);
		
		kunden.put(kunde.getName(), kunde);
		kontos.put(neuesKonto.getNummer(), neuesKonto);
		kundenKonten.put(neuesKonto, kunde);
		
		return neuesKonto.getNummer();
	}
	
	public List<Konto> getAllKontenVonKunde(String kundenName){
		Kunde kunde = kunden.get(kundenName);
		List<Konto> konten = kunde.getKonten();
		return konten;
	}
	
	public String getKontoStand(int kontoNumber) {
		Konto konto = kontos.get(kontoNumber);
		return Integer.toString(konto.getStand());
	}
	
	public List<Transaktion> getKontoTransaktionen(int kontonummer, String transaktionsTyp){
		Konto konto = kontos.get(kontonummer);
		List<Transaktion> transaktionen = konto.getTransaktionen();
		List<Transaktion> ausgabe = new ArrayList<>();
		for (Transaktion t : transaktionen) {
			if (transaktionsTyp.equals("Einzahlung") && t.getTyp() == TransaktionsTyp.Einzahlung) {
				ausgabe.add(t);
			} else if (transaktionsTyp.equals("Auszahlung") && t.getTyp() == TransaktionsTyp.Auszahlung) {
				ausgabe.add(t);
			}
		}
		return ausgabe;
	}
	
	public TransaktionsAntwort doTransaktion(int kontonummer, Transaktion transaktion) {
		Konto konto = kontos.get(kontonummer);
		Kunde kunde = kundenKonten.get(konto);
		if(transaktion.getTyp() == TransaktionsTyp.Einzahlung) {
			konto.setStand(konto.getStand() + transaktion.getBetrag());
		} else if(transaktion.getTyp() == TransaktionsTyp.Auszahlung){
			konto.setStand(konto.getStand() - transaktion.getBetrag());
		}
	List<Transaktion> list = konto.getTransaktionen();
	list.add(transaktion);
	konto.setTransaktionen(list);
	
	TransaktionsAntwort ta = new TransaktionsAntwort(kunde.getName(), konto.getStand());
	
	return ta;
	}
}
