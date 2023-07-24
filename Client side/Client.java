import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.crypto.*;

public class Client
{
    private static final SecretKey[] s = new SecretKey[1];
    final static File[] file = new File[1];
    private static ArrayList<File> filearray = new ArrayList<File>();
    static JFileChooser filechooser;
    private static int otplen = 4;
    static String numbers = "0123456789";
    static Random rndm_method = new Random();
    private static String starttime;
    static char[] otp;
    static String filenamerequestaccess;
    static Socket socket12;
    static ServerSocket ss1 ;
    static String message;
	private static String ipaddr = "localhost";
	private static JLabel userlabel;
	private static JTextField usertext;
	private static JLabel passwordlabel;
	private static JPasswordField passwordtext;
	private static JButton button;
	private static JLabel success;
	public static void main(String[] args) throws Exception
	{
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userlabel = new JLabel("User");
		userlabel.setBounds(10,20,80,25);
		panel.add(userlabel);
		
		usertext = new JTextField(20);
		usertext.setBounds(100, 20, 125, 25);
		panel.add(usertext);
		
		passwordlabel = new JLabel("password");
		passwordlabel.setBounds(10,50,80,25);
		panel.add(passwordlabel);
		
		passwordtext = new JPasswordField(50);
		passwordtext.setBounds(100,50,125,25);
		panel.add(passwordtext);
		
	    button = new JButton("Login");
		button.setBounds(10,80,80,25);
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String user =usertext.getText(); 
				String password = passwordtext.getText();
				System.out.println(user+","+password);
				
				if (user.equals("madhvesh")&& password.equals("M@dhvesh2314")) 
				{
					JFrame mainframe = new JFrame("Client");
					mainframe.setSize(2000, 2000);
					mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainframe.setLayout(new BoxLayout(mainframe.getContentPane(), BoxLayout.Y_AXIS));
					mainframe.getContentPane().setBackground(new Color(172, 176, 179));

					JLabel projtitle = new JLabel("Secure Data Communication Protocol Between Doctors and Hospital Server");
					projtitle.setFont(new Font("Arial", Font.BOLD, 30));
					projtitle.setBorder(new EmptyBorder(20,0,10,0));
					projtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

					JLabel client = new JLabel("Client Side (Doctor's Application)");
					client.setFont(new Font("Arial", Font.BOLD, 25));
					client.setBorder(new EmptyBorder(20,0,10,0));
					client.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					JLabel label = new JLabel("What action are you going to perform");
					label.setFont(new Font("Arial", Font.BOLD, 25));
					label.setBorder(new EmptyBorder(20,0,10,0));
					label.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					JPanel mainpanel = new JPanel();
					mainpanel.setBorder(new EmptyBorder(75, 0, 10, 0));
					mainpanel.setAlignmentY(Component.CENTER_ALIGNMENT);
					mainpanel.setBackground(new Color(154, 154, 154));
					
					JButton add = new JButton("Add a new file to Server");
					add.setPreferredSize(new Dimension(300,75));
					add.setFont(new Font("Arial", Font.BOLD, 20));
					add.setFocusable(false);
					add.setBackground(Color.BLACK);
					add.setForeground(Color.WHITE);
					
					JButton del = new JButton("Delete a file in Server");
					del.setPreferredSize(new Dimension(300,75));
					del.setFont(new Font("Arial", Font.BOLD, 20));
					del.setFocusable(false);
					del.setBackground(Color.BLACK);
					del.setForeground(Color.WHITE);
					
					JButton acc = new JButton("Access file from Server");
					acc.setPreferredSize(new Dimension(300,75));
					acc.setFont(new Font("Arial", Font.BOLD, 20));
					acc.setFocusable(false);
					acc.setBackground(Color.BLACK);
					acc.setForeground(Color.WHITE);

					mainpanel.add(add);
					mainpanel.add(del);
					mainpanel.add(acc);
					
					add.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e) 
								{
									try
									{
										addnewpatient();
									}
									catch(Exception exception)
									{
										exception.printStackTrace();
									}
								}
							});
					
					acc.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e) 
								{
									accessfile();
								}
							});
					
					del.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e) 
								{
									deletefile();
								}
							});
					mainframe.add(projtitle);
					mainframe.add(client);
					mainframe.add(label);
					mainframe.add(mainpanel);
					mainframe.setVisible(true);
				}
				else
				{
					success.setText("Login Failed! TRY AGAIN...");
				}
			}
		});
		panel.add(button);
		
	    success = new JLabel("");
		success.setBounds(10,110,100,65);
		panel.add(success);
		
		
		
		frame.setVisible(true);
	}

		
	
	
	public static void deletefile()
	{
		JFrame frame = new JFrame("Client");
		frame.setSize(1000, 500);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(172, 176, 179));

		JLabel jlTitle = new JLabel("Secure Data Communication Protocol Between Doctors and Hospital Server");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel title = new JLabel("Delete Patient details files");
		title.setFont(new Font("Arial",Font.BOLD,25));
		title.setBorder(new EmptyBorder(20,0,10,0));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel filename = new JLabel("Choose a file: ");
		filename.setFont(new Font("Arial",Font.BOLD,20));
		filename.setBorder(new EmptyBorder(50,0,0,0));
		filename.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins1 = new JLabel("-> Enter the name of the file ");
		ins1.setFont(new Font("Arial",Font.BOLD,20));
		ins1.setBorder(new EmptyBorder(50,0,0,0));
		ins1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttons = new JPanel();
		buttons.setBorder(new EmptyBorder(75,0,10,0));
		buttons.setBackground(new Color(154, 154, 154));
		
		JButton choosefile = new JButton("Delete");
		choosefile.setPreferredSize(new Dimension(150,75));
		choosefile.setFont(new Font("Arial", Font.BOLD, 20));
		choosefile.setFocusable(false);
		choosefile.setBackground(Color.BLACK);
		choosefile.setForeground(Color.WHITE);		
		
		buttons.add(choosefile);
		
		choosefile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String filenamedelete = JOptionPane.showInputDialog("Enter the name of the file: ");
					int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete "+filenamedelete+"\nNote: Access the file before deleting it.");
					if(option == 0)
					{
						Socket socket = new Socket(ipaddr, 6969);
						OutputStream outputstream = socket.getOutputStream();
						DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
						dataoutputstream.writeUTF(filenamedelete);
						dataoutputstream.flush();
						dataoutputstream.close();
						int option1 = JOptionPane.showConfirmDialog(frame, "Do you want remove the file permenantly?");
						if(option1==0)
						{
						File dir = new File("D:\\javatextfiles");
							File[] directoryListing = dir.listFiles();
							if (directoryListing != null) {
								for (File child : directoryListing) 
								{
									if(child.getName().equals(filenamedelete))
									{
										child.delete();
									}
								}
							}
						}
					}
				}
				catch(IOException ioexp)
				{
					ioexp.printStackTrace();
				}
			}
		});
		
		frame.add(jlTitle);
		frame.add(title);
		frame.add(filename);
		frame.add(buttons);
		frame.setVisible(true);
	}
	
	public static void accessfile()
	{
		JFrame frame = new JFrame("Client");
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.getContentPane().setBackground(new Color(172, 176, 179));

		JLabel jlTitle = new JLabel("Secure Data Communication Protocol Between Doctors and Hospital Server");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel title = new JLabel("Access Patient details files");
		title.setFont(new Font("Arial",Font.BOLD,25));
		title.setBorder(new EmptyBorder(20,0,10,0));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel filename = new JLabel("Choose a file: ");
		filename.setFont(new Font("Arial",Font.BOLD,20));
		filename.setBorder(new EmptyBorder(50,0,0,0));
		filename.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins1 = new JLabel("-> Click choose file and enter the name of the file.");
		ins1.setFont(new Font("Arial",Font.BOLD,20));
		ins1.setBorder(new EmptyBorder(50,0,0,0));
		ins1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins2 = new JLabel("-> Generate the OTP");
		ins2.setFont(new Font("Arial",Font.BOLD,20));
		ins2.setBorder(new EmptyBorder(50,0,0,0));
		ins2.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins3 = new JLabel("-> Enter the OTP within 10 seconds to authenticate successfully!");
		ins3.setFont(new Font("Arial",Font.BOLD,20));
		ins3.setBorder(new EmptyBorder(50,0,0,0));
		ins3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buttons = new JPanel();
		buttons.setBorder(new EmptyBorder(75,0,10,0));
		buttons.setBackground(new Color(154, 154, 154));
		
		JButton generateotp = new JButton("Generate OTP");
		generateotp.setPreferredSize(new Dimension(250,75));
		generateotp.setFont(new Font("Arial", Font.BOLD, 20));
		generateotp.setFocusable(false);
		generateotp.setBackground(Color.BLACK);
		generateotp.setForeground(Color.WHITE);
		
		JButton enterotp = new JButton("Enter OTP");
		enterotp.setPreferredSize(new Dimension(150,75));
		enterotp.setFont(new Font("Arial", Font.BOLD, 20));
		enterotp.setFocusable(false);
		enterotp.setBackground(Color.BLACK);
		enterotp.setForeground(Color.WHITE);
		
		JButton choosefile = new JButton("Choose File");
		choosefile.setPreferredSize(new Dimension(150,75));
		choosefile.setFont(new Font("Arial", Font.BOLD, 20));
		choosefile.setFocusable(false);
		choosefile.setBackground(Color.BLACK);
		choosefile.setForeground(Color.WHITE);
		
		buttons.add(generateotp);
		buttons.add(enterotp);
		buttons.add(choosefile);
		
		choosefile.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						try
						{
							filenamerequestaccess = JOptionPane.showInputDialog("Enter the name of the file you want to access: ");
							Socket socket = new Socket(ipaddr, 7777);
							OutputStream outputstream = socket.getOutputStream();
							DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
							dataoutputstream.writeUTF(filenamerequestaccess);
							dataoutputstream.flush();
							dataoutputstream.close();
							JOptionPane.showMessageDialog(frame, "Generate the OTP and authenticate.");
						}
						catch(IOException ioexp)
						{
							ioexp.printStackTrace();
						}
					}
				});
		
		generateotp.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						otp = new char[otplen];
				        for (int i = 0; i < otplen; i++)
				        {
				            otp[i] =numbers.charAt(rndm_method.nextInt(numbers.length()));
				        }
				        JOptionPane.showMessageDialog(frame, "Your OTP is: "+toString(otp)+"\nEnter the OTP within 10 seconds");
				        starttime = Long.toString(System.currentTimeMillis());
				        new Reminder(10);
					}
					public static String toString(char[] a)
				    {
				        String string = new String(a);
				        return string;
				    }
				});
		
		enterotp.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						try
						{
							String inputotpstring = JOptionPane.showInputDialog(frame, "Enter the otp: "); 
							String originalotpstring = toString(otp);
							Long originalotp = Long.parseLong(originalotpstring);
							Long inputotp = Long.parseLong(inputotpstring);
							message = "false";
							if(originalotp.equals(inputotp))
							{
								message = "true";
								socket12 = new Socket(ipaddr, 6666);
								OutputStream outputstream = socket12.getOutputStream();
								DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
								dataoutputstream.writeUTF(message);
								dataoutputstream.flush();
								dataoutputstream.close();
								socket12.close();

								ServerSocket ss = new ServerSocket(5555);
								Socket socket1 = ss.accept();
			        			InputStream inputStream = socket1.getInputStream();
		        				DataInputStream dataInputStream = new DataInputStream(inputStream);
                                int readbytes = 0;
                                FileOutputStream fos = new FileOutputStream("Receivedfromserver.txt");
                                long filesize = dataInputStream.readLong();
                                byte[] writebuffer = new byte[512];
                                while((filesize>0) && (readbytes = dataInputStream.read(writebuffer, 0, (int)Math.min(writebuffer.length, filesize)))>=0)
                                {
                                    fos.write(writebuffer, 0, readbytes);
                                    filesize = filesize - readbytes;
                                }
                                fos.close();
		        				ss.close();
		        				socket1.close();
                                //encanddec.fun("null", "Receivedfromserver.txt", "dec.txt", s[0]);
                                for(int i =0 ;i<filearray.size();i++)
                                {
                                    if(filenamerequestaccess.equals(filearray.get(i).getName()))
                                    {
                                        encanddec.copyContent(filearray.get(i), new File("dec.txt"));
                                        break;
                                    }
                                }
                                String filedata = "";
                                FileReader fr = new FileReader("dec.txt");
                                int i;
                                while ((i = fr.read()) != -1)
                                    filedata=filedata+(char)i;
                                
                                //decryption(new FileInputStream("Receivedfromserver.txt"), new FileOutputStream("decryptedfiledata.txt"));

								JFrame fileframe = new JFrame(filenamerequestaccess);
		                    	fileframe.setSize(2000, 2000);
		                    	fileframe.setLayout(null);
								fileframe.getContentPane().setBackground(new Color(172, 176, 179));
									
		                    	JTextArea textarea = new JTextArea(filedata);
								textarea.setBackground(Color.BLACK);
  								textarea.setFont(new Font("Arial",Font.PLAIN,20));
								textarea.setForeground(Color.WHITE);

								JPanel filepanel = new JPanel();
								filepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
								filepanel.setBounds(10, 30, 1500, 1500);
								filepanel.setBackground(new Color(172,176,179));

		                    	JScrollPane sp = new JScrollPane(filepanel);
		                    	sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
									
								filepanel.add(textarea);
								fileframe.add(filepanel);
		                    	fileframe.add(sp);

		                    	fileframe.setVisible(true);
								fileframe.setAlwaysOnTop(true);
		                    	fileframe.validate();
							}
							else
							{
								message = "false";
								JOptionPane.showMessageDialog(frame, "failed");
								socket12 = new Socket(ipaddr, 6666);
								DataOutputStream os = new DataOutputStream(socket12.getOutputStream());
								os.writeUTF(message);
								os.flush();
								os.close();
								socket12.close();
							}
						}
						catch(Exception error)
						{
							JOptionPane.showMessageDialog(frame, "Failed to Authenticate :(\n"+error.toString());
						}
					}
                    public static String toString(char[] a)
				    {
				        String string = new String(a);
				        return string;
				    }
				});
		frame.add(jlTitle);
		frame.add(title);
		frame.add(filename);
		frame.add(ins1);
		frame.add(ins2);
		frame.add(ins3);
		frame.add(buttons);
		frame.setVisible(true);
	}
	public static void addnewpatient() throws Exception
	{
		JFrame addFrame = new JFrame("Client");
        addFrame.setSize(1000, 1000);
		addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
		addFrame.getContentPane().setBackground(new Color(172, 176, 179));
		addFrame.setLocationRelativeTo(null);
        
        JLabel jlTitle = new JLabel("Secure Data Communication Protocol Between Doctors and Hospital Server");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jlFileName = new JLabel("");
        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins1 = new JLabel("-> Choose a file that you want to send.");
        ins1.setFont(new Font("Arial", Font.BOLD, 20));
        ins1.setBorder(new EmptyBorder(50, 0, 0, 0));
        ins1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins2 = new JLabel("-> Click send button to store the file in the server.");
        ins2.setFont(new Font("Arial", Font.BOLD, 20));
        ins2.setBorder(new EmptyBorder(50, 0, 0, 0));
        ins2.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ins3 = new JLabel("-> Click submit once all files are loaded.");
        ins3.setFont(new Font("Arial", Font.BOLD, 20));
        ins3.setBorder(new EmptyBorder(50, 0, 0, 0));
        ins3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(75, 0, 10, 0));
		buttonsPanel.setBackground(new Color(154, 154, 154));
        
		JButton send = new JButton("Send File");
        send.setPreferredSize(new Dimension(150, 75));
		send.setBackground(Color.BLACK);
		send.setForeground(Color.WHITE);
        send.setFont(new Font("Arial", Font.BOLD, 20));
		send.setFocusable(false);
        
		JButton choose = new JButton("Choose File");
        choose.setPreferredSize(new Dimension(150, 75));
		choose.setBackground(Color.BLACK);
		choose.setForeground(Color.WHITE);
        choose.setFont(new Font("Arial", Font.BOLD, 20));
		choose.setFocusable(false);

        buttonsPanel.add(send);
        buttonsPanel.add(choose);

        choose.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File("D:\\javatextfiles"));
                filechooser.setDialogTitle("Choose a file to send.");
                if (filechooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) 
				{
                    file[0] = filechooser.getSelectedFile();
                    jlFileName.setText("The file you want to send is: " + file[0].getName());
                }
            }
        });

        send.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                if (file[0] == null) 
				{
					JOptionPane.showMessageDialog(addFrame, "No file selected.");
                    jlFileName.setText("Please choose a file to send first!");
                } else 
				{
                    try 
					{
                        try 
						{
                            filearray.add(file[0]);
                            encanddec.copyContent(file[0], new File("data.txt"));
                            s[0] = encanddec.fun("data.txt", "encrypted.txt", "decrypt.txt", null);
                        } catch (Exception e1) 
						{
                            e1.printStackTrace();
                        }
						File filee = new File("encrypted.txt");
                        FileInputStream fileInputStream = new FileInputStream(filee.getAbsolutePath());
                        Socket socket = new Socket(ipaddr, 1234);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String fileName = file[0].getName();
                        byte[] fileNameBytes = fileName.getBytes();
                        byte[] fileBytes = new byte[(int)filee.length()];
                        fileInputStream.read(fileBytes);
                        dataOutputStream.writeInt(fileNameBytes.length);
                        dataOutputStream.write(fileNameBytes);
                        dataOutputStream.writeInt(fileBytes.length);
                        dataOutputStream.write(fileBytes);
                    } catch (IOException ex) 
					{
                        ex.printStackTrace();
                    }
                }
            }
            
        });
        addFrame.add(jlTitle);
        addFrame.add(jlFileName);
		addFrame.add(ins1);
		addFrame.add(ins2);
		addFrame.add(ins3);
        addFrame.add(buttonsPanel);
        addFrame.setVisible(true);
	}
}