package openinterface;

import java.io.IOException;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
 
/**
 *
 * @author magician
 */
public class OpenInterface {
 
   public static void main(String args []){
 
       System.out.println(System.getProperty("java.library.path"));
        //Lista de interfaces de rede no sistema.
        NetworkInterface[] interfaces = JpcapCaptor.getDeviceList();
 
        try{
            //Abre a interface 0 da lista.
            JpcapCaptor captor = JpcapCaptor.openDevice(interfaces[0], 65535, false, 20);
 
            //Simples contador.
            int i = 0;
            Packet p = null;
 
            //Cliclo para capturar 20 pacotes.
            while(true){
                //Captura um pacote.
                p = captor.getPacket();
 
                //Verifica se o pacote é do tipo TCPPacket
                if(p instanceof TCPPacket){
                    TCPPacket tcp = (TCPPacket) p;
                    System.out.println("Fonte: " + tcp.src_ip.getHostAddress() + ":" + tcp.src_port + 
                            "   DESTINO: " + tcp.dst_ip.getHostAddress() +":" + tcp.dst_port + 
                            "   tSize = " + tcp.length + " bytes");
 
                }
                //Verifica se o pacote é do tipo UDPPacket
                else if(p instanceof UDPPacket){
                    UDPPacket udp = (UDPPacket) p;
                    System.out.println("FONTE: " + udp.src_ip.getHostAddress() + ":" + udp.src_port + 
                            "   DESTINO: " + udp.dst_ip.getHostAddress() +":" + udp.dst_port +
                            "   tSize = " + udp.length + " bytes");
                }
                i++;
                
                Thread.sleep(2000);
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
}