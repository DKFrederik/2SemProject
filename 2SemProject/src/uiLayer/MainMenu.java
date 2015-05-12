package uiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnField = new JButton("Field");
		btnField.set {
			
		});
		btnField.setBounds(157, 51, 89, 23);
		contentPane.add(btnField);
		
		JButton btnTeam = new JButton("Team");
		btnTeam.setBounds(157, 98, 89, 23);
		contentPane.add(btnTeam);
		
		JButton btnTraning = new JButton("Training");
		btnTraning.setBounds(157, 143, 89, 23);
		contentPane.add(btnTraning);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(157, 207, 89, 23);
		contentPane.add(btnExit);
	}
}
