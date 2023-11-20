package server;

public interface Database {

    boolean set(String keyInput, String valueInput);

    String get(String keyInput);

    boolean delete(String keyInput);

}
