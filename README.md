#YetAnotherLoginMessageMod (YALMM)
Presenting yet another very simple to use mod intended mostly for servers to display a defined message to players upon logging in to your server.

##Features
* Configure which file should be used to read the message from
* File content can be changed during runtime, YALMM will always read the file anew if it's been changed
* Configure the delay when the message should be sent after a player logs in (instantly or up to 30 seconds)
* Supports full color coding and fancy formatting of messages
* Supports multi-line messages
* Automatically parses links and makes them clickable (prototype functionality)

##Formatting help
YALMM uses the standard Minecraft formatting codes. Keep in mind that all formatting codes only mark where the specific formatting will start. To reset it you have to add §r before the new formatting. Also keep in mind that colors have to be defined before other fancy formatting (bold, underline etc)

* §0 - Black
* §1 - Dark Blue
* §2 - Dark Green
* §3 - Dark Aqua
* §4 - Dark Red
* §5 - Dark Purple
* §6 - Gold
* §7 - Gray
* §8 - Dark Gray
* §9 - Blue
* §a - Green
* §b - Aqua
* §c - Red
* §d - Light purple
* §e - Yellow
* §f - White
* §k - Obfuscated
* §l - Bold
* §m - Strike-through
* §n - Underline
* §o - Italic
* §r - Reset

Example: A message that is all Green and only has one word bold would be formatted like this:
**§aHello world, how are §lyou§r§a doing?**
