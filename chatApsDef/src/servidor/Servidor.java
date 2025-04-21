package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        
        //servidor que faz a conexão 127.1.0.0
        //preciso entender pq ele aceita qualquer um
        try {
            ServerSocket servidor = new ServerSocket(7000);
            System.out.println("Servidor Iniciado");
            while(true){
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado, IP:" + cliente.getInetAddress());
                InputStreamReader fluxoDados = new InputStreamReader(cliente.getInputStream());
                BufferedReader dado = new BufferedReader(fluxoDados);
                String mensagem = dado.readLine(); // Lê a linha

                if (mensagem != null) {
                    System.out.println("Mensagem recebida: " + mensagem);
                }
                
           }
            
        }
        catch (IOException e){
                  System.out.println("Ocorreu um erro"); 
                    }
    }
    
}
