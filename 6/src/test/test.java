package test;

import java.awt.*;
import java.lang.*;
import java.util.Random;

import javax.swing.*;

public class test {
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
                int th = (rand.nextInt(0x7F109319) ^ rand.nextInt(0x01204989));
                fieldung.setText(Integer.toString(Integer.toString(th).hashCode()));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldung.setText("");
            }
        }
    }

    public static void main(String[] args){
        JFrame framenn = new JFrame();
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setSize(200, 200);
        framenn.setLocation(333, 333);
        framenn.setLayout(new GridLayout(4, 1));
        framenn.setBackground(Color.BLACK);
        
        
        JTextField fieldung[] = new JTextField[4];
        thr thrds[] = new thr[4];
        //Random rand = new Random();
        for(int i = 0; i < 4; i++) {
            fieldung[i] = new JTextField(0x20);
            fieldung[i].setBorder(BorderFactory.createLineBorder(Color.decode("#000077"), 4));
            thrds[i] = new thr(fieldung[i]);
            thrds[i].start();
            framenn.add(fieldung[i]);
        }
        framenn.setVisible(true);
    }
}