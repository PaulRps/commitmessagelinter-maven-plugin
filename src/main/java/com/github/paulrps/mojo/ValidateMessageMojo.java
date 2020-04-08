package com.github.paulrps.mojo;

import com.github.paulrps.exception.CustomMojoExecutionException;
import com.github.paulrps.model.MessagesEnum;
import com.github.paulrps.model.MojoParameter;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Goal which validates the git commit message.
 *
 * @author PaulRps
 */
@Mojo(name = "validate-message", defaultPhase = LifecyclePhase.VALIDATE)
public class ValidateMessageMojo extends CustomAbstractMojo {

  @Override
  public void execute(MojoParameter parameters) throws CustomMojoExecutionException {
    final Log log = getLog();

    if (parameters.isDisableValidationMessage()) {
      log.info(MessagesEnum.INFO_DISABLED_VALIDATION.getMessage());
      return;
    }

    if (!Optional.ofNullable(parameters.getCommitMessage()).isPresent()) {
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_COMMIT_MESSAGE_NOT_FOUND);
    } else if (!Optional.ofNullable(parameters.getRegex()).isPresent()) {
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_PARAMETER_NOT_FOUND, "regex");
    }

    Pattern pattern = Pattern.compile(parameters.getRegex());
    Matcher matcher = pattern.matcher(parameters.getCommitMessage());
    if (!matcher.find()) {
      throw new CustomMojoExecutionException(
          MessagesEnum.ERROR_REGEX_NOT_MATCH, parameters.getRegex());
    }

    log.info(MessagesEnum.INFO_END_VALIDATION.getFormatedMessage(parameters.getCommitMessage()));
  }
}
