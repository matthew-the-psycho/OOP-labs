package classes;

import java.lang.Thread;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;



public class Ball extends Thread {
    private JPanel panellen;
    private int schtep;
    private int seisenn;
    private int x0;
    private int y0;
    private Color Koloren;

    Ball(JPanel jp, int step, int size, int x0_0, int y0_0, Color color) {
        super();
        this.panellen = jp;
        this.schtep = step;
        this.seisenn = size;
        this.x0 = x0_0;
        this.y0 = y0_0;
        this.Koloren = color;
    }
    Color getKoloren() {
        return this.Koloren;
    }
    @Override
    public void run() {
        int x = this.x0;
        int y = this.y0;
        int xdir = +1;
        int ydir = +1;

        this.panellen.setBackground(Color.BLACK);
        
        while(true) {
            //gr.setColor(this.getKoloren());
            Graphics gr = panellen.getGraphics();
            gr.setColor(Color.MAGENTA);
            gr.fillOval(x, y, seisenn, seisenn);
            //____________________________________________
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gr.setColor(Color.BLACK);
            gr.fillOval(x, y, this.seisenn, this.seisenn);
            //____________________________________________
            if (x > this.panellen.getWidth() - this.seisenn) {
                xdir = -1;
            }
            if (x < this.seisenn) {
                xdir = +1;
            }
            if (y > this.panellen.getHeight() - this.seisenn) {
                ydir = -1;
            }
            if (y < this.seisenn) {
                ydir = +1;
            }
            //____________________________________________
            x += xdir * this.schtep;
            y += ydir * this.schtep;
        }
    }
}