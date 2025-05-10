package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Servidor {
    private static List<Socket> clientes = new ArrayList<>();
    private static HashMap<Socket, String> clientesNomes = new HashMap<>();
    private static final String ARQUIVO_HISTORICO = "historico_mensagens.txt";
    
    public static void main(String[] args) {
        // Criar arquivo de histórico se não existir
        try {
            File file = new File(ARQUIVO_HISTORICO);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de histórico: " + e.getMessage());
        }
        
        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Servidor Iniciado");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado, IP:" + cliente.getInetAddress());

                clientes.add(cliente);
                new Thread(() -> tratarCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro");
            e.printStackTrace();
        }
    }

    private static void tratarCliente(Socket cliente) {
        try {
            BufferedReader dado = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            
            // Primeira mensagem do cliente deve ser o nome
            String nome = dado.readLine();
            clientesNomes.put(cliente, nome);
            
            // 1. Primeiro envia o histórico para o novo cliente
            enviarHistoricoParaCliente(cliente);
            
            // 2. Depois envia a mensagem de entrada para todos (incluindo o novo cliente)
            String msgEntrada = nome + " entrou no chat.";
            System.out.println(msgEntrada);
            enviarParaTodos(msgEntrada, false); // false = não salvar no histórico
            
            String mensagem;
            while ((mensagem = dado.readLine()) != null) {
                String msgCompleta = nome + ": " + mensagem;
                System.out.println(msgCompleta);
                enviarParaTodos(msgCompleta, true); // true = salvar no histórico
            }
        } catch (IOException e) {
            String nome = clientesNomes.get(cliente);
            String msgSaida = nome + " saiu do chat.";
            System.out.println(msgSaida);
            enviarParaTodos(msgSaida, false); // false = não salvar no histórico
            clientes.remove(cliente);
            clientesNomes.remove(cliente);
        }
    }

    private static void enviarParaTodos(String mensagem, boolean salvarNoHistorico) {
        if (salvarNoHistorico) {
            salvarMensagemNoHistorico(mensagem);
        }
        
        for (Socket cliente : clientes) {
            try {
                PrintStream saida = new PrintStream(cliente.getOutputStream());
                saida.println(mensagem);
            } catch (IOException e) {
                System.out.println("Erro ao enviar mensagem para um cliente");
                clientes.remove(cliente);
            }
        }
    }
    
    private static void salvarMensagemNoHistorico(String mensagem) {
        try {
            Files.write(Paths.get(ARQUIVO_HISTORICO), 
                      (mensagem + System.lineSeparator()).getBytes(),
                      StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erro ao salvar mensagem no histórico: " + e.getMessage());
        }
    }
    
    private static void enviarHistoricoParaCliente(Socket cliente) {
        try {
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            List<String> linhas = Files.readAllLines(Paths.get(ARQUIVO_HISTORICO));
            
            if (!linhas.isEmpty()) {
                saida.println("=== Mensagens Anteriores ===");
                for (String linha : linhas) {
                    saida.println(linha);
                }
                saida.println("===========================");
            }
        } catch (IOException e) {
            System.err.println("Erro ao enviar histórico para cliente: " + e.getMessage());
        }
    }
}