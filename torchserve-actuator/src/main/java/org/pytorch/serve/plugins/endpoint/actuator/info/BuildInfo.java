package org.pytorch.serve.plugins.endpoint.actuator.info;

import java.util.Objects;
import java.util.Optional;

public class BuildInfo {
  private final String artifact;
  private final String version;
  private final String group;

  public BuildInfo(String artifact, String version, String group) {
    this.artifact = artifact;
    this.version = version;
    this.group = group;
  }

  BuildInfo(Package pkg) {
    Objects.requireNonNull(pkg);
    this.artifact = Optional.ofNullable(pkg.getImplementationTitle()).orElse("");
    this.version = Optional.ofNullable(pkg.getImplementationVersion()).orElse("");
    this.group = Optional.ofNullable(pkg.getImplementationVendor()).orElse("");
  }

  public BuildInfo() {
    this(BuildInfo.class.getPackage());
  }

  public String getArtifact() {
    return artifact;
  }

  public String getVersion() {
    return version;
  }

  public String getGroup() {
    return group;
  }
}
