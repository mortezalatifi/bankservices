package com.example.bank.bankservices.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.bank.bankservices.model.Konto;
import com.example.bank.bankservices.model.Kunde;
import com.example.bank.bankservices.model.Transaktion;
import com.example.bank.bankservices.model.TransaktionsAntwort;
import com.example.bank.bankservices.model.TransaktionsTyp;

public class BankClient {
	
	public static void main(String[] args) {
		
		Client klient = ClientBuilder.newClient();		
		WebTarget basisTarget = klient.target("http://localhost:55554/bankservices/");
		
		WebTarget kundenTarget = basisTarget.path("kunden");
		WebTarget einzelnKundeTarget = kundenTarget.path("{kundenName}");
		WebTarget kundenKonten = einzelnKundeTarget.path("konten");
		
		WebTarget konten = basisTarget.path("konten");
		WebTarget transaktionsTarget = konten.path("{kontonummer}/transaktionen");
		WebTarget kontoStandTarget = konten.path("{kontonummer}/stand");
		
		//1. Die Kunden erstellen
		System.out.println("Aufgabe 01:");
		Kunde peter = new Kunde("Peter", "Bonn");
		Kunde michael = new Kunde("Michael", "Aachen");
		Kunde klaus = new Kunde("Klaus", "Paderborn");
		
		kundenTarget.request().post(Entity.xml(peter));
		kundenTarget.request().post(Entity.xml(michael));
		kundenTarget.request().post(Entity.xml(klaus));
		
		//2.Kunde Peter erfragen
		System.out.println("Aufgabe 02:");
		Kunde peterInfo = einzelnKundeTarget.resolveTemplate("kundenName", "Peter").request(MediaType.APPLICATION_XML).get(Kunde.class);
		System.out.println("Hier sind die Infos ==> Kundenname: \"" 
							+ peterInfo.getName() 
							+ "\", Adresse: \"" 
							+ peterInfo.getAdresse() 
							+ "\"");
		
		//3.Für jeden Kunden ein Konto einreichen
		System.out.println("Aufgabe 03:");
		Response peterNeuesKonto = kundenKonten.resolveTemplate("kundenName", "Peter").request(MediaType.TEXT_PLAIN).post(Entity.text("50"));
		int peterKontonummer = Integer.parseInt(peterNeuesKonto.readEntity(String.class));
		System.out.println("Kontonummer von Peter: " + peterKontonummer);
		
		Response michaelNeuesKonto = kundenKonten.resolveTemplate("kundenName", "Michael").request(MediaType.TEXT_PLAIN).post(Entity.text("80"));
		int michaelKontonummer = Integer.parseInt(michaelNeuesKonto.readEntity(String.class));
		System.out.println("Kontonummer von Michael: " + michaelKontonummer);
			
		Response klausNeuesKonto = kundenKonten.resolveTemplate("kundenName", "Klaus").request(MediaType.TEXT_PLAIN).post(Entity.text("120"));
		int klausKontonummer = Integer.parseInt(klausNeuesKonto.readEntity(String.class));
		System.out.println("Kontonummer von Klaus: " + klausKontonummer);
		
		//4.Auf das Konto von „Michael“ sollen 30€ eizahlen
		System.out.println("Aufgabe 04:");
		Transaktion michaelEinzahlung = new Transaktion(TransaktionsTyp.Einzahlung, 30);
		Response MichaelEinzahlungResponse = transaktionsTarget.resolveTemplate("kontonummer", 2).request(MediaType.APPLICATION_XML).post(Entity.xml(michaelEinzahlung));
		TransaktionsAntwort michaelEinzahlunsAntwort = MichaelEinzahlungResponse.readEntity(TransaktionsAntwort.class);
		System.out.println("Neue Einzahlung für \"" + michaelEinzahlunsAntwort.getKundenName() + "\", Neuer Kontostand: " + michaelEinzahlunsAntwort.getNeuerStand() + "€");
		
		//5.Alle Konten von Michael ausgeben
		System.out.println("Aufgabe 05:");
		List<Konto> michaelKontenRp = kundenKonten.resolveTemplate("kundenName", "Michael").request(MediaType.APPLICATION_XML).get(new GenericType<List<Konto>>(){});
		System.out.println("Es gibt \"" + michaelKontenRp.size() + "\" Konto(en) von Michael:");
		for (Konto m : michaelKontenRp) {
			System.out.println("Kontonummer: \"" + m.getNummer() + "\", Kontostand: \"" + m.getStand() + "\".");
		}
		
		//6. Von dem Konto von „Michael“ sollen 20€ abgehoben werden.
		System.out.println("Aufgabe 06:");
		Transaktion michaelAuszahlung = new Transaktion(TransaktionsTyp.Auszahlung, 20);
		Response MichaelAuszahlungResponse = transaktionsTarget.resolveTemplate("kontonummer", 2).request(MediaType.APPLICATION_XML).post(Entity.xml(michaelAuszahlung));
		TransaktionsAntwort michaelAuszahlunsAntwort = MichaelAuszahlungResponse.readEntity(TransaktionsAntwort.class);
		System.out.println("Neue Auszahlung für \"" + michaelAuszahlunsAntwort.getKundenName() + "\", Neuer Kontostand: " + michaelAuszahlunsAntwort.getNeuerStand() + "€");
		
		//7.Für das Konto von „Michael“ der aktuelle Kontostand erfragen.
		System.out.println("Aufgabe 07:");
		String michaelKontoStand = kontoStandTarget.resolveTemplate("kontonummer", michaelKontenRp.get(0).getNummer()).request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("Kontostand von \"Michael\":" + michaelKontoStand + "€");
		
		//8.Für das Konto von „Michael“ sollen alle Einzahlungen abfragen
		System.out.println("Aufgabe 08:");
		List<Transaktion> michaelEinzahlungen = transaktionsTarget.resolveTemplate("kontonummer", michaelKontonummer).queryParam("typ", "Einzahlung").request(MediaType.APPLICATION_XML).get(new GenericType<List<Transaktion>>(){});
		System.out.println("Die Einzahlungen von Michael: ");
		for(Transaktion tb : michaelEinzahlungen) {
			System.out.println("+" + tb.getBetrag() + "€");
		}
		
		//9.Für das Konto von „Michael“ sollen alle Auszahlungen abgefragen
		System.out.println("Aufgabe 09:");
		List<Transaktion> michaelAuszahlungen = transaktionsTarget.resolveTemplate("kontonummer", michaelKontonummer).queryParam("typ", "Auszahlung").request(MediaType.APPLICATION_XML).get(new GenericType<List<Transaktion>>(){});
		System.out.println("Die Auszahlungen von Michael: ");
		for(Transaktion tb : michaelAuszahlungen) {
			System.out.println("-" + tb.getBetrag() + "€");
		}
		
		//10. Es soll die Liste aller Kunden erfragt werden und dann die Namen der Kunden ausgegeben werden.
		System.out.println("Aufgabe 10:");
		List<Kunde> kundenList = kundenTarget.request(MediaType.APPLICATION_XML).get(new GenericType<List<Kunde>>(){});
		System.out.println("Die Lsite aller Kunden:");
		int zaehler = 0;
		for (Kunde k : kundenList) {
			System.out.println(++zaehler + ". " + k.getName());
		}

	}
}