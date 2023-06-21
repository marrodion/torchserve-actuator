package org.pytorch.serve.plugins.endpoint.actuator.info;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.ModelServerEndpointException;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

public class InfoEndpoint extends ModelServerEndpoint {
  private static final String INFO_RESPONSE = InfoImplementation.getInfoJson();
  private static final String JSON_HEADER = "application/json";

  @Override
  public void doGet(Request req, Response res, Context ctx)
      throws ModelServerEndpointException, IOException {
    res.setStatus(HTTP_OK);
    res.setContentType(JSON_HEADER);
    res.getOutputStream().write(INFO_RESPONSE.getBytes(StandardCharsets.UTF_8));
  }
}
