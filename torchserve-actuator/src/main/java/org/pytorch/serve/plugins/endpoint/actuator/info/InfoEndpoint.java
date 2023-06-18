package org.pytorch.serve.plugins.endpoint.actuator.info;

import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.ModelServerEndpointException;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

import java.io.IOException;

public class InfoEndpoint extends ModelServerEndpoint {
    @Override
    public void doGet(Request req, Response res, Context ctx) throws ModelServerEndpointException, IOException {
        super.doGet(req, res, ctx);
    }
}
