import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JScrollBar;

public class windowbuilder extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	private JTextField textField_4;
	/*private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JTextArea textArea_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTextField textField_2;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField textField_3;*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowbuilder window = new windowbuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public windowbuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
    	frame = new JFrame();
		frame.getContentPane().setBackground(null);
		frame.setBounds(100, 100, 1192, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		final AlgoNN nn = new AlgoNN();	
		frame.getContentPane().setLayout(null);
		
		final panel panel = new panel();
		panel.setBounds(185, 10, 498, 501);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		
		final panel1 panel1 = new panel1();
		panel1.setBounds(693, 10, 473, 501);
		panel1.setBackground(Color.WHITE);
		frame.getContentPane().add(panel1);
		
		final JFileChooser fc = new JFileChooser();
		final JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(10, 10, 75, 31);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				fc.setCurrentDirectory(new java.io.File("C:/Users/Lynn/workspace/NNHW2/hw2_data"));
                fc.setDialogTitle("開啟");
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                
                if (fc.showOpenDialog(btnOpen) == JFileChooser.APPROVE_OPTION)
                {
                }
			}
		});
		btnOpen.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		frame.getContentPane().add(btnOpen);
		
		JLabel label = new JLabel("\u5B78\u7FD2\u7387");
		label.setBounds(38, 51, 47, 21);
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("新細明體", Font.PLAIN, 15));
		textField.setBounds(87, 51, 88, 21);
		textField.setText("0.5");
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.getText();
		
		JLabel label_1 = new JLabel("\u8FED\u4EE3\u6B21\u6578");
		label_1.setBounds(25, 76, 60, 21);
		label_1.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("新細明體", Font.PLAIN, 15));
		textField_1.setBounds(87, 76, 88, 21);
		textField_1.setText("10000");
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField.getText();
		
		JLabel label_2 = new JLabel("\u96B1\u85CF\u795E\u7D93\u5143");
		label_2.setBounds(10, 101, 75, 21);
		label_2.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("新細明體", Font.PLAIN, 15));
		textField_2.setBounds(87, 103, 88, 21);
		textField_2.setText("2");
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField.getText();
		
		JButton button = new JButton("\u57F7\u884C\u7D50\u679C");
		button.setBounds(87, 10, 90, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				nn.learnrate=Float.parseFloat(textField.getText());
				nn.condition=Integer.parseInt(textField_1.getText());
				nn.hide=Integer.parseInt(textField_2.getText());
				nn.output=Integer.parseInt(textField_3.getText());
				nn.m=Float.parseFloat(textField_4.getText());
				try {
						nn.run(fc.getSelectedFile().getAbsolutePath());
					} 
				catch (IOException e1) 
				{
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textArea.setText(textArea.getText()+"訓練辨識率:");//取得訓練值
                textArea.setText(textArea.getText() + "\n" +"  "+ (int)nn.trainr+"%");//取得測試值
            	textArea.setText(textArea.getText()+ "\n"+"測試辨識率:");//取得測試值
            	textArea.setText(textArea.getText() + "\n" +"  "+ (int)nn.testr+"%");//取得測試值
            	textArea.setText(textArea.getText()+ "\n"+"鍵結值:");
            	textArea.setText(textArea.getText()+ "\n"+"  "+nn.outputx[0][0]);
            	textArea.setText(textArea.getText()+ "\n"+"  "+nn.outputx[0][1]);
            	textArea.setText(textArea.getText()+ "\n"+"  "+nn.outputx[0][2]+"\n");
            	textArea.setText(textArea.getText()+"迭代次數:");
            	textArea.setText(textArea.getText()+ "\n"+"  "+nn.time+ "\n");
            	panel.trainchange1=nn.trainchange;
				panel.traina1=nn.traina;
				panel.testa1=nn.testa;
				panel.w1=nn.w;
				panel.h1=nn.h;
				panel.savetrain1=nn.savegraphtrain;
				panel.savetest1=nn.savegraphtest;
				panel.repaint();
				panel.temp1=nn.temp;
				
            	panel1.trainchange2=nn.trainchange;
            	panel1.testchange2=nn.testchange;
				panel1.traina2=nn.traina;
				panel1.testa2=nn.testa;
				panel1.w2=nn.w;
				panel1.h2=nn.h;
				panel1.savetrain2=nn.savegraphtrain;
				panel1.num = nn.temp;
				panel1.savetest2=nn.savegraphtest;
				panel1.outputx2=nn.outputx;
				panel1.temp2=nn.temp;
				panel1.repaint();
			}
		});
		
    	
		button.setForeground(null);
		button.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		frame.getContentPane().add(button);
		
		JLabel label_4 = new JLabel("\u7D50\u679C");
		label_4.setBounds(63, 180, 47, 21);
		label_4.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		frame.getContentPane().add(label_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 209, 165, 199);
		frame.getContentPane().add(scrollPane);
		
	    textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label_7 = new JLabel("\u8ABF\u6574");
		label_7.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		label_7.setBounds(55, 132, 30, 21);
		frame.getContentPane().add(label_7);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("新細明體", Font.PLAIN, 15));
		textField_4.setText("0.5");
		textField_4.setBounds(87, 132, 88, 21);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_3.setEditable(false);
		textField_3.setBounds(38, 231, 90, 21);
		textField_3.setText("1");
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.getText();
		
	}
}

