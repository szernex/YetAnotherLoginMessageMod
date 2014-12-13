package org.szernex.yalmm.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import org.szernex.yalmm.core.MessageTask;
import org.szernex.yalmm.util.FileHelper;

import java.io.File;
import java.util.List;
import java.util.Timer;

public class PlayerLoginHandler
{
	private long lastModifiedTimestamp = 0;
	private List<String> loginMessage = null;

	/*private void loadLoginMessage(String path)
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
	}*/

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		File file = new File(ConfigHandler.messageFile);

		if (file.lastModified() > lastModifiedTimestamp)
		{
			loginMessage = FileHelper.readFile(ConfigHandler.messageFile);
			lastModifiedTimestamp = file.lastModified();
		}

		if (loginMessage == null)
		{
			return;
		}

		new Timer().schedule(new MessageTask(loginMessage, event.player), ConfigHandler.messageDelay);
	}
}
