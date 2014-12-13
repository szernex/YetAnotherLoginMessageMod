package org.szernex.yalmm.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import org.apache.commons.io.FileUtils;
import org.szernex.yalmm.core.MessageTask;
import org.szernex.yalmm.util.LogHelper;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class PlayerLoginHandler
{
	private long lastModifiedTimestamp = 0;
	private String loginMessage = null;

	private void loadLoginMessage(String path)
	{
		try
		{
			File message_file = new File(path);

			// does the file exist?
			if (!message_file.exists())
			{
				// can we create a new one?
				if (!message_file.createNewFile())
				{
					LogHelper.error("Could not create new message file " + path);
					return;
				}

				LogHelper.info("New empty message file " + path + " created");
			}

			// can we read the file?
			if (!message_file.canRead())
			{
				LogHelper.error("Can not open message file " + path + ": File does not exist or can not be read");
				return;
			}

			// did the file get modified since we last read it?
			if (message_file.lastModified() <= lastModifiedTimestamp)
			{
				return;
			}

			lastModifiedTimestamp = message_file.lastModified();

			// todo: format message
			loginMessage = FileUtils.readFileToString(message_file);
		}
		catch (IOException ex)
		{
			LogHelper.error("Error while reading message file " + path + ": " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		loadLoginMessage(ConfigHandler.messageFile);

		if (loginMessage == null)
		{
			return;
		}

		new Timer().schedule(new MessageTask(loginMessage, event.player), ConfigHandler.messageDelay);
	}
}
