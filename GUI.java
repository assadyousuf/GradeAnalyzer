import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class GUI {

	private JFrame frame;
	private JTextField txtInputGrade;
	private JTextField txtMinGrade;
	private JTextField txtMaxGrade;
	private JTextField txtGradeToDelete;
	private ArrayList<Double> dataSet=new ArrayList<Double>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 810, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridwidth = 6;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		//tab 1 begins
		JCheckBox chckbxAppendGrade=new JCheckBox("Append Grade");
		JPanel DataEntryTab = new JPanel();
		tabbedPane.addTab("Data Entry", null, DataEntryTab, null);
		GridBagLayout gbl_DataEntryTab = new GridBagLayout();
		gbl_DataEntryTab.columnWidths = new int[]{161, 0, 106, 0};
		gbl_DataEntryTab.rowHeights = new int[]{21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_DataEntryTab.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_DataEntryTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		DataEntryTab.setLayout(gbl_DataEntryTab);
		
		JButton btnNewButton = new JButton("Upload Grade File");
		btnNewButton.addActionListener(new ActionListener() {
			final JFileChooser fc = new JFileChooser();
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					final JFileChooser fc = new JFileChooser();
	                if (e.getSource() == btnNewButton) {
	                    int returnVal = fc.showOpenDialog(frame);
	                    File file = fc.getSelectedFile();
	                    String fileExtention= file.getName().substring(file.getName().lastIndexOf('.') + 1);
	                    
	                    if(!chckbxAppendGrade.isSelected()) {
	                    	dataSet.clear();
	                    }
	                    
	                    if(fileExtention.charAt(0) == 'c') { //checks if it's a .csv file
	                    	ReadinDataSet(true, file);
	                    } else if(fileExtention.charAt(0) == 't') { //checks if its a .txt file
	                    	ReadinDataSet(false, file);
	                    }
	                }
				}
				
			}
		});
	
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		DataEntryTab.add(btnNewButton, gbc_btnNewButton);
		
		txtInputGrade = new JTextField();
		txtInputGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == txtInputGrade) {
					if(!chckbxAppendGrade.isSelected()) {
						dataSet.clear();
						dataSet.add(Double.parseDouble(txtInputGrade.getText()));
					} else if(chckbxAppendGrade.isSelected() ) {
						dataSet.add(Double.parseDouble(txtInputGrade.getText()));
					}
				}
			}
		});
		txtInputGrade.setText("Input Grade");
		GridBagConstraints gbc_txtInputGrade = new GridBagConstraints();
		gbc_txtInputGrade.insets = new Insets(0, 0, 5, 5);
		gbc_txtInputGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInputGrade.gridx = 1;
		gbc_txtInputGrade.gridy = 2;
		DataEntryTab.add(txtInputGrade, gbc_txtInputGrade);
		txtInputGrade.setColumns(10);
		
		
		chckbxAppendGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_chckbxAppendGrade = new GridBagConstraints();
		gbc_chckbxAppendGrade.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAppendGrade.gridx = 1;
		gbc_chckbxAppendGrade.gridy = 3;
		DataEntryTab.add(chckbxAppendGrade, gbc_chckbxAppendGrade);
		
		txtMinGrade = new JTextField();
		
		txtMinGrade.setText("Min Grade");
		GridBagConstraints gbc_txtMinGrade = new GridBagConstraints();
		gbc_txtMinGrade.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinGrade.gridx = 1;
		gbc_txtMinGrade.gridy = 4;
		DataEntryTab.add(txtMinGrade, gbc_txtMinGrade);
		txtMinGrade.setColumns(10);
		
		txtMaxGrade = new JTextField();
		txtMaxGrade.setText("Max Grade");
		GridBagConstraints gbc_txtMaxGrade = new GridBagConstraints();
		gbc_txtMaxGrade.insets = new Insets(0, 0, 5, 5);
		gbc_txtMaxGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxGrade.gridx = 1;
		gbc_txtMaxGrade.gridy = 5;
		DataEntryTab.add(txtMaxGrade, gbc_txtMaxGrade);
		txtMaxGrade.setColumns(10);
		
		JButton btnSetBounds = new JButton("Set Bounds");
		btnSetBounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSetBounds) {
					double max=Double.parseDouble(txtMaxGrade.getText());
					double min=Double.parseDouble(txtMinGrade.getText());
					EliminateMaxFromDataSet(dataSet, max );
					EliminateMinFromDataSet(dataSet, min);
				}
			}
		});
		GridBagConstraints gbc_btnSetBounds = new GridBagConstraints();
		gbc_btnSetBounds.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetBounds.gridx = 1;
		gbc_btnSetBounds.gridy = 6;
		DataEntryTab.add(btnSetBounds, gbc_btnSetBounds);
		
		txtGradeToDelete = new JTextField();
		txtGradeToDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == txtGradeToDelete) {
				Double gradeToDelete=Double.parseDouble(txtGradeToDelete.getText());
				DataAnalysis.deleteGrade(dataSet,gradeToDelete);
				}
			
			}
		});
		txtGradeToDelete.setText("Grade to Delete");
		GridBagConstraints gbc_txtGradeToDelete = new GridBagConstraints();
		gbc_txtGradeToDelete.insets = new Insets(0, 0, 5, 5);
		gbc_txtGradeToDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGradeToDelete.gridx = 1;
		gbc_txtGradeToDelete.gridy = 7;
		DataEntryTab.add(txtGradeToDelete, gbc_txtGradeToDelete);
		txtGradeToDelete.setColumns(10);
		
		//tab 2 begins
		JTextArea textArea = new JTextArea();
		JPanel AnalysisTab = new JPanel();
		tabbedPane.addTab("Analysis", null, AnalysisTab, null);
		GridBagLayout gbl_AnalysisTab = new GridBagLayout();
		gbl_AnalysisTab.columnWidths = new int[]{148, 132, 0};
		gbl_AnalysisTab.rowHeights = new int[]{29, 0, 0, 0, 0, 0};
		gbl_AnalysisTab.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_AnalysisTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		AnalysisTab.setLayout(gbl_AnalysisTab);
		
		JButton btnGenerateData = new JButton("Generate Data");
		btnGenerateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnGenerateData) {
					textArea.setText("");
					textArea.append("Number Of Enteries:" + DataAnalysis.getNumEntries(dataSet) + "\n");
					textArea.append("Highest Grade:" + DataAnalysis.getHighestGrade(dataSet) + "\n");
					textArea.append("Lowest Grade:" + DataAnalysis.getLowestGrade(dataSet) + "\n");
					textArea.append("Mean:" + DataAnalysis.getMean(dataSet) + "\n");
					textArea.append("Median:" + DataAnalysis.getMedian(dataSet) + "\n");
					if(DataAnalysis.getMode(dataSet).isEmpty()) {
						textArea.append("Mode:" + "\n");
					}
					else {
						textArea.append("Mode:" + DataAnalysis.getMode(dataSet) + "\n");
					}
				}
			}
		});
		GridBagConstraints gbc_btnGenerateData = new GridBagConstraints();
		gbc_btnGenerateData.gridwidth = 2;
		gbc_btnGenerateData.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerateData.anchor = GridBagConstraints.NORTH;
		gbc_btnGenerateData.gridx = 0;
		gbc_btnGenerateData.gridy = 0;
		AnalysisTab.add(btnGenerateData, gbc_btnGenerateData);
		
		JButton btnDisplayColumns = new JButton("Display Columns");
		btnDisplayColumns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDisplayColumns) {
					textArea.setText("");
					for(int i=0; i<dataSet.size(); i++) {
						
						textArea.append(Double.toString(dataSet.get(i)) + "\n");
					}
				}
			}
		});
		
		GridBagConstraints gbc_btnDisplayColumns = new GridBagConstraints();
		gbc_btnDisplayColumns.gridwidth = 2;
		gbc_btnDisplayColumns.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayColumns.gridx = 0;
		gbc_btnDisplayColumns.gridy = 1;
		AnalysisTab.add(btnDisplayColumns, gbc_btnDisplayColumns);
		
		JButton btnDisplayGraph = new JButton("Display Graph");
		btnDisplayGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnDisplayGraph = new GridBagConstraints();
		gbc_btnDisplayGraph.gridwidth = 2;
		gbc_btnDisplayGraph.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayGraph.gridx = 0;
		gbc_btnDisplayGraph.gridy = 2;
		AnalysisTab.add(btnDisplayGraph, gbc_btnDisplayGraph);
		
		JButton btnDistribution = new JButton("Distribution");
		btnDistribution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnDistribution = new GridBagConstraints();
		gbc_btnDistribution.gridwidth = 2;
		gbc_btnDistribution.insets = new Insets(0, 0, 5, 5);
		gbc_btnDistribution.gridx = 0;
		gbc_btnDistribution.gridy = 3;
		AnalysisTab.add(btnDistribution, gbc_btnDistribution);
		
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 4;
		AnalysisTab.add(textArea, gbc_textArea);
		
		//tab 3 begins
		JPanel ErrorsTab = new JPanel();
		tabbedPane.addTab("Errors", null, ErrorsTab, null);
		GridBagLayout gbl_ErrorsTab = new GridBagLayout();
		gbl_ErrorsTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_ErrorsTab.rowHeights = new int[]{0, 0, 0, 0};
		gbl_ErrorsTab.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_ErrorsTab.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		ErrorsTab.setLayout(gbl_ErrorsTab);
		
		JTextArea textArea_2 = new JTextArea();
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.gridheight = 3;
		gbc_textArea_2.gridwidth = 7;
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridx = 0;
		gbc_textArea_2.gridy = 0;
		ErrorsTab.add(textArea_2, gbc_textArea_2);
		
		JPanel ReportTab = new JPanel();
		tabbedPane.addTab("Report", null, ReportTab, null);
		GridBagLayout gbl_ReportTab = new GridBagLayout();
		gbl_ReportTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_ReportTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_ReportTab.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_ReportTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		ReportTab.setLayout(gbl_ReportTab);
		
		JButton btnNewButton_1 = new JButton("Generate Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 2;
		ReportTab.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Download");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 3;
		ReportTab.add(btnNewButton_2, gbc_btnNewButton_2);
	}
	
	public void ReadinDataSet(boolean fileType, File file) {
        if(fileType == true) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Scanner rd = new Scanner(br);
                
        
                rd.useDelimiter(",");
                while(rd.hasNext()) {
                	dataSet.add(Double.parseDouble(rd.next()));
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(fileType == false) {
        	try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Scanner rd = new Scanner(br);
                
                	rd.useDelimiter("\n");
                	 while(rd.hasNext()) {
                     	dataSet.add(Double.parseDouble(rd.next()));
                     }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public void EliminateMaxFromDataSet(ArrayList <Double> dataSet, double max) {
		for(int i=0; i<dataSet.size(); i++) {
			if(dataSet.get(i) > max) {
				dataSet.remove(i); //messes up ordering of checking 
				i=-1;
			}
		}
		
		
	}
	
	public void EliminateMinFromDataSet(ArrayList <Double> dataSet, double min) {
		
		for(int i=0; i<dataSet.size(); i++) {
			if(dataSet.get(i) < min) {
				dataSet.remove(i);  //messes up ordering of checking ex:80,81,82,85 First 80 is deleted and then 81 is placed at index 0 while i is at index 1 
				i=-1; //resets i to start from beggining of arraylist again
			}
		}
	}
	

}
