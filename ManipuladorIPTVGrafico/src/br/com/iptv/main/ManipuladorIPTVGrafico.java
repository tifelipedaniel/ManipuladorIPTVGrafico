package br.com.iptv.main;
import br.com.iptv.download.*;
import br.com.iptv.manipulador.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Action;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;

public class ManipuladorIPTVGrafico {

	private JFrame frame;
	private JTextField txtArquivoOrigem;
	private JTextField txtArquivoDestino;
	//private JProgressBar prbProgresso;
	private final Action action = new SwingAction();
	private JTextField txtArquivoDrive;
	private JTextField txtLink;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManipuladorIPTVGrafico window = new ManipuladorIPTVGrafico();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManipuladorIPTVGrafico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Teste");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mnNewMenu.add(mntmNewMenuItem);
		frame.getContentPane().setLayout(null);
		
		JPanel panel01 = new JPanel();
		panel01.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel01.setBounds(7, 7, 417, 118);
		frame.getContentPane().add(panel01);
		
		txtArquivoDestino = new JTextField();
		txtArquivoDestino.setText("getsaida.xlsx");
		txtArquivoDestino.setColumns(10);
		
		JLabel lblArquivoDestino = new JLabel("Arquivo de Destino:");
		
		JLabel lblArquivoOrigem = new JLabel("Arquivo de Origem:");
		
		txtArquivoOrigem = new JTextField();
		txtArquivoOrigem.setText("get.php");
		txtArquivoOrigem.setToolTipText("Teste");
		txtArquivoOrigem.setColumns(10);
		panel01.setLayout(new MigLayout("", "[99px][298.00px,grow]", "[20px][20px][][]"));
		panel01.add(txtArquivoDestino, "cell 1 1,growx,aligny top");
		panel01.add(lblArquivoDestino, "cell 0 1,alignx left,aligny center");
		panel01.add(lblArquivoOrigem, "cell 0 0,alignx left,growy");
		panel01.add(txtArquivoOrigem, "cell 1 0,growx,aligny top");
		
		JLabel lblArquivoDestinoDrive = new JLabel("Arquivo Drive:");
		panel01.add(lblArquivoDestinoDrive, "cell 0 2,alignx left");
		
		txtArquivoDrive = new JTextField();
		txtArquivoDrive.setText("getsaidadrive.xlsx");
		panel01.add(txtArquivoDrive, "cell 1 2,growx");
		txtArquivoDrive.setColumns(10);
		
		JLabel lblLink = new JLabel("Link:");
		panel01.add(lblLink, "cell 0 3,alignx left");
		
		txtLink = new JTextField();
		txtLink.setText("http://natv.fm/get.php?username=3196255762&password=95679578831&type=m3u_plus&output=ts");
		panel01.add(txtLink, "cell 1 3,growx");
		txtLink.setColumns(10);
		
		JPanel panel03 = new JPanel();
		panel03.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel03.setBounds(7, 129, 417, 38);
		frame.getContentPane().add(panel03);
		panel03.setLayout(null);
		

		
		JPanel panel04 = new JPanel();
		panel04.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel04.setBounds(7, 170, 417, 73);
		frame.getContentPane().add(panel04);
		panel04.setLayout(null);
		
		JLabel lblProgresso = new JLabel("Processando");
		lblProgresso.setBounds(10, 11, 397, 14);
		panel04.add(lblProgresso);
		
		JProgressBar prbProgresso = new JProgressBar();
		prbProgresso.setBounds(10, 28, 397, 34);
		panel04.add(prbProgresso);
		
		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					lblProgresso.setText("Baixando arquivo...");
					Download downloadFile = new Download();
					downloadFile.downloadFile(txtLink.getText(), txtArquivoOrigem.getText());
				} catch (IOException e1) {
					lblProgresso.setText("Verificar erros.");
					JOptionPane.showMessageDialog(null, e1.toString());
				}
				
				try {
					lblProgresso.setText("Convertendo em Excel...");
					ManipuladorArquivo converter = new ManipuladorArquivo();
					converter.Principal(txtArquivoOrigem.getText(), txtArquivoDestino.getText());					
				} catch (Exception e2) {
					lblProgresso.setText("Verificar erros.");
					JOptionPane.showMessageDialog(null, e2.toString());
				}
				
//				OrdenaExcel ordenador = new OrdenaExcel();
//				ordenador.ordenarPlanilha(txtArquivoDestino.getText());
				

			}
		});
		btnNewButton.setBounds(169, 8, 79, 23);
		panel03.add(btnNewButton);
		btnNewButton.setAction(action);
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Gerar");
			putValue(SHORT_DESCRIPTION, "Gerar");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
