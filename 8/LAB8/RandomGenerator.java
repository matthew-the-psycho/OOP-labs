package LAB8;

public class RandomGenerator implements IGenerator {
    @Override
    public String getName() {
        return "Random";
    }
    @Override
    public long getElement(int i, int j) {
        return (long)Math.floor(Math.random() * 100);
    }
}