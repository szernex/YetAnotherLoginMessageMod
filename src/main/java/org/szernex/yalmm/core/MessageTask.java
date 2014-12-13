package org.szernex.yalmm.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.TimerTask;

public class MessageTask extends TimerTask
{
	private String loginMessage;
	private EntityPlayer targetPlayer;

	public MessageTask()
	{
		super();
	}

	public MessageTask(String message, EntityPlayer player)
	{
		super();

		loginMessage = message.replaceAll("\r", ""); // get rid of the carriage return
		targetPlayer = player;
	}

	@Override
	public void run()
	{
		if (loginMessage != null)
		{
			String[] lines = loginMessage.split("\n");

			for (int i = 0; i < lines.length; i++)
			{
				targetPlayer.addChatComponentMessage(new ChatComponentText(lines[i]));
			}
		}
	}
}
