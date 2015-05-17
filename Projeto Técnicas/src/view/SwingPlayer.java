package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class SwingPlayer extends JFrame implements ActionListener{

	private JPanel contentPane;
	private final Action actBiblioteca = new SwingAction();
	private final Action actEstilos = new SwingAction_1();
	private final Action actPlaylists = new SwingAction_2();
	private final Action actFavoritos = new SwingAction_3();
	
	JButton btnFavoritos = new JButton("");
	JButton btnPlaylists = new JButton("");
	JButton btnEstilos = new JButton("");
	JButton btnBiblioteca = new JButton("");
	
	private ImageIcon iconFavoritos = new ImageIcon(getClass().getResource(
			"/icons/iconFavoritos.png"));
	private ImageIcon iconPlaylists = new ImageIcon(getClass().getResource(
			"/icons/iconPlaylist.png"));
	private ImageIcon iconBiblioteca = new ImageIcon(getClass().getResource(
			"/icons/iconBiblioteca.png"));
	private ImageIcon iconEstilos = new ImageIcon(getClass().getResource(
			"/icons/iconEstilos.png"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingPlayer frame = new SwingPlayer();
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
	public SwingPlayer() {
		
		super("Coffee Player");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Modificando as características dos botões da página Home
		
		btnPlaylists.setAction(actPlaylists);
		btnPlaylists.setIcon(iconPlaylists);
		btnPlaylists.setBounds(475, 194, 71, 59);
		
		btnEstilos.setAction(actEstilos);
		btnEstilos.setIcon(iconEstilos);
		btnEstilos.setBounds(475, 124, 71, 59);

		btnBiblioteca.setAction(actBiblioteca);
		btnBiblioteca.setIcon(iconBiblioteca);
		btnBiblioteca.setBounds(475, 54, 71, 59);
				
		btnFavoritos.setAction(actFavoritos);
		btnFavoritos.setIcon(iconFavoritos);
		btnFavoritos.setBounds(475, 264, 71, 59);
		
		// Adicionando ações aos botões da página Home
		
		btnBiblioteca.addActionListener(this);
		btnEstilos.addActionListener(this);
		btnPlaylists.addActionListener(this);
		btnFavoritos.addActionListener(this);
		
		
		// Label onde está conida a imagem da página Home
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 576, 399);
		label.setIcon(new ImageIcon("C:\\Users\\Thyelle\\Downloads\\imagem-fundo.JPG"));
		
		// Adicionando os botões e labels na Frame...
		// SetLayout: Absolute (por isso que o argumento do método setLayout é 'null')
		
		contentPane.setLayout(null);
		contentPane.add(btnPlaylists);
		contentPane.add(btnEstilos);
		contentPane.add(btnBiblioteca);
		contentPane.add(btnFavoritos);
		contentPane.add(label);
	}
	
	@Override
	// Método sobrescrito da interface ActionListener. Aqui será tratada 
	// as ações dos botões seleccionados.
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton button = (JButton) source;
			if (button == btnBiblioteca) {
				File f = biblioteca();
				System.out.println(f);
			} else if (button == btnEstilos) {
				System.out.println("Estilos");
			} else if (button == btnPlaylists){
				System.out.println("Playlists");
			} else if (button == btnFavoritos) {
				System.out.println("Favoritos");
			}
		}
		
	}
	
	
	private File biblioteca(){
		
		JFileChooser fileChooser = new JFileChooser();
		
		FileFilter mp3Only = new FileFilter(){
			public String getDescription() {
				return "Sound file (*.MP3)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".mp3");
				}
			}
		};
		
		fileChooser.setFileFilter(mp3Only);
		fileChooser.setDialogTitle("Coffee Player");
		fileChooser.showOpenDialog(btnBiblioteca);
		
		return fileChooser.getSelectedFile();
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			
			putValue(SHORT_DESCRIPTION, "Biblioteca");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {

			putValue(SHORT_DESCRIPTION, "Estilos");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {

			putValue(SHORT_DESCRIPTION, "Playlists");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			
			putValue(SHORT_DESCRIPTION, "Favoritos");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}

}