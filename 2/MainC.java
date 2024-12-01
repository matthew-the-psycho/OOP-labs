package lab2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Backend {
    double first_procent;
    double first_volume;
    double need_percent;
    double need_volume;

    public void setNP(double n_p) {
        need_percent = n_p;
        return;
    }

    public Backend() {
        first_procent = 96.6;
        first_volume = 0;
        need_percent = 40;
        need_volume = 0;
    }

    public Backend(double f_p, double f_v, double n_p){
        this.first_procent = f_p;
        this.first_volume = f_v;
        this.need_percent = n_p;
    }

    public double FindNeedWasser() {
        if (this.need_percent == 0) {
            throw ExceptionWasser;
        }
        this.need_volume = (this.first_procent / this.need_percent - 1) * this.first_volume;
        return this.need_volume;
    }

    public double getFullVol() {
        return this.first_volume + this.need_volume;
    }
}

public class MainC {
    public static void main(String args[]) {
        JFrame framenn = new JFrame();
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setSize(664, 332);
        framenn.setTitle("Framenn du alkohol kalkuljatoryje");
        framenn.setLocation(0, 0);
        framenn.setVisible(true);
        framenn.setLayout(new FlowLayout());
        JButton knopkenn = new JButton();
        knopkenn.setText("Rassczjot!");
        framenn.add(knopkenn);
        framenn.add(new JLabel("Alkohol first percentage:"));
        JTextField FP = new JTextField("            ");
        framenn.add(FP);
        framenn.add(new JLabel("Alkohol first volumenn:"));
        JTextField FV = new JTextField("            ");
        framenn.add(FV);
        framenn.add(new JLabel("Alkohol need percentage:"));
        JTextField NP = new JTextField("            ");
        framenn.add(NP);
        framenn.add(new JLabel("Wasser need volume:"));
        JLabel NV = new JLabel();
        framenn.add(NV);

        knopkenn.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {System.out.print("Actionenn::Entered");};
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {System.out.print("Actionenn::Exited");};
                @Override
                public void mouseReleased(java.awt.event.MouseEvent e) {System.out.print("Actionenn::Released");};
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {System.out.print("Actionenn::Pressed");};
                @Override
                public void mouseClicked(java.awt.event.MouseEvent arg0) {
                    
                    double first_Proc = Double.parseDouble(FP.getText());
                    double first_Vol = Double.parseDouble(FV.getText());
                    double need_Proc = Double.parseDouble(NP.getText());
                    //System.out.println("Try Backend");
                    //Backend kalk = new Backend(first_Proc, first_Vol, need_Proc);
                    
                    //System.out.println("Try Find NeedWasser");
                    //double need_Vol = kalk.FindNeedWasser();
                    
                    //catch (ExceptionWasser e) {
                    //    NV.setText("You need Infinite wasser!");
                    //}
                    //System.out.println("Try SetText");
                    //NV.setText(String.valueOf(need_Vol));
                    //System.out.println("Text must be set");
                    
                    //catch (if (!need_Proc)) {
                    if (need_Proc  == 0) {
                        NV.setText("You need Infinite wasser!");    
                    }
                    else {
                        double need_Vol = (first_Proc / need_Proc - 1) * first_Vol;
                        NV.setText(String.valueOf(need_Vol));
                    }
                    
                }
            }
            
        );
    }
}
