import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
/**
 * @see http://www.jc-mouse.net/
 * @author mouse
 */
public class MainServer1 {

    /**
     * Puerto 
     */
    private final static int PORT = 5000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit=false;//bandera para controlar ciclo del programa
        try {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado");    
            System.out.println("Servidor> En espera de cliente...");    
            //Socket de cliente
            Socket clientSocket;
            while(!exit){
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                //se lee peticion del cliente
                String request = input.readLine();
                System.out.println("El Cliente envia el mensaje>  [" + request +  "]");
				if(request.equals("exit")){//terminar aplicacion
                    exit=true;                  
                    System.out.println("Cliente> Fin de programa");    
                }  
                clientSocket.close();
            }    

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
}
