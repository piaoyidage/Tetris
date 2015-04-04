package config;

/**
 * 对应于cfg.xml的layer节点
 * @author Ben
 *
 */
public class LayerConfig
{
	private String className;
	private int x;
	private int y;
	private int w;
	private int h;
	

	public LayerConfig(String className, int x, int y, int w, int h)
	{
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getClassName()
	{
		return className;
	}


	public int getX()
	{
		return x;
	}


	public int getY()
	{
		return y;
	}


	public int getW()
	{
		return w;
	}


	public int getH()
	{
		return h;
	}

	
	
}
