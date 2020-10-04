package pa1;

public abstract class BaseDifferential {
    public double totalElapsedTime=0.0;

    public abstract String retrieveRecord(String key) throws Exception;

    public abstract String getType();
}
