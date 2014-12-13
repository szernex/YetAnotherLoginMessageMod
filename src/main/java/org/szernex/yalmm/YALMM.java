package org.szernex.yalmm;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.szernex.yalmm.handler.ConfigHandler;
import org.szernex.yalmm.handler.PlayerLoginHandler;
import org.szernex.yalmm.reference.Reference;
import org.szernex.yalmm.util.FileHelper;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.MOD_NAME, acceptableRemoteVersions = "*")
public class YALMM
{
	@Mod.Instance(Reference.MOD_ID)
	public static YALMM instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.init(event.getSuggestedConfigurationFile());

		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		FMLCommonHandler.instance().bus().register(new PlayerLoginHandler());

		FileHelper.initFile(ConfigHandler.messageFile);
	}
}
