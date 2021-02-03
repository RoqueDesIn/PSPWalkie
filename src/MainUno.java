import java.util.Scanner;

public class MainUno {

	static Scanner sc= new Scanner (System.in);
	static String mensaje="";
	public static void main(String[] args) {
	
		// definición de variables
		FlujoServer server=null;
		FlujoClient client=null;
		int puerto= 6125;
		
		// Escribe el menú
		while (true) {
			// esribe el menú
			pintaMenu("De momento nadie");
			// gestiona el menú
			String entrada= sc.nextLine();
			// switch tecla
			switch (entrada) {
			case "1":
	 			pintaMenu("Manolo su servidor");
				mensaje="";
				// Crea servidor
				server= new FlujoServer(puerto);
				iAmServer(server);
				// cierra el server
				server.serverClose();
				break;
			case "2":
	 			pintaMenu("Pepe su cliente");
				mensaje="";
				// crea el cliente
				client= new FlujoClient(puerto);
				// mientras no reciba "cambio y corto envia y recibe
				iAmClient(client);
				// cierra el cliente
				client.clientClose();
				break;
			case "3":
				System.out.println("Programa finalizado por el usuario.");
				System.exit(0);
		        }
		}
	}
	
	/**
	 * parte del servidor
	 * @param server
	 */
	private static void iAmServer(FlujoServer server) {	
		
		// mientras no reciba "cambio y corto envia y recibe
		while (!mensaje.contains("cambio y corto")) {
			// envía mensaje mientras que no reciba cambio
			mensaje="";
			while (!mensaje.startsWith("cambio")) {
				if (!mensaje.startsWith("cambio y corto")) {
					// solicita el mensaje
					System.out.println("Manolo introduzca mensaje a enviar: ");
					mensaje = sc.nextLine();
					// envia el mensaje
					server.serverSend(mensaje);
				} else {
					// envia el mensaje y corta
					server.serverSend(mensaje);
					mensaje="cambio";
				}
			}
			// mientras que el servidor sea distinto de cambio recibe
			mensaje = "";
			while (!mensaje.startsWith("cambio")) {
				// envia el mensaje
				mensaje=server.serverReceive();
			}
		}
	}
	/**
	 * Parte del cliente
	 * @param client
	 */
	private static void iAmClient (FlujoClient client) {
		
		// mientras no reciba "cambio y corto" recibe y envia
		while (!mensaje.contains("cambio y corto")) {
			mensaje="";
			// mientras que el servidor sea distinto de cambio recibe
			while (!mensaje.startsWith("cambio")) {
				// envia el mensaje
				mensaje=client.clientReceive();
			}
			//envía mensajes de forma recursiva hasta que reciba camio y corto o cambio
			if (!mensaje.startsWith("cambio y corto")) {
				mensaje="";
				while (!mensaje.startsWith("cambio") ) {
					// solicita el mensaje
					System.out.println("Pepe introduzca mensaje a enviar: ");
					mensaje = sc.nextLine();
					// envia el mensaje
					client.clientSend(mensaje);
				} 
						
			} else {
				// envia el mensaje y corta la conversación
				client.clientSend(mensaje);		
			}			
		}
	}
	
/**
 * Pinta el menú
 * @param whoAmI
 */
	private static void pintaMenu(String whoAmI) {
		String menu="*******************************\n"+
				   "             Walkie " + whoAmI
				   + "\n"+
				   "*******************************\n"
					+ "1. Hablar \r\n" + 
					"2. Esperar a recibir mensaje \r\n" + 
					"3. Salir";
		System.out.println(menu);
	};
	
	// acaba Main
}