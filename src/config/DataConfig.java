package config;

import java.io.Serializable;

import org.dom4j.Element;

public class DataConfig implements Serializable
{

	private final int maxRows;
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;
	
	public DataConfig(Element data)
	{
		maxRows = Integer.parseInt(data.attributeValue("maxRows"));
		dataA = new DataInterfaceConfig(data.element("dataA"));
		dataB = new DataInterfaceConfig(data.element("dataB"));
	}

	public DataInterfaceConfig getDataA()
	{
		return dataA;
	}

	public DataInterfaceConfig getDataB()
	{
		return dataB;
	}

	public int getMaxRows()
	{
		return maxRows;
	}
	
	

}
