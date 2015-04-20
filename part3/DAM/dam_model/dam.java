package dam_model;

import java.util.Random;
import player.defender;
import player.attacker;

public class dam 
{

	public static void main(String[] args) 
	{
	
		/* These parameters determine the settings for this game */
		final int N = 500;
		final int sizeV = 100;
		final int costV = 100;
		final int defenderMemSize = 5;
		final int attackerMemSize = 5;
		final float defenderS = 1.0f;
		final float attackerS = 1.0f;
		
		/* Players initialization */
		defender def = new defender(defenderMemSize);
		attacker atk = new attacker(attackerMemSize);
		
		/* Random used to determine if player will choose vector from memory or not */
		Random rand = new Random();
		float p;
		
		attackVector attack = null;
		attackVector defense = null;
		
		int attackerWin_Count = 0;
		int defenderWin_Count = 0;
		int attackerCost = 0;
		int defenderCost = 0;
		int attackerPayoff = 0;
		int defenderPayoff = 0;
		
		/* Populate attack vector list V */
		for(int i=0; i<sizeV; i++)
		{
			attackVector vec = new attackVector(i, costV);
			
			/* All players keep a copy of the entire list of attackVectors to keep the model flexible */
			atk.addV(vec);
			def.addV(vec);
		}
				
		for(int i=0; i<N; i++)
		{ 
			/* Attacker pick */
			if(i < attackerMemSize)
			{
				/* Initial moves */
				/* Attacker chooses from V uniformly at random */
				attack = atk.chooseFromV();
			}
			else
			{
				/* After initial moves */
				p = rand.nextFloat();
				
				if(0.0f <= p && p <= attackerS)
				{
					attack = atk.chooseFromMem();
				}
				else
				{
					attack = atk.chooseFromV();
				}
			}
			
			/* Defender pick */
			if(i < defenderMemSize)
			{
				/* Initial moves */
				/* Defender chooses from V uniformly at random */
				defense = def.chooseFromV();
				def.addMem(defense);
			}
			else
			{
				/* After initial moves */
				p = rand.nextFloat();
				
				if(0.0f <= p && p <= defenderS)
				{
					defense = def.chooseFromMem();
				}
				else
				{
					defense = def.chooseFromV();
				}
				def.remember(attack);
			}
			
			
			if(defense.getID() == attack.getID())
			{
				/* Defense wins */
				defenderWin_Count++;
				defenderPayoff += 10000;
			}
			else
			{
				/* Attack wins */
				attackerWin_Count++;
				attackerPayoff += 10000;
			}
			defenderCost += 100;
			attackerCost += 100;
		}	
		System.out.println("Attacker Wins: " + attackerWin_Count + " Defender Wins: " + defenderWin_Count);
		System.out.println("Attacker Payoff: $" + (attackerPayoff - attackerCost) + " Defender Payoff: $" + (defenderPayoff - defenderCost));
	}
}