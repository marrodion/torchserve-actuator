package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;

public final class InfoImplementation {
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
  private static final InfoResponse INFO;

  static {
    GitInfo gitInfo = new GitInfo();
    BuildInfo buildInfo = new BuildInfo();
    INFO = new InfoResponse(gitInfo, buildInfo);
  }

  private InfoImplementation() {}

  public static InfoResponse getInfo() {
    return INFO;
  }

  public static String getInfoJson() {
    return new GsonBuilder().setDateFormat(DATE_FORMAT).create().toJson(INFO);
  }
}
