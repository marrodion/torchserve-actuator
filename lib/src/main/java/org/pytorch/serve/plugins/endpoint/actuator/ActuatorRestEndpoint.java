package org.pytorch.serve.plugins.endpoint.actuator;

import org.pytorch.serve.plugins.endpoint.actuator.health.HealthEndpoint;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.annotations.Endpoint;
import org.pytorch.serve.servingsdk.annotations.helpers.EndpointTypes;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Endpoint(
        urlPattern = "actuator",
        endpointType = EndpointTypes.INFERENCE,
        description = "Actuator health status")
public class ActuatorRestEndpoint extends ModelServerEndpoint {

    private static final Map<String, ModelServerEndpoint> endpoints = Map.of("health", new HealthEndpoint());

    @Override
    public void doGet(Request req, Response rsp, Context ctx) throws IOException {
        switch (req.getRequestURI()) {
            case "/actuator" -> doGetActuator(req, rsp, ctx);
            case "/actuator/health" -> getEndpointByName("health").doGet(req, rsp, ctx);
            default -> {
                rsp.setStatus(404);
                rsp.getOutputStream().write(String.format("%s Not found", req.getRequestURI()).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    private void doGetActuator(Request req, Response rsp, Context ctx) throws IOException {
        rsp.setStatus(200);
        rsp.getOutputStream().write("Actuator".getBytes(StandardCharsets.UTF_8));
    }

    private ModelServerEndpoint getEndpointByName(String name) {
        return endpoints.get(name);
    }
}
