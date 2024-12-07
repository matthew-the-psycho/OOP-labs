package classes;

import java.lang.Thread;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Lab6 {
    
    public static void main(String[] args){
        JFrame framenn = new JFrame();
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setSize(666, 666);
        framenn.setLocation(333, 333);
        framenn.setLayout(new GridLayout(2, 2));
        framenn.setBackground(Color.BLACK);
        JPanel panellen[] = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            panellen[i] = new JPanel();
            //panellen[i].setBackground(Color.BLACK);
            framenn.add(panellen[i]);
            panellen[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
            //panellen[i].setVisible(true);
            //System.out.println("Panellen " + i + " kreatenenn!");
        }
        

        Ball ballen[] = new Ball[4];
        ballen[0] = new Ball(panellen[0], 1, 10, 66, 6, Color.GREEN);
        ballen[1] = new Ball(panellen[1], 2, 5, 33, 44, Color.BLUE);
        ballen[2] = new Ball(panellen[2], 5, 2, 44, 33, Color.CYAN);
        ballen[3] = new Ball(panellen[3], 10, 1, 6, 66, Color.MAGENTA);

        System.out.println("Before: " + Thread.activeCount());

        for (int i = 0; i < 4; i++) {
            ballen[i].start();
            //ballen[i].run();
        }

        System.out.println("After: " + Thread.activeCount());  
        
        framenn.setVisible(true);
        System.out.println("Framenn must be fillen!");
    }
}