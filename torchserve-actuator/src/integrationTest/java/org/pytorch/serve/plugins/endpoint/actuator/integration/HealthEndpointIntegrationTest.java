package org.pytorch.serve.plugins.endpoint.actuator.integration;

import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured;
import java.net.HttpURLConnection;
import org.testng.annotations.Test;

public class HealthEndpointIntegrationTest extends SetupTestSuite {
  @Test
  public void testHealthEndpoint() {
    RestAssured.given()
        .spec(getRequestSpec())
        .when()
        .get("/health")
        .then()
        .statusCode(HttpURLConnection.HTTP_OK)
        .body(
            "status",
            equalTo("UP"),
            "components.size()",
            equalTo(1),
            "components.noop.status",
            equalTo("UP"));
  }
}
