package it.sciandrone.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class GenerateData {

	public static void main(String[] args) throws Exception{
		
		MongoClient mongo=new MongoClient();
		MongoDatabase database = mongo.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("persone");
		collection.drop();
		List<Persona> generaPersone = generaPersone(10);
		Gson gson=new Gson();
		for(Persona p:generaPersone){
			collection.insertOne(Document.parse(gson.toJson(p)));
		}
		mongo.close();
		System.out.println("Generata lista");
         
	}

	private static List<Persona>  generaPersone(int n) {
		List<Persona> listaPersone=new ArrayList<>();
		for(int i=0;i<n;i++){
			Persona p=new Persona();
			List<Indirizzo> listaIndirizzi=new ArrayList<>();
			p.setCognome(UUID.randomUUID().toString());
			p.setNome(UUID.randomUUID().toString());
			p.setEta(new Random().nextInt(100));
			Indirizzo indirizzo=new Indirizzo();
			indirizzo.setVia(UUID.randomUUID().toString());
			indirizzo.setComune(UUID.randomUUID().toString());
			indirizzo.setProvincia(UUID.randomUUID().toString());
			indirizzo.setCivico(new Random().nextInt(300));
			listaIndirizzi.add(indirizzo);
			p.setListaIndirizzi(listaIndirizzi);
			listaPersone.add(p);
		}
		return listaPersone;
	}

}
