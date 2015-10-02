package edu.Duquesne.Database.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextPane;

public class MenuGUI extends JFrame {
	static String buttonOne = "", buttonTwo = "", buttonThree = "", buttonFour = "", displayBoxText = "";
	private JPanel contentPane;

	public void setDisplayBox(String displayBoxText){
		this.displayBoxText = displayBoxText;
	}
	/**
	 * Create the frame.
	 */
	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 585);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 197, 189));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(MenuGUI.class.getResource("/edu/Duquesne/Database/GUI/DB_logo.png")));
		lblNewLabel_1.setBounds(6, 23, 603, 109);
		//lblNewLabel.setIcon(new ImageIcon(MenuGUI.class.getResource("/edu/Duquesne/Database/GUI/DB_logo.png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setBounds(231, 233, 61, 16);
		contentPane.add(label);

		JButton btnNewButton = new JButton(buttonOne);
		btnNewButton.setBackground(new Color(219, 217, 218));
		btnNewButton.setBounds(42, 200, 156, 58);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton(buttonTwo);
		btnNewButton.setBackground(new Color(219, 217, 218));
		button.setBounds(42, 280, 156, 58);
		contentPane.add(button);
		
		JButton button_1 = new JButton(buttonThree);
		btnNewButton.setBackground(new Color(219, 217, 218));
		button_1.setBounds(42, 362, 156, 58);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton(buttonFour);
		btnNewButton.setBackground(new Color(219, 217, 218));
		button_2.setBounds(42, 446, 156, 58);
		contentPane.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(235, 232, 237)));
		panel.setBounds(16, 162, 214, 382);
		panel.setBackground(new Color(149, 87,78));
		contentPane.add(panel);
		
		JTextPane displayBox = new JTextPane();
		displayBox.setBackground(new Color(219, 217, 218));
		displayBox.setBounds(251, 162, 335, 134);
		contentPane.add(displayBox);
	}
}
