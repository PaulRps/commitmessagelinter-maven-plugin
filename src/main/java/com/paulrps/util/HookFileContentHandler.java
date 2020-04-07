package com.paulrps.util;

import lombok.Builder;

import java.io.IOException;
import java.util.Properties;

@Builder
public class HookFileContentHandler {

  private static final String CONTENT =
      "#!/bin/bash\n"
          + "file=$1\n"
          + "message=`cat $file`\n"
          + "mvn %s:%s:%s:validate-message -DcommitMessage=\"${message}\"\n";

  public static String getContent() throws IOException {

    final Properties properties = new Properties();
    properties.load(
        HookFileContentHandler.class.getClassLoader().getResourceAsStream("project.properties"));
    return String.format(
        CONTENT,
        properties.getProperty("plugin.groupId"),
        properties.getProperty("plugin.artifactId"),
        properties.getProperty("plugin.version"));
  }
}
