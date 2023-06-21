package org.pytorch.serve.plugins.endpoint.actuator.info;

public class InfoResponse {
  private final GitInfo git;
  private final BuildInfo build;

  public InfoResponse(GitInfo git, BuildInfo build) {
    this.git = git;
    this.build = build;
  }

  public GitInfo getGit() {
    return git;
  }

  public BuildInfo getBuild() {
    return build;
  }
}
