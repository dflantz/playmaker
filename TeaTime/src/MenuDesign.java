import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Component;
public class MenuDesign extends JFrame {

	private JPanel contentPane;
	private JTextField txtPlayMaker;
	private JButton btnNewPlay;
	private JRadioButton rdbtnNewRadioButton;
	private JButton btnPlay;
	private JButton btnLoad;
	private JButton btnSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuDesign frame = new MenuDesign();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuDesign() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel bkgPic = new JLabel("New label");
		bkgPic.setIcon(new ImageIcon("C:/Users/John/workspace/TeaTime/FieldBkgrd"));
//		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//LayoutManager bkgPic2 = new OverlayLayout(contentPane);
		//contentPane.setLayout(content);
		setContentPane(contentPane);
		
		contentPane.add(bkgPic, BorderLayout.CENTER);
		txtPlayMaker = new JTextField();
		txtPlayMaker.setOpaque(true);
		txtPlayMaker.setFont(new Font("Yuanti SC", Font.BOLD | Font.ITALIC, 80));
		txtPlayMaker.setText("Play Maker");
		txtPlayMaker.setForeground(new Color(0, 204, 0));
		txtPlayMaker.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPlayMaker.setColumns(10);
		txtPlayMaker.setBackground(new Color(0,0,0));
		contentPane.add(txtPlayMaker, BorderLayout.NORTH);
		
		btnPlay = new JButton("New Play");
		btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnPlay.setContentAreaFilled(false);
		btnPlay.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnPlay.setBorder(new EmptyBorder(0,0,0,0));
		btnPlay.setDisabledSelectedIcon(null);
		btnPlay.setBackground(Color.WHITE);
		btnPlay.setContentAreaFilled(false);
		btnPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					@Override
					public void run(){
						GamePlay.newPlay();
					}
				}.start();
			}
		});
		contentPane.add(btnPlay, BorderLayout.CENTER);
		
		btnLoad = new JButton("Load Play");
		btnLoad.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnLoad.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnLoad.setBorder(new EmptyBorder(0,0,0,0));
		//btnLoad.setContentAreaFilled(false);
		//btnLoad.setBackground(new Color(0,0,0));
		//btnLoad.setForeground(new Color(0,0,0));
		btnLoad.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fileName = JOptionPane.showInputDialog("Enter The File Name");
				new Thread(){
					@Override
					public void run(){
						GamePlay.readData(fileName);
					}
				}.start();
			}
			
		});
		contentPane.add(btnLoad, BorderLayout.WEST);
		
		btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnSettings.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.add(btnSettings, BorderLayout.EAST);
		btnSettings.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Thread(){
					@Override
					public void run(){
						SettingsPg.main(null);
					}
				}.start();
			}
			
		});
		
	
		
//		btnNewPlay = new JButton("New Play");
//		btnNewPlay.setVerticalTextPosition(SwingConstants.TOP);
//		btnNewPlay.setVerticalAlignment(SwingConstants.TOP);
//		btnNewPlay.setOpaque(true);
//		contentPane.add(btnNewPlay, BorderLayout.SOUTH);
		//txtPlayMaker.setSelectionColor();
		
	}

}
