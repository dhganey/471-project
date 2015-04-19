package dam_model;


public class attackVector 
{
	private int id;
	private int cost;
	
	public attackVector(int id, int cost)
	{
		this.id = id;
		this.cost = cost;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public int getCost()
	{
		return this.cost;
	}
	
}
