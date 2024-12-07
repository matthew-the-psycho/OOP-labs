package LAB8;

import javax.swing.JTextArea;

public class CalculateTraceListener extends JTextArea implements IMatrixListener {
    @Override
    public void MatrixUpdated(Matrix mtx) {
        long trc = 0;
        for(int i = 0; i < mtx.getSize(); i++) {
            trc += mtx.getValue(i, i);
        }
        setText("Trace = " + String.valueOf(trc));
    }
}