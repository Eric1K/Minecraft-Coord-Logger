import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	static JMenuBar menu;
	static JMenu coord;
	static JMenuItem exit, addCoord, deleteCoord;
	static CoordinateTracker ct;
	static JMenu file;
	
	public static JTextArea textArea = new JTextArea();
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnDeleteCoordinate;
	private JButton btnClearCoords;
	private JMenuItem mntmClearCoordinate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		Intialize();
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setResizable(false);
		setTitle("MC Tools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(41,51,155));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLookandFeel();
		
		file = new JMenu("File");
		menu = new JMenuBar();
		coord = new JMenu("Coords");
		
		exit = new JMenuItem("Exit");
		addCoord= new JMenuItem("Add Coordinate");
		deleteCoord = new JMenuItem("Delete Coordinate");
		exit.addActionListener(new buttonListener());
		addCoord.addActionListener(new buttonListener());
		deleteCoord.addActionListener(new buttonListener());
		
		file.add(exit);
		coord.add(addCoord);
		coord.add(deleteCoord);
		
		menu.add(file);
		menu.add(coord);
		
		mntmClearCoordinate = new JMenuItem("Clear All Coords");
		coord.add(mntmClearCoordinate);
		mntmClearCoordinate.addActionListener(new buttonListener());
		
		this.setJMenuBar(menu);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(116,164,188));
		panel_1.setBounds(0, 0, 110, 240);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnNewButton = new JButton("Add Coordinate");
		btnNewButton.setBounds(0, 0, 110, 41);
		btnNewButton.addActionListener(new buttonListener());
		panel_1.add(btnNewButton);
		
		btnDeleteCoordinate = new JButton("Delete Coords");
		btnDeleteCoordinate.setBounds(0, 40, 110, 41);
		btnDeleteCoordinate.addActionListener(new buttonListener());
		
		
		panel_1.add(btnDeleteCoordinate);
		
		btnClearCoords = new JButton("Clear All Coords");
		btnClearCoords.setBounds(0, 80, 110, 41);
		btnClearCoords.addActionListener(new buttonListener());
		
		panel_1.add(btnClearCoords);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 240);
		contentPane.add(panel);
		panel.setBackground(new Color(182,214,204)); 
		panel.setLayout(null);
		
		//JLabel lblCoordinatesOfinsert = new JLabel("\"INSERT WORLD NAME HERE\"");
		//lblCoordinatesOfinsert.setBounds(197, 11, 150, 14);
		//panel.add(lblCoordinatesOfinsert);
		
		
		textArea.setEditable(false);
		textArea.setBounds(110, 0, 324, 240);
		
		
		
		panel.add(textArea);
		
	}
	

	public static void Intialize() 
	{
		
		File file = new File(".storage/");		
		if(!file.exists()){
			boolean result = file.mkdir();
			if(result){
				System.out.println("Successfully created "+file.getAbsolutePath());
			}
			else{
				System.out.println("Failed creating "+file.getAbsolutePath());
			}
		}else{
			System.out.println("Pathname already exists");
		}
		
		
		//Initialize Classes
		try {
			ct = new CoordinateTracker();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public class buttonListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addCoord || e.getSource() == btnNewButton)
			{

				ct.newCoord();
			}
			
			if(e.getSource() == deleteCoord || e.getSource() == btnDeleteCoordinate)
			{
				ct.deleteCoord();
			}
			
			if(e.getSource() == btnClearCoords || e.getSource() == mntmClearCoordinate)
			{
				//ask for confirm
				try {
					ct.clear();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == exit)
			{
				System.exit(0);
			}
			
			
		}
	}
	
	private void setLookandFeel()
	{
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
	}
}
