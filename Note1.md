# Test note1
* Item 1
* Item 2
* Item 3

# See if link works:  [[Linux|Linux]] page

# Check Java code:
```java
  public static void main(String[] args) {
    System.out.println("Hello");
  }
```

# Check xml:
```xml
<?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" debug="true" threshold="all">

    <!-- ================================= -->
    <!-- Appenders                         -->
    <!-- ================================= -->

    <appender name="FILE" class="org.apache.log4j.FileAppender">
        <param name="File"      value="${project.build.directory}/surefire-reports/${artifactId}-log4j.log"/>
        <param name="Append"    value="true"/>
        <param name="Threshold" value="DEBUG"/>

        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="[%X{unit-test}] %d [%-5p] [thread: %t] %F:%L (%c:%M()) - %m%n"/>
        </layout>
    </appender>

    <category name="com.rdc" additivity="true">
        <priority value="DEBUG"/>
    </category>
   
    <root>
        <appender-ref ref="FILE"/>
    </root>

</log4j:configuration>

```
