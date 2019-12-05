import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.PrintGraphics;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.GraphicAttribute;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.event.ChangeListener;

import org.omg.PortableInterceptor.ServerRequestInfo;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frame;
	private JTextField txtInputGrade;
	private JTextField txtMinGrade;
	private JTextField txtMaxGrade;
	private JTextField txtGradeToDelete;
	private ArrayList<Double> dataSet=new ArrayList<Double>();
	private JTextArea UserHistoryTextArea = new JTextArea();
	private JTextArea textArea = new JTextArea();
	private String errorLogString="";
	private JTextArea textArea_1 = new JTextArea();
	private String analysis;
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
		
		frame = new JFrame("Grade Analyzer");
		frame.setBounds(100, 100, 1019, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex()==2) {
					textArea_1.setText("");
					textArea_1.append(errorLogString);
				}
			}
		});
		frame.getContentPane().add(tabbedPane);
		
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
	                    
	                    if(returnVal==JFileChooser.CANCEL_OPTION || returnVal==JFileChooser.ERROR_OPTION) {
	                    	UserHistoryTextArea.append("User did not choose file...\n");
	                    }
	                    
	                    File file = fc.getSelectedFile();
	                    String fileExtention= file.getName().substring(file.getName().lastIndexOf('.') + 1);
	                    
	                    if(!chckbxAppendGrade.isSelected()) {
	                    	UserHistoryTextArea.append("Wiping current user histroy..");
	                    	UserHistoryTextArea.setText("");
	                    	textArea.setText("");
	                    	dataSet.clear();
	                    	errorLogString="";
	                    }
	                    
	                    if(fileExtention.charAt(0) == 'c') { //checks if it's a .csv file
	                    	ReadinDataSet(true, file);
	                    } else if(fileExtention.charAt(0) == 't') { //checks if its a .txt file
	                    	ReadinDataSet(false, file);
	                    } 
	                    else {
	                    	UserHistoryTextArea.append("File user is trying to open is not .csv or .txt ...\n");
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
						dataSet.clear();}
					
						try { 
						double numAdded=Double.parseDouble(txtInputGrade.getText());
						dataSet.add(numAdded);
						UserHistoryTextArea.append("Adding " + numAdded + " to dataset...\n");
						}
						catch(Exception error) {
							UserHistoryTextArea.append("ERROR: Grade Inputed is not a number...\n");
							errorLogString=errorLogString+ "ERROR: Grade Inputed is not a number...\n";
						}
					 
						
					
				}
			}
		});
		
		JLabel lblInputGrade = new JLabel("Input Grade");
		GridBagConstraints gbc_lblInputGrade = new GridBagConstraints();
		gbc_lblInputGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputGrade.anchor = GridBagConstraints.EAST;
		gbc_lblInputGrade.gridx = 0;
		gbc_lblInputGrade.gridy = 2;
		DataEntryTab.add(lblInputGrade, gbc_lblInputGrade);
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
		
		JLabel lblMinGrade = new JLabel("Min Grade");
		GridBagConstraints gbc_lblMinGrade = new GridBagConstraints();
		gbc_lblMinGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGrade.anchor = GridBagConstraints.EAST;
		gbc_lblMinGrade.gridx = 0;
		gbc_lblMinGrade.gridy = 4;
		DataEntryTab.add(lblMinGrade, gbc_lblMinGrade);
		
		txtMinGrade = new JTextField();
		GridBagConstraints gbc_txtMinGrade = new GridBagConstraints();
		gbc_txtMinGrade.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinGrade.gridx = 1;
		gbc_txtMinGrade.gridy = 4;
		DataEntryTab.add(txtMinGrade, gbc_txtMinGrade);
		txtMinGrade.setColumns(10);
		
		JLabel lblMaxGrade = new JLabel("Max Grade");
		GridBagConstraints gbc_lblMaxGrade = new GridBagConstraints();
		gbc_lblMaxGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxGrade.anchor = GridBagConstraints.EAST;
		gbc_lblMaxGrade.gridx = 0;
		gbc_lblMaxGrade.gridy = 5;
		DataEntryTab.add(lblMaxGrade, gbc_lblMaxGrade);
		
		txtMaxGrade = new JTextField();
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
				
				if(e.getSource() == btnSetBounds && checkIfDatatSetIsEmpty() == false) {
					double max=0.0;
					double min=0.0;
					try {
					max=Double.parseDouble(txtMaxGrade.getText());}
					catch (Exception error) {
						errorLogString=errorLogString+"Max Bound is not a float or integer";
					}
					try {
					min=Double.parseDouble(txtMinGrade.getText());}
					catch (Exception error) {
						errorLogString=errorLogString+"Min Bound is not a float or integer";
					}
					UserHistoryTextArea.append("Setting new bounds\n");
					EliminateMaxFromDataSet(dataSet, max );
					EliminateMinFromDataSet(dataSet, min);
				}
				
				else if(checkIfDatatSetIsEmpty()==true) {
					UserHistoryTextArea.append("Cannot Set Bounds of an Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Set Bounds of an Empty dataset!\n";
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
				if(e.getSource() == txtGradeToDelete && checkIfDatatSetIsEmpty()==false) {
					Double gradeToDelete=0.0;
					try {
				 gradeToDelete=Double.parseDouble(txtGradeToDelete.getText());
					}
					catch(Exception erorr) {
						errorLogString=errorLogString+"Grade you are trying to delete is not a float or integer\n";
					}
					if(DataAnalysis.deleteGrade(dataSet,gradeToDelete) == true) {
				UserHistoryTextArea.append("Deleting Grade " + gradeToDelete + "...");
					} else {
						errorLogString=errorLogString+"The grade "+gradeToDelete + " is not in the dataset\n";
						UserHistoryTextArea.append("The grade "+gradeToDelete + " is not in the dataset\n");
					}
				} 
				else if(checkIfDatatSetIsEmpty()==true) {
					UserHistoryTextArea.append("Cannot Delete a grade from a Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Delete a grade from a Empty dataset!\n";
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
		JPanel AnalysisTab = new JPanel();
		tabbedPane.addTab("Analysis", null, AnalysisTab, null);
		GridBagLayout gbl_AnalysisTab = new GridBagLayout();
		gbl_AnalysisTab.columnWidths = new int[]{148, 0};
		gbl_AnalysisTab.rowHeights = new int[]{29, 0, 0, 0, 0, 0};
		gbl_AnalysisTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_AnalysisTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		AnalysisTab.setLayout(gbl_AnalysisTab);
		
		
		//tab 2 begins
		JScrollPane scrollPane_2 = new JScrollPane();
		
		
		JButton btnGenerateData = new JButton("Generate Data");
		btnGenerateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis="Number Of Entries:" + DataAnalysis.getNumEntries(dataSet) + "\n"+
						"Highest Grade:" + DataAnalysis.getHighestGrade(dataSet) + "\n"+
						"Mean:" + DataAnalysis.getMean(dataSet) + "\n"+
						"Median:" + DataAnalysis.getMedian(dataSet) + "\n";
				
				if(e.getSource() == btnGenerateData && checkIfDatatSetIsEmpty()==false) {
					UserHistoryTextArea.append("Generating Data..\n");
					textArea.setText("");
					textArea.append(analysis);
					if(DataAnalysis.getMode(dataSet).isEmpty()) {
						textArea.append("Mode:" + "\n");
					}
					else {
						textArea.append("Mode:" + DataAnalysis.getMode(dataSet) + "\n");
					}
				}
				else if(checkIfDatatSetIsEmpty()==true) {
					UserHistoryTextArea.append("Cannot Generate Data of an Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Generate Data of an Empty dataset!\n";
				}
			}
		});
		GridBagConstraints gbc_btnGenerateData = new GridBagConstraints();
		gbc_btnGenerateData.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerateData.anchor = GridBagConstraints.NORTH;
		gbc_btnGenerateData.gridx = 0;
		gbc_btnGenerateData.gridy = 0;
		AnalysisTab.add(btnGenerateData, gbc_btnGenerateData);
		
		JButton btnDisplayColumns = new JButton("Display Columns");
		btnDisplayColumns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDisplayColumns && checkIfDatatSetIsEmpty()==false) {
					textArea.setText("");
					UserHistoryTextArea.append("Displaying Columns..\n");
					for(int i=0; i<dataSet.size(); i++) {
						
						
						textArea.append(Double.toString(dataSet.get(i)) + "\n");
					}
				} else if(checkIfDatatSetIsEmpty() == true) {
					UserHistoryTextArea.append("Cannot Display Columns of an Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Display Columns of an Empty dataset!\n";
				}
			}
		});
		
		GridBagConstraints gbc_btnDisplayColumns = new GridBagConstraints();
		gbc_btnDisplayColumns.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisplayColumns.gridx = 0;
		gbc_btnDisplayColumns.gridy = 1;
		AnalysisTab.add(btnDisplayColumns, gbc_btnDisplayColumns);
		
		JButton btnDisplayGraph = new JButton("Display Graph");
		btnDisplayGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnDisplayGraph && checkIfDatatSetIsEmpty() == false) {
					textArea.setText("");
					UserHistoryTextArea.append("Displaying Graph...\n");
					textArea.append(displayGraph());
				} 
				else if(checkIfDatatSetIsEmpty() == true) {
					UserHistoryTextArea.append("Cannot Display Graph of an Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Display Graph of an Empty dataset!\n";
				}
			}
		});
		GridBagConstraints gbc_btnDisplayGraph = new GridBagConstraints();
		gbc_btnDisplayGraph.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisplayGraph.gridx = 0;
		gbc_btnDisplayGraph.gridy = 2;
		AnalysisTab.add(btnDisplayGraph, gbc_btnDisplayGraph);
		
		JButton btnDistribution = new JButton("Distribution");
		btnDistribution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnDistribution && checkIfDatatSetIsEmpty()==false) {
					textArea.setText("");
					UserHistoryTextArea.append("Displaying Distibution...\n");
					textArea.append(DataDistribution.displayDistrbution(dataSet));
				} else if(checkIfDatatSetIsEmpty()==true) {
					UserHistoryTextArea.append("Cannot Display Distribution of an Empty dataset!\n");
					errorLogString=errorLogString+"Cannot Display Distribution of an Empty dataset!\n";
				}
			}
			
		});
		GridBagConstraints gbc_btnDistribution = new GridBagConstraints();
		gbc_btnDistribution.insets = new Insets(0, 0, 5, 0);
		gbc_btnDistribution.gridx = 0;
		gbc_btnDistribution.gridy = 3;
		AnalysisTab.add(btnDistribution, gbc_btnDistribution);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 4;
		AnalysisTab.add(scrollPane_3, gbc_scrollPane_3);
		
		
		
		scrollPane_3.setViewportView(textArea);
		
		//tab 3 begins
		JPanel ErrorsTab = new JPanel();
		tabbedPane.addTab("Errors", null, ErrorsTab, null);
		GridBagLayout gbl_ErrorsTab = new GridBagLayout();
		gbl_ErrorsTab.columnWidths = new int[]{0, 0};
		gbl_ErrorsTab.rowHeights = new int[]{0, 0};
		gbl_ErrorsTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_ErrorsTab.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		ErrorsTab.setLayout(gbl_ErrorsTab);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		ErrorsTab.add(scrollPane_1, gbc_scrollPane_1);
		
		
		scrollPane_1.setViewportView(textArea_1);
		
		JPanel ReportTab = new JPanel();
		tabbedPane.addTab("Report", null, ReportTab, null);
		GridBagLayout gbl_ReportTab = new GridBagLayout();
		gbl_ReportTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_ReportTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_ReportTab.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_ReportTab.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		ReportTab.setLayout(gbl_ReportTab);
		
		JButton btnNewButton_2 = new JButton("Download");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_2) {
					DownloadReport();
					
				}
				
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 2;
		ReportTab.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(UserHistoryTextArea);
		
		JLabel lblUserHistory = new JLabel(" User History");
		scrollPane.setColumnHeaderView(lblUserHistory);
	}
	
	public void ReadinDataSet(boolean fileType, File file) {
        if(fileType == true) {
        	UserHistoryTextArea.append("Reading in the file " + file.getName() + "\n");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Scanner rd = new Scanner(br);
                
        
                rd.useDelimiter(",");
                while(rd.hasNext()) {
                	String numadded=rd.next();
                	try {
                	dataSet.add(Double.parseDouble(numadded));
                	UserHistoryTextArea.append("Adding " + numadded + "  to dataset..\n");
                	}
                	catch(Exception error) {
                		UserHistoryTextArea.append( numadded + " is not an integer or a float so it will not be added to the data set\n");
                		errorLogString=errorLogString + numadded + " is not an integer or a float so it will not be added to the data set\n";
                	}
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(fileType == false) {
        	UserHistoryTextArea.append("Reading in the file " + file.getName() + "\n");
        	try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Scanner rd = new Scanner(br);
                
                	rd.useDelimiter("\n");
                	 while(rd.hasNext()) {
                		 String numadded=rd.next();
                     	try {
                     	dataSet.add(Double.parseDouble(numadded));
                     	UserHistoryTextArea.append("Adding " + numadded + " to dataset..\n");
                     	}
                     	catch(Exception error) {
                     		UserHistoryTextArea.append( numadded + " is not an integer or a float so it will not be added to the data set\n");
                     		errorLogString=errorLogString + numadded + " is not an integer or a float so it will not be added to the data set\n";
                     	}
                     }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public void EliminateMaxFromDataSet(ArrayList <Double> dataSet, double max) {
		for(int i=0; i<dataSet.size(); i++) {
			if(dataSet.get(i) > max) {
				UserHistoryTextArea.append("Removing " + dataSet.get(i) + " from dataset\n" );
				dataSet.remove(i); //messes up ordering of checking 
				i=-1;
			}
		}
		
		
	}
	
	public void EliminateMinFromDataSet(ArrayList <Double> dataSet, double min) {
		
		for(int i=0; i<dataSet.size(); i++) {
			if(dataSet.get(i) < min) {
				UserHistoryTextArea.append("Removing " + dataSet.get(i) + " from dataset\n" );
				dataSet.remove(i);  //messes up ordering of checking ex:80,81,82,85 First 80 is deleted and then 81 is placed at index 0 while i is at index 1 
				i=-1; //resets i to start from beggining of arraylist again
			}
		}
	}
	
	
	public String displayGraph() {
		String graph="";
		if(dataSet.size()==0) {
			return null;
		}
		
		else {
			//0-10%
			graph = graph + "0->10%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 0 <= dataSet.get(i)  && dataSet.get(i)< 10) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			//10-20%
			graph=graph+"10->20%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 10 <= dataSet.get(i)  && dataSet.get(i)< 20) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
		
			

			//20-30%
			graph=graph+"20->30%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 20 <= dataSet.get(i)  && dataSet.get(i)< 30) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			
			
			
			
			//30-40%
			graph=graph+"30->40%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 30 <= dataSet.get(i)  && dataSet.get(i)< 40) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			//40-50%
			graph=graph+"40->50%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 40 <= dataSet.get(i)  && dataSet.get(i)< 50) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			//50-60%
			graph=graph+"50->60%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 50 <= dataSet.get(i)  && dataSet.get(i)< 60) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			//60-70%
			graph=graph+"60->70%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 60 <= dataSet.get(i)  && dataSet.get(i)< 70) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			
			

			//60-70%
			graph=graph+"70->80%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 70 <= dataSet.get(i)  && dataSet.get(i)< 80) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			
			
			
			//80-90%
			graph=graph+"80->90%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 80 <= dataSet.get(i)  && dataSet.get(i)< 90) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			//90-100%
			graph=graph+"90->100+%: ";
			for(int i=0; i<dataSet.size(); i++) {
				if( 90 <= dataSet.get(i)  && dataSet.get(i) <= 100) {
					graph=graph+"*";
				}
			}
			graph=graph+"\n";
			
			
			
			
			
			graph=graph+"\n";
			
			
			
			
			
			
			
		}
		
		
		return graph;
		
	}
	
	public boolean checkIfDatatSetIsEmpty() {
		if(dataSet.size()==0) {
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	
	public void DownloadReport() {
		
		if(checkIfDatatSetIsEmpty()==true) {
			UserHistoryTextArea.append("Cannot generate report for empty dataset!\n");
			return;
		}
		
		final JFileChooser fc = new JFileChooser();
		int returnVal=fc.showSaveDialog(frame);
		
		  
        if(returnVal==JFileChooser.CANCEL_OPTION || returnVal==JFileChooser.ERROR_OPTION) {
        	UserHistoryTextArea.append("User did not download the report file...\n");
        }
		
		
		File reportFile=fc.getSelectedFile();
		String report="Data:\n";
		
		String fileExtention= reportFile.getName().substring(reportFile.getName().lastIndexOf('.') + 1);
        
		analysis="Number Of Entries:" + DataAnalysis.getNumEntries(dataSet) + "\n"+
				"Highest Grade:" + DataAnalysis.getHighestGrade(dataSet) + "\n"+
				"Mean:" + DataAnalysis.getMean(dataSet) + "\n"+
				"Median:" + DataAnalysis.getMedian(dataSet) + "\n";
		
		 report="Data:\n" + "\n" + "Analysis:\n"+analysis + "\n"+  "Graph:\n" + displayGraph() + "Distribution:\n" + DataDistribution.displayDistrbution(dataSet); 
		
		if(fileExtention.charAt(0)=='t') {
			try {
				FileWriter fileWriter = new FileWriter(reportFile);
				fileWriter.write(report);
				fileWriter.close();
				UserHistoryTextArea.append("Saving " + reportFile.getName() + "\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				UserHistoryTextArea.append("Error with saving report\n");
			}
			
		} else {
			UserHistoryTextArea.append("Please choose a text file or give your report file a .txt extension\n");
		}
		
		
	}
	
	

}
