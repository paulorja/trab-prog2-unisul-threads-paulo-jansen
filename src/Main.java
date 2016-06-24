

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
		
		try {
			limpar_saida("saida/saida.txt");
			limpar_saida("saida/cidades.txt");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao limpar as saidas");
		}
		
	    Buffer bufferCompartilhado = new Buffer();
	    
	    Produtor produtor = new Produtor(1, bufferCompartilhado, "prova2.txt");
	    Consumidor consumidor = new Consumidor(1, bufferCompartilhado);
	 
	    produtor.start();
	    consumidor.start();	    

	}
	
	public static void limpar_saida(String caminho) throws IOException {
		FileWriter saida = new FileWriter(caminho);
		saida.write("");
		saida.close();
    }

}
