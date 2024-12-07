package LAB8;

import java.util.ArrayList;

public class Matrix {
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