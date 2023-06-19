package org.pytorch.serve.plugins.endpoint.actuator.integration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.net.URL;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class SetupTestSuite {
  private static final String TORCH_SERVE_IMAGE = "pytorch/torchserve:latest";

  static final GenericContainer<?> TORCHSERVE_CONTAINER;

  static {
    TORCHSERVE_CONTAINER = getContainer();
    TORCHSERVE_CONTAINER.start();
  }

  private static GenericContainer<?> getContainer() {
    return new GenericContainer<>(DockerImageName.parse(TORCH_SERVE_IMAGE))
        .withExposedPorts(8080, 8081)
        .withClasspathResourceMapping(
            "noop.mar", "/home/model-server/model-store/noop.mar", BindMode.READ_ONLY)
        .withClasspathResourceMapping(
            "config.properties", "/home/model-server/config.properties", BindMode.READ_ONLY)
        .withFileSystemBind(
            System.getenv("ACTUATOR_JAR"),
            "/home/model-server/plugins/actuator.jar",
            BindMode.READ_ONLY)
        .waitingFor(Wait.forLogMessage(".*WORKER_MODEL_LOADED.*\\n", 6));
  }

  static URL getHost() {
    try {
      return new URL("http://" + TORCHSERVE_CONTAINER.getHost());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static RequestSpecification getRequestSpec() {
    return new RequestSpecBuilder()
        .setBaseUri(getHost().toString())
        .setPort(TORCHSERVE_CONTAINER.getMappedPort(8080))
        .setBasePath("/actuator")
        .log(LogDetail.ALL)
        .setContentType(ContentType.JSON)
        .build();
  }
}
