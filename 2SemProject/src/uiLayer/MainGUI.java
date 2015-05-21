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
		
		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 434, 261);
		contentPane.add(panelField);
		panelField.setLayout(null);
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		
		JPanel panelTraining = new JPanel();
		panelTraining.setBounds(0, 0, 434, 261);
		contentPane.add(panelTraining);
		panelTraining.setLayout(null);
		panelTraining.setVisible(false);
		panelPlayer.setVisible(false);
		panelField.setVisible(false);
		
		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);
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
//			panelTeam.setVisible(true);
		});
		panelMain.add(teamBtnM);
		
		JButton exitBtnM = new JButton("Exit system");
		exitBtnM.setBounds(149, 227, 126, 23);
		exitBtnM.addActionListener(e -> {
			System.exit(1);
		});
		panelMain.add(exitBtnM);

		
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
		panelPlayer.setBounds(0, 0, 434, 261);
		contentPane.add(panelPlayer);

		
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
		
		
		JPanel panelTeam = new JPanel();
		panelTeam.setBounds(0, 0, 434, 261);
		panelField.add(panelTeam);
		panelTeam.setLayout(null);
		panelTeam.setVisible(false);

		
		JButton btnCreateT = new JButton("Create");
		btnCreateT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateT.setBounds(10, 196, 89, 23);
		panelTeam.add(btnCreateT);
		
		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.setBounds(109, 196, 89, 23);
		panelTeam.add(btnAddPlayerT);
		
		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.setBounds(208, 196, 89, 23);
		panelTeam.add(btnAddManagerT);
		
		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.setBounds(307, 196, 89, 23);
		panelTeam.add(btnAddTeamleaderT);
		
		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(307, 227, 89, 23);
		panelTeam.add(btnBackT);
		
		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.setBounds(109, 227, 89, 23);
		panelTeam.add(btnRemovePersonT);
		
		JButton btnFindT = new JButton("Find");
		btnFindT.setBounds(208, 227, 89, 23);
		panelTeam.add(btnFindT);
		panelPlayer.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(28, 202, 89, 23);
		panelPlayer.add(btnCreate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(127, 202, 89, 23);
		panelPlayer.add(btnDelete);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(226, 202, 89, 23);
		panelPlayer.add(btnFind);
		
		JButton btnResetAll = new JButton("Clear fields");
		btnResetAll.setBounds(325, 202, 89, 23);
		panelPlayer.add(btnResetAll);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(28, 236, 89, 23);
		btnBack.addActionListener(e -> {
			panelPlayer.setVisible(false);
			panelMain.setVisible(true);
		});
		panelPlayer.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(137, 236, 89, 23);
		panelPlayer.add(btnUpdate);

		
		/**
		 * MAIN MENU
		 */	

	}
}