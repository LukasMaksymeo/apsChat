package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Servidor {
    private static List<Socket> clientes = new ArrayList<>(); // Lista para guardar todos os clientes conectados
    private static HashMap<Socket, String> clientesNomes = new HashMap<>();  // Mapeia Socket -> Nome
    
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Servidor Iniciado");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado, IP:" + cliente.getInetAddress());

                clientes.add(cliente); // Adiciona o novo cliente na lista

                // Cria uma thread para cada cliente
                new Thread(() -> tratarCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro");
            e.printStackTrace();
        }
    }

            //Recebe as mensagens dos Clientes
    private static void tratarCliente(Socket cliente) {
        try {
            BufferedReader dado = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            // Primeira mensagem do cliente deve ser o nome
            String nome = dado.readLine();
            clientesNomes.put(cliente, nome);
            System.out.println(nome + " entrou no chat.");
            String historico = BDmensagens.carregarHistorico(10);
            saida.println(historico);

            String mensagem;
            while ((mensagem = dado.readLine()) != null) {
                System.out.println(nome + " disse: " + mensagem);
                BDmensagens.salvarMensagem(nome, mensagem);
                enviarParaTodos(nome + ": " + mensagem);  // Adiciona o nome antes da mensagem
                
            }
        } catch (IOException e) {
            String nome = clientesNomes.get(cliente);
            System.out.println(nome + " saiu do chat.");
            enviarParaTodos(nome + " saiu do chat " );
            clientes.remove(cliente);
            clientesNomes.remove(cliente);
        }
    }

    private static void enviarParaTodos(String mensagem) {
        for (Socket cliente : clientes) {
            try {
                PrintStream saida = new PrintStream(cliente.getOutputStream());
                saida.println(mensagem); // Envia a mensagem para o cliente
            } catch (IOException e) {
                System.out.println("Erro ao enviar mensagem para um cliente");
                clientes.remove(cliente);
            }
        }
    }
}
