package com.github.paulrps.exception;

import com.github.paulrps.model.MessagesEnum;
import org.apache.maven.plugin.MojoExecutionException;

public class CustomMojoExecutionException extends MojoExecutionException {

  public CustomMojoExecutionException(MessagesEnum error, String parameter) {
    super(error.getFormatedMessage(parameter));
  }

  public CustomMojoExecutionException(MessagesEnum error) {
    super(error.getMessage());
  }
}
