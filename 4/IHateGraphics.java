//package IHateGraphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;


public class IHateGraphics {
    public static void main (String[] args){
        JFrame framenn = new JFrame("Framenn zu Lab4");
        framenn.setLocation(500, 500);
        framenn.setSize(400, 150);
        framenn.setBackground(Color.BLACK);
        framenn.getContentPane().setBackground(Color.black);
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout GD = new GridLayout(3, 2);
        framenn.setLayout(GD);

        JLabel Passwordenn = new JLabel("Passwordenn:");
        Passwordenn.setBackground(Color.BLACK);
        Passwordenn.setForeground(Color.MAGENTA);
        framenn.add(Passwordenn);
        
        JTextField pswd = new JTextField(0x20);
        pswd.setBackground(Color.BLACK);
        pswd.setCaretColor(Color.GREEN);
        pswd.setForeground(Color.MAGENTA);
        framenn.add(pswd);
        
        JLabel Salten = new JLabel("Salten:");
        Salten.setBackground(Color.BLACK);
        Salten.setForeground(Color.MAGENTA);
        framenn.add(Salten);

        JTextField salt = new JTextField(0x20);
        salt.setBackground(Color.BLACK);
        salt.setCaretColor(Color.GREEN);
        salt.setForeground(Color.MAGENTA);
        framenn.add(salt);
        
        JLabel Hash = new JLabel("Hash:");
        Hash.setBackground(Color.BLACK);
        Hash.setForeground(Color.MAGENTA);
        framenn.add(Hash);
        
        JTextField hash = new JTextField(0x20);
        hash.setBackground(Color.BLACK);
        hash.setCaretColor(Color.GREEN);
        hash.setForeground(Color.MAGENTA);
        framenn.add(hash);
        
        
        pswd.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent arg0) {
                String pwd = pswd.getText();
                String slt = salt.getText();
                String to_hash = pwd + slt;
                hash.setText(Integer.toString(to_hash.hashCode()));
            }
        });
        salt.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent arg0) {
                String pwd = pswd.getText();
                String slt = salt.getText();
                String to_hash = pwd + slt;
                hash.setText(Integer.toString(to_hash.hashCode()));
            }
        });
        framenn.setVisible(true);;
    }
}

