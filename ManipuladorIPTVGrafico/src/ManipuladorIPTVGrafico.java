import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Action;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;

public class ManipuladorIPTVGrafico {

	private JFrame frame;
	private JTextField txtArquivoOrigem;
	private JTextField txtDiretorioDestino;
	//private JProgressBar prbProgresso;
	private final Action action = new SwingAction();
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		frame.setBounds(100, 100, 450, 297);
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
		panel01.setBounds(7, 7, 417, 62);
		frame.getContentPane().add(panel01);
		
		txtDiretorioDestino = new JTextField();
		txtDiretorioDestino.setText("C:\\Temp\\TesteIPTV\\");
		txtDiretorioDestino.setColumns(10);
		
		JLabel lblDiretorioDestino = new JLabel("Diret\u00F3rio de Destino:");
		
		JLabel lblArquivoOrigem = new JLabel("Arquivo de Origem:");
		
		txtArquivoOrigem = new JTextField();
		txtArquivoOrigem.setText("C:\\Temp\\TesteIPTV\\m3u-FelipeDani-plus.m3u");
		txtArquivoOrigem.setToolTipText("Teste");
		txtArquivoOrigem.setColumns(10);
		panel01.setLayout(new MigLayout("", "[99px][298.00px]", "[20px][20px]"));
		panel01.add(txtDiretorioDestino, "cell 1 1,growx,aligny top");
		panel01.add(lblDiretorioDestino, "cell 0 1,alignx left,aligny center");
		panel01.add(lblArquivoOrigem, "cell 0 0,alignx left,growy");
		panel01.add(txtArquivoOrigem, "cell 1 0,growx,aligny top");
		
		JPanel panel02 = new JPanel();
		panel02.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel02.setBounds(7, 77, 196, 71);
		frame.getContentPane().add(panel02);
		panel02.setLayout(new MigLayout("", "[188px]", "[23px][23px]"));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("M3U");
		buttonGroup.add(rdbtnNewRadioButton);
		panel02.add(rdbtnNewRadioButton, "cell 0 0,growx,aligny top");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("CSV");
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel02.add(rdbtnNewRadioButton_1, "cell 0 1,growx,aligny top");
		
		JPanel panel03 = new JPanel();
		panel03.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel03.setBounds(213, 77, 211, 71);
		frame.getContentPane().add(panel03);
		panel03.setLayout(null);
		

		
		JPanel panel04 = new JPanel();
		panel04.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel04.setBounds(7, 159, 417, 73);
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
				
				ManipuladorIPTVGrafico man = new ManipuladorIPTVGrafico();
				
			}
		});
		btnNewButton.setBounds(63, 23, 79, 23);
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
