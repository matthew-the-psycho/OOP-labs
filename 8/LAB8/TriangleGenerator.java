package LAB8;

public class TriangleGenerator implements IGenerator {
    @Override
    public String getName() {
        return "Triangle";
    }
    @Override
    public long getElement(int i, int j) {
        return i <= j ? (long)Math.floor(Math.random() * 100) : 0;
    }
}