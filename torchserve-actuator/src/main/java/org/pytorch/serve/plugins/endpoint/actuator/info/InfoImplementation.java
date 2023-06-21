package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;

public final class InfoImplementation {
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
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

  public static String getInfoJson() {
    return new GsonBuilder().setDateFormat(DATE_FORMAT).create().toJson(info);
  }
}
