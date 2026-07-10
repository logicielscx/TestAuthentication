# Player Distance HUD

Player Distance HUD is a small client-side Fabric mod for Minecraft Java Edition 1.21.11. It displays the nearest other player currently loaded in the local client world and that player's true three-dimensional distance, for example:

```text
Steve - 24.7 blocks
```

## Features

- Finds the nearest other player currently known to the client.
- Uses exact entity positions and full 3D Euclidean distance, including vertical separation.
- Updates continuously as players move or teleport.
- Renders a centered, shadowed HUD line below the crosshair.
- Hides automatically when the vanilla HUD is hidden with F1, when a screen is open, or when no valid target is loaded.
- Keeps no cached player references, so world changes and disconnects cannot leave a stale target.

## Requirements

- Minecraft Java Edition 1.21.11
- Java 21
- Fabric Loader 0.19.3 or newer compatible loader
- Fabric API 0.141.4+1.21.11 or newer compatible 1.21.11 release

The mod is **client-side only**. It does not need to be installed on the server and sends no custom network packets.

## Build

On Unix-like systems:

```bash
./gradlew build
```

On Windows:

```bat
gradlew.bat build
```

The installable mod is produced at:

```text
build/libs/playerdistancehud-1.0.0.jar
```

The similarly named `-sources.jar` is source code for IDEs and is not the file to install.

## Installation

1. Install Java 21.
2. Install Fabric Loader for Minecraft 1.21.11.
3. Place Fabric API for Minecraft 1.21.11 in the Minecraft `mods` folder.
4. Place `playerdistancehud-1.0.0.jar` in the same `mods` folder.
5. Start Minecraft with the Fabric profile.

No server installation is required.

## Usage

Join a world or server where at least one other player entity is within the client's tracking range. The nearest loaded player's name and distance appear below the crosshair. The target changes automatically when another loaded player becomes closer.

Press F1 to hide the vanilla HUD and this display. Opening a menu or other screen also hides the display.

## Known limitations

- The mod can only see player entities sent to and currently tracked by the client. It cannot find distant, vanished, spectator-hidden, or otherwise untracked players.
- Only players in the local player's current dimension can be considered.
- There are no configuration options in version 1.0.0.
- The HUD is intentionally hidden whenever a GUI screen is open, including chat, inventory, and pause screens.
