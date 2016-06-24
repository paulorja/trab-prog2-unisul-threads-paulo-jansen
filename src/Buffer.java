
import java.util.ArrayList;
 
public class Buffer {
 
    private String conteudo;
    private boolean disponivel;
 
    public synchronized void set(int idProdutor, String linha) {
        while (disponivel == true) {
            try {
                System.out.println("Produtor #" + idProdutor + " esperando...");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conteudo = linha;
        

		System.out.println("Produtor #" + idProdutor + " colocou " + conteudo);
	    disponivel = true;
	    notifyAll();
    }
 
    public synchronized String get(int idProdutor) {    	
        while (disponivel == false) {
            try {
                System.out.println("Consumidor #" + idProdutor
                        + " esperado...");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumidor #" + idProdutor + " consumiu: "
                + conteudo);
        disponivel = false;
        notifyAll();
        return conteudo;
    }
}