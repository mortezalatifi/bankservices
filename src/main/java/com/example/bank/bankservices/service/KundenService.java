package com.example.bank.bankservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.bank.bankservices.database.DatabaseClass;
import com.example.bank.bankservices.model.Kunde;

public class KundenService {
	Map<String, Kunde> kunden = DatabaseClass.getKunden();
	
	public Kunde addKunde(Kunde kunde) {
		kunde.setId(kunden.size()+1);
		kunden.put(kunde.getName(), kunde);
		return kunde;
	}
	
	public Kunde getKunde(String name) {
		return kunden.get(name);
	}
	
	public List<Kunde> getAllKunden(){
		return new ArrayList<>(kunden.values());
	}
}
