import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MainCliente2 {
	
    /**
    * Puerto
    * */
    private final static int PORT = 80;
    /**
    * Host
    * */
    private final static String SERVER = "localhost";
    
    public static void main(String[] args) {
    	boolean exit=false;//bandera para controlar ciclo del programa
        Socket socket;//Socket para la comunicacion cliente servidor        
        try {            
            System.out.println("Cliente> Inicio");  
            while( !exit ){//ciclo repetitivo                                
                socket = new Socket(SERVER, PORT);//abre socket                
                //Para leer lo que envie el servidor      
                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
                //para imprimir datos del servidor
                PrintStream output = new PrintStream(socket.getOutputStream());                
                //Para leer lo que escriba el usuario            
                BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));            
                System.out.println("Cliente> Escriba comando");                
                //captura comando escrito por el usuario
                String request = brRequest.readLine();                
                //manda peticion al servidor
				String request2 = "GET / HTTP/1.1 \n"+
				"Host: 127.0.0.1:5000 \n"+
				"Connection: keep-alive \n"+
				"Upgrade-Insecure-Requests: 1 \n"+
				"User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 \n"+
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp ;q=0.8 \n"+
				"Accept-Encoding: gzip, deflate, sdch, br \n"+
				"Accept-Language: en-US,en;q=0.8,es;q=0.6  ";
                output.println(request2); 
                //captura respuesta e imprime
                String st = input.readLine();
                if( st != null ) System.out.println("Servidor> " + st );    
                if(request.equals("exit")){//terminar aplicacion
                    exit=true;                  
                    System.out.println("Cliente> Fin de programa");    
                }  
                socket.close();
            }//end while                                    
       } catch (IOException ex) {        
         System.err.println("Cliente> " + ex.getMessage());   
       }
    }
    
}
