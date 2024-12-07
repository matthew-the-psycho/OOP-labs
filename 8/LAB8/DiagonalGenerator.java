package LAB8;

public class DiagonalGenerator implements IGenerator {
    @Override
    public String getName() {
        return "Diagonal";
    }
    @Override
    public long getElement(int i, int j) {
        return i == j ? (long)Math.floor(Math.random() * 100) : 0;
    }
}