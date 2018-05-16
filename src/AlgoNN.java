import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AlgoNN 
{
	private String p ;
	public ArrayList<float[]> inputa;
	public int w;
	public int h;
	public int condition;
	public float learnrate;
	public float m;
	public int hide;
	public int output;
	public int time;
	public float[][] testchange;
	public float[][] trainchange;
	public float[][] testa;
	public float[][] traina;
	
	//神經元個數&值
	public float[][] hidex;
	public float[][] outputx;
	public float[] hidey;
	public float[] outputy;
	public float[] hidez;
	public float[] outputz;
	public float[] hideq;
	public float[] outputq;
	public float[] savetest;
	public float[] savetrain;
	public float[] save;
	public float[] nor;
	public float testr;
	public float trainr;
	public float temp = 0;
	public float[] savegraphtest;
	public float[] savegraphtrain;
	public float[] savegraph;
	
	public  void run(String argv) throws IOException
	{
		temp = 0 ;
		p = argv;
		inputa=new ArrayList<float[]>();
		inputa=read();
		w = inputa.get(0).length;
	    h =inputa.size();
	    testa=new float[h/3][w];
	    traina=new float[2*h/3+1][w];
	    hidex=new float[hide][w];
	    outputx=new float[output][hide+1];
	    hidey=new float[hide];
	    outputy=new float[output];
	    hidez=new float[hide];
	    outputz=new float[output];
	    hideq=new float[hide]; 
	    outputq=new float[output];
	    save=new float[h];
	    savetrain=new float[2*h/3+1];
	    savetest=new float[h/3];
	    savegraph=new float[h];
	    savegraphtrain=new float[2*h/3+1];
	    savegraphtest=new float[h/3];
	    nor=new float[6];
	    
	   
	    for(int i=0;i<h;i++)
	    {
	    	save[i]=inputa.get(i)[w-1];
	    	savegraph[i]=inputa.get(i)[w-1];
	    }	    
	    nor[0]=inputa.get(0)[w-1];
	    for(int i=0;i<h;i++)
	    {
	    	if(save[i]!=nor[(int) temp])
	    	{
	    		nor[(int) (temp+1)]=save[i];
	    		temp++;
	    	}		
	    }
	    for(int i=0;i<h;i++)
	    {
	    	for(int j=0;j<temp+1;j++)
	    	{
	    		if(save[i]==nor[j])
	    		{	
	    			save[i]=j / temp ;
	    			savegraph[i]=j;
	    		}
	    	}
	    }
	    Double re = 1.0 / (temp+1.0) ;
	    
	    Random r1 = new Random();
	    int t=0;
		int t1=0;
		for(int i=0;i<h;i++)
		{
			int rn;
    		rn=r1.nextInt(2);
	    	if((rn==0 && t!=2*h/3+1 )|| t1==h/3)
	    	{
				for(int j=0;j<w;j++)
				{
					if(j == 0)
					{
					  traina[t][j] = -1;
					}
					else
					{
					  traina[t][j] = inputa.get(i)[j-1];					
					}
				}
				savetrain[t]=save[i];
				savegraphtrain[t]=savegraph[i];
				t++;
	    	}
	    	
	    	else if( (rn==1 &&  t1!=h/3) || t==2*h/3+1)//分成測試資料
			{
				for(int j=0;j<w;j++)
				{
					if(j == 0)
					{
					  testa[t1][j] = -1;
					}
					else
					{
					  testa[t1][j] = inputa.get(i)[j-1];					
					}
				}		
				savetest[t1]=save[i];
				savegraphtest[t1]=savegraph[i];
				t1++;
			}
		}
	    
	    Random r2 = new Random();
	    for(int i=0;i<hide;i++)
	    {
	    	for(int j=0;j<w;j++)
	    	{
	    		int rn;
	    		rn=r2.nextInt(2);
				if(rn==0)
				{
					hidex[i][j] = (-1)*r2.nextFloat();
				}
				else if(rn==1)
				{
					hidex[i][j] = r2.nextFloat();
				}
	    	}
	    }
	    
	    for(int i=0;i<output;i++)
	    {
	    	for(int j=0;j<hide+1;j++)
	    	{
	    		int count;
				count=r2.nextInt(2);
				if(count==0)
				{
					outputx[i][j] = (-1)*r2.nextFloat();
				}
				else if(count==1)
				{
					outputx[i][j] = r2.nextFloat();				
				}
	    	}
	    }
	    float dhidex [][] = new float [hide][w];
	    float doutputx[][]=new float [output][hide+1];
	    for(int i=0;i<hide;i++)
	    {
	    	for(int j=0;j<w;j++)
	    	{
	    		dhidex [i][j] = 0;
	    	}
	    }
	    for(int i=0;i<output;i++)
	    {
	    	for(int j=0;j<hide+1;j++)
	    	{
	    		doutputx [i][j] = 0;
	    	}
	    }
	    time=0;
	    for(int i=0;i<2*h/3+1;i++)
	    {
	    	
	    	for(int j=0;j<hide;j++)
	    	{
	    		for(int k=0;k<w;k++)
	    		{
	    			hidey[j]=hidey[j]+traina[i][k]*hidex[j][k];
	    		}	
	    		hidez[j]=(float) (1 / (1 + Math.exp((-1)*hidey[j])));	
	    		hidey[j]=0;
	    	}
	    	for(int j=0;j<output;j++)
	    	{
	    		for(int k=0;k<hide+1;k++)
		    	{
		    		if(k==0)
		    		{
		    			outputy[j]=(-1)*outputx[j][k];
		    		}
		    		else
		    		{
		    			outputy[j]=outputy[j]+hidez[k-1]*outputx[j][k]; 	
		    		}
		    	}
	    		outputz[j]=(float) (1 / (1 + Math.exp((-1)*outputy[j])));
	    		outputy[j]=0;
		    }
	    	hideq=new float[hide];
	    	outputq=new float[output];	    	
	    	if ( !(savegraphtrain[i] * re <= outputz[0] &&  outputz[0] < (savegraphtrain[i]+1) * re ))
	    	{
	    		for(int j=0;j<output;j++)
	    		{
	    			outputq[j]=(savetrain[i]-outputz[j])*outputz[j]*(1-outputz[j]);
	    		}
	    		for(int j=hide;j>0;j--)
	    		{
	    			hideq[j-1]=hidez[j-1]*(1-hidez[j-1])*(outputq[0]*outputx[0][j]);
	    		}
	    		
	    		for(int j=0;j<hide;j++)
	    		{
	    			for(int k=0;k<w;k++)
	    			{
	    				hidex[j][k]=hidex[j][k]+dhidex[j][k]+learnrate*hideq[j]*traina[i][k];
	    				dhidex[j][k]=m*learnrate*hideq[j]*traina[i][k];
	    			}
	    		}
	    		for(int j=0;j<output;j++)
	    		{
	    			
	    			for(int k=0;k<hide+1;k++)
	    			{
	    				if(k==0)
	    				{
	    					outputx[j][k]=outputx[j][k]+doutputx[j][k]+learnrate*outputq[j]*-1;
	    					doutputx[j][k]=m*learnrate*outputq[j]*-1;
	    				}
	    				else 
	    				{
	    					outputx[j][k]=outputx[j][k]+doutputx[j][k]+learnrate*outputq[j]*hidez[k-1];
	    					doutputx[j][k]=m*learnrate*outputq[j]*hidez[k-1];
	    				}
	    			}
	    		}
	    		time++;
	    		i=-1;
	    	}
	    		if(time >condition)
	    			break;
	    	
	    }
	    trainr=0;
	    for(int i=0;i<2*h/3+1;i++)
	    {
	    	for(int j=0;j<hide;j++)
	    	{
	    		for(int k=0;k<w;k++)
	    		{
	    			hidey[j]=hidey[j]+traina[i][k]*hidex[j][k];
	    		}	
	    		hidez[j]=(float) (1 / (1 + Math.exp((-1)*hidey[j])));	
	    		hidey[j]=0;
	    	}
	    	for(int j=0;j<output;j++)
	    	{
	    		for(int k=0;k<hide+1;k++)
		    	{
		    		if(k==0)
		    		{
		    			outputy[j]=(-1)*outputx[j][k];
		    		}
		    		else
		    		{
		    			outputy[j]=outputy[j]+hidez[k-1]*outputx[j][k];  	
		    		}
		    	}
	    		outputz[j]=(float) (1 / (1 + Math.exp((-1)*outputy[j])));
	    		outputy[j]=0;
	    		
		    }
	    	
	    	if ( (savegraphtrain[i] * re <= outputz[0] &&  outputz[0] < (savegraphtrain[i]+1) * re ))
	    	{
	    		trainr++;
	    	}
	    }
	    trainr=(trainr*100)/((2*h/3)+1);
	    
	    
	    testr=0;
	    for(int i=0;i<h/3;i++)
	    {
	    	for(int j=0;j<hide;j++)
	    	{
	    		for(int k=0;k<w;k++)
	    		{
	    			hidey[j]=hidey[j]+testa[i][k]*hidex[j][k];
	    		}	
	    		hidez[j]=(float) (1 / (1 + Math.exp((-1)*hidey[j])));	
	    		hidey[j]=0;
	    	}
	    	for(int j=0;j<output;j++)
	    	{
	    		for(int k=0;k<hide+1;k++)
		    	{
		    		if(k==0)
		    		{
		    			outputy[j]=(-1)*outputx[j][k];
		    		}
		    		else
		    		{
		    			outputy[j]=outputy[j]+hidez[k-1]*outputx[j][k];  	
		    		}
		    	}
	    		outputz[j]=(float) (1 / (1 + Math.exp((-1)*outputy[j])));
	    		outputy[j]=0;
		    }
	    	if ( (savegraphtest[i] * re <= outputz[0] &&  outputz[0] < (savegraphtest[i]+1) * re )) // 
	    	{
	    		testr++;
	    	}
	    }
	    
	    testr=(testr*100)/(h/3);
	    trainchange=new float[2*h/3+1][w];
	    testchange=new float[h/3][w];
	    for(int i=0;i<2*h/3+1;i++)
	    {
	    	trainchange[i][0]=(float) (1.0 / (1.0 + Math.exp((-1)*((-1.0)*hidex[0][0]+traina[i][1]*hidex[0][1]+traina[i][2]*hidex[0][2]))));
    		trainchange[i][1]=(float) (1.0 / (1.0 + Math.exp((-1)*((-1.0)*hidex[1][0]+traina[i][1]*hidex[1][1]+traina[i][2]*hidex[1][2]))));
	    }
	    for(int i=0;i<h/3;i++)
	    {
	    	testchange[i][0]=(float) (1.0 / (1.0 + Math.exp((-1.)*((-1.0)*hidex[0][0]+testa[i][1]*hidex[0][1]+testa[i][2]*hidex[0][2]))));
    		testchange[i][1]=(float) (1.0 / (1.0 + Math.exp((-1.0)*((-1.0)*hidex[1][0]+testa[i][1]*hidex[1][1]+testa[i][2]*hidex[1][2]))));
	    }
	    
}
	public ArrayList<float[]> read() throws IOException
	{
		FileReader fr = new FileReader(p);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<float[]> inputa=new ArrayList<float[]>();
		String txt;
		while ((txt = br.readLine()) != null) 
		{

			String[] token = txt.trim().split("\\s+");
			float[] token2 = new float[token.length];
			for (int i = 0; i < token.length; i++) 
			{
				token2[i] = Float.parseFloat(token[i]);
			} 
			inputa.add(token2);
		}
		fr.close();
		return inputa;
	}
}
