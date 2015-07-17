/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoredes;


import java.io.IOException;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

/**
 *
 * @author Victor
 */
public class telaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form telaPrincipal
     */
    public telaPrincipal() {
        initComponents();
        txtPackagesList.setText("IgorGay");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPackagesList = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fatec Analyzer Package");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Play.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 160, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Stop.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 502, 166, 50));

        txtPackagesList.setColumns(20);
        txtPackagesList.setRows(5);
        jScrollPane1.setViewportView(txtPackagesList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 125, 638, 330));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Title.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 480, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Background.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        iniciarCaptura(true);
        txtPackagesList.setText("IgorGay2");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniciarCaptura(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaPrincipal().setVisible(true);
            }
            
        });
    }
    
    /**
     *
     */
    public void iniciarCaptura(boolean state){
         NetworkInterface[] interfaces = JpcapCaptor.getDeviceList();
         
         System.out.println("interfaces: "+ interfaces[1]);
 
        try{
            //Abre a interface 0 da lista.
            JpcapCaptor captor = JpcapCaptor.openDevice(interfaces[0], 65535, false, 20);
            
            // adicionei um filtro para testar mais tarde, este filtro ira filtrar todos os pacotes que  são tcp e sao do host 192.168.0.16 para todos os servidores
            captor.setFilter("tcp and src host 192.168.0.16 and dst port 80", true);
 
            //Simples contador.
            int i = 0;
            Packet p = null;
 
            //Cliclo para capturar 20 pacotes.
            while(true){ // usar o parametro da função
                //Captura um pacote.
                p = captor.getPacket();
                //Verifica se o pacote é do tipo TCPPacket
                if(p instanceof TCPPacket){                    
                    System.out.println("TCP");
                    TCPPacket tcp = (TCPPacket) p;                    
                    System.out.println("Fonte: " + tcp.src_ip.getHostAddress() + ":" + tcp.src_port + 
                            "   DESTINO: " + tcp.dst_ip.getHostAddress() +":" + tcp.dst_port + 
                            "   tSize = " + tcp.length + " bytes");
                    tcp = null; 
                    
                if(p.data.length > 0){
                    System.out.println( new String (p.data));
                }
                    
                }
                //Verifica se o pacote é do tipo UDPPacket
                else if(p instanceof UDPPacket){
                    
                    System.out.println("UDP");
                    UDPPacket udp = (UDPPacket) p;
                    System.out.println("FONTE: " + udp.src_ip.getHostAddress() + ":" + udp.src_port + 
                            "   DESTINO: " + udp.dst_ip.getHostAddress() +":" + udp.dst_port +
                            "   tSize = " + udp.length + " bytes" + " Endereço de site? : " + udp.sec);
                    udp = null;
                }
                i++;
                                
                p = null;
                
                Thread.sleep(200);
            }
 
            //Fecha a captura de pacotes.
//            captor.close();
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtPackagesList;
    // End of variables declaration//GEN-END:variables
}
