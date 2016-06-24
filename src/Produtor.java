
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Produtor extends Thread {
    private int id_prod;
    private Buffer buffer;
    private String nomeArquivo;
    public String linha;

 
    public Produtor(int id, Buffer p, String nomeArquivo) {
        id_prod = id;
        buffer = p;
        this.nomeArquivo = nomeArquivo;
    }
    
 
    public void run() {    	
		InputStream leitorByte = null;
		InputStreamReader leitorCaracter = null;
		BufferedReader leitorPalavras = null;
		
		try {			
			leitorByte = new FileInputStream("entrada/"+nomeArquivo);
			leitorCaracter = new InputStreamReader(leitorByte);
			leitorPalavras = new BufferedReader(leitorCaracter);
						
			linha = leitorPalavras.readLine();
			
			while(linha != null) {
				
				buffer.set(id_prod, linha);
	        	
				linha = leitorPalavras.readLine();
			}
			
			buffer.set(id_prod, "FIM");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (leitorByte != null) {
					leitorByte.close();
				}
				if (leitorCaracter != null) {
					leitorCaracter.close();
				}
				if (leitorPalavras != null) {
					leitorPalavras.close();
				}
			} catch (Exception e) {}
		}
		
    }
}