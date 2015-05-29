package uiLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import ctrLayer.FieldCtr;
import ctrLayer.PersonCtr;
import ctrLayer.ScheduleCtr;
import ctrLayer.TeamCtr;
import modelLayer.Field;
import modelLayer.Person;
import modelLayer.Player;
import modelLayer.Schedule;
import modelLayer.Team;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.transaction.TransactionRolledbackException;

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
	private Field f;
	private Team t;
	private JTextField textFieldNumber;
	private JTextField textFieldtype;
	private JTextField textFieldLength;
	private JTextField textFieldWidth;
	private JTextField textTeamNumber;
	private JTextField textTeamLeague;
	private JTextField textTeamManager;
	private JTextField textTeamLeader;
	private JTextField textTeamPhoneNo;
	private JTable table;
	private JTextField textTrainDate;
	private JTextField textTrainCreator;
	private JTextField trainTeamName;
	private DefaultTableModel dtm;
	private JTextField textTrainCName;
	private JTextField textTrainCLName;
	private JTextField textFejl;
	private JTextField textTeamFejl;
	private JTextField textPlayerFejl;

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
		FieldCtr fCtr = FieldCtr.getInstance();
		TeamCtr tCtr = TeamCtr.getInstance();
		ScheduleCtr sCtr = ScheduleCtr.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		setBounds(300, 300, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);

		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);
		panelMain.setBounds(0, 0, 682, 453);
		contentPane.add(panelMain);
		panelMain.setBounds(0, 0, 682, 453);
		contentPane.add(panelMain);

		JButton playerBtnM = new JButton("Player");
		playerBtnM.setBounds(209, 13, 250, 74);
		playerBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelPlayer.setVisible(true);
		});
		panelMain.setLayout(null);
		panelMain.add(playerBtnM);

		JPanel panelTeam = new JPanel();
		panelTeam.setBounds(0, 0, 682, 453);
		contentPane.add(panelTeam);
		panelTeam.setLayout(null);
		panelTeam.setVisible(false);
		
		JPanel panelTraining = new JPanel();
		panelTraining.setBounds(0, 0, 682, 453);
		contentPane.add(panelTraining);
		panelTraining.setLayout(null);
		panelTraining.setVisible(false);
		
		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 682, 453);
		contentPane.add(panelField);
		panelField.setLayout(null);
		panelField.setVisible(false);

		JButton trainingBtnM = new JButton("Training");
		trainingBtnM.setBounds(209, 94, 250, 74);
		trainingBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelTraining.setVisible(true);
		});
		panelMain.add(trainingBtnM);

		JButton fieldBtnM = new JButton("Field");
		fieldBtnM.setBounds(209, 255, 250, 74);
		fieldBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelField.setVisible(true);
		});
		panelMain.add(fieldBtnM);

		JButton teamBtnM = new JButton("Team");
		teamBtnM.setBounds(209, 174, 250, 74);
		teamBtnM.addActionListener(e -> {
			panelMain.setVisible(false);
			panelTeam.setVisible(true);
		});
		panelMain.add(teamBtnM);

		JButton exitBtnM = new JButton("Exit system");
		exitBtnM.setBounds(245, 342, 186, 74);
		exitBtnM.addActionListener(e -> {
			System.exit(1);
		});
		panelMain.add(exitBtnM);

		JButton btnCreateT = new JButton("Create");
		btnCreateT.addActionListener(e -> {

			String teamNumber = textTeamNumber.getText();
			try {
				int league = Integer.parseInt(textTeamLeague.getText());
				t = new Team(teamNumber, league);
			} catch (NumberFormatException nfe) {
				textTeamFejl.setText("League is not an number.");
			}

			try {
				if (tCtr.insertTeam(t)) {
					textTeamFejl.setText("Team created");
					t = null;
				} else {
					textTeamFejl.setText("Team not created");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				textTeamFejl.setText("Failed to create teams");
			}
		});

		btnCreateT.setBounds(10, 28, 131, 23);
		panelTeam.add(btnCreateT);

		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.addActionListener(e -> {
			if (checkPhone(textTeamPhoneNo.getText())) {
				if (tCtr.insertPlayer(textTeamPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Player added.");
				} else {
					textTeamFejl.setText("Player not found.");
				}
			} else
				textTeamFejl.setText("Invalid Phone number");
		});
		btnAddPlayerT.setBounds(10, 76, 131, 23);
		panelTeam.add(btnAddPlayerT);

		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.addActionListener(e -> {
			if (checkPhone(textTeamPhoneNo.getText())) {
				if (tCtr.insertManager(textTeamPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Manager added.");
				} else {
					textTeamFejl.setText("Manager not found.");
				}
			} else
				textTeamFejl.setText("Invalid Phone number");
		});
		btnAddManagerT.setBounds(10, 129, 131, 23);
		panelTeam.add(btnAddManagerT);

		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.addActionListener(e -> {
			if (checkPhone(textTeamPhoneNo.getText())) {
				if (tCtr.insertTeamLeader(textTeamPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Teamleader added.");
				} else {
					textTeamFejl.setText("Teamleader not found.");
				}
			} else
				textTeamFejl.setText("Invalid Phone number");
		});
		btnAddTeamleaderT.setBounds(10, 188, 131, 23);
		panelTeam.add(btnAddTeamleaderT);

		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(12, 407, 89, 23);
		panelTeam.add(btnBackT);

		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.addActionListener(e -> {
			if (checkPhone(textPhoneNo.getText())) {
				if (tCtr.deletePlayer(textPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Player deleted");
				} else if (tCtr.deleteTeamLeader(textPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Teamleader deleted");
				} else if (tCtr.deleteManager(textPhoneNo.getText(),
						textTeamNumber.getText())) {
					textTeamFejl.setText("Manger deleted ");
				} else {
					textTeamFejl.setText("No such person");
				}
			}
		});
		btnRemovePersonT.setBounds(10, 243, 131, 23);
		panelTeam.add(btnRemovePersonT);

		JButton btnFindT = new JButton("Find");
		btnFindT.addActionListener(e -> {
			t = tCtr.findTeam(textTeamNumber.getText());
			if (t instanceof Team) {
				Team t1 = (Team) t;
				textTeamNumber.setText(t1.getTeamNumber());
				textTeamLeague.setText(String.valueOf(t1.getLeague()));
				if (t1.getManager() != null) {
					textTeamManager.setText(String.valueOf(t1.getManager()
							.getFname() + " " + t1.getManager().getLname()));
				}
				if (t1.getTeamLeader() != null) {
					textTeamLeader.setText(String.valueOf(t1.getTeamLeader()
							.getFname() + " " + t1.getTeamLeader().getLname()));
				}
			} else {
				textTeamFejl.setText("Team not found.");
			}
		});
		btnFindT.setBounds(10, 296, 131, 23);
		panelTeam.add(btnFindT);

		textTeamNumber = new JTextField();
		textTeamNumber.setBounds(329, 28, 341, 22);
		panelTeam.add(textTeamNumber);
		textTeamNumber.setColumns(10);

		textTeamLeague = new JTextField();
		textTeamLeague.setColumns(10);
		textTeamLeague.setBounds(329, 88, 341, 22);
		panelTeam.add(textTeamLeague);

		textTeamManager = new JTextField();
		textTeamManager.setColumns(10);
		textTeamManager.setBounds(329, 227, 341, 22);
		panelTeam.add(textTeamManager);

		textTeamLeader = new JTextField();
		textTeamLeader.setColumns(10);
		textTeamLeader.setBounds(329, 296, 341, 22);
		panelTeam.add(textTeamLeader);

		textTeamPhoneNo = new JTextField();
		textTeamPhoneNo.setColumns(10);
		textTeamPhoneNo.setBounds(329, 155, 341, 22);
		panelTeam.add(textTeamPhoneNo);

		JLabel lblTeamLeader = new JLabel("Team leader");
		lblTeamLeader.setBounds(188, 290, 82, 34);
		panelTeam.add(lblTeamLeader);

		JLabel lblTeamManager = new JLabel("Team manager");
		lblTeamManager.setBounds(188, 224, 94, 29);
		panelTeam.add(lblTeamManager);

		JLabel lblTeamLeague = new JLabel("Team League");
		lblTeamLeague.setBounds(188, 88, 94, 23);
		panelTeam.add(lblTeamLeague);

		JLabel lblTeamNumber = new JLabel("Team number");
		lblTeamNumber.setBounds(188, 21, 94, 37);
		panelTeam.add(lblTeamNumber);

		JLabel lblTeamPhoneNumber = new JLabel("phone number");
		lblTeamPhoneNumber.setBounds(188, 158, 142, 16);
		panelTeam.add(lblTeamPhoneNumber);

		textTeamFejl = new JTextField();
		textTeamFejl.setEditable(false);
		textTeamFejl.setColumns(10);
		textTeamFejl.setBounds(153, 407, 517, 22);
		panelTeam.add(textTeamFejl);
		panelPlayer.setBounds(0, 0, 682, 453);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(e -> {
			try {
				pCtr.createPlayer(textFname.getText(), textLname.getText(),
						textEmail.getText(), textPhoneNo.getText(),
						textZipcode.getText(), textSbDay.getText(),
						textPosition.getText());
			} catch (Exception e1) {
				textPlayerFejl.setText(e1.toString());
			}
		});

		JLabel lblFnameLabel = new JLabel("First Name");
		lblFnameLabel.setBounds(154, 48, 72, 16);
		panelPlayer.add(lblFnameLabel);

		textFname = new JTextField();
		textFname.setBounds(260, 45, 353, 22);
		panelPlayer.add(textFname);
		textFname.setColumns(10);

		textLname = new JTextField();
		textLname.setBounds(260, 113, 353, 22);
		panelPlayer.add(textLname);
		textLname.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(260, 176, 353, 22);
		panelPlayer.add(textEmail);
		textEmail.setColumns(10);

		textPhoneNo = new JTextField();
		textPhoneNo.setBounds(260, 229, 353, 22);
		panelPlayer.add(textPhoneNo);
		textPhoneNo.setColumns(10);

		textZipcode = new JTextField();
		textZipcode.setBounds(260, 284, 143, 22);
		panelPlayer.add(textZipcode);
		textZipcode.setColumns(10);

		textSbDay = new JTextField();
		textSbDay.setBounds(260, 336, 353, 22);
		panelPlayer.add(textSbDay);
		textSbDay.setColumns(10);

		textPosition = new JTextField();
		textPosition.setBounds(260, 383, 353, 22);
		panelPlayer.add(textPosition);
		textPosition.setColumns(10);

		textCityName = new JTextField();
		textCityName.setEditable(false);
		textCityName.setBounds(440, 284, 173, 22);
		panelPlayer.add(textCityName);
		textCityName.setColumns(10);
		btnCreate.setBounds(10, 45, 96, 23);
		panelPlayer.add(btnCreate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pCtr.deletePerson(textPhoneNo.getText());
				textPlayerFejl.setText("Player Deleted");
			}
		});
		btnDelete.setBounds(10, 113, 96, 23);
		panelPlayer.add(btnDelete);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(e -> {
			if (checkPhone(textPhoneNo.getText())) {

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
					textPlayerFejl.setText("Person not found.");
				}
			} else {
				textPlayerFejl.setText("Invalid Phone number.");
			}
		});
		btnFind.setBounds(10, 192, 96, 23);
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
		btnResetAll.setBounds(10, 266, 96, 23);
		panelPlayer.add(btnResetAll);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 418, 96, 23);
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
				textPlayerFejl.setText("Player updated.");
			} else {
				textPlayerFejl.setText("Error.");
			}
		});
		btnUpdate.setBounds(10, 347, 96, 23);
		panelPlayer.add(btnUpdate);

		JLabel lblLnameLabel = new JLabel("Last Name");
		lblLnameLabel.setBounds(154, 116, 72, 16);
		panelPlayer.add(lblLnameLabel);

		JLabel lblEmailLabel = new JLabel("Email");
		lblEmailLabel.setBounds(154, 179, 72, 16);
		panelPlayer.add(lblEmailLabel);

		JLabel lblPhoneNoLabel = new JLabel("Phone Number");
		lblPhoneNoLabel.setBounds(154, 232, 89, 16);
		panelPlayer.add(lblPhoneNoLabel);

		JLabel lblPostalCodeLabel = new JLabel("Postal Code");
		lblPostalCodeLabel.setBounds(154, 287, 72, 16);
		panelPlayer.add(lblPostalCodeLabel);

		JLabel lblSbDayLabel = new JLabel("Birthday");
		lblSbDayLabel.setBounds(154, 339, 72, 16);
		panelPlayer.add(lblSbDayLabel);

		JLabel lblPosiotionLabel = new JLabel("Position");
		lblPosiotionLabel.setBounds(154, 386, 72, 16);
		panelPlayer.add(lblPosiotionLabel);

		textPlayerFejl = new JTextField();
		textPlayerFejl.setEditable(false);
		textPlayerFejl.setColumns(10);
		textPlayerFejl.setBounds(149, 418, 464, 22);
		panelPlayer.add(textPlayerFejl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 13, 401, 335);
		panelTraining.add(scrollPane);

		table = new JTable();
		dtm = new DefaultTableModel(0, 0);

		String[] header = new String[] { "Team", "Field", "Time" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		scrollPane.setViewportView(table);

		JButton btnCreate_1 = new JButton("Show training schedule");
		btnCreate_1
				.addActionListener(e -> {
					try {
						sCtr.makeSchedule();
						Schedule s = sCtr.getCurrentSchedule();
						setupSchedule(s);
					} catch (NullPointerException npe) {
						textFejl.setText("No active schedule. Please make a schedule first.");
					}
				});
		btnCreate_1.setBounds(376, 361, 198, 23);
		panelTraining.add(btnCreate_1);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(e -> {
			panelTraining.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBack_1.setBounds(12, 417, 95, 23);
		panelTraining.add(btnBack_1);

		JButton btnAddDate = new JButton("Add");
		btnAddDate.addActionListener(e -> {
			if (checkDate()) {
				String date = textTrainDate.getText();
				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(5, 7));
				int day = Integer.parseInt(date.substring(8, 10));
				if (sCtr.createSchedule(new java.sql.Date(year - 1900,
						month - 1, day)) != -1) {
					textFejl.setText("Schedule created.");
				} else {
					textFejl.setText("Schedule allready exist.");
					setupSchedule(sCtr.getSchedule(new java.sql.Date(
							year - 1900, month - 1, day), true));
					textTrainCreator
							.setText(sCtr
									.getSchedule(
											new java.sql.Date(year - 1900,
													month - 1, day), true)
									.getCreator().getPhone());
				}
			} else {
				textFejl.setText("Invalid date.");
			}
		});
		btnAddDate.setBounds(12, 45, 97, 25);
		panelTraining.add(btnAddDate);

		JLabel lblDato = new JLabel("Dato yyyy-mm-dd");
		lblDato.setBounds(12, 16, 109, 16);
		panelTraining.add(lblDato);

		textTrainDate = new JTextField();
		textTrainDate.setBounds(133, 13, 124, 22);
		panelTraining.add(textTrainDate);
		textTrainDate.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tlf. nr. p\u00E5 opretter");
		lblNewLabel.setBounds(12, 99, 109, 16);
		panelTraining.add(lblNewLabel);

		textTrainCreator = new JTextField();
		textTrainCreator.setBounds(133, 96, 124, 22);
		panelTraining.add(textTrainCreator);
		textTrainCreator.setColumns(10);

		JButton btnAddCreator = new JButton("Add");
		btnAddCreator.addActionListener(e -> {
			if (checkPhone(textTrainCreator.getText())) {
				if (sCtr.setCreator(textTrainCreator.getText())) {
					p = pCtr.findPerson(textTrainCreator.getText());
					textTrainCName.setText(p.getFname());
					textTrainCLName.setText(p.getLname());
				} else {
					textFejl.setText("The person does not exist.");
				}
			} else {
				textFejl.setText("Invalid Phone number.");
			}
		});
		btnAddCreator.setBounds(12, 128, 97, 25);
		panelTraining.add(btnAddCreator);

		JButton btnnAddTeam = new JButton("Add");
		btnnAddTeam.addActionListener(e -> {
			if (sCtr.addTeam(trainTeamName.getText())) {
				dtm.addRow(new Object[] { trainTeamName.getText() });
			} else {
				textFejl.setText("No such team.");
			}
		});
		btnnAddTeam.setBounds(12, 249, 97, 25);
		panelTraining.add(btnnAddTeam);

		JLabel lblHoldNavn = new JLabel("Hold navn");
		lblHoldNavn.setBounds(12, 220, 109, 16);
		panelTraining.add(lblHoldNavn);

		trainTeamName = new JTextField();
		trainTeamName.setColumns(10);
		trainTeamName.setBounds(133, 217, 124, 22);
		panelTraining.add(trainTeamName);

		JButton btnTrainClear = new JButton("Clear");
		btnTrainClear.addActionListener(e -> {
			sCtr.removeCurrentSchedule();
			textTrainCreator.setText("");
			textTrainCName.setText("");
			textTrainCLName.setText("");
			trainTeamName.setText("");
			textTrainDate.setText("");

			int rc = dtm.getRowCount();
			for (int i = rc - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		});
		btnTrainClear.setBounds(119, 416, 97, 25);
		panelTraining.add(btnTrainClear);

		JButton btnSaveSchedule = new JButton("Save");
		btnSaveSchedule.addActionListener(e -> {
			try {
				sCtr.completeSchedule();
				textFejl.setText("Schedule saved.");
			} catch (TransactionRolledbackException trbe) {
				textFejl.setText(trbe.toString());
			}
		});
		btnSaveSchedule.setBounds(12, 379, 97, 25);
		panelTraining.add(btnSaveSchedule);

		textTrainCName = new JTextField();
		textTrainCName.setEditable(false);
		textTrainCName.setBounds(133, 131, 124, 22);
		panelTraining.add(textTrainCName);
		textTrainCName.setColumns(10);

		textTrainCLName = new JTextField();
		textTrainCLName.setEditable(false);
		textTrainCLName.setColumns(10);
		textTrainCLName.setBounds(133, 166, 124, 22);
		panelTraining.add(textTrainCLName);

		JButton btnTrainDelete = new JButton("Delete");
		btnTrainDelete.addActionListener(e -> {
			if (checkDate()) {
				String date = textTrainDate.getText();
				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(5, 7));
				int day = Integer.parseInt(date.substring(8, 10));
				try {
					sCtr.deleteSchedule(new java.sql.Date(year - 1900,
							month - 1, day));
					textTrainCLName.setText("");
					textTrainCName.setText("");
					textTrainCreator.setText("");
					textTrainDate.setText("");
					int rc = dtm.getRowCount();
					for (int i = rc - 1; i >= 0; i--) {
						dtm.removeRow(i);
					}
					textFejl.setText("Schedule deleted.");
				} catch (TransactionRolledbackException trbe) {
					textFejl.setText("Delete not completed.");
				}

			} else {
				textFejl.setText("Invalid date. Please use yyyy-mm-dd.");
			}
		});
		btnTrainDelete.setBounds(119, 378, 97, 25);
		panelTraining.add(btnTrainDelete);

		textFejl = new JTextField();
		textFejl.setEditable(false);
		textFejl.setColumns(10);
		textFejl.setBounds(269, 417, 401, 22);
		panelTraining.add(textFejl);

		JButton btnClearFields = new JButton("Clear textfields");
		btnClearFields.addActionListener(e -> {
			textFieldNumber.setText("");
			textFieldtype.setText("");
			textFieldLength.setText("");
			textFieldWidth.setText("");
		});
		btnClearFields.setBounds(183, 417, 119, 23);
		panelField.add(btnClearFields);

		JButton btnCreateField = new JButton("Create field");
		btnCreateField.addActionListener(e -> {

			try {
				fCtr.createField(textFieldNumber.getText(),
						textFieldtype.getText(),
						Integer.parseInt(textFieldLength.getText()),
						Integer.parseInt(textFieldWidth.getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnCreateField.setBounds(551, 417, 119, 23);
		panelField.add(btnCreateField);

		JButton btnBackF = new JButton("Back");
		btnBackF.addActionListener(e -> {
			panelField.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackF.setBounds(12, 417, 105, 23);
		panelField.add(btnBackF);

		JButton btnDeleteField = new JButton("Delete field");
		btnDeleteField.addActionListener(e -> {
			fCtr.deleteField(textFieldNumber.getText());
		});
		btnDeleteField.setBounds(367, 417, 119, 23);
		panelField.add(btnDeleteField);

		textFieldNumber = new JTextField();
		textFieldNumber.setBounds(215, 26, 434, 22);
		panelField.add(textFieldNumber);
		textFieldNumber.setColumns(10);

		textFieldtype = new JTextField();
		textFieldtype.setBounds(215, 103, 434, 22);
		panelField.add(textFieldtype);
		textFieldtype.setColumns(10);

		textFieldLength = new JTextField();
		textFieldLength.setBounds(215, 188, 434, 22);
		panelField.add(textFieldLength);
		textFieldLength.setColumns(10);

		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(215, 276, 434, 22);
		panelField.add(textFieldWidth);
		textFieldWidth.setColumns(10);

		JLabel lblFieldNumber = new JLabel("Field Number in even numbers ");
		lblFieldNumber.setBounds(10, 29, 193, 16);
		panelField.add(lblFieldNumber);

		JLabel labelFieldType = new JLabel("Field Type Match or Training");
		labelFieldType.setBounds(10, 106, 193, 16);
		panelField.add(labelFieldType);

		JLabel lblFieldLength = new JLabel("Field Length");
		lblFieldLength.setBounds(10, 191, 193, 16);
		panelField.add(lblFieldLength);

		JLabel lblFieldWidth = new JLabel("Field Width");
		lblFieldWidth.setBounds(12, 279, 193, 16);
		panelField.add(lblFieldWidth);

	}

	private boolean checkDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(textTrainDate.getText());
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	private boolean checkPhone(String phone) {
		if (phone.length() != 8) {
			return false;
		}
		try {
			Integer.parseInt(phone);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void setupSchedule(Schedule s) {
		int apps = s.getAppointments().size();
		int rc = dtm.getRowCount();

		for (int i = rc - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		for (int i = 0; i < apps; i++) {
			dtm.addRow(new Object[] {
					s.getAppointments().get(i).getTeam().getTeamNumber(),
					s.getAppointments().get(i).getField().getFieldNumber(),
					s.getAppointments().get(i).getTime() });
		}

		textTrainCreator.setText(s.getCreator().getPhone());
		textTrainCName.setText(s.getCreator().getFname());
		textTrainCLName.setText(s.getCreator().getLname());
	}
}