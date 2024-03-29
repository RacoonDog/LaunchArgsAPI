a# Launch Args API
A Fabric library that provides common hooks to access Minecraft launch arguments

## Usage

### `CREATE_SPECS` Event
Gives you access to [joptsimple](https://github.com/jopt-simple/jopt-simple) `OptionParser` object
### `PARSE_ARGS` Event
Gives you access to [joptsimple](https://github.com/jopt-simple/jopt-simple) `OptionSet` object

```java
public class LaunchArgsTest implements ArgsListener {
    private static final Logger LOG = LogUtils.getLogger();
    private OptionSpec<Void> optionSpec;

    @Override
    public void createSpecs(OptionParser optionParser) {
        optionSpec = optionParser.accepts("someArg");
    }

    @Override
    public void parseArgs(OptionSet optionSet) {
        if (optionSet.has(optionSpec)) LOG.info("Found argument!");
    }
}
```

```groovy
repositories {
    maven {
        name = "meteor-maven"
        url = "https://maven.meteordev.org/releases"
    }
}

dependencies {
  modImplementation "io.github.racoondog:LaunchArgsAPI:1.1.3"
  include "io.github.racoondog:LaunchArgsAPI:1.1.3"
}
```

## Difference between LaunchArgsAPI and `FabricLoader.getLaunchArguments`
Parsing custom launch arguments from `FabricLoader.getLaunchArguments()` doesn't remove the "Completely ignored arguments" message

## Minecraft launch argument documentation
Since I couldn't find this anywhere else...
### Client (As of 1.19.3)
- `--demo`: Enables demo mode if present
- `--disableMultiplayer`: Disables multiplayer if present
- `--disableChat`: Disables chat if present
- `--fullscreen`: Starts game in fullscreen if present
- `--checkGlErrors`
- `--jfrProfile`
- `--server`: `String`; Server address to join after game initialization
- `--port`: `Integer`; Server port to join after game initialization
- `--gameDir`: `File`; Defaults to `.`
- `--assetsDir`: `File`; Defaults to `{gameDir}/assets/`
- `--resourcePackDir`: `File`; Defaults to `{gameDir}/resourcepacks/`
- `--proxyHost`: `String`
- `--proxyPort`: `Integer`; Defaults to `8080`
- `--proxyUser`: `String`
- `--proxyPass`: `String`
- `--username`: `String`; Defaults to `Player {4 random digits}`; Minecraft account username, required for login
- `--uuid`: `String`; Defaults to random; Minecraft account uuid, required for login
- `--xuid`: `String`; Defaults to empty; XBox uuid
- `--clientId`: `String`; Defaults to empty
- `--accessToken`: `String`; Required; Minecraft account token, required for login
- `--version`: `String`; Required
- `--width`: `Integer`; Defaults to `854`
- `--height`: `Integer`; Defaults to `480`
- `--fullscreenWidth`: `Integer`
- `--fullscreenHeight`: `Integer`
- `--userProperties`: `String`; Defaults to `{}`
- `--profileProperties`: `String`; Defaults to `{}`
- `--assetIndex`: `String`
- `--userType`: `String`; Defaults to `legacy`
- `--versionType`: `String`; Defaults to `release`

### Server (As of 1.19.3)
- `--nogui`: Disables gui if present
- `--initSettings`: Initializes 'server.properties' and 'eula.txt', then quits
- `--demo`: Enables demo mode if present
- `--bonusChest`: Enables bonus chest if present
- `--forceUpgrade`: Forcibly runs datafixers on world if present
- `--eraseCache`: If present, erase cache (Heightmaps, Lightmaps) during world upgrade, only has an effect if `forceUpgrade` is present
- `--safeMode`: Loads level with vanilla datapack only if present
- `--help`: Prints commands, then quits
- `--singleplayer`: `String`
- `--universe`: `String`; Defaults to `.`
- `--world`: `String`
- `--port`: `Integer`; Defaults to `-1`
- `--serverId`: `String`
- `--jfrProfile`
- `nogui`: Disables gui if present

Thanks unascribed for helping me understand some launch arguments