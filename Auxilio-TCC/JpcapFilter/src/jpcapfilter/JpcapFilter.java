package jpcapfilter;

import java.io.IOException;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.TCPPacket;
 
/**
 *
 * @author magician
 */
public class JpcapFilter {
 
    public static void main(String args []){
 
        //Lista de interfaces de rede no sistema.
        NetworkInterface[] interfaces = JpcapCaptor.getDeviceList();
        
        for(NetworkInterface a : interfaces){ 
            System.out.println("Name:" + a.name);
            System.out.println("Name datalink:" + a.datalink_name);
//            System.out.println("Name:" + a.name);
        }
 
        try{
            //Abre a interface 0 da lista.
            JpcapCaptor captor = JpcapCaptor.openDevice(interfaces[0], 65535, false, 20);
 
            //Captura apenas pacotes TCP com origem no host 192.168.1.100 e que
            //tem como destino a porta 80 ou seja HTTP
            captor.setFilter("tcp and src host 192.168.0.22 and dst port 80", true);
 
            //Simples contador.
            int i = 0;
 
            //Cliclo para capturar 20 pacotes.
            while(i < 20){
                //Captura um pacote e converte para TCPPacket dado que apenas
                //a capturar pacotes TCP.
                TCPPacket p = (TCPPacket) captor.getPacket();
 
                //Gera o output com a informaÃ§Ã£o sobre o pacote
                System.out.println("FONTE: " + p.src_ip.getHostAddress() + ":" + p.src_port + 
                        "   DST: " + p.dst_ip.getHostAddress() +":" + p.dst_port + 
                        "   tSize = " + p.length + " bytes");
 
                //Caso o pacote contenha dados este sÃ£o impressos e o programa para.
                if(p.data.length > 0){
                    System.out.println(new String(p.data));
                    break;
                }
                i++;
            }
 
            //Fecha a captura de pacotes.
            captor.close();
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}