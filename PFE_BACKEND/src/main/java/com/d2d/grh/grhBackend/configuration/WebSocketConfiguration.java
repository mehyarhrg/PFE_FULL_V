package com.d2d.grh.grhBackend.configuration;

import io.micrometer.core.lang.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

//@Configuration
//@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/notification")
                .setAllowedOrigins("*")
                // initial Request/Handshake interceptor
                .addInterceptors(new HttpSessionHandshakeInterceptor() {

                    @Override
                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception ex) {
                        super.afterHandshake(request, response, wsHandler, ex);
                    }

                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request,
                                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                                   Map<String, Object> attributes) throws Exception {
                        boolean b = super.beforeHandshake(request, response, wsHandler, attributes);
                        // && (request.getPrincipal()).isAuthenticated();
                        return b;
                    }

                });
    }
}
