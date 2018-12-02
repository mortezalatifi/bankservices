package com.example.bank.bankservices.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.bank.bankservices.model.Konto;
import com.example.bank.bankservices.model.Kunde;
import com.example.bank.bankservices.service.KontoService;
import com.example.bank.bankservices.service.KundenService;

@Path("kunden")
public class KundenResource {
	KundenService kundenService = new KundenService();
	KontoService kontoService = new KontoService();
	
	//1.Erzeugung eines neuen Kunden
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Kunde addKunde(Kunde kunde) {
		return kundenService.addKunde(kunde);
	}
	
	//2.Abfrage der Kunden-Resource für einen Kuneden mit gegebenem Namen
	@GET
	@Path("/{kundenName}")
	@Produces(MediaType.APPLICATION_XML)
	public Kunde getKundebyName(@PathParam("kundenName") String name) {
		return kundenService.getKunde(name);
	}
		
	//3.Für einen Kunden mit gegebenem Namen ein neues Konto erstellen	
	@POST
	@Path("/{kundenName}/konten")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String addKonto(@PathParam("kundenName") String kundenName, String stand) {
		return Integer.toString(kontoService.addKonto(kundenName, stand));
	}
	
	//5.Für einen Kunden mit einem gegebenen Namen die Liste aller Konten.Objekte des Kunden liefern
	@GET
	@Path("/{kundenName}/konten")
	@Produces(MediaType.APPLICATION_XML)
	public List<Konto> getAllKontenVonKunde(@PathParam("kundenName") String kundenName) {
		return kontoService.getAllKontenVonKunde(kundenName);
	}
	
	//8.Die Liste aller Kunden-Objekte im Format XML erfragen
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Kunde> getAllKunden(){
		return kundenService.getAllKunden();
	}
}
