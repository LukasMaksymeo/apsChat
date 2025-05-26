package chatapsdef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TelaChat extends javax.swing.JFrame {
    
    private String ip;
    private String nome;
    private PrintStream ps;
    private int porta;
    private Socket cliente;
    
    public TelaChat(String IP, String NOME, int PORTA) throws IOException {
        this.ip = IP;
        this.nome = NOME;
        this.porta = PORTA;
        initComponents();
        lbl_IP.setText("IP: " + IP);
        lbl_nome.setText("NOME: " + NOME);

        try {
            cliente = new Socket(ip, porta);
            ps = new PrintStream(cliente.getOutputStream());
            
            ps.println(nome);

            new Thread(() -> {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    String resposta;
                    while ((resposta = br.readLine()) != null) {
                        jTextArea1.append(resposta + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao ler do servidor: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Configuração inicial do fundo verde claro
        getContentPane().setBackground(new java.awt.Color(200, 255, 200));

        txt_mensagem = new javax.swing.JTextField();
        btn_enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_voltar = new javax.swing.JButton();
        lbl_IP = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatApp");
        setBackground(new java.awt.Color(200, 255, 200));
        setForeground(new java.awt.Color(0, 51, 0));

        // Configuração dos componentes
        txt_mensagem.setBackground(new java.awt.Color(255, 255, 255));
        txt_mensagem.setForeground(new java.awt.Color(0, 0, 0));

        btn_enviar.setBackground(new java.awt.Color(150, 255, 150));
        btn_enviar.setFont(new java.awt.Font("Bahnschrift", 0, 12));
        btn_enviar.setText("enviar");
        btn_enviar.setForeground(new java.awt.Color(0, 0, 0));
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 14));
        jTextArea1.setRows(5);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setOpaque(false); // Permite ver o fundo verde
        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setOpaque(false); // Permite ver o fundo verde
        jScrollPane1.getViewport().setOpaque(false); // Permite ver o fundo verde

        btn_voltar.setBackground(new java.awt.Color(150, 255, 150));
        btn_voltar.setFont(new java.awt.Font("Bahnschrift", 0, 12));
        btn_voltar.setText("voltar");
        btn_voltar.setForeground(new java.awt.Color(0, 0, 0));
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        lbl_IP.setBackground(new java.awt.Color(200, 255, 200));
        lbl_IP.setForeground(new java.awt.Color(0, 0, 0));
        lbl_IP.setOpaque(true);

        lbl_nome.setBackground(new java.awt.Color(200, 255, 200));
        lbl_nome.setForeground(new java.awt.Color(0, 0, 0));
        lbl_nome.setOpaque(true);

        // Layout modificado para melhor exibição do fundo
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_mensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_voltar)
                    .addComponent(lbl_IP)
                    .addComponent(lbl_nome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        TelaInicial TelaLog = new TelaInicial();
        TelaLog.setVisible(true);
        dispose();
        try {
            if (ps != null) ps.close(); 
            if (cliente != null) cliente.close();
        }
        catch (Exception e) {
         System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }                                          

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            String mensagem = txt_mensagem.getText();
            ps.println(mensagem);
            txt_mensagem.setText("");
        } 
        catch (Exception e) {
            System.out.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_IP;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JTextField txt_mensagem;
    // End of variables declaration                   
}