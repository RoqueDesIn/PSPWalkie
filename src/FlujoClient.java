import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FlujoClient {
    private Socket Cliente=null;
    private DataOutputStream flujoSalida=null;
    private DataInputStream flujoEntrada=null;
    
	 /**
     * Constructor
     * @param puerto
     */

    
    public FlujoClient(int puerto) {
    	clientListen(puerto);
	}

	/**
     * Creamos el socket cliente y esperamos respuesta del servidor
     * @param puerto
     */
	public Socket clientListen (int puerto) {
		String Host = "localhost";
        int Puerto = puerto;//puerto remoto

		try {       
	        System.out.println("PROGRAMA CLIENTE INICIADO....");
			Cliente = new Socket(Host, Puerto);
			System.out.println("socket esperando");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Cliente;
	}
	
	/**
	 * El cliente recibe mensaje del servidor
	 */
	public String clientReceive() {
		String mensaje="";
		try {
			// CREO FLUJO DE ENTRADA AL SERVIDOR
			flujoEntrada = new DataInputStream(Cliente.getInputStream());
	        mensaje = flujoEntrada.readUTF();
	        // EL SERVIDOR ME ENVIA UN MENSAJE   
	        System.out.println("Recibiendo del SERVIDOR: \n\t" + mensaje);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mensaje;
	}
	
	/**
	 * Envia un mensaje como cliente al servidor
	 */
	public void clientSend(String mensaje) {
        try {
            // CREO FLUJO DE SALIDA AL SERVIDOR
            flujoSalida = new DataOutputStream(Cliente.getOutputStream());
            // ENVIO UN SALUDO AL SERVIDOR
			flujoSalida.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * cierra los objetos
	 */
	public void clientClose() {
		// CERRAR STREAMS Y SOCKETS
        try {
			flujoEntrada.close();
	        flujoSalida.close();
	        Cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
