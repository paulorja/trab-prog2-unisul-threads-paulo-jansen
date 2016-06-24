
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Consumidor extends Thread {
    private int id_cons;
    private Buffer buffer;
	public ArrayList<String> cidades;

 
    public Consumidor(int id, Buffer p) {
        id_cons = id;
        buffer = p;
        cidades = new ArrayList<String>();
    }
    
    public String formata_saida(String texto) {
    	String[] splited_text = texto.split(";");
    	String texto_formatado = "";
    	
    	if (splited_text.length>1) {
    		texto_formatado = "ID:"+splited_text[0]+";";
        	texto_formatado += "Nome:"+splited_text[1]+";";
        	texto_formatado += "Sexo:"+splited_text[2]+";";
        	texto_formatado += "Cidade:"+splited_text[3]+";";   	
        	texto_formatado += "Estado:"+splited_text[4]+";";   	
        	texto_formatado += "Acertos:"+splited_text[5]+";";   	
        	texto_formatado += "Classificacao:"+splited_text[6]+";";   	
    	}
    	
    	
    	return texto_formatado;
    }
    
    public void salvar_cidade(String nova_cidade) {
    	try {
    		if (!cidades.contains(nova_cidade.split(";")[3])) {
            	cidades.add(nova_cidade.split(";")[3]);
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void escreve_cidades() {
    	Collections.sort(cidades);
    	
    	OutputStream escritorByte = null;
		OutputStreamWriter escritorCaracter = null;
		BufferedWriter escritorPalavras = null;
				
		try {
			
			escritorByte = new FileOutputStream("saida/cidades.txt", true);
			escritorCaracter = new OutputStreamWriter(escritorByte);
			escritorPalavras = new BufferedWriter(escritorCaracter);
						
			for (String cidade: cidades) {
				escritorPalavras.write(cidade);
				escritorPalavras.newLine();
				escritorPalavras.flush();
			}
    			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (escritorByte != null) {
					escritorByte.close();
				}
				if (escritorCaracter != null) {
					escritorCaracter.close();
				}
				if (escritorPalavras != null) {
					escritorPalavras.close();
				}
			} catch (Exception e) {}
		}
    }
 
    public void run() {
    	OutputStream escritorByte = null;
		OutputStreamWriter escritorCaracter = null;
		BufferedWriter escritorPalavras = null;
				
		try {
			
			escritorByte = new FileOutputStream("saida/saida.txt", true);
			escritorCaracter = new OutputStreamWriter(escritorByte);
			escritorPalavras = new BufferedWriter(escritorCaracter);
						
			String texto_produzido = "";
			String texto_formatado;
			
	    	while(true) {
	    		if(texto_produzido.equals("FIM")) {
	    			escreve_cidades();
	    			break;
	    		}
    			texto_produzido = buffer.get(id_cons);
    			texto_formatado = formata_saida(texto_produzido);
    			salvar_cidade(texto_produzido);

		    		
				escritorPalavras.write(texto_formatado);
				escritorPalavras.newLine();
				escritorPalavras.flush();
	    	}
    			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (escritorByte != null) {
					escritorByte.close();
				}
				if (escritorCaracter != null) {
					escritorCaracter.close();
				}
				if (escritorPalavras != null) {
					escritorPalavras.close();
				}
			} catch (Exception e) {}
		}
    		
        
    }
}