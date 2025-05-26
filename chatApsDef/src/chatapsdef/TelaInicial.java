package chatapsdef;

import java.net.Socket;
import javax.swing.ImageIcon;

public class TelaInicial extends javax.swing.JFrame {

    public int porta = 5000;
    
    public TelaInicial() {
        initComponents();
        this.setIconImage(new ImageIcon("src/defaul/radar-icon.png").getImage());
        // Configuração do fundo verde claro
        getContentPane().setBackground(new java.awt.Color(200, 255, 200));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Configuração inicial do fundo verde claro
        getContentPane().setBackground(new java.awt.Color(200, 255, 200));

        btn_conectar = new javax.swing.JButton();
        btn_fechar = new javax.swing.JButton();
        inp_IP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inp_nome = new javax.swing.JTextField();
        lbl_informaivo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatApp");
        setBackground(new java.awt.Color(200, 255, 200));
        setForeground(new java.awt.Color(0, 51, 0));
        setIconImage(new ImageIcon("src/defaul/radar-icon.png").getImage());
        setLocation(new java.awt.Point(750, 450));

        btn_conectar.setBackground(new java.awt.Color(150, 255, 150));
        btn_conectar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btn_conectar.setText("Conectar");
        btn_conectar.setForeground(new java.awt.Color(0, 0, 0));
        btn_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarActionPerformed(evt);
            }
        });

        btn_fechar.setBackground(new java.awt.Color(150, 255, 150));
        btn_fechar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btn_fechar.setText("Fechar");
        btn_fechar.setForeground(new java.awt.Color(0, 0, 0));
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });

        inp_IP.setBackground(new java.awt.Color(255, 255, 255));
        inp_IP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel1.setText("IP:");
        jLabel1.setBackground(new java.awt.Color(200, 255, 200));
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel2.setText("Nome:");
        jLabel2.setBackground(new java.awt.Color(200, 255, 200));
        jLabel2.setOpaque(true);

        inp_nome.setBackground(new java.awt.Color(255, 255, 255));
        inp_nome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inp_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inp_nomeActionPerformed(evt);
            }
        });

        lbl_informaivo.setBackground(new java.awt.Color(200, 255, 200));
        lbl_informaivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_informaivo.setOpaque(true);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png")));
        jLabel3.setBackground(new java.awt.Color(200, 255, 200));
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(108, 108, 108)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inp_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(inp_IP)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_fechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_conectar)))
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel3)
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_informaivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inp_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inp_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_informaivo, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_conectar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        pack();
    }

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void inp_nomeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_conectarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Socket cliente = new Socket(inp_IP.getText(), porta);
            cliente.close();
            TelaChat Chat = new TelaChat(inp_IP.getText(), inp_nome.getText(), porta);
            Chat.setVisible(true);
            dispose();
        } catch (Exception e) {
            lbl_informaivo.setText("<html><div style='text-align: center;'>Ocorreu um erro durante a conexão<br>Verifique o endereço de IP!</div></html>");
        }
    }

    // Variables declaration
    private javax.swing.JButton btn_conectar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JTextField inp_IP;
    private javax.swing.JTextField inp_nome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_informaivo;
}