package com.codeit.propertiesexampleV2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/** YAML 목록을 List<Server>로 받습니다. 목록 원소는 host와 port를 가진 객체입니다. */
@Component
@ConfigurationProperties(prefix = "my")
@Getter
@Setter
public class HostListProperties {
    private List<Server> servers;

    @Getter
    @Setter
    public static class Server {
        private String host;
        private int port;

        @Override
        public String toString() {
            return "Server [host=" + host + ", port=" + port + "]";
        }
    }
}
