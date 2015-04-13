package config;

import org.dom4j.Element;

/**
 * ¶ÔÓ¦ÓÚcfg.xml button
 * @author Ben
 *
 */
public class ButtonConfig
{
	private int startX;
	private int startY;
	private int startW;
	private int startH;
	
	private int userConfigX;
	private int userConfigY;
	private int userConfigW;
	private int userConfigH;
	
	public ButtonConfig(Element button)
	{
		Element start = button.element("start");
		startX = Integer.parseInt(start.attributeValue("x"));
		startY = Integer.parseInt(start.attributeValue("y"));
		startW = Integer.parseInt(start.attributeValue("w"));
		startH = Integer.parseInt(start.attributeValue("h"));
		
		Element userConfig = button.element("userConfig");
		userConfigX = Integer.parseInt(userConfig.attributeValue("x"));
		userConfigY = Integer.parseInt(userConfig.attributeValue("y"));
		userConfigW = Integer.parseInt(userConfig.attributeValue("w"));
		userConfigH = Integer.parseInt(userConfig.attributeValue("h"));
	}

	public int getStartX()
	{
		return startX;
	}

	public int getStartY()
	{
		return startY;
	}

	public int getStartW()
	{
		return startW;
	}

	public int getStartH()
	{
		return startH;
	}

	public int getUserConfigX()
	{
		return userConfigX;
	}

	public int getUserConfigY()
	{
		return userConfigY;
	}

	public int getUserConfigW()
	{
		return userConfigW;
	}

	public int getUserConfigH()
	{
		return userConfigH;
	}
	
	
}
