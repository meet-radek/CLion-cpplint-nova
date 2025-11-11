# Building CLion Cpplint Nova

This document describes how to build the CLion Cpplint Nova plugin from source.

## Prerequisites

### Required Software

1. **JDK 21**
   - Download from: https://www.oracle.com/java/technologies/downloads/#java21
   - Or use OpenJDK: https://adoptium.net/temurin/releases/?version=21
   - Verify installation:
     ```bash
     java -version
     # Should show: java version "21.x.x"
     ```

2. **Gradle 9.0.0 or later**
   - Download from: https://gradle.org/releases/
   - Recommended: 9.2.0 or later
   - Extract to a directory (e.g., `C:\Gradle\gradle-9.2.0` on Windows)
   - Verify installation:
     ```bash
     gradle -version
     # Should show: Gradle 9.x.x
     ```

### Environment Setup

Set the `JAVA_HOME` environment variable to point to your JDK 21 installation:

**Windows:**
```bash
set JAVA_HOME=C:\Program Files\Java\jdk-21
```

**Linux/macOS:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
# or wherever your JDK 21 is installed
```

## Build Commands

### Using Local Gradle Installation

If you have Gradle installed globally:

```bash
# Clean build artifacts
gradle clean

# Build the plugin
gradle buildPlugin

# Run tests
gradle test

# Verify plugin compatibility
gradle verifyPlugin
```

### Using Gradle Wrapper (Recommended)

The project includes Gradle wrapper files for consistent builds:

**Windows:**
```bash
gradlew.bat clean buildPlugin
```

**Linux/macOS:**
```bash
./gradlew clean buildPlugin
```

### Windows with Custom Gradle Location

If you have Gradle in a custom location:

```bash
cd C:\Apps\CLion-cpplint-nova\old
set JAVA_HOME=C:\Program Files\Java\jdk-21
C:\Gradle\gradle-9.2.0\bin\gradle clean buildPlugin
```

### Linux/macOS with Custom Gradle Location

```bash
cd /path/to/CLion-cpplint-nova/old
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
/path/to/gradle-9.2.0/bin/gradle clean buildPlugin
```

## Build Output

After a successful build, the plugin distribution will be available at:

```
build/distributions/CLion-cpplint-nova-{version}.zip
```

For example: `build/distributions/CLion-cpplint-nova-1.0.0.zip`

## Common Build Tasks

### Clean Build
Removes all build artifacts:
```bash
gradle clean
```

### Build Plugin
Compiles the plugin and creates the distribution ZIP:
```bash
gradle buildPlugin
```

### Run in Development Mode
Launches CLion with the plugin installed in a sandbox environment:
```bash
gradle runIde
```

### Run Tests
Executes the plugin's test suite:
```bash
gradle test
```

### Verify Plugin
Checks plugin compatibility with target IDE versions:
```bash
gradle verifyPlugin
```

### Full Clean Build
Clean and rebuild everything:
```bash
gradle clean build
```

## Troubleshooting

### Error: "Could not find or load main class"
- Ensure `JAVA_HOME` is set correctly to JDK 21
- Verify JDK 21 is installed: `java -version`

### Error: "Unsupported class file major version"
- You need JDK 21 or later
- Check your Java version: `java -version`

### Error: "Gradle version too old"
- Upgrade to Gradle 9.0.0 or later
- Check your Gradle version: `gradle -version`

### Build Fails with "Version is missing"
- Ensure `CHANGELOG.md` exists and has proper format
- Check that README.md has plugin description markers

### Out of Memory Errors
Increase Gradle memory in `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m
```

## Development Workflow

1. **Make Changes**: Edit source files in `src/main/kotlin/`
2. **Test Locally**: Run `gradle runIde` to test in sandbox
3. **Run Tests**: Execute `gradle test` to verify functionality
4. **Build Plugin**: Run `gradle buildPlugin` to create distribution
5. **Verify**: Run `gradle verifyPlugin` to check compatibility

## Plugin Structure

```
old/
├── src/
│   ├── main/
│   │   ├── kotlin/           # Kotlin source code
│   │   └── resources/        # Plugin resources (plugin.xml, icons)
│   └── test/                 # Test files
├── build/
│   └── distributions/        # Built plugin ZIP files
├── build.gradle.kts          # Gradle build configuration
├── gradle.properties         # Plugin metadata and versions
└── settings.gradle.kts       # Gradle settings
```

## Publishing

After building, the plugin ZIP can be:
1. **Uploaded to JetBrains Marketplace**: https://plugins.jetbrains.com/
2. **Installed manually**: File → Settings → Plugins → Install from Disk
3. **Distributed directly**: Share the ZIP file with users

## Additional Resources

- [IntelliJ Platform Plugin SDK](https://plugins.jetbrains.com/docs/intellij/welcome.html)
- [IntelliJ Platform Gradle Plugin](https://github.com/JetBrains/intellij-platform-gradle-plugin)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Gradle Documentation](https://docs.gradle.org/)
