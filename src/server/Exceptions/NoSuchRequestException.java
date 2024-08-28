package server.Exceptions;

public class NoSuchRequestException extends RuntimeException{
    public NoSuchRequestException() {
        super("Bad request");
    }
}