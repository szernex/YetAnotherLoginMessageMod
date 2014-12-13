package org.szernex.yalmm.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import org.szernex.yalmm.reference.Reference;

import java.io.File;

public class ConfigHandler
{
	public static Configuration configuration;

	public static String messageFile = "yalmm_message.txt";
	public static int messageDelay = 1000;


	public static void init(File config_file)
	{
		if (configuration == null)
		{
			configuration = new Configuration(config_file);
			loadConfig();
		}
	}

	public static void loadConfig()
	{
		messageFile = configuration.getString("messageFile", Configuration.CATEGORY_GENERAL, messageFile, "The file to read the login message from.");
		messageDelay = configuration.getInt("messageDelay", Configuration.CATEGORY_GENERAL, messageDelay, 0, 30000, "The time (in milliseconds) to wait before sending the message to a player.");

		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfig();
		}
	}
}
