package uiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import ctrLayer.FieldCtr;
import ctrLayer.PersonCtr;
import modelLayer.Field;
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
	private Field f;
	private JTextField textFieldNumber;
	private JTextField textFieldtype;
	private JTextField textFieldLength;
	private JTextField textFieldWidth;

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelMain = new JPanel();
		panelMain.setVisible(true);
		panelMain.setBounds(0, 0, 434, 261);
		contentPane.add(panelMain);

		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);

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
		btnCreateT.setBounds(10, 11, 131, 23);
		panelTeam.add(btnCreateT);

		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.setBounds(10, 43, 131, 23);
		panelTeam.add(btnAddPlayerT);

		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.setBounds(10, 77, 131, 23);
		panelTeam.add(btnAddManagerT);

		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.setBounds(10, 111, 131, 23);
		panelTeam.add(btnAddTeamleaderT);

		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(10, 227, 89, 23);
		panelTeam.add(btnBackT);

		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.setBounds(10, 145, 131, 23);
		panelTeam.add(btnRemovePersonT);

		JButton btnFindT = new JButton("Find");
		btnFindT.setBounds(10, 179, 131, 23);
		panelTeam.add(btnFindT);
		panelPlayer.setBounds(0, 0, 434, 261);
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

		JPanel panelField = new JPanel();
		panelField.setBounds(0, 0, 434, 261);
		contentPane.add(panelField);
		panelField.setLayout(null);
		panelField.setVisible(false);

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
				String number = textFieldNumber.getText();
				String type = textFieldtype.getText();
				Integer length = Integer.getInteger(textFieldLength.getText());
				Integer width = Integer.getInteger(textFieldWidth.getText());
				Field f = new Field(number, type, length, width);
				if (fCtr.insertField(f) > 0) {
					System.out.println("Field created ");
				} else {
					System.out.println("Field not created");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
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

		JPanel panelPlayer = new JPanel();
		panelPlayer.setVisible(true);
		panelPlayer.setVisible(false);
		panelPlayer.setBounds(0, 0, 434, 261);
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
				String number = textFieldNumber.getText();
				String type = textFieldtype.getText();
				Integer length = Integer.getInteger(textFieldLength.getText());
				Integer width = Integer.getInteger(textFieldWidth.getText());
				Field f = new Field(number, type, length, width);
				if (fCtr.insertField(f) > 0) {
					System.out.println("Field created ");
				} else {
					System.out.println("Field not created");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
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
		btnCreateT.setBounds(10, 11, 131, 23);
		panelTeam.add(btnCreateT);

		JButton btnAddPlayerT = new JButton("Add player");
		btnAddPlayerT.setBounds(10, 43, 131, 23);
		panelTeam.add(btnAddPlayerT);

		JButton btnAddManagerT = new JButton("Add manager");
		btnAddManagerT.setBounds(10, 77, 131, 23);
		panelTeam.add(btnAddManagerT);

		JButton btnAddTeamleaderT = new JButton("Add teamleader");
		btnAddTeamleaderT.setBounds(10, 111, 131, 23);
		panelTeam.add(btnAddTeamleaderT);

		JButton btnBackT = new JButton("Back");
		btnBackT.addActionListener(e -> {
			panelTeam.setVisible(false);
			panelMain.setVisible(true);
		});
		btnBackT.setBounds(10, 227, 89, 23);
		panelTeam.add(btnBackT);

		JButton btnRemovePersonT = new JButton("Remove person");
		btnRemovePersonT.setBounds(10, 145, 131, 23);
		panelTeam.add(btnRemovePersonT);

		JButton btnFindT = new JButton("Find");
		btnFindT.setBounds(10, 179, 131, 23);
		panelTeam.add(btnFindT);




**/
		

		/**
		 * MAIN MENU
		 */

	}
}
