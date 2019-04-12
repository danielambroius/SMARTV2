package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class TableViewer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static Object[][] data = {{1,2},{3,4}};
	static Object[] titles = {"first", "not first"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				
				try {
					TableViewer frame = new TableViewer(data, titles);
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
	public TableViewer(Object[][] dataToDislplay, Object[] header) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		 
		tableModel.setDataVector(dataToDislplay, header);
		table.setModel(tableModel);
		
		
		contentPane.add(new JScrollPane(table));
		
	}

}