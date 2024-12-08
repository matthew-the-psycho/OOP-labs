package LAB8;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import javafx.event.ActionEvent;

public class Main3 {
    
    public static interface IMatrixListener {
        void MatrixUpdated(Matrix mtx);
    }

    public static abstract class AbstractListener extends JTextArea implements IMatrixListener{
        @Override
        final public void MatrixUpdated(Matrix mtx) {
            startenn(mtx);
            for(int i = 0; i < mtx.getSize(); i++) {
                for(int j = 0; j < mtx.getSize(); j++) {
                    nextSteppenn(i, j, mtx);
                }
            }
            setBackground(Color.BLACK);
            setForeground(Color.MAGENTA);
            finalizatenn(mtx);
        }        

        protected abstract void startenn(Matrix mtx);
        protected abstract void nextSteppenn(int i, int j, Matrix mtx);
        protected abstract void finalizatenn(Matrix mtx);
    }

    public static class CalculateMaxListener extends AbstractListener {
        long max;
        @Override
        protected void startenn(Matrix mtx) {
            max = mtx.getValue(0, 0);
        }
        @Override
        protected void nextSteppenn(int i, int j, Matrix mtx) {
            if (max < mtx.getValue(i, j)) {
                max = mtx.getValue(i, j);
            }
        }
        @Override
        protected void finalizatenn(Matrix mtx) {
            setText("Max = " + String.valueOf(max));
        }
    }

    public static class CalculateSumListener extends AbstractListener {
        long sum;
        @Override
        protected void startenn(Matrix mtx) {
            sum = 0;
        }
        @Override
        protected void nextSteppenn(int i, int j, Matrix mtx) {
            sum += mtx.getValue(i, j);
        }
        @Override
        protected void finalizatenn(Matrix mtx) {
            setText("Sum = " + String.valueOf(sum));
        }
    }

    public static class CalculateTraceListener extends AbstractListener {
        long trc;
        @Override
        protected void startenn(Matrix mtx) {
            trc = 0;
        }
        @Override
        protected void nextSteppenn(int i, int j, Matrix mtx) {
            if (i == j) {
                trc += mtx.getValue(i, j);
            }
        }
        @Override
        protected void finalizatenn(Matrix mtx) {
            setText("Trace = " + String.valueOf(trc));
        }
    }

    public static class DisplayMatrixListener extends AbstractListener {
        String s;
        @Override
        protected void startenn(Matrix mtx) {
            s = "";
        }
        @Override
        protected void nextSteppenn(int i, int j, Matrix mtx) {
            s = s + String.valueOf(mtx.getValue(i, j)) + " ";
            if (j == mtx.getSize() - 1) {
                s += "\n";
            }
        }
        @Override
        protected void finalizatenn(Matrix mtx) {
            setText(s);
        }
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

    public static class MaxLinesSumListener extends AbstractListener {
        long maxs[];
        @Override
        protected void startenn(Matrix mtx) {
            maxs = new long[mtx.getSize()];
        }
        @Override
        protected void nextSteppenn(int i, int j, Matrix mtx) {
            if (j == 0) {
                maxs[i] = mtx.getValue(i, j);
            }
            if (maxs[i] < mtx.getValue(i, j)) {
                maxs[i] = mtx.getValue(i, j);
            }
        }
        @Override
        protected void finalizatenn(Matrix mtx) {
            long sum = 0;
            for (int i = 0; i < mtx.getSize(); i++) {
                sum += maxs[i];
            }
            setText("MaxOfLineSum = " + String.valueOf(sum));
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