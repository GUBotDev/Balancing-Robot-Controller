/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcontroller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rollie
 */
public class RobotController extends Application {
    static boolean up = false, down = false, left = false, right = false, send = false;
    ConnectionThread connThread;
    String ip = "192.168.0.51";
    int port = 6789;
    
    @Override
    public void start(Stage primaryStage) {
        GridPane gPane = new GridPane();
        
        Button btnU = new Button();
        Button btnD = new Button();
        Button btnL = new Button();
        Button btnR = new Button();
        Button btnC = new Button();
        
        btnU.setText("Up");
        btnD.setText("Down");
        btnL.setText("Left");
        btnR.setText("Right");
        btnC.setText("Connect");
        
        btnU.setOnMousePressed((MouseEvent me) -> {
            up = true;
        });
        
        btnU.setOnMouseReleased((MouseEvent me) -> {
            up = false;
        });
        
        btnD.setOnMousePressed((MouseEvent me) -> {
            down = true;
        });
        
        btnD.setOnMouseReleased((MouseEvent me) -> {
            down = false;
        });
        
        btnL.setOnMousePressed((MouseEvent me) -> {
            left = true;
        });
        
        btnL.setOnMouseReleased((MouseEvent me) -> {
            left = false;
        });
        
        btnR.setOnMousePressed((MouseEvent me) -> {
            right = true;
        });
        
        btnR.setOnMouseReleased((MouseEvent me) -> {
            right = false;
        });
        
        btnC.setOnMousePressed((MouseEvent me) -> {
            try {
                //connThread = new ConnectionThread(ip.getText(), Integer.parseInt(port.getText()));
                connThread = new ConnectionThread(ip, port);
                connThread.start();
            }
            catch (IOException ex) {
                Logger.getLogger(RobotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnU.setAlignment(Pos.CENTER);
        btnD.setAlignment(Pos.CENTER);
        btnL.setAlignment(Pos.CENTER);
        btnR.setAlignment(Pos.CENTER);
        btnC.setAlignment(Pos.CENTER);
        
        GridPane.setConstraints(btnU, 1, 0);
        GridPane.setConstraints(btnR, 2, 1);
        GridPane.setConstraints(btnL, 0, 1);
        GridPane.setConstraints(btnD, 1, 2);
        GridPane.setConstraints(btnC, 0, 3);
        
        GridPane.setMargin(btnU, new Insets(10, 10, 10, 10));
        GridPane.setMargin(btnD, new Insets(10, 10, 10, 10));
        GridPane.setMargin(btnL, new Insets(10, 10, 10, 10));
        GridPane.setMargin(btnR, new Insets(10, 10, 10, 10));
        GridPane.setMargin(btnC, new Insets(10, 10, 10, 10));
        
        //gPane.setPadding(new Insets(10, 10, 10, 10));
        
        gPane.getChildren().addAll(btnU, btnD, btnL, btnR, btnC);
        
        Scene scene = new Scene(gPane, 225, 200);
        
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.UP) {
                up = true;
            }
            if (ke.getCode() == KeyCode.DOWN) {
                down = true;
            }
            if (ke.getCode() == KeyCode.LEFT) {
                left = true;
            }
            if (ke.getCode() == KeyCode.RIGHT) {
                right = true;
            }
        });

        scene.setOnKeyReleased((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.UP) {
                up = false;
            }
            if (ke.getCode() == KeyCode.DOWN) {
                down = false;
            }
            if (ke.getCode() == KeyCode.LEFT) {
                left = false;
            }
            if (ke.getCode() == KeyCode.RIGHT) {
                right = false;
            }
        });
        
        primaryStage.setTitle("RobotController");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }
}
