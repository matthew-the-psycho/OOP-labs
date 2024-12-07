package LAB8;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame framenn = new JFrame("Framenn zu LAB8 :: Listeners und Generators");
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setBackground(Color.BLACK);
        framenn.setLayout(new GridLayout());
        framenn.setLocation(0, 300);
        framenn.setSize(800, 400);
        Matrix mtx = new Matrix();
        for (IMatrixListener listener : MatrixData.listeners) {
            framenn.add((JTextArea)listener);
            mtx.addListener(listener);
        }
        Vector<String> arr = new Vector<String>();
        int kol = 0;
        for (IGenerator generator : MatrixData.generators) {
            arr.add(kol, generator.getName());
            kol++;
        }
        JComboBox combo = new JComboBox<String>(arr);
        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mtx.setGenerator(MatrixData.generators[combo.getSelectedIndex()]);
                mtx.update();
            }
        });
        framenn.add(combo);
        framenn.setVisible(true);
    }
}