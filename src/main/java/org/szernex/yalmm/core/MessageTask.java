package org.szernex.yalmm.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;

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
				targetPlayer.addChatComponentMessage(getParsedText(line));
			}
		}
	}

	private ChatComponentText getParsedText(String text)
	{
		ChatComponentText output = new ChatComponentText("");
		ChatStyle style = new ChatStyle();
		String[] splitted = text.split(" ");
		String temp = "";

		style.setBold(true);
		style.setUnderlined(true);

		for (String s : splitted)
		{
			if (s.startsWith("http"))
			{
				output.appendText(temp);
				temp = "";

				style.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, s));
				output.appendSibling(new ChatComponentText(s).setChatStyle(style));
				output.appendText(" ");
			}
			else
			{
				temp += s + " ";
			}
		}

		output.appendText(temp);

		return output;
	}
}
