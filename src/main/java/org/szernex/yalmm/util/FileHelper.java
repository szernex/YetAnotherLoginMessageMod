package org.szernex.yalmm.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelper
{
	public static boolean initFile(String path)
	{
		File file = new File(path);

		if (file.exists())
		{
			if (file.canRead())
			{
				return true;
			}
			else
			{
				LogHelper.error("Can not read file " + path);
				return false;
			}
		}

		try
		{
			if (!file.createNewFile())
			{
				LogHelper.error("Can not create new file " + path);
				return false;
			}

			LogHelper.info("New file " + path + " initialized");
			return true;
		}
		catch (IOException ex)
		{
			LogHelper.error("Error initializing file " + path + ": " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	public static List<String> readFile(String path)
	{
		List<String> data = null;

		if (!initFile(path))
		{
			LogHelper.error("Could not read file " + path);
			return null;
		}

		try
		{
			data = FileUtils.readLines(new File(path));
		}
		catch (IOException ex)
		{
			LogHelper.error("Error reading file " + path + ": " + ex.getMessage());
			ex.printStackTrace();
		}

		return data;
	}
}
