import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {

	private static JPanel contentPane;
	private static JLabel lblStageNum;
	/**
	 * Create the frame.
	 */
	public GameFrame() {
		super("Play Maker");
		setBounds(0, 0, new ImageIcon(SettingsPg.fileName).getIconWidth(), new ImageIcon(SettingsPg.fileName).getIconHeight()+80);
		//this.setResizable(false);
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseAdapter());
		setBackground(Color.LIGHT_GRAY);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setVisible(true);
		
		GameBoard canvas = new GameBoard();
		canvas.addMouseListener(new MouseAdapter());
		canvas.addMouseMotionListener(new MouseAdapter());
		canvas.setVisible(true);
		
		contentPane.add(canvas, BorderLayout.CENTER);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		
		JLabel lblStage = new JLabel("Stage");
		lblStageNum = new JLabel("0");
		panel.add(lblStage);
		panel.add(lblStageNum);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new MouseAdapter());
		panel.add(runButton);
		
		JButton previousStage = new JButton("Previous Stage");
		previousStage.addActionListener(new MouseAdapter());
		panel.add(previousStage);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("File");
		comboBox.addItem("Save");
		comboBox.addItem("Exit");
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(arg0.getItem().equals("Exit")){
					dispose();
				} else if(arg0.getItem().equals("Save")){
					FileOutputStream fileOutputStream;
					try {
						String fileName = JOptionPane.showInputDialog("Please Enter A File Name");
						File f = new File(fileName);
						if(f.exists())
							f.delete();
						fileOutputStream = new FileOutputStream(fileName);
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
						objectOutputStream.writeObject(GamePlay.getPlay());
						objectOutputStream.writeObject(GamePlay.getPlay().getStages());
						objectOutputStream.writeObject(GamePlay.getCharacters());
						objectOutputStream.close();
						fileOutputStream.close();
						//System.out.println("File Saved");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} 
				
				//comboBox.setSelectedItem(comboBox.getItemAt(0));
			}
			
		});
		panel.add(comboBox);
//		
//		JButton clearButton = new JButton("Clear");
//		clearButton.addActionListener(new MouseAdapter());
//		panel.add(clearButton);
		
		JButton btnNextStage = new JButton("Next Stage");
		btnNextStage.addActionListener(new MouseAdapter());
		panel.add(btnNextStage);
		
		contentPane.setVisible(true);
	}
	public static void update(String s) {
		lblStageNum.setText(s);
		contentPane.revalidate();
		
	}
	

}
