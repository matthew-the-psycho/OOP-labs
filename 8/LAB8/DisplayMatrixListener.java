package LAB8;

import javax.swing.JTextArea;

public class DisplayMatrixListener extends JTextArea implements IMatrixListener {
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