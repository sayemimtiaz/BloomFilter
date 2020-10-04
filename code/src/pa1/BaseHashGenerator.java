package pa1;

public abstract class BaseHashGenerator {
    public abstract void init(String s);
    public abstract long getHash(int iteration);
}
