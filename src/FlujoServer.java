import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FlujoServer {
    private Socket clienteConectado = null;
	private ServerSocket servidor = null;
    private OutputStream salida = null;
    private InputStream entrada = null;
    private DataInputStream flujoEntrada=null;
    private DataOutputStream flujoSalida=null;
    
    /**
     * Constructor
     * @param puerto
     */
	public FlujoServer(int puerto) {
		serverListen(puerto);
	}


	/**
	 * crea el socket servidor y espera respuesta del cliente
	 * @param puerto
	 */
	public ServerSocket serverListen (int puerto) {	
        try {
        	int numeroPuerto = puerto;// Puerto
            servidor = new ServerSocket(numeroPuerto);
            System.out.println("Esperando al cliente.....");
			// esperando respuesta del cliente
            clienteConectado = servidor.accept();
            System.out.println("el cliente acepta...");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servidor;
	}
	
	
	/**
	 * Creamos flujo de entrada al servidor
	 * y recibimos mensaje del cliente
	 */
	public void serverReceive () {
        try {
        	// CREO FLUJO DE ENTRADA DEL CLIENTE   
	        entrada = clienteConectado.getInputStream();
	        flujoEntrada = new DataInputStream(entrada);
	       
	        // EL CLIENTE ME ENVIA UN MENSAJE
	        System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());
	        
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * creamos flujo de salida y
	 * enviamos mensaje al cliente
	 */
	public void serverSend(String mensaje) {
		// CREO FLUJO DE SALIDA AL CLIENTE
        try {
			salida = clienteConectado.getOutputStream();
	        flujoSalida = new DataOutputStream(salida);
	        
	        // ENVIO UN SALUDO AL CLIENTE
	        flujoSalida.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Cerramos todos los objetos usados
	 */
	public void serverClose() {
        // CERRAR STREAMS Y SOCKETS
        try {
			entrada.close();
	        flujoEntrada.close();
	        salida.close();
	        flujoSalida.close();
	        clienteConectado.close();
	        servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
