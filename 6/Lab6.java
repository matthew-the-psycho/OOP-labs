//package Lab;

import java.awt.*;
import java.lang.*;
import java.util.Random;

import javax.swing.*;

public class Lab6 {
    public static class thr extends Thread {
        private JTextField fieldung;
        private Random rand = new Random();

        thr(JTextField tf) {
            super();
            this.fieldung = tf;
            fieldung.setBackground(Color.BLACK);
            fieldung.setForeground(Color.MAGENTA);
            fieldung.setCaretColor(Color.GREEN);
        }

        @Override
        public void run() {
            while (true) {
                int seisennn = rand.nextInt(0x100);
                StringBuilder rS = new StringBuilder("");
                for(int i = 0; i < seisennn; i++) {
                    char Symb = (char)(rand.nextInt(0x5E) + 0x20);
                    rS.append(Symb);
                }
                fieldung.setText(rS.toString());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldung.setText("");
            }
        }
    }

    public static void main(String[] args){
        JFrame framenn = new JFrame();
        framenn.setTitle("Labenn zu Threade quazi-graphique-edition");
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setSize(1920, 200);
        framenn.setLocation(0, 333);
        framenn.setLayout(new GridLayout(4, 1));
        framenn.setBackground(Color.BLACK);
        
        
        JTextField fieldung[] = new JTextField[4];
        thr thrds[] = new thr[4];
        //Random rand = new Random();
        for(int i = 0; i < 4; i++) {
            fieldung[i] = new JTextField(0x100);
            fieldung[i].setBorder(BorderFactory.createLineBorder(Color.decode("#406040"), 2));
            thrds[i] = new thr(fieldung[i]);
            thrds[i].start();
            framenn.add(fieldung[i]);
        }
        framenn.setVisible(true);
    }
}