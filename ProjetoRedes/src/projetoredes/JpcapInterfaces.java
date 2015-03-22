import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
 
/**
 *
 * @author Victor
 */
public class JpcapInterfaces {
 
    /**
     * Dado um array de bytes retorna a String equivalente.
     * @param input - Array de bytes.
     * @return String com a representação textual do array de bytes.
     */
    public static String hex2String(byte [] input){
        String output = "";
        for (int i = 0; i < input.length-1; i++) {
            output += Integer.toHexString(input[i] & 0xff) + ":";
        }
        output += Integer.toHexString(input[input.length-1] & 0xff);
        return output;
    }
 
    public static void main(String args[]) {
 
        //Obtém a lista de interfaces de rede no sistema.
        NetworkInterface[] interfaces = JpcapCaptor.getDeviceList();
 
        for (NetworkInterface ni : interfaces) {
            System.out.println("-------------------------------------------------");
            //Nome da interface.
            System.out.println("Nome: " + ni.name);
            //Descrição da interface caso exista.
            System.out.println("Descrição: " + ni.description);
            //DataLink da interface.
            System.out.println("Nome da DataLink : " + ni.datalink_name);
            //Descrição do DataLink caso exista.
            System.out.println("Descrição da DataLink : " + ni.datalink_description);
            //MAC Address da interface.
            System.out.println("MAC Address: "+ hex2String(ni.mac_address));
 
            for(NetworkInterfaceAddress a : ni.addresses){
                //Endereço de IP da interface.
                System.out.println("IP: " + a.address.getHostAddress());
                //Endereço de Broadcast da interface.
                System.out.println("BroadCast: " + a.broadcast);
                //Mascara de SubRede.
                System.out.println("SubNet: " + a.subnet.getHostAddress());
                //Em caso de ligações P2P o endereço de destino.
                System.out.println("Destinho P2P: " + a.destination);
            }          
            System.out.println("-------------------------------------------------n");
        }
    }   
}