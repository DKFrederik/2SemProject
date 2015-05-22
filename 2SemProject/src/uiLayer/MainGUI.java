package uiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import ctrLayer.PersonCtr;
import modelLayer.Person;
import modelLayer.Player;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFname;
	private JTextField textLname;
	private JTextField textEmail;
	private JTextField textPhoneNo;
	private JTextField textZipcode;
	private JTextField textSbDay;
	private JTextField textPosition;
	private JTextField textCityName;

	private Person p;

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

		PersonCtr pCtr = PersonCtr.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);

		JPanel panelTeam = new JPanel();
		panelTeam.setBounds(0, 0, 434, 261);
		contentPane.add(panelTeam);
		panelTeam.setLayout(null);
		panelTeam.setVisible(false);

		JButton btnCreateT = new JButton("Create");
		btnCreateT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateT.setBounds(10, 11, 89, 23);
		panelTeam.add(btnCreateT);

		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.setBounds(10, 43, 89, 23);
		panelTeam.add(btnAddPlayerT);

		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.setBounds(10, 77, 89, 23);
		panelTeam.add(btnAddManagerT);

		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.setBounds(10, 111, 89, 23);
		panelTeam.add(btnAddTeamleaderT);

		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(10, 227, 89, 23);
		panelTeam.add(btnBackT);

		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.setBounds(10, 145, 89, 23);
		panelTeam.add(btnRemovePersonT);

		JButton btnFindT = new JButton("Find");
		btnFindT.setBounds(10, 179, 89, 23);
		panelTeam.add(btnFindT);
		panelPlayer.setBounds(0, 0, 434, 261);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);

		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);
		panelMain.setBounds(0, 0, 434, 261);
		contentPane.add(panelMain);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(e -> {
			try {
				pCtr.createPlayer(textFname.getText(), textLname.getText(),
						textEmail.getText(), textPhoneNo.getText(),
						textZipcode.getText(), textSbDay.getText(),
						textPosition.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		JLabel lblFnameLabel = new JLabel("First Name");
		lblFnameLabel.setBounds(111, 14, 72, 16);
		panelPlayer.add(lblFnameLabel);

		textFname = new JTextField();
		textFname.setBounds(233, 11, 116, 22);
		panelPlayer.add(textFname);
		textFname.setColumns(10);

		textLname = new JTextField();
		textLname.setBounds(236, 45, 116, 22);
		panelPlayer.add(textLname);
		textLname.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(241, 79, 116, 22);
		panelPlayer.add(textEmail);
		textEmail.setColumns(10);

		textPhoneNo = new JTextField();
		textPhoneNo.setBounds(241, 113, 116, 22);
		panelPlayer.add(textPhoneNo);
		textPhoneNo.setColumns(10);

		textZipcode = new JTextField();
		textZipcode.setBounds(214, 147, 89, 22);
		panelPlayer.add(textZipcode);
		textZipcode.setColumns(10);

		textSbDay = new JTextField();
		textSbDay.setBounds(211, 176, 116, 22);
		panelPlayer.add(textSbDay);
		textSbDay.setColumns(10);

		textPosition = new JTextField();
		textPosition.setBounds(207, 205, 116, 22);
		panelPlayer.add(textPosition);
		textPosition.setColumns(10);

		textCityName = new JTextField();
		textCityName.setEditable(false);
		textCityName.setBounds(306, 147, 116, 22);
		panelPlayer.add(textCityName);
		textCityName.setColumns(10);
		btnCreate.setBounds(10, 11, 89, 23);
		panelPlayer.add(btnCreate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pCtr.deletePerson(textPhoneNo.getText());
			}
		});
		btnDelete.setBounds(10, 45, 89, 23);
		panelPlayer.add(btnDelete);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(e -> {
			p = pCtr.findPerson(textPhoneNo.getText());
			if (p instanceof Player) {
				Player pl = (Player) p;
				textFname.setText(pl.getFname());
				textLname.setText(pl.getLname());
				textPhoneNo.setText(pl.getPhone());
				textPosition.setText(pl.getPosition());
				textSbDay.setText(pl.getBDay().toString());
				textCityName.setText(pl.getCity());
				textEmail.setText(pl.getEmail());
				textZipcode.setText(pl.getZipcode());
			} else {
				// fejlmdl. her
			}
		});
		btnFind.setBounds(10, 79, 89, 23);
		panelPlayer.add(btnFind);

		JButton btnResetAll = new JButton("Clear fields");
		btnResetAll.addActionListener(e -> {
			textCityName.setText("");
			textEmail.setText("");
			textFname.setText("");
			textLname.setText("");
			textPhoneNo.setText("");
			textPosition.setText("");
			textSbDay.setText("");
			textZipcode.setText("");
		});
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
		btnUpdate.addActionListener(e -> {
			if (pCtr.updatePlayer(textFname.getText(), textLname.getText(),
					textEmail.getText(), textPhoneNo.getText(),
					textZipcode.getText(), textSbDay.getText(),
					textPosition.getText(), p.getPhone())) {
				// succes
			} else {
				// error
			}
		});
		btnUpdate.setBounds(10, 147, 89, 23);
		panelPlayer.add(btnUpdate);

		JLabel lblLnameLabel = new JLabel("Last Name");
		lblLnameLabel.setBounds(111, 48, 72, 16);
		panelPlayer.add(lblLnameLabel);

		JLabel lblEmailLabel = new JLabel("Email");
		lblEmailLabel.setBounds(111, 82, 72, 16);
		panelPlayer.add(lblEmailLabel);

		JLabel lblPhoneNoLabel = new JLabel("Phone Number");
		lblPhoneNoLabel.setBounds(111, 116, 89, 16);
		panelPlayer.add(lblPhoneNoLabel);

		JLabel lblPostalCodeLabel = new JLabel("Postal Code");
		lblPostalCodeLabel.setBounds(111, 150, 72, 16);
		panelPlayer.add(lblPostalCodeLabel);

		JLabel lblSbDayLabel = new JLabel("Birthday");
		lblSbDayLabel.setBounds(111, 179, 72, 16);
		panelPlayer.add(lblSbDayLabel);

		JLabel lblPosiotionLabel = new JLabel("Position");
		lblPosiotionLabel.setBounds(111, 208, 72, 16);
		panelPlayer.add(lblPosiotionLabel);

		JPanel panelTraining = new JPanel();
		panelTraining.setBounds(0, 0, 434, 261);
		contentPane.add(panelTraining);
		panelTraining.setLayout(null);
		panelTraining.setVisible(false);

		JButton btnCreate_1 = new JButton("Show training schedule");
		btnCreate_1.setBounds(132, 181, 198, 23);
		panelTraining.add(btnCreate_1);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(e -> {
			panelTraining.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBack_1.setBounds(10, 227, 89, 23);
		panelTraining.add(btnBack_1);

		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 434, 261);
		contentPane.add(panelField);
		panelField.setLayout(null);
		panelField.setVisible(false);

		JButton btnClearFields = new JButton("Clear textfields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearFields.setBounds(10, 44, 105, 23);
		panelField.add(btnClearFields);

		JButton btnCreateField = new JButton("Create field");
		btnCreateField.setBounds(10, 11, 89, 23);
		panelField.add(btnCreateField);

		JButton btnBackF = new JButton("Back");
		btnBackF.addActionListener(e -> {
			panelField.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackF.setBounds(10, 227, 105, 23);
		panelField.add(btnBackF);

		JButton btnDeleteField = new JButton("Delete field");
		btnDeleteField.setBounds(10, 78, 105, 23);
		panelField.add(btnDeleteField);

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

		/**
		 * MAIN MENU
		 */

	}
}