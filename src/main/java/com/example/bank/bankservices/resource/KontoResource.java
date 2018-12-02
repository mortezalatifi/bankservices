package com.example.bank.bankservices.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.bank.bankservices.model.Transaktion;
import com.example.bank.bankservices.model.TransaktionsAntwort;
import com.example.bank.bankservices.service.KontoService;

@Path("konten")
public class KontoResource {
		
	KontoService kontoService = new KontoService();
	
	
	//4.Für ein Konto mit gegebener Kontonummer eine neue Transaktion anlegen
	@POST
	@Path("/{kontonummer}/transaktionen")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public TransaktionsAntwort transaktion(@PathParam("kontonummer") int kontonummer, Transaktion transaktion){
		return kontoService.doTransaktion(kontonummer, transaktion);
	}
	
	//6.Für ein Konto mit gegebenr Kontonummer den aktuellen Kontostand liefern
	@GET
	@Path("/{kontoNumber}/stand")
	@Produces(MediaType.TEXT_PLAIN)
	public String getKontoStand(@PathParam("kontoNumber") int kontoNumber) {
		return kontoService.getKontoStand(kontoNumber);
	}
	
	//7.Für ein Konto mit gegebener Kontonummer alle Einzahlungen bzw. alle Auszahlungen abfragen
	@GET
	@Path("/{kontonummer}/transaktionen")
	@Produces(MediaType.APPLICATION_XML)
	public List<Transaktion> getKontoTransaktionen(@PathParam("kontonummer") int kontonummer, @QueryParam("typ") String transaktionsTyp){
		return kontoService.getKontoTransaktionen(kontonummer, transaktionsTyp);
	}
}
