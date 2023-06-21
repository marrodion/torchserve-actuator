package org.pytorch.serve.plugins.endpoint.actuator.info;

import static org.eclipse.jgit.lib.Constants.HEAD;

import java.io.IOException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitInfo {
  private final String branch;
  private final CommitInfo commit;

  public GitInfo(String branch, CommitInfo commit) {
    this.branch = branch;
    this.commit = commit;
  }

  public GitInfo() {
    CommitInfo commitInfo;
    String branch = "";
    try (Repository repository =
        new FileRepositoryBuilder().readEnvironment().findGitDir().build()) {
      branch = repository.getBranch();
      ObjectId sha = repository.resolve(HEAD);
      RevCommit commit = repository.parseCommit(sha);
      commitInfo = new CommitInfo(commit);
    } catch (IOException e) {
      commitInfo = new CommitInfo();
    }
    this.branch = branch;
    this.commit = commitInfo;
  }

  public String getBranch() {
    return branch;
  }

  public CommitInfo getCommit() {
    return commit;
  }
}
