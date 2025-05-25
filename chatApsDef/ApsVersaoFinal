/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapsdef;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author lukas
 */
public class ChatApsDef {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TelaInicial TelaLog = new TelaInicial();
        TelaLog.setVisible(true);
        try {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("FlatLaf Dark".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // If Nimbus is not available, you can set the GUI to another look and feel.
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChatApsDef.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ChatApsDef.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ChatApsDef.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ChatApsDef.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    
    }
    
}
