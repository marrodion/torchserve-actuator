package org.pytorch.serve.plugins.endpoint.actuator.integration;

import static org.pytorch.serve.plugins.endpoint.actuator.integration.SetupTestSuite.getRequestSpec;

import io.restassured.RestAssured;
import java.net.HttpURLConnection;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class InfoEndpointIntegrationTest {
  @Test
  public void testInfoEndpoint() {
    RestAssured.given()
        .spec(getRequestSpec())
        .when()
        .get("/info")
        .then()
        .statusCode(HttpURLConnection.HTTP_OK)
        .body(
            "build.artifact",
            Matchers.notNullValue(),
            "build.group",
            Matchers.notNullValue(),
            "build.version",
            Matchers.notNullValue(),
            "git.branch",
            Matchers.notNullValue(),
            "git.commit.id",
            Matchers.notNullValue(),
            "git.commit.time",
            Matchers.notNullValue());
  }
}
