package uiLayer;

import java.awt.BorderLayout;
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
import modelLayer.Manager;
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
	private JTextField textTeamFname;
	private JTextField textTeamPhoneNo;
	private JTextField textTeamLname;
	private JTextField textTeamEmail;
	private JTextField textTeamPosition;
	private JTextField textTeamSbDay;
	private JTextField textTeamZipcode;
	private JTable table;
	private JTextField textTrainDate;
	private JTextField textTrainCreator;
	private JTextField trainTeamName;
	private DefaultTableModel dtm;
	private JTextField textTrainCName;
	private JTextField textTrainCLName;

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

		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);

		JPanel panelTraining = new JPanel();
		panelTraining.setBounds(0, 0, 682, 453);
		contentPane.add(panelTraining);
		panelTraining.setLayout(null);
		panelTraining.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(143, 152, 390, 238);
		panelTraining.add(scrollPane);
		
		table = new JTable();
		dtm = new DefaultTableModel (0, 0);
		
		String[] header = new String[] {"Team", "Field", "Time"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);				
		scrollPane.setViewportView(table);

		JButton btnCreate_1 = new JButton("Show training schedule");
		btnCreate_1.addActionListener(e -> {
			try{
				sCtr.makeSchedule();
				Schedule s = sCtr.getCurrentSchedule();
				setupSchedule(s);
			}
			catch (NullPointerException npe) {
				//fejl    Intet aktivt skema
			}
		});
		btnCreate_1.setBounds(247, 417, 198, 23);
		panelTraining.add(btnCreate_1);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(e -> {
			panelTraining.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBack_1.setBounds(12, 417, 89, 23);
		panelTraining.add(btnBack_1);
				
		JButton btnAddDate = new JButton("Add");
		btnAddDate.addActionListener(e -> {
			if(checkDate()) {
				String date = textTrainDate.getText();
				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(5, 7));
				int day = Integer.parseInt(date.substring(8, 10));
				if(sCtr.createSchedule(new java.sql.Date(year - 1900, month - 1, day)) != -1) {
					
				}
				else {
					//fejl msg Skema findes
					setupSchedule(sCtr.getSchedule(new java.sql.Date(year - 1900, month - 1, day), true));
					textTrainCreator.setText(sCtr.getSchedule(new java.sql.Date(year - 1900, month - 1, day), true).getCreator().getPhone());
					
				}
			}
			else {
				//fejl
			}
		});
		btnAddDate.setBounds(259, 9, 97, 25);
		panelTraining.add(btnAddDate);
		
		JLabel lblDato = new JLabel("Dato yyyy-mm-dd");
		lblDato.setBounds(12, 13, 109, 16);
		panelTraining.add(lblDato);
		
		textTrainDate = new JTextField();
		textTrainDate.setBounds(123, 10, 124, 22);
		panelTraining.add(textTrainDate);
		textTrainDate.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tlf. nr. p\u00E5 opretter");
		lblNewLabel.setBounds(12, 54, 109, 16);
		panelTraining.add(lblNewLabel);
		
		textTrainCreator = new JTextField();
		textTrainCreator.setBounds(123, 51, 124, 22);
		panelTraining.add(textTrainCreator);
		textTrainCreator.setColumns(10);
		
		JButton btnAddCreator = new JButton("Add");
		btnAddCreator.addActionListener(e -> {
			if(checkPhone(textTrainCreator.getText())) {
				if(sCtr.setCreator(textTrainCreator.getText())) {
					p = pCtr.findPerson(textTrainCreator.getText());
					textTrainCName.setText(p.getFname());
					textTrainCLName.setText(p.getLname());
				}
				else {
					//fejl PERSON FANDTES IKKE
				}
			}
			else {
				//fejl TLF WROOOONG
			}
		});
		btnAddCreator.setBounds(259, 50, 97, 25);
		panelTraining.add(btnAddCreator);
		
		JButton btnnAddTeam = new JButton("Add");
		btnnAddTeam.addActionListener(e -> {
			if(sCtr.addTeam(trainTeamName.getText())) {
				dtm.addRow(new Object[] { trainTeamName.getText() });
			}
			else {
				//fejl
			}
		});
		btnnAddTeam.setBounds(259, 88, 97, 25);
		panelTraining.add(btnnAddTeam);
		
		JLabel lblHoldNavn = new JLabel("Hold navn");
		lblHoldNavn.setBounds(12, 92, 109, 16);
		panelTraining.add(lblHoldNavn);
		
		trainTeamName = new JTextField();
		trainTeamName.setColumns(10);
		trainTeamName.setBounds(123, 89, 124, 22);
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
		btnTrainClear.setBounds(24, 344, 97, 25);
		panelTraining.add(btnTrainClear);
		
		JButton btnSaveSchedule = new JButton("Save");
		btnSaveSchedule.addActionListener(e -> {
			try {
				sCtr.completeSchedule();
			}
			catch (TransactionRolledbackException trbe) {
				//trbe.toString()
			}
		});
		btnSaveSchedule.setBounds(24, 306, 97, 25);
		panelTraining.add(btnSaveSchedule);
		
		textTrainCName = new JTextField();
		textTrainCName.setEditable(false);
		textTrainCName.setBounds(388, 51, 116, 22);
		panelTraining.add(textTrainCName);
		textTrainCName.setColumns(10);
		
		textTrainCLName = new JTextField();
		textTrainCLName.setEditable(false);
		textTrainCLName.setColumns(10);
		textTrainCLName.setBounds(388, 89, 116, 22);
		panelTraining.add(textTrainCLName);
		
		JButton btnTrainDelete = new JButton("Delete");
		btnTrainDelete.addActionListener(e -> {
			if(checkDate()) {
				String date = textTrainDate.getText();
				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(5, 7));
				int day = Integer.parseInt(date.substring(8, 10));
				try {
					sCtr.deleteSchedule(new java.sql.Date(year - 1900, month - 1, day));
					textTrainCLName.setText("");
					textTrainCName.setText("");
					textTrainCreator.setText("");
					textTrainDate.setText("");
					int rc = dtm.getRowCount();
					for (int i = rc - 1; i >= 0; i--) {
					    dtm.removeRow(i);
					}
				}
				catch (TransactionRolledbackException trbe) {
					//fejl
				}
	
			}
			else {
				//fejl
			}
		});
		btnTrainDelete.setBounds(12, 230, 109, 25);
		panelTraining.add(btnTrainDelete);

		JPanel panelTeam = new JPanel();
		panelTeam.setBounds(0, 0, 682, 453);
		contentPane.add(panelTeam);
		panelTeam.setLayout(null);
		panelTeam.setVisible(false);

		JButton btnCreateT = new JButton("Create");
		btnCreateT.addActionListener(e -> {
			try {
				String teamNumber = textTeamNumber.getText();
				Integer league = Integer.getInteger(textTeamLeague.getText());
				Team t = new Team(teamNumber, league);
				if (tCtr.insertTeam(t)) {
					System.out.println("Team created ");
				} else {
					System.out.println("Team not created");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		btnCreateT.setBounds(10, 28, 131, 23);
		panelTeam.add(btnCreateT);

		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.addActionListener(e -> {
			try {
				tCtr.insertPlayer(textPhoneNo.getText(),
						textTeamNumber.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnAddPlayerT.setBounds(10, 64, 131, 23);
		panelTeam.add(btnAddPlayerT);

		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.addActionListener(e -> {
			try {
				tCtr.insertManager(textPhoneNo.getText(),
						textTeamNumber.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnAddManagerT.setBounds(10, 111, 131, 23);
		panelTeam.add(btnAddManagerT);

		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.addActionListener(e -> {
			try {
				tCtr.insertTeamLeader(textPhoneNo.getText(),
						textTeamNumber.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnAddTeamleaderT.setBounds(10, 155, 131, 23);
		panelTeam.add(btnAddTeamleaderT);

		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(308, 227, 89, 23);
		panelTeam.add(btnBackT);

		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemovePersonT.setBounds(10, 191, 131, 23);
		panelTeam.add(btnRemovePersonT);

		JButton btnFindT = new JButton("Find");
		btnFindT.addActionListener(e -> {
			t = tCtr.findTeam(textTeamNumber.getText());
			if (t instanceof Team) {
				Team t1 = (Team) t;
				textTeamNumber.setText(t1.getTeamNumber());
				textTeamLeague.setText(String.valueOf(t1.getLeague()));
				textTeamManager.setText(String.valueOf(t1.getManager()));
				textTeamLeader.setText(String.valueOf(t1.getTeamLeader()));
			} else {
				// fejlmdl. her
			}
		});
		btnFindT.setBounds(10, 227, 131, 23);
		panelTeam.add(btnFindT);

		textTeamNumber = new JTextField();
		textTeamNumber.setBounds(153, 29, 116, 22);
		panelTeam.add(textTeamNumber);
		textTeamNumber.setColumns(10);

		textTeamLeague = new JTextField();
		textTeamLeague.setColumns(10);
		textTeamLeague.setBounds(153, 64, 116, 22);
		panelTeam.add(textTeamLeague);

		textTeamManager = new JTextField();
		textTeamManager.setColumns(10);
		textTeamManager.setBounds(153, 111, 116, 22);
		panelTeam.add(textTeamManager);

		textTeamLeader = new JTextField();
		textTeamLeader.setColumns(10);
		textTeamLeader.setBounds(153, 157, 116, 22);
		panelTeam.add(textTeamLeader);

		textTeamFname = new JTextField();
		textTeamFname.setColumns(10);
		textTeamFname.setBounds(153, 192, 116, 22);
		panelTeam.add(textTeamFname);

		textTeamPhoneNo = new JTextField();
		textTeamPhoneNo.setColumns(10);
		textTeamPhoneNo.setBounds(281, 64, 116, 22);
		panelTeam.add(textTeamPhoneNo);

		textTeamLname = new JTextField();
		textTeamLname.setColumns(10);
		textTeamLname.setBounds(153, 227, 116, 22);
		panelTeam.add(textTeamLname);

		textTeamEmail = new JTextField();
		textTeamEmail.setColumns(10);
		textTeamEmail.setBounds(281, 29, 116, 22);
		panelTeam.add(textTeamEmail);

		textTeamPosition = new JTextField();
		textTeamPosition.setColumns(10);
		textTeamPosition.setBounds(281, 192, 116, 22);
		panelTeam.add(textTeamPosition);

		textTeamSbDay = new JTextField();
		textTeamSbDay.setColumns(10);
		textTeamSbDay.setBounds(281, 157, 116, 22);
		panelTeam.add(textTeamSbDay);

		textTeamZipcode = new JTextField();
		textTeamZipcode.setColumns(10);
		textTeamZipcode.setBounds(281, 111, 116, 22);
		panelTeam.add(textTeamZipcode);

		JLabel lblTeamLname = new JLabel("Last name");
		lblTeamLname.setBounds(153, 210, 71, 16);
		panelTeam.add(lblTeamLname);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(153, 179, 71, 16);
		panelTeam.add(lblFirstName);

		JLabel lblTeamLeader = new JLabel("Team leader");
		lblTeamLeader.setBounds(153, 130, 82, 34);
		panelTeam.add(lblTeamLeader);

		JLabel lblTeamManager = new JLabel("Team manager");
		lblTeamManager.setBounds(153, 85, 82, 29);
		panelTeam.add(lblTeamManager);

		JLabel lblTeamLeague = new JLabel("Team League");
		lblTeamLeague.setBounds(153, 46, 94, 23);
		panelTeam.add(lblTeamLeague);

		JLabel lblTeamNumber = new JLabel("Team number");
		lblTeamNumber.setBounds(153, 0, 94, 37);
		panelTeam.add(lblTeamNumber);

		JLabel lblTeamPhoneNumber = new JLabel("phone number");
		lblTeamPhoneNumber.setBounds(280, 46, 142, 23);
		panelTeam.add(lblTeamPhoneNumber);

		JLabel lblTeamEmail = new JLabel("email");
		lblTeamEmail.setBounds(280, 0, 94, 37);
		panelTeam.add(lblTeamEmail);

		JLabel lblManager = new JLabel("Zipcode");
		lblManager.setBounds(281, 80, 82, 29);
		panelTeam.add(lblManager);

		JLabel lblTeamZipcode = new JLabel("Birthday");
		lblTeamZipcode.setBounds(281, 130, 82, 34);
		panelTeam.add(lblTeamZipcode);

		JLabel lblName = new JLabel("Position");
		lblName.setBounds(281, 179, 71, 16);
		panelTeam.add(lblName);
		panelMain.setBounds(0, 0, 682, 453);
		contentPane.add(panelMain);

		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);
		panelPlayer.setBounds(0, 0, 682, 453);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);

		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 682, 453);
		contentPane.add(panelField);
		panelField.setLayout(null);
		panelField.setVisible(false);
		panelMain.setBounds(0, 0, 682, 453);
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
		textFname.setBounds(207, 11, 215, 22);
		panelPlayer.add(textFname);
		textFname.setColumns(10);

		textLname = new JTextField();
		textLname.setBounds(207, 45, 215, 22);
		panelPlayer.add(textLname);
		textLname.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(207, 79, 215, 22);
		panelPlayer.add(textEmail);
		textEmail.setColumns(10);

		textPhoneNo = new JTextField();
		textPhoneNo.setBounds(207, 113, 215, 22);
		panelPlayer.add(textPhoneNo);
		textPhoneNo.setColumns(10);

		textZipcode = new JTextField();
		textZipcode.setBounds(207, 147, 96, 22);
		panelPlayer.add(textZipcode);
		textZipcode.setColumns(10);

		textSbDay = new JTextField();
		textSbDay.setBounds(207, 176, 215, 22);
		panelPlayer.add(textSbDay);
		textSbDay.setColumns(10);

		textPosition = new JTextField();
		textPosition.setBounds(207, 205, 215, 22);
		panelPlayer.add(textPosition);
		textPosition.setColumns(10);

		textCityName = new JTextField();
		textCityName.setEditable(false);
		textCityName.setBounds(306, 147, 116, 22);
		panelPlayer.add(textCityName);
		textCityName.setColumns(10);
		btnCreate.setBounds(10, 11, 96, 23);
		panelPlayer.add(btnCreate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pCtr.deletePerson(textPhoneNo.getText());
			}
		});
		btnDelete.setBounds(10, 45, 96, 23);
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
		btnFind.setBounds(10, 79, 96, 23);
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
		btnResetAll.setBounds(10, 113, 96, 23);
		panelPlayer.add(btnResetAll);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 227, 96, 23);
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
		btnUpdate.setBounds(10, 147, 96, 23);
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

		JButton btnClearFields = new JButton("Clear textfields");
		btnClearFields.addActionListener(e -> {
			textFieldNumber.setText("");
			textFieldtype.setText("");
			textFieldLength.setText("");
			textFieldWidth.setText("");
		});
		btnClearFields.setBounds(10, 191, 119, 23);
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
		btnCreateField.setBounds(303, 191, 119, 23);
		panelField.add(btnCreateField);

		JButton btnBackF = new JButton("Back");
		btnBackF.addActionListener(e -> {
			panelField.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackF.setBounds(10, 227, 105, 23);
		panelField.add(btnBackF);

		JButton btnDeleteField = new JButton("Delete field");
		btnDeleteField.addActionListener(e -> {
			fCtr.deleteField(textFieldNumber.getText());
		});
		btnDeleteField.setBounds(303, 227, 119, 23);
		panelField.add(btnDeleteField);

		textFieldNumber = new JTextField();
		textFieldNumber.setBounds(215, 26, 207, 22);
		panelField.add(textFieldNumber);
		textFieldNumber.setColumns(10);

		textFieldtype = new JTextField();
		textFieldtype.setBounds(215, 57, 207, 22);
		panelField.add(textFieldtype);
		textFieldtype.setColumns(10);

		textFieldLength = new JTextField();
		textFieldLength.setBounds(215, 92, 207, 22);
		panelField.add(textFieldLength);
		textFieldLength.setColumns(10);

		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(215, 127, 207, 22);
		panelField.add(textFieldWidth);
		textFieldWidth.setColumns(10);

		JLabel lblFieldNumber = new JLabel("Field Number in even numbers ");
		lblFieldNumber.setBounds(10, 29, 193, 16);
		panelField.add(lblFieldNumber);

		JLabel labelFieldType = new JLabel("Field Type Match or Training");
		labelFieldType.setBounds(10, 60, 193, 16);
		panelField.add(labelFieldType);

		JLabel lblFieldLength = new JLabel("Field Length");
		lblFieldLength.setBounds(10, 95, 193, 16);
		panelField.add(lblFieldLength);

		JLabel lblFieldWidth = new JLabel("Field Width");
		lblFieldWidth.setBounds(10, 130, 193, 16);
		panelField.add(lblFieldWidth);

	}
	
	private boolean checkDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
	     try {
	          format.parse(textTrainDate.getText());	
	          return true;
	     }
	     catch(ParseException ex){
	    	 return false;
	     }
	}
	
	private boolean checkPhone(String phone) {
		if(phone.length() != 8) {
			return false;
		}
		try {
			Integer.parseInt(phone);
			return true;
		}
		catch (NumberFormatException e ) {
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
			dtm.addRow(new Object[] { s.getAppointments().get(i).getTeam().getTeamNumber(), 
					s.getAppointments().get(i).getField().getFieldNumber(), 
					s.getAppointments().get(i).getTime() });
		}

		textTrainCreator.setText(s.getCreator().getPhone());
		textTrainCName.setText(s.getCreator().getFname());
		textTrainCLName.setText(s.getCreator().getLname());
	}
}