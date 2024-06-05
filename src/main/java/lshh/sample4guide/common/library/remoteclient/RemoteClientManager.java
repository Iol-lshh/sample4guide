package lshh.sample4guide.common.library.remoteclient;

import java.util.Optional;

public interface RemoteClientManager<T> {
    Optional<RemoteClient<T>> findById(String id);
    Optional<RemoteClient<T>> findAvailable();

    void add(String id, RemoteClient<T> client);

    void setCurrent(RemoteClient<T> target);

    RemoteClient<T> getCurrent();

    Optional<T> tryGet(String url);
    <V> Optional<V> tryGet(String url, Class<V> responseType);

    Optional<T> tryPost(String url, Object body);
    <V> Optional<V> tryPost(String url, Object body, Class<V> responseType);
}
