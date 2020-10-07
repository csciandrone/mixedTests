package it.sciandrone.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class JsonListValidator implements Validator{

	@Override
	public boolean validate(File f) {
		try
		{
		List<OggettoJson> listaOggetti=jsonObjects(f);
		Gson g=new Gson();
		int elemento=0;
		int riga=0;
		for(OggettoJson s:listaOggetti){
			elemento++;
			try {
				g.fromJson(s.getValore(), Object.class);
				riga=riga+s.getCountRighe();
			} catch (Exception e) {
				System.err.println(String.format("Attenzione errore nell'oggetto %d che inizia a riga %d, messaggio ", elemento,riga,e.getMessage()));
				e.printStackTrace();
				return false;
			}
		}
		return true;
		}
		catch(Exception ex){
			System.err.println("Errore nella lettura del file");
			ex.printStackTrace();
			return false;
		}
	}
	
	private List<OggettoJson> jsonObjects(File f) throws Exception{
		FileInputStream fis = new FileInputStream(f);
		 List<OggettoJson> listString=new ArrayList<>();
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    int count=0;
	    int contaRighe=0;
		String line = null;
		StringBuilder sb=new StringBuilder();
		while ((line = br.readLine()) != null) {
			contaRighe++;
			if(line.startsWith("{") || line.startsWith("}")){
				count++;
			}
			else
			{
				count=0;
			}
			if(count==2){
				OggettoJson obj=new OggettoJson(sb.toString(), contaRighe);
				contaRighe=0;
				sb.setLength(0);
				listString.add(obj);
				
			}
			sb.append(line);
		}
	    
		br.close();
		return listString;
	}
	
	public static void main(String[] args){
		ClassLoader classLoader = JsonListValidator.class.getClassLoader();
		File file = new File(classLoader.getResource("persone.json").getFile());
		Validator v=new JsonListValidator();
		System.out.println(v.validate(file));
	}

}
class OggettoJson{
	private String valore;
	private int countRighe;
	
	
	
	
	public OggettoJson(String valore, int countRighe) {
		super();
		this.valore = valore;
		this.countRighe = countRighe;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
	public int getCountRighe() {
		return countRighe;
	}
	public void setCountRighe(int countRighe) {
		this.countRighe = countRighe;
	}
	
	
	
}
