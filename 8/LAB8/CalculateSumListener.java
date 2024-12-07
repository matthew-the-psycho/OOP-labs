package LAB8;

import javax.swing.JTextArea;

public class CalculateSumListener extends JTextArea implements IMatrixListener {
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