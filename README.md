# Mine Mine no Mi
Mine Mine no Mi is a Minecraft Mod, using MinecraftForge, inspired by the famous One Piece manga, written and illustrated by Eiichiro Oda. It aims to be an adventure oriented mod based around exploration.

## Features
* 43 Devil Fruits, each fruit comes with a bunch of different abilities to use, with a grand total of 203 abilities (and counting)
* Different classes, factions and fighting styles to choose from, each slightly changing the outcome of the playthrough
* Various new enemies from different factions like marines, pirates, bandits as well different types of animals, each with different, custom-made AI
* New structures to raid and loot, such as pirate ships or marine bases
* A bounty system for Pirates, Revolutionaries and other similar factions as well as a Bounty Hunter system for players to hunt them down
* A crew system where Pirates can form their own crews, create a Jolly Roger and explore the world together
* Quests and open world activities for players to do while in singleplayer or multiplayer
* New weapon/armor enchantments using dials or kairoseki
* Various weapons and vanity items
* Customization support for both servers and single player worlds in the form of different config options

## How to Install
1. Download and install MinecraftForge from their [official website](https://files.minecraftforge.net/)
	* This Mod is made using 1.14.4 - 28.2.17, so using that version or something higher than it is recommended
2. Download the Mod from either our [official website](https://pixelatedw.xyz/mine-mine-no-mi/downloads), or the [curse page](https://www.curseforge.com/minecraft/mc-mods/mine-mine-no-mi)
3. Create a new "mods" folder, if not already present, inside your ".minecraft" folder
    * On Windows the ".minecraft" folder can be found by pressing Windows Key + R and running the %appdata% command
    * On Linux it can be found inside the "~/.minecraft" directory
    * On macOS it can be found inside "~/Library/Application Support/minecraft" directory
4. Move the Mod file (the .jar file) inside this folder
5. Start Minecraft and choose the newly created "forge" profile
6. Enjoy

## Support
* Donations via [Paypal](shorturl.at/cfhHR) or [Patreon](https://www.patreon.com/wynd) help us keep the servers going
* Help us localize the mod in your language, make sure nobody already thought about doing that first by checking this list
* Report bugs when you encounter them in a **clear** and **concise** way using the available templates here on Github
* Join the [discord server](http://discord.gg/CYK9xs8), helping newcomers, chatting or just sharing dank memes
* Spread the word about our projects 

## Development
1. Clone the repository
    ```http
    https://github.com/PixelatedW/mineminenomi
    ```

2. Run one of the following command, in order to setup forge and generate the .launch files
    * For Eclipse:
    ```bash
    ./gradlew eclipse genEclipseRuns
    ```
    * For IntelliJ:
    ```bash
    ./gradlew intellij genIntellijRuns
    ```

3. Open your IDE and Import the project
    * For Eclipse:
        1. File -> Import
        2. General/Existing Projects into Workspace
        3. In the "Select root directory" field browse and select the new project
        4. Make sure its marked under the "Projects" list
        5. Finish
    
    * For IntelliJ:
        1. File -> New 
        2. Project from existing source...
        3. Click the build.gradle
        
        - You can alternatively open it as a project folder with the 
        windows extension and it should start importing it automatically.


## License
GNU General Public License v3.0
