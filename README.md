# commitmessagelinter-maven-plugin

# Simple Example

## With Basic Configuration
Add that in your POM.xml file:
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

Install the hook commit message file by running the maven goal install-hook:
```bash
mvn com.paulrps:commitmessagelinter-maven-plugin:install-hook
```

So, when you execute git commit -m "message", this will be validated for configuration.regex parameter in your POM.xml file.