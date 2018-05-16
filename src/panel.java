import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class panel extends JPanel implements  MouseWheelListener
{
	public float[][] testa1;
	public float[][] traina1;
	public float[][] trainchange1;
	public int w1;
	public float h1;
	public float[] savetrain1;
	public float [] savetest1;
	public int r ;
	public float temp1 ;
	public panel() 
	{
		r = 0 ;
		addMouseWheelListener(this);
	} 

	
	@Override
    protected void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		int enlarge=50+r;
		if ( enlarge < 0 )
			enlarge = 0 ;
        g.translate(250, 250);
        g.drawLine(-250,0,250,0);
        g.drawLine(0,-250,0,250);
        
        if(traina1 == null ) return ;
        for(int i=0;i<2*h1/3;i++)
        {   
        	if(savetrain1[i]==0)
        	{
        		g.setColor(Color.black);
        		g.fillRect((int)(enlarge*traina1[i][1]), (int)(-enlarge*traina1[i][2]),5,5);
        	}
        	else if(savetrain1[i]==1)
        	{
        		g.setColor(Color.blue);
        		g.fillRect((int)(enlarge*traina1[i][1]), (int)(-enlarge*traina1[i][2]),5,5);
        	}
        	else if(savetrain1[i]==2)
        	{
        		g.setColor(Color.pink);
        		g.fillRect((int)(enlarge*traina1[i][1]), (int)(-enlarge*traina1[i][2]),5,5);       		
        	}
        	else if(savetrain1[i]==3)
        	{
        		g.setColor(Color.orange);
        		g.fillRect((int)(enlarge*traina1[i][1]), (int)(-enlarge*traina1[i][2]),5,5);          		
        	}

        }
        
        for(int i=0;i<h1/3-1;i++)
        {
        	if(savetest1[i]*temp1==0)
        	{
        		g.setColor(Color.red);
        		g.fillRect((int)(enlarge*testa1[i][1]), (int)(-enlarge*testa1[i][2]),5,5);
        	}
        	else if(savetest1[i]==1)
        	{
        		g.setColor(Color.green);
        		g.fillRect((int)(enlarge*testa1[i][1]), (int)(-enlarge*testa1[i][2]),5,5);
        	}
        	else if(savetest1[i]==2)
        	{
        		g.setColor(Color.gray);
        		g.fillRect((int)(enlarge*testa1[i][1]), (int)(-enlarge*testa1[i][2]),5,5);       		
        	}
        	else if(savetest1[i]==3)
        	{
        		g.setColor(Color.yellow);
        		g.fillRect((int)(enlarge*testa1[i][1]), (int)(-enlarge*testa1[i][2]),5,5);          		
        	}
        }
    }	

	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		r = r-e.getWheelRotation();
		repaint();
	}
}
