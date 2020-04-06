package com.paulrps.mojo;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.paulrps.exception.CustomMojoExecutionException;
import com.paulrps.model.MessagesEnum;
import com.paulrps.model.MojoParameter;
import com.paulrps.util.HookFileContentHandler;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * @phase process-sources
 */
// @NoArgsConstructor
// @Data
@Mojo(name = "install-hook")
public class InstallHookMojo extends CustomAbstractMojo {

  public void execute(final MojoParameter parameters) throws CustomMojoExecutionException {
    Log log = this.getLog();
    if (!Optional.ofNullable(parameters.getProject()).isPresent()) {
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_PARAMETER_NOT_FOUND, "${project}");
    }

    String baseDir = parameters.getProject().getBasedir().getAbsolutePath();
    log.info(MessagesEnum.INFO_PROJECT_BASE_DIR.getFormatedMessage(baseDir));

    final String target = String.format("%s/.git/hooks/commit-msg", baseDir);
    log.info(MessagesEnum.INFO_TARGET_GIT_FILE.getFormatedMessage(target));

    final File file = new File(target);
    FileWriter fw = null;
    BufferedWriter bw = null;
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(HookFileContentHandler.getContent());
    } catch (final IOException ex) {
      log.error(ex);
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_INSTALL_HOOK_FILE);
    } finally {
      try {
        fw.close();
        bw.close();
      } catch (Throwable t) {
        //        ignore
      }
    }
  }
}
