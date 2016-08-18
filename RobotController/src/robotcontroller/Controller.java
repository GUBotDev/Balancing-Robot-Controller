/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcontroller;

import java.io.IOException;

/**
 *
 * @author Rollie
 */
public class Controller {
    TCPClient client;
    
    Controller(String ip, int port) throws IOException{
        System.out.println("Waiting for pi connection...");
        client = new TCPClient(ip, port);
        System.out.println("Connected to " + ip + ":" + port);
    }
    
    public void waitForServer() throws IOException, InterruptedException{
        String read;
        
        while(true){
            read = client.readFromClient();
            
            if(!(read == null)){
                if(read.equals("Write Controls")){
                    break;
                }
            }
        }
    }
    
    public void writeControls(boolean up, boolean down, boolean left, boolean right) throws IOException{
        String u, d, l, r;
        
        u = up ? "1" : "0";
        d = down ? "1" : "0";
        l = left ? "1" : "0";
        r = right ? "1" : "0";
        
        client.writeToClient(u + "," + d + "," + l + "," + r + "\n");
    }
}
