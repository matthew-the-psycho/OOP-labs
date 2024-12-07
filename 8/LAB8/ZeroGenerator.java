package LAB8;

public class ZeroGenerator implements IGenerator {
    @Override
    public String getName() {
        return "Zeros";
    }
    @Override
    public long getElement(int i, int j) {
        return 0;
    }
}