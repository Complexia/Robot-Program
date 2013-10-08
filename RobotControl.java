import javax.swing.*;
class RobotControl
{
	private Robot r;
	public RobotControl(Robot r)
	{
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[])
	{

		scenario(barHeights,blockHeights);

	}


	public void scenario(int barHeights[], int blockHeights[])
	{



		int h = 2;         
		int w = 1;         
		int d = 0;        
		int currentBlock = blockHeights.length -1;  
		int sourceHt = 0; 
		int currentBar = 0;
		int contractAmt = 7;
		int contract = 0;
		int targetCol1Ht = 0; 
		int targetCol2Ht = 0;    



		int blockHt = 3;



		for (int i = 0; i < blockHeights.length; i++){
			sourceHt += blockHeights[i];
			//for loop to determine how many blocks are in the array, and then making that number the sourceHt

		}			



		int clearance = 12;  

		while ( h < clearance + 1 ) 
		{

			r.up();     

			h++;
		}

		while (sourceHt > 0){
			//This while loop is wrapped around the whole program, while the Source Height is more than 0, the program loops till this condition is met

			int extendAmt = 10;

			while (d > 0){

				r.raise();
				d--;
			}

			while ( w < extendAmt )
			{

				r.extend();

				w++;
			}


			while (h - d > sourceHt + 1) 

			{

				r.lower();
				
				d++;
			}


			r.pick();
			sourceHt -= blockHeights[currentBlock];

			while ( d > 0)
			{
				r.raise();
				d--;
			} 

			//The previous block of code just after while (sourceHt > 0){ controls the robot arm for all blocks, it raises, extends, lowers, picks and raises again

			if (blockHeights[currentBlock] == 1)
			{
				//if loop for blockHeights of 1, the program contracts to 9, which is to column 1
				contractAmt = 9;


				while ( contractAmt > 0 )
				{
					r.contract();
					contractAmt--;
					w--;
				}
				while ( (h - 1) - d - blockHeights[currentBlock] > targetCol1Ht)   
				{
					r.lower();
					d++;
				}
				r.drop();
				targetCol1Ht += blockHeights[currentBlock];
				//targetCol1Ht is updated with the currentBlock
				currentBlock--;
			}
			else if
			(blockHeights[currentBlock] == 2)
			{
				//else if loop for blockHeights of 2, the program contracts to 8, which is to column 2

				contractAmt = 8; 


				while ( contractAmt > 0 )
				{
					r.contract();
					contractAmt--;
					w--;
				}
				while ( (h - 1) - d - blockHeights[currentBlock] > targetCol2Ht)   
				{
					r.lower();
					d++;

				}
				r.drop();
				targetCol2Ht += blockHeights[currentBlock];
				//targetCol2Ht is updated with currentBlock
				currentBlock--;
				//This takes away the block just placed from sourceHt
			}

			else if (blockHeights[currentBlock] == 3)
			{
				//else if loop for blockHeights of 2, the program contracts to 7, which is to the column where the bars start
				contractAmt = 7 - contract++;
				//changing the contractAmt according to what block it is
				while ( contractAmt > 0 )
				{
					r.contract();
					contractAmt--;
					w--;
				}



				while (h - d - blockHt > barHeights[currentBar] + 1)
				{
					r.lower();

					d++;
				}

				r.drop();
				contractAmt--;
				currentBar++;
				currentBlock--;

				while (d > 0)
				{
					r.raise();
					d--;
				}

			}
		}
	}
}
