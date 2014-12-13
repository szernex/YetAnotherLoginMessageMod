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
