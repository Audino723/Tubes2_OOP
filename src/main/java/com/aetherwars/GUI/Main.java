package com.aetherwars.GUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        GUI gui = new GUI(player1,player2);
        while(true){
            String Command = sc.nextLine();
            if(Command.equals("attack")){
                gui.p2.attack(gui.p1);
            }
            if(Command.equals("end")){
                break;
            }
        }
        sc.close();
    }
}

