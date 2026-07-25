package com.esboco_comix.chatbot.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;
import java.util.stream.Collectors;

import com.esboco_comix.core.config.ConfigController;
import com.esboco_comix.core.utils.ConversorJson;

import jakarta.servlet.http.HttpServletRequest;

public class ChatbotProxyClient {

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

    public Object criarPrompt(HttpServletRequest req) throws Exception {

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

        return ConversorJson.jsonToObject(response.body(), Object.class);
    }
}
