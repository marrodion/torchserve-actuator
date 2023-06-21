package org.pytorch.serve.plugins.endpoint.actuator.info;

import java.util.Date;
import org.eclipse.jgit.revwalk.RevCommit;

class CommitInfo {
  private final String id;
  private final Date time;

  public CommitInfo(String id, Date time) {
    this.id = id;
    this.time = time;
  }

  public CommitInfo(RevCommit commit) {
    this.id = commit.getId().toString();
    this.time = new Date(commit.getCommitTime() * 1000L);
  }

  public CommitInfo() {
    this.id = "";
    this.time = new Date(0);
  }

  public String getId() {
    return id;
  }

  public Date getTime() {
    return Date.from(time.toInstant());
  }
}
