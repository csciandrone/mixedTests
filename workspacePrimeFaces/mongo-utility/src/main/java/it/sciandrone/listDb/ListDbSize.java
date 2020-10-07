package it.sciandrone.listDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ListDbSize {

	public ListDbSize() {
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Inserire uri mongodb (es. mongodb://localhost:27017) ");
		String uri=getInputFromConsole();
		MongoClient mc=new MongoClient(new MongoClientURI(uri));
		MongoDatabase adminDatabase = mc.getDatabase("admin");
	    Document runCommand = adminDatabase.runCommand(new Document("listDatabases",1));
	 List<Document> listaDocuments =(List<Document>) runCommand.get("databases");
	 List<Database> listaDbs=new ArrayList<>();
	 for(Document database:listaDocuments){
		 listaDbs.add(new Database(database.getString("name"), database.getDouble("sizeOnDisk"), database.getBoolean("empty")));
	 }
	 Collections.sort(listaDbs);
	 System.out.println();
	 System.out.println("****************************************************");
	 System.out.println("Lista database in ordine decrescente di spazio disco");
	 for(int i=1;i<listaDbs.size();i++){
		 Database database = listaDbs.get(i-1);
		 System.out.println(String.format("%d - %s %s ", i,database.getNome(),database.getSizeHumanReadableFormat(2)));
	 }
	 System.out.println("****************************************************");
		mc.close();

	}
	
	public static String getInputFromConsole(){
		Scanner scanner=new Scanner(System.in);
		return scanner.nextLine();
	}
	
static class Database implements Comparable<Database>{
	private String nome;
	private double size;
	private boolean empty;
	
	private String format(double bytes,int cifre){
		String[] dizionario = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		 int index = 0;
	        for (index = 0; index < dizionario.length; index++) {
	            if (bytes < 1024) {
	                break;
	            }
	            bytes = bytes / 1024;
	        }
	        return String.format("%." + cifre + "f", bytes) + " " + dizionario[index];
	}
	
	public String getSizeHumanReadableFormat(int cifre){
		return format(this.size, cifre);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Database [nome=");
		builder.append(nome);
		builder.append(", size in bytes=");
		builder.append(size);
		builder.append(", empty=");
		builder.append(empty);
		builder.append("]");
		return builder.toString();
	}

	public Database(String nome, double size, boolean empty) {
		super();
		this.nome = nome;
		this.size = size;
		this.empty = empty;
	}
	
	public String getNome() {
		return nome;
	}

	public double getSize() {
		return size;
	}

	public boolean isEmpty() {
		return empty;
	}

	@Override
	public int compareTo(Database o) {
		if(o.getSize()<this.size){
			return -1;
		}
		else if(o.getSize()==this.size){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	
}
}
