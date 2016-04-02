package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CustomMazeDialog extends JDialog {
	
	Window parentWindow;

	public void setParent(Window w1){
		this.parentWindow = w1;
	}
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomMazeDialog dialog = new CustomMazeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomMazeDialog() {
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 884, 529);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblMazeDimension = new JLabel("Maze Dimension");
			lblMazeDimension.setBounds(10, 11, 139, 14);
			contentPanel.add(lblMazeDimension);
		}
		{
			JLabel label = new JLabel();
			label.setBounds(103, 11, 46, 14);
			contentPanel.add(label);
		}
		{
			JButton minusBtn = new JButton("-");
			minusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			minusBtn.setBounds(10, 36, 64, 23);
			contentPanel.add(minusBtn);
		}
		{
			JButton plusBtn = new JButton("+");
			plusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			plusBtn.setBounds(85, 36, 64, 23);
			contentPanel.add(plusBtn);
		}
		{
			JButton boxBtn = new JButton();
			boxBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			boxBtn.setIcon(new ImageIcon("Z:\\LPOO\\Maze\\res\\box.png"));
			boxBtn.setBounds(10, 184, 64, 69);
			contentPanel.add(boxBtn);
		}
		{
			JButton doorBtn = new JButton();
			doorBtn.setIcon(new ImageIcon("Z:\\LPOO\\Maze\\res\\door.png"));
			doorBtn.setBounds(85, 184, 64, 69);
			contentPanel.add(doorBtn);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(10, 405, 139, 51);
			contentPanel.add(btnCancel);
		}
		{
			JButton button = new JButton("OK");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(10, 467, 139, 51);
			contentPanel.add(button);
		}
		{
			JButton buttonNew = new JButton("New");
			buttonNew.setBounds(10, 70, 139, 23);
			contentPanel.add(buttonNew);
		}
		{
			JLabel label = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
			label.setBounds(10, 537, 195, 14);
			getContentPane().add(label);
		}
	}

}
