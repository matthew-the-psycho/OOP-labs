package LAB8;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import javafx.event.ActionEvent;

public class Main2 {
    public static class CalculateMaxListener extends JTextArea implements IMatrixListener {
        @Override
        public void MatrixUpdated(Matrix mtx) {
            long max = mtx.getValue(0, 0);
            for(int i = 0; i < mtx.getSize(); i++) {
                for(int j = 0; j < mtx.getSize(); j++) {
                    if (max < mtx.getValue(i, j)) {
                        max = mtx.getValue(i, j);
                    }
                }
            }
            setText("Max = " + String.valueOf(max));
        }
    }

    public static class CalculateSumListener extends JTextArea implements IMatrixListener {
        @Override
        public void MatrixUpdated(Matrix mtx) {
            long sum = 0;
            for(int i = 0; i < mtx.getSize(); i++) {
                for(int j = 0; j < mtx.getSize(); j++) {
                    sum += mtx.getValue(i, j);
                }
            }
            setText("Sum = " + String.valueOf(sum));
        }
    }

    public static class CalculateTraceListener extends JTextArea implements IMatrixListener {
        @Override
        public void MatrixUpdated(Matrix mtx) {
            long trc = 0;
            for(int i = 0; i < mtx.getSize(); i++) {
                trc += mtx.getValue(i, i);
            }
            setText("Trace = " + String.valueOf(trc));
        }
    }

    public static class DisplayMatrixListener extends JTextArea implements IMatrixListener {
        @Override
        public void MatrixUpdated(Matrix mtx) {
            String outter = "";
            for(int i = 0; i < mtx.getSize(); i++) {
                for(int j = 0; j < mtx.getSize(); j++) {
                    outter += (mtx.getValue(i, j) + " ");
                }
                outter += "\n";
            }
            setText(outter);
        }
    }

    public static interface IMatrixListener {
        void MatrixUpdated(Matrix mtx);
    }

    public static interface IGenerator {
        String getName();
        long getElement(int i, int j);
    }

    public static class RandomGenerator implements IGenerator {
        @Override
        public String getName() {
            return "Random";
        }
        @Override
        public long getElement(int i, int j) {
            return (long)Math.floor(Math.random() * 100);
        }
    }

    public static class ZeroGenerator implements IGenerator {
        @Override
        public String getName() {
            return "Zeros";
        }
        @Override
        public long getElement(int i, int j) {
            return 0;
        }
    }

    public static class IdentityGenerator implements IGenerator {
        @Override
        public String getName() {
            return "Eye";
        }
        @Override
        public long getElement(int i, int j) {
            return i==j ? 1 : 0;
        }
    }

    public static class DiagonalGenerator implements IGenerator {
        @Override
        public String getName() {
            return "Diagonal";
        }
        @Override
        public long getElement(int i, int j) {
            return i == j ? (long)Math.floor(Math.random() * 100) : 0;
        }
    }

    public static class Matrix {
        final private int seiz = 10;
        private long[][] containment;
        private IGenerator gen;
    
        public int getSize() {
            return seiz;
        }
        
        public void setGenerator(IGenerator generatorenn) {
            this.gen = generatorenn;
        }
    
        public long getValue(int i, int j) {
            return containment[i][j];
        }
    
        private ArrayList<IMatrixListener> listeners = new ArrayList<IMatrixListener>();
        public void addListener(IMatrixListener listener) {
            listeners.add(listener);
        }
    
        public void notifyAboutUpdate() {
            for (IMatrixListener listener : listeners) {
                listener.MatrixUpdated(this);
            }
        }
    
        public void update() {
            containment = new long[seiz][seiz];
            for(int i = 0; i < seiz; i++) {
                for(int j = 0; j < seiz; j++) {
                    containment[i][j] = gen.getElement(i, j);
                }
            }
            notifyAboutUpdate();
        }
    }  

    public static class MaxLinesSumListener extends JTextArea implements IMatrixListener {
        @Override
        public void MatrixUpdated(Matrix mtx) {
            long maxs[] = new long[mtx.getSize()];
            for(int i = 0; i < mtx.getSize(); i++) {
                maxs[i] = mtx.getValue(i, 0);
                for(int j = 0; j < mtx.getSize(); j++) {
                    if (maxs[i] < mtx.getValue(i, j)) {
                        maxs[i] = mtx.getValue(i, j);
                    }
                }
            }
            long out = 0;
            for(int i = 0; i < mtx.getSize(); i++) {
                out += maxs[i];
            }
            setText("MaxOfLinesSum = " + String.valueOf(out));
        }
    } 

    public static class TriangleGenerator implements IGenerator {
        @Override
        public String getName() {
            return "Triangle";
        }
        @Override
        public long getElement(int i, int j) {
            return i <= j ? (long)Math.floor(Math.random() * 100) : 0;
        }
    }

    public static class MatrixData {
        public static IGenerator[] generators = {
            new ZeroGenerator(),
            new IdentityGenerator(),
            new DiagonalGenerator(),
            new RandomGenerator(),
            new TriangleGenerator()
        };
        public static IMatrixListener[] listeners = {
            new DisplayMatrixListener(),
            new CalculateMaxListener(),
            new CalculateSumListener(),
            new CalculateTraceListener(),
            new MaxLinesSumListener()
        };
    }

    

    public static void main(String[] args) {
        JFrame framenn = new JFrame("Framenn zu LAB8 :: Listeners und Generators");
        framenn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framenn.setBackground(Color.BLACK);
        framenn.setLayout(new GridLayout());
        framenn.setLocation(0, 300);
        framenn.setSize(800, 400);
        Matrix mtx = new Matrix();
        //MatrixData mtxdat;
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