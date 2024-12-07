package LAB8;

import javax.swing.JTextArea;

public class MaxLinesSumListener extends JTextArea implements IMatrixListener {
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