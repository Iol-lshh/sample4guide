package lshh.sample4guide.common.library.remoteclient;

import java.util.Optional;

public interface RemoteClient<T> {
    Optional<T> get(String url);
    <V> Optional<V> get(String url, Class<V> responseType);
    Optional<T> post(String url, Object body);
    <V> Optional<V> post(String url, Object body, Class<V> responseType);
    boolean isHealthy();
}
