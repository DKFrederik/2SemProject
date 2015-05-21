package uiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);
		
		
		JPanel panelTeam = new JPanel();
		panelTeam.setBounds(0, 0, 434, 261);
		contentPane.add(panelTeam);
		panelTeam.setLayout(null);
		
		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 434, 261);
		contentPane.add(panelField);
		panelField.setLayout(null);
		panelTeam.setVisible(false);
		panelField.setVisible(false);
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);
		panelPlayer.setBounds(0, 0, 434, 261);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(10, 11, 89, 23);
		panelPlayer.add(btnCreate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 45, 89, 23);
		panelPlayer.add(btnDelete);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(10, 79, 89, 23);
		panelPlayer.add(btnFind);
		
		JButton btnResetAll = new JButton("Clear fields");
		btnResetAll.setBounds(10, 113, 89, 23);
		panelPlayer.add(btnResetAll);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 227, 89, 23);
		btnBack.addActionListener(e -> {
			panelPlayer.setVisible(false);
			panelMain.setVisible(true);
		});
		panelPlayer.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 147, 89, 23);
		panelPlayer.add(btnUpdate);
		
		JPanel panelTraining = new JPanel();
		panelTraining.setBounds(0, 0, 434, 261);
		contentPane.add(panelTraining);
		panelTraining.setLayout(null);
		panelTraining.setVisible(false);
		
				
				JButton btnCreate_1 = new JButton("Show training schedule");
				btnCreate_1.setBounds(69, 114, 198, 23);
				panelTraining.add(btnCreate_1);
				
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(e -> {
			panelTraining.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBack_1.setBounds(302, 227, 89, 23);
		panelTraining.add(btnBack_1);
		panelMain.setBounds(0, 0, 434, 261);
		contentPane.add(panelMain);
		
		JButton playerBtnM = new JButton("Player");
		playerBtnM.setBounds(163, 25, 89, 23);
		playerBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelPlayer.setVisible(true);
		});
		panelMain.setLayout(null);
		panelMain.add(playerBtnM);
		
		JButton trainingBtnM = new JButton("Training");
		trainingBtnM.setBounds(163, 59, 89, 23);
		trainingBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelTraining.setVisible(true);
		});
		panelMain.add(trainingBtnM);
		
		JButton fieldBtnM = new JButton("Field");
		fieldBtnM.setBounds(163, 93, 89, 23);
		fieldBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelField.setVisible(true);
		});
		panelMain.add(fieldBtnM);
		
		JButton teamBtnM = new JButton("Team");
		teamBtnM.setBounds(163, 127, 89, 23);
		teamBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelTeam.setVisible(true);
		});
		panelMain.add(teamBtnM);
		
		JButton exitBtnM = new JButton("Exit system");
		exitBtnM.setBounds(149, 227, 126, 23);
		exitBtnM.addActionListener(e -> {
			System.exit(1);
		});
		panelMain.add(exitBtnM);

		
		JButton btnClearFields = new JButton("Clear textfields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearFields.setBounds(106, 227, 105, 23);
		panelField.add(btnClearFields);
		
		JButton btnCreateField = new JButton("Create field");
		btnCreateField.setBounds(10, 227, 89, 23);
		panelField.add(btnCreateField);
		
		JButton btnBackF = new JButton("Back");
		btnBackF.addActionListener(e ->  {
			panelField.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackF.setBounds(319, 227, 105, 23);
		panelField.add(btnBackF);
		
		JButton btnDeleteField = new JButton("Delete field");
		btnDeleteField.setBounds(215, 227, 105, 23);
		panelField.add(btnDeleteField);

		
		JButton btnCreateT = new JButton("Create");
		btnCreateT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateT.setBounds(10, 11, 89, 23);
		panelTeam.add(btnCreateT);
		
		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.setBounds(10, 42, 89, 23);
		panelTeam.add(btnAddPlayerT);
		
		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.setBounds(10, 76, 89, 23);
		panelTeam.add(btnAddManagerT);
		
		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.setBounds(10, 110, 89, 23);
		panelTeam.add(btnAddTeamleaderT);
		
		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(335, 227, 89, 23);
		panelTeam.add(btnBackT);
		
		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.setBounds(10, 144, 89, 23);
		panelTeam.add(btnRemovePersonT);
		
		JButton btnFindT = new JButton("Find");
		btnFindT.setBounds(10, 178, 89, 23);
		panelTeam.add(btnFindT);

		
		/**
		 * MAIN MENU
		 */	

	}
}
