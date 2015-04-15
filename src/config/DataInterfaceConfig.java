package config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig implements Serializable
{
	private final String className;
	private final Map<String, String> param;
	
	public DataInterfaceConfig(Element e)
	{
		className = e.attributeValue("className");
		param = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Element> params = e.elements("param");
		for (Element element : params)
		{
			param.put(element.attributeValue("key"), element.attributeValue("value"));
		}
	}

	public String getClassName()
	{
		return className;
	}

	public Map<String, String> getParam()
	{
		return param;
	}
	
	
}
