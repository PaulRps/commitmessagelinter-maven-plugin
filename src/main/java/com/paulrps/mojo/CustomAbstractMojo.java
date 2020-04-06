package com.paulrps.mojo;

import com.paulrps.exception.CustomMojoExecutionException;
import com.paulrps.model.MojoParameter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class CustomAbstractMojo extends AbstractMojo {

  @Parameter private MavenProject project;
  @Parameter private String commitMessage;
  @Parameter private String regex;
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
