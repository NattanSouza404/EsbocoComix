package com.esboco_comix.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;
import java.util.stream.Collectors;

import com.esboco_comix.config.ConfigController;
import com.esboco_comix.utils.ConversorJson;

import jakarta.servlet.http.HttpServletRequest;

public class ChatbotProxyService {

    private final HttpClient client = HttpClient.newHttpClient();

    private static final Set<String> HEADERS_PROIBIDOS = Set.of(
        "content-length",
        "host",
        "connection",
        "transfer-encoding",
        "expect",
        "date",
        "from",
        "via",
        "upgrade"
    );

    public Object forwardJson(HttpServletRequest req) throws Exception {

        String path = req.getServletPath();
        String url = ConfigController.CHATBOT_URL + path+"/get-message";

        String body = req.getReader().lines().collect(Collectors.joining("\n"));

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .method(
                req.getMethod(),
                body.isEmpty()
                    ? HttpRequest.BodyPublishers.noBody()
                    : HttpRequest.BodyPublishers.ofString(body)
            );

        req.getHeaderNames().asIterator().forEachRemaining(name -> {
            if (!HEADERS_PROIBIDOS.contains(name.toLowerCase())) {
                builder.header(name, req.getHeader(name));
            }
        });

        HttpResponse<String> response =
            client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        // sempre JSON
        return ConversorJson.jsonToObject(response.body(), Object.class);
    }
}
