package LAB8;

public class MatrixData {
    public static IGenerator[] generators = {
        new ZeroGenerator(),
        new IdentityGenerator(),
        new DiagonalGenerator(),
        new RandomGenerator()
    };
    public static IMatrixListener[] listeners = {
        new DisplayMatrixListener(),
        new CalculateMaxListener(),
        new CalculateSumListener(),
        new CalculateTraceListener()
    };
}