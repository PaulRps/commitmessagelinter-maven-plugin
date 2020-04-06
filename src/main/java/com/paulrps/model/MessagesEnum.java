package com.paulrps.model;

import lombok.Getter;

@Getter
public enum MessagesEnum {
  ERROR_PARAMETER_NOT_FOUND(
      "ERROR01", "Parameter %s not found. See your plugin configuration in pom file!"),
  ERROR_INSTALL_HOOK_FILE("ERROR02", "Unable to write file in %s."),
  ERROR_COMMIT_MESSAGE_NOT_FOUND(
      "ERROR03", "Commit message not found. See your git commit command -m!"),
  ERROR_REGEX_NOT_MATCH("ERROR04", "Commit message does not match with the pattern: %s"),

  INFO_PROJECT_BASE_DIR("INFO01", "Project base directory: %s"),
  INFO_TARGET_GIT_FILE("INFO02", "Target git file: %s"),
  INFO_DISABLED_VALIDATION(
      "INFO03", "Message validation is disabled. See your plugin configuration in pom file!"),
  INFO_END_VALIDATION("INFO04", "Validation message executed: %s");

  private MessagesEnum(final String code, final String message) {
    this.code = code;
    this.message = message;
  }

  private String code;
  private String message;

  public String getFormatedMessage(String parameter) {
    return String.format(this.getMessage(), parameter);
  }
}
