package player;
import java.util.ArrayList;
import java.util.Random;

import dam_model.attackVector;

public class player
{
	
	protected int memSize;
	protected ArrayList<attackVector> V;
	protected attackVector[] mem;
	protected int memIndex;
	
	protected Random rand = new Random();
	protected int p;
	
	public void addV(attackVector vec)
	{
		this.V.add(vec);
	}
	
	public attackVector getMem(int i)
	{
		return this.mem[i];
	}
	
	public void printMem()
	{
		System.out.println("------------------------ MEM CONTENTS -----------------------");
		System.out.println();
		for(int i=0; i<this.memSize; i++)
		{
			if(this.mem[i] != null)
			System.out.println("id: "+this.mem[i].getID());
		}
		System.out.println();
	}
	
	public void printV()
	{
		System.out.println("------------------------ V CONTENTS -----------------------");
		System.out.println("size: "+this.V.size());
		for(attackVector temp : this.V)
		{
			System.out.println("id: "+temp.getID());
		}
		System.out.println();
	}

}