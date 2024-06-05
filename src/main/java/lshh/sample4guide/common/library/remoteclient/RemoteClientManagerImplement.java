package lshh.sample4guide.common.library.remoteclient;

import java.util.*;

public class RemoteClientManagerImplement<T> implements RemoteClientManager<T>{

    protected final Map<String, Integer> ID_MAP;
    protected final List<RemoteClient<T>> CLIENT_LIST;
    protected RemoteClient<T> current;

    private RemoteClientManagerImplement(Map<String, Integer> idMap, List<RemoteClient<T>> apiClientList) {
        ID_MAP = idMap;
        CLIENT_LIST = apiClientList;
    }

    public static <T> RemoteClientManager<T> of(Class<T> defaultReturnType){
        return new RemoteClientManagerImplement<>(
                new HashMap<>(),
                new ArrayList<>()
        );
    }

    @Override
    public Optional<RemoteClient<T>> findById(String id) {
        Integer index = ID_MAP.get(id);
        if (index == null) {
            return Optional.empty();
        }
        return Optional.of(CLIENT_LIST.get(index));
    }

    @Override
    public Optional<RemoteClient<T>> findAvailable(){
        RemoteClient<T> currentClient = getCurrent();
        if(currentClient != null){
            return Optional.of(currentClient);
        }

        for (RemoteClient<T> client : CLIENT_LIST){
            try{
                if (client.isHealthy()) {
                    setCurrent(client);
                    return Optional.of(client);
                }
            } catch (Exception ignore){}
        }
        return Optional.empty();
    }

    @Override
    public void add(String id, RemoteClient<T> client) {
        ID_MAP.put(id, CLIENT_LIST.size());
        CLIENT_LIST.add(client);
    }

    @Override
    public void setCurrent(RemoteClient<T> target){
        this.current = target;
    }

    @Override
    public RemoteClient<T> getCurrent(){
        if(!this.current.isHealthy()){
            this.current = null;
            return null;
        }
        return this.current;
    }

    @Override
    public Optional<T> tryGet(String url) {
        RemoteClient<T> client = findAvailable()
                .orElseThrow(() -> new RemoteClientConnectException("No any client exists."));
        return client.get(url);
    }

    @Override
    public <V> Optional<V> tryGet(String url, Class<V> responseType) {
        RemoteClient<T> client = findAvailable()
                .orElseThrow(() -> new RemoteClientConnectException("No any client exists."));
        return client.get(url, responseType);
    }

    @Override
    public Optional<T> tryPost(String url, Object body) {
        RemoteClient<T> client = findAvailable()
                .orElseThrow(() -> new RemoteClientConnectException("No any client exists."));
        return client.post(url, body);
    }

    @Override
    public <V> Optional<V> tryPost(String url, Object body, Class<V> responseType) {
        RemoteClient<T> client = findAvailable()
                .orElseThrow(() -> new RemoteClientConnectException("No any client exists."));
        return client.post(url, body, responseType);
    }
}
