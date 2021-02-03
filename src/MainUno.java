import java.rmi.server.ServerCloneException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainUno {

	
	public static void main(String[] args) {
		String menu="*******************************\n"+
				   "             Walkie UNO\n"+
				   "*******************************\n"
					+ "1. Hablar \r\n" + 
					"2. Esperar a recibir mensaje \r\n" + 
					"3. Salir";
		Scanner sc= new Scanner (System.in);
		String mensaje=null;
		FlujoServer server=null;
		FlujoClient client=null;
		int puerto= 6125;
		
		// Escribe el menú
		while (true) {
			// esribe el menú
			System.out.println(menu);
			// gestiona el menú
			String entrada= sc.nextLine();
			// switch tecla
			switch (entrada) {
			case "1":
				// se queda esperando handshake
				server= new FlujoServer(puerto);
				// recibe handshake
				server.serverReceive();
				// solicita el mensaje
				System.out.println("introduzca mensaje a enviar: ");
				mensaje = sc.nextLine();
				// envia el mensaje
				server.serverSend(mensaje);
				// cierra el server
				server.serverClose();
	 			System.out.println(menu);
				break;
			case "2":
				// Esperar mensaje, se convierte en cliente y espera mensaje
				// de saludo del servidor
				client= new FlujoClient(puerto);
				// recibe handshake
				client.clientReceive();
				// cierra el cliente
				client.clientClose();
				break;
			case "3":
				System.out.println("Programa finalizado por el usuario.");
				System.exit(0);
		        }
		}
	}
}