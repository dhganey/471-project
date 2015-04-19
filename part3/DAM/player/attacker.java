package player;

import java.util.ArrayList;

import dam_model.attackVector;

public class attacker extends player 
{
	public attacker(int memSize)
	{
		this.memSize = memSize;
		this.V = new ArrayList<attackVector>();
		this.mem = new attackVector[memSize];
		this.memIndex = 0;
	}
	
	public void addMem(attackVector vec)
	{
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
		this.addMem(vec);
		this.V.remove(p);
		return vec;
	}
	
	public attackVector chooseFromMem()
	{
		this.p = rand.nextInt(this.memSize);
		attackVector vec = this.mem[p];
		return vec;
	}
}
