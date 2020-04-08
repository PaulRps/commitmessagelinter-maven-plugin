package com.github.paulrps.mojo;

import com.github.paulrps.exception.CustomMojoExecutionException;
import com.github.paulrps.model.MessagesEnum;
import com.github.paulrps.model.MojoParameter;
import com.github.paulrps.util.HookFileContentHandler;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Goal which install the hook file commit-msg in .git/hooks.
 *
 * @author PaulRps
 */
@Mojo(name = "install-hook")
public class InstallHookMojo extends CustomAbstractMojo {

  public void execute(final MojoParameter parameters) throws CustomMojoExecutionException {
    Log log = this.getLog();
    if (!Optional.ofNullable(parameters.getProject()).isPresent()) {
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_PARAMETER_NOT_FOUND, "${project}");
    }

    String baseDir = parameters.getProject().getBasedir().getAbsolutePath();
    log.info(MessagesEnum.INFO_PROJECT_BASE_DIR.getFormatedMessage(baseDir));

    final String gitFolder = Paths.get(baseDir, ".git").toString();
    if (!new File(gitFolder).exists()) {
      log.info(MessagesEnum.INFO_GIT_FOLDER_NOT_FOUND.getFormatedMessage(gitFolder));
      return;
    }

    final String target = Paths.get(gitFolder, "hooks", "commit-msg").toString();
    log.info(MessagesEnum.INFO_TARGET_GIT_FILE.getFormatedMessage(target));

    final File file = new File(target);

    FileWriter fw = null;
    BufferedWriter bw = null;
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(HookFileContentHandler.getContent());
    } catch (final IOException ex) {
      log.error(ex);
      throw new CustomMojoExecutionException(MessagesEnum.ERROR_INSTALL_HOOK_FILE, target);
    } finally {
      try {
        fw.close();
        bw.close();
      } catch (Exception t) {
        //        ignore
      }
    }
  }
}
