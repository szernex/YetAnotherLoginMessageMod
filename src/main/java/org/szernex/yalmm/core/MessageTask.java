package org.szernex.yalmm.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.List;
import java.util.TimerTask;

public class MessageTask extends TimerTask
{
	private List<String> loginMessage;
	private EntityPlayer targetPlayer;

	public MessageTask()
	{
		super();
	}

	public MessageTask(List<String> message, EntityPlayer player)
	{
		super();

		loginMessage = message;
		targetPlayer = player;
	}

	@Override
	public void run()
	{
		if (loginMessage != null)
		{
			for (String line : loginMessage)
			{
				targetPlayer.addChatComponentMessage(new ChatComponentText(line));
			}
		}
	}
}
