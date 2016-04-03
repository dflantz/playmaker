import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class SettingsPg extends JFrame {

	private JPanel contentPane;
	public static String fileName = "BballCourt.png";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsPg frame = new SettingsPg("Settings");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static String getFileName(){
		return fileName;
	}
	/**
	 * Create the frame.
	 */
	public SettingsPg(String title) {
		super(title);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 50));
		contentPane.add(comboBox, BorderLayout.CENTER);
		comboBox.addItem("Soccer");
		comboBox.addItem("Frisbee");
		comboBox.addItem("Football");
		comboBox.addItem("Basketball");
		
		JLabel lblSettings = new JLabel("Change Sport");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSettings, BorderLayout.NORTH);
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getItem().equals("Soccer")){
					fileName = "SoccerField.jpg";
				} else if(arg0.getItem().equals("Frisbee")){
					fileName = "UltimateField.jpg";
				} else if(arg0.getItem().equals("Football")){
					fileName = "AFootballField.jpg";
				} else if(arg0.getItem().equals("Basketball")){
					fileName = "BballCourt.png";
				}
				
			}
			
			
		});
		
	}

}
