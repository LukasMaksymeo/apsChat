
package servidor;

import java.sql.*;
import java.util.UUID;

public class BDmensagens {
    private static final String DB_URL = "jdbc:derby://localhost:1527/BDchat"; // Arquivo do banco

    // Método para obter conexão
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Salva mensagem (a tabela é criada automaticamente se não existir)
    public static void salvarMensagem(String usuario, String mensagem) {
        String sql = "INSERT INTO mensagens (pk_msg, msg, usuario) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, UUID.randomUUID().toString()); // Gera UUID como chave
            pstmt.setString(2, mensagem);
            pstmt.setString(3, usuario);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar mensagem: " + e.getMessage());
        }
    }

    // Carrega histórico (últimas 'limite' mensagens)
    public static String carregarHistorico(int limite) {
        StringBuilder historico = new StringBuilder();
        String sql = "SELECT usuario, msg, data_hora FROM mensagens ORDER BY data_hora DESC LIMIT ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, limite);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                historico.append(rs.getString("usuario"))
                         .append(" [")
                         .append(rs.getTimestamp("data_hora"))
                         .append("]: ")
                         .append(rs.getString("msg"))
                         .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar histórico: " + e.getMessage());
        }
        
        return historico.toString();
    }
}

