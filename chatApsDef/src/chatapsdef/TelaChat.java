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
        
        // Envia o nome para o servidor primeiro
        ps.println(nome);

        // Thread para receber mensagens (agora sem "Servidor: ")
        new Thread(() -> {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String resposta;
                while ((resposta = br.readLine()) != null) {
                    jTextArea1.append(resposta + "\n");  // Exibe diretamente a mensagem formatada
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_mensagem = new javax.swing.JTextField();
        btn_enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_voltar = new javax.swing.JButton();
        lbl_IP = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_mensagem.setText("jTextField1");

        btn_enviar.setText("enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        btn_voltar.setText("voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_mensagem)
                        .addGap(18, 18, 18)
                        .addComponent(btn_enviar))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(lbl_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_IP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enviar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
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

    }//GEN-LAST:event_btn_voltarActionPerformed

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        try {
            String mensagem = txt_mensagem.getText();
            ps.println(mensagem); // Só envia, pois já temos o socket e o ps aberto
            txt_mensagem.setText("");
        } 
        catch (Exception e) {
            System.out.println("Erro ao enviar mensagem: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_enviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_IP;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JTextField txt_mensagem;
    // End of variables declaration//GEN-END:variables
}
