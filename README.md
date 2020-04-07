# commitmessagelinter-maven-plugin

# Example

## Basic Configuration
First, Add that in your root POM.xml file:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.paulrps</groupId>
            <artifactId>commitmessagelinter-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <configuration>
                <project>${project}</project>
                <commitMessage>${commitMessage}</commitMessage>
                <regex>^IssueId: #\d+</regex>
                <disableValidationMessage>false</disableValidationMessage>
            </configuration>
        </plugin>
    </plugins>
</build>
```
If there is more than one .git folder in the project base directory (for example submodules), every commit in all of them will be validated.
 
Then, install the hook commit message file by running the maven goal install-hook:
```bash
mvn com.paulrps:commitmessagelinter-maven-plugin:install-hook
```

So, when you execute git commit -m "message", this will be validated by configuration.regex parameter in your POM.xml file.