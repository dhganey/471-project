package player;
import java.util.ArrayList;

import dam_model.attackVector;


public class defender extends player
{	
	public defender(int memSize)
	{
		this.memSize = memSize;
		this.V = new ArrayList<attackVector>();
		this.mem = new attackVector[memSize];
		this.memIndex = 0;
	}

	public void addMem(attackVector vec)
	{
		this.V.remove(vec);
		
		if(this.mem[this.memIndex] != null)
			this.V.add(this.mem[this.memIndex]);
			
		this.mem[this.memIndex] = vec;
		
		if(this.memIndex == this.memSize-1)
			this.memIndex = 0;
		else
			this.memIndex++;
	}
	
	public attackVector chooseFromV()
	{
		this.p = rand.nextInt(this.V.size());
		attackVector vec = this.V.get(p);
		return vec;
	}
	
	public attackVector chooseFromMem()
	{
		this.p = rand.nextInt(this.memSize);
		attackVector vec = this.mem[p];
		return vec;
	}
	
	public void remember(attackVector vec)
	{
		if(!checkMemDuplicates(vec))
		{
			this.V.remove(vec);
			
			if(this.mem[this.memIndex] != null)
				this.V.add(this.mem[this.memIndex]);
			
			this.mem[this.memIndex] = vec;
			
			if(this.memIndex == this.memSize-1)
				this.memIndex = 0;
			else
				this.memIndex++;
		}
	}
	
	private boolean checkMemDuplicates(attackVector vec)
	{
		for(int i=0; i<this.memSize; i++)
		{
			if(this.mem[i] == null || this.mem[i].getID() == vec.getID())
			{
				return true;
			}
		}
		return false;
	}

}
