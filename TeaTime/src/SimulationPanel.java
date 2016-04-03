import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel implements ActionListener{
	public SimulationPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		
		JButton run = new JButton("Run");
		JButton clear = new JButton("Clear");
		
		run.addActionListener(this);		
		
		panel.add(run);
		panel.add(clear);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Run")){
			System.out.println("Run Pressed");
			//RUN
		} else if(arg0.getActionCommand().equals("Clear")){
			System.out.println("Clear Pressed");
			GamePlay.clear();
		}
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawString("Hello", 20, 20);
	}
}
