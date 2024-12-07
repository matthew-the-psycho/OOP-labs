package LAB8;

public class IdentityGenerator implements IGenerator {
    @Override
    public String getName() {
        return "Eye";
    }
    @Override
    public long getElement(int i, int j) {
        return i==j ? 1 : 0;
    }
}