package LAB8;

import javax.swing.JTextArea;

public class CalculateMaxListener extends JTextArea implements IMatrixListener {
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