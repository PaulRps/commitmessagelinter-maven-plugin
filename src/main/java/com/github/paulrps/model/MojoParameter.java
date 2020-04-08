package com.github.paulrps.model;


import lombok.Builder;
import lombok.Data;
import org.apache.maven.project.MavenProject;

@Data
@Builder
public class MojoParameter {
    private MavenProject project;
    private String commitMessage;
    private String regex;
    private boolean disableValidationMessage;
}
