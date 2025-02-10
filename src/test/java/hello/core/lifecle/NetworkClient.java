package hello.core.lifecle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @PostConstruct
    public void connect() {
        System.out.println("NetworkClient.connect");
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }


    @PreDestroy
    public void disconnect() {
        System.out.println("NetworkClient.disconnect");
        System.out.println("close: " + url);
    }

}
