package getJSON;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SummonerProfile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblIsPlayerOnline;
	private JLabel lblPlayerLevel;
	Font uFont = new Font("Eras Bold ITC", Font.PLAIN, 15);
	Summoner player;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SummonerProfile frame = new SummonerProfile();
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
	public SummonerProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterYourSummoner = new JLabel("Enter your summoner name:");
		lblEnterYourSummoner.setForeground(Color.LIGHT_GRAY);
		lblEnterYourSummoner.setFont(uFont);
		lblEnterYourSummoner.setBounds(10, 13, 236, 15);
		contentPane.add(lblEnterYourSummoner);
		
		textField = new JTextField();
		textField.setBounds(256, 11, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				player = new Summoner(textField.getText());
				getPlayerOnline();
				setPlayerLevel();
			}
		});
		btnLoad.setFont(uFont);
		btnLoad.setBounds(355, 42, 89, 23);
		contentPane.add(btnLoad);
		
		lblPlayerLevel = new JLabel();
		lblPlayerLevel.setEnabled(false);
		lblPlayerLevel.setForeground(Color.LIGHT_GRAY);
		lblPlayerLevel.setFont(uFont);
		lblPlayerLevel.setBounds(10, 100, 160, 14);
		
		contentPane.add(lblPlayerLevel);
		
		lblIsPlayerOnline = new JLabel();
		lblIsPlayerOnline.setForeground(Color.LIGHT_GRAY);
		lblIsPlayerOnline.setFont(uFont);
		lblIsPlayerOnline.setBounds(10, 125, 202, 14);
		contentPane.add(lblIsPlayerOnline);
		
		JLabel background = new JLabel(new ImageIcon(makeBackgroundImage()));
		background.setLocation(0, 0);
		background.setSize(484, 311);
		contentPane.add(background);
	}
	private Image makeBackgroundImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/Aaron/Downloads/srMap.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img.getScaledInstance(500, 350, Image.SCALE_SMOOTH);
	}
	
	private void setPlayerLevel() {
		String level = player.pullData("playerAccountData", "summonerLevel");
		lblPlayerLevel.setText("Player level: " + level);
		lblPlayerLevel.setEnabled(true);
		
	}
	private void getPlayerOnline() {
		boolean isOnline = player.getPlayerStatus();
		lblIsPlayerOnline.setText("Is player online: " + isOnline);
		lblIsPlayerOnline.setEnabled(true);
	}
}
