/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcontroller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rollie
 */
public class ConnectionThread implements Runnable {
    private Thread thread;
    private String name = "Connection";
    private final Controller controller;
    
    ConnectionThread(String ip, int port) throws IOException{
        controller = new Controller(ip, port);
    }
    
    @Override
    public void run(){
        try{
            while(true){
                //controller.waitForServer();
                controller.writeControls(RobotController.up, RobotController.down, RobotController.left, RobotController.right);

                //System.out.println(RobotController.up + "," + RobotController.down + "," + RobotController.left + "," + RobotController.right);

                Thread.sleep(100);
            }
        }
        catch(IOException ex) {
            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start(){
        if (thread == null){
            thread = new Thread(this, name);
            thread.start();
        }
    }
}
