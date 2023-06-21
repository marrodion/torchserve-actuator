package org.pytorch.serve.plugins.endpoint.actuator.info;

public final class InfoImplementation {
  private static final InfoResponse info;

  static {
    GitInfo gitInfo = new GitInfo();
    BuildInfo buildInfo = new BuildInfo();
    info = new InfoResponse(gitInfo, buildInfo);
  }

  private InfoImplementation() {}

  public static InfoResponse getInfo() {
    return info;
  }
}
