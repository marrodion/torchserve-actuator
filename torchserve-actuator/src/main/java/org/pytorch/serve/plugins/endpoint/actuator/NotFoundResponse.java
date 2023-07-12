package org.pytorch.serve.plugins.endpoint.actuator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

public class NotFoundResponse {
  private static final int HTTP_NOT_FOUND = 404;

  public static void notFoundResponse(Request req, Response rsp) throws IOException {
    rsp.setStatus(HTTP_NOT_FOUND);
    rsp.getOutputStream()
        .write(String.format("%s Not found", req.getRequestURI()).getBytes(StandardCharsets.UTF_8));
  }
}
