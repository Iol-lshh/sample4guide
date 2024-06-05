package lshh.sample4guide.common.library.remoteclient;

public class RemoteClientConnectException extends RuntimeException {
    public RemoteClientConnectException(String message) {
        super(message);
    }

    public RemoteClientConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteClientConnectException() {
        super("Remote client connect exception");
    }
}
