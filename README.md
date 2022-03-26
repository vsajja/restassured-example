# restassured-example

Example test suite for the SigFig.com Login API using rest-assured and JUnit.

## Prerequisites

- [Java JDK](|https://adoptopenjdk.net) version 8 or higher to be installed. To check installation:
    ```
    $ java -version
    openjdk version "11.0.14.1" 2022-02-08
    OpenJDK Runtime Environment Temurin-11.0.14.1+1 (build 11.0.14.1+1)
    OpenJDK 64-Bit Server VM Temurin-11.0.14.1+1 (build 11.0.14.1+1, mixed mode)
    ```
- [IntelliJ IDE](https://www.jetbrains.com/idea/download/) 

## Running Tests

Gradle runs on all major operating systems assuming you have Java installed.

### Linux/Unix

```
./gradlew clean test
```

### Windows
```
gradlew.bat clean test
```

## Reports

After the test run, reports can be found in the `build/reports/tests` directory.

## Useful Links

-  [Rest-Assured  User Guide](https://github.com/rest-assured/rest-assured/wiki/Usage)
