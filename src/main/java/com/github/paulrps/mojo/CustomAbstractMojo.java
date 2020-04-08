package com.github.paulrps.mojo;

import com.github.paulrps.exception.CustomMojoExecutionException;
import com.github.paulrps.model.MojoParameter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class CustomAbstractMojo extends AbstractMojo {

  /**
   * Maven project ${project} that you put your root pom file.
   *
   * @parameter
   */
  @Parameter private MavenProject project;

  /**
   * Commit message that is get from command installed in .git/hooks/commit-msg file.
   *
   * @parameter
   */
  @Parameter private String commitMessage;

  /**
   * Regex pattern you want to use to validate the commit message.
   *
   * @parameter
   */
  @Parameter private String regex;

  /**
   * Flag that you can disable the commit message validation.
   *
   * @parameter
   */
  @Parameter private boolean disableValidationMessage;

  public void execute() throws CustomMojoExecutionException {
    execute(buildParameters());
  }

  public abstract void execute(final MojoParameter parameters) throws CustomMojoExecutionException;

  private MojoParameter buildParameters() {
    return MojoParameter.builder()
            .project(project)
            .commitMessage(commitMessage)
            .regex(regex)
            .disableValidationMessage(disableValidationMessage)
            .build();
  }
}
