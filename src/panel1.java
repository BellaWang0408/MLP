import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class panel1 extends JPanel implements MouseWheelListener
{
	public float[][] testa2;//測試資料值
	public float[][] traina2;//訓練資料值
	public float[][] trainchange2;//訓練資料值
	public float[][] testchange2;//訓練資料值
	public int w2;
	public float outputx2[][];
	public float h2;
	public float[] savetrain2;
	public float [] savetest2;
	public int r = 0 ;
	public float temp2;
	public float num = 0 ;
	public panel1() 
	{
		r = 0 ;
		addMouseWheelListener(this);
	} // graph_panel()
	
	@Override
    protected void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int enlarge=50+r;
		if ( enlarge < 0 )
			enlarge = 0 ;
        g2d.translate(250, 250);
        g2d.drawLine(-250,0,250,0);
        g2d.drawLine(0,-250,0,250);
        
        if(traina2 == null ) return ;

        for(int i=0;i<2*h2/3;i++)
        {
        	if(savetrain2[i]==0)
        	{
        		g2d.setColor(Color.black);
        		g2d.fillRect((int)(enlarge*trainchange2[i][0]), (int)(-enlarge*trainchange2[i][1]),5,5);       
        	}
        	else if(savetrain2[i]==1)
        	{
        		g2d.setColor(Color.blue);
        		g2d.fillRect((int)(enlarge*trainchange2[i][0]), (int)(-enlarge*trainchange2[i][1]),5,5);       
        	}
        	else if(savetrain2[i]==2)
        	{
        		g.setColor(Color.PINK);
        		g.fillRect((int)(enlarge*trainchange2[i][0]), (int)(-enlarge*trainchange2[i][1]),5,5);       		
        	}
        	else if(savetrain2[i]==3)
        	{
        		g.setColor(Color.orange);
        		g.fillRect((int)(enlarge*trainchange2[i][0]), (int)(-enlarge*trainchange2[i][1]),5,5);          		
        	}

        	Double d = (double) -(outputx2[0][0]+outputx2[0][1]*trainchange2[i][0] + outputx2[0][2]*trainchange2[i][1] ) ;
        }
        
        for(int i=0;i<h2/3-1;i++)
        {
        	if(savetest2[i]==0)
        	{
        		g.setColor(Color.red);
        		g.fillRect((int)(enlarge*testchange2[i][0]), (int)(-enlarge*testchange2[i][1]),5,5);       
        	}
        	else if(savetest2[i]==1)
        	{
        		g.setColor(Color.green);
        		g.fillRect((int)(enlarge*testchange2[i][0]), (int)(-enlarge*testchange2[i][1]),5,5);       
        	}
        	else if(savetest2[i]==2)
        	{
        		g.setColor(Color.gray);
        		g.fillRect((int)(enlarge*testchange2[i][1]), (int)(-enlarge*testchange2[i][2]),5,5);       		
        	}
        	else if(savetest2[i]==3)
        	{
        		g.setColor(Color.yellow);
        		g.fillRect((int)(enlarge*testchange2[i][1]), (int)(-enlarge*testchange2[i][2]),5,5);          		
        	}
        }

        for ( int i = 1 ; i < num+ 1  ; i++ ) {
            Double y1,y2;
        	y1= (double) ((outputx2[0][0] - Math.log((double)(num+1.0)/i - 1.0)-(-50)*outputx2[0][1])/outputx2[0][2]);
        	y2= (double) ((outputx2[0][0]- Math.log((double)(num+1.0)/i - 1.0)-(50)*outputx2[0][1])/outputx2[0][2]);
        	g2d.setColor(Color.magenta);
        	g2d.drawLine(enlarge*(-50), (int)(-enlarge*y1),enlarge* 50, (int)(-enlarge*y2));
        } 

    }

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		r = r-arg0.getWheelRotation();
		repaint();
	}

}
