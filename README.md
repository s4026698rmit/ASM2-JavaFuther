# Event Management System (EMS)

## Prerequisites
- Java 17
- Maven 3.9+
- JavaFX runtime installed
  - macOS (Homebrew): `brew install openjfx`
    - JavaFX libs path: `/opt/homebrew/opt/openjfx/libexec/lib`
  - Windows/Linux: download JavaFX SDK from https://openjfx.io and point to its `lib` directory.

## First-time setup
1) Clone the repo:
```
git clone <repo-url>
cd ASM2-JavaFuther
```
2) Build:
```
mvn clean package
```

## Running the app
You must supply the JavaFX module path at runtime.

### Option A: Maven JavaFX plugin
```
mvn clean javafx:run \
  -Djavafx.module.path=/opt/homebrew/opt/openjfx/libexec/lib \
  -Dexec.mainClass=com.groupX.ems.MainApp
```
Adjust `javafx.module.path` for your OS/installation.

### Option B: Run shaded JAR
After `mvn package`, run:
```
/usr/bin/env java \
  --module-path /opt/homebrew/opt/openjfx/libexec/lib \
  --add-modules javafx.controls,javafx.fxml,javafx.graphics \
  -jar target/ems-1.0-SNAPSHOT-shaded.jar
```

### Option C: IDE run configuration
Add VM options in your Run/Debug config:
```
--module-path /opt/homebrew/opt/openjfx/libexec/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics
```
Main class: `com.groupX.ems.MainApp`

## Database configuration
Update `src/main/java/com/groupX/ems/config/DatabaseConnection.java` with your actual JDBC URL, user, and password. If you use `setup.sql`, ensure it matches your schema or add `IF NOT EXISTS` guards to avoid errors when tables already exist.

## Notes
- `module-info.java` is configured for the project packages; keep the JavaFX module path on the command line or in your IDE.
- Auth and event/presenter services use stub implementations; replace with real repository-backed services as needed.