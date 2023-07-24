import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.security.spec.KeySpec;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class Server 
{
	static ArrayList<Filetype> files = new ArrayList<>();
	private static JPanel panel;
	private static ServerSocket serversocket;
	private static ServerSocket ss;
	private static Socket socket1;
	private static InputStream inputStream;
	private static DataInputStream dataInputStream;
	private static ServerSocket ss1;
	private static ServerSocket ss2;
	private static Socket socket2;
	private static DataInputStream dataInputStream11;
	private static Socket socket11 ;
	private static String message;
	private static String message1;
	private static DataInputStream ip;
	private static JLabel jlFileName;
	private static String ipaddr= "localhost";
	public static void main(String[] args) throws IOException
	{
		int fileid = 0;
		JFrame frame = new JFrame("Server");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(172, 176, 179));
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel title = new JLabel("Secure Data Communication Protocol Between Doctors and Hospital Server");
		title.setFont(new Font("Arial",Font.BOLD,30));
		title.setBorder(new EmptyBorder(20,0,10,0));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setForeground(Color.WHITE);

		JLabel server= new JLabel("Hospital Server");
		server.setFont(new Font("Arial", Font.BOLD, 25));
		server.setBorder(new EmptyBorder(20,0,10,0));
		server.setAlignmentX(Component.CENTER_ALIGNMENT);
		server.setForeground(Color.WHITE);

		JButton submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.setFont(new Font("Arial", Font.BOLD, 20));
		submit.setBackground(Color.WHITE);
		submit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "Submitted successfully!");	
				remaining();
			}
			private void remaining() 
			{
				try
				{
					socket1 = ss.accept();
			        inputStream = socket1.getInputStream();
		        	dataInputStream = new DataInputStream(inputStream);
		        	message = dataInputStream.readUTF();
		        	ss.close();
		        	socket1.close();
					//ipaddr = socket1.getInetAddress().toString();
					
		        	socket11 = ss1.accept();
		        	ip = new DataInputStream(socket11.getInputStream());
		        	message1 = ip.readUTF();
		        	ss1.close();
		        	socket11.close();
		        	int fid ;
		        	String fname;
		        	byte[] fcontent;
		        	String fext;
					Boolean bool = false;
					Boolean onetimeonly = false;
		        	for(int counter =0; counter<files.size();counter++)
		        	{
		        		if(files.get(counter).getname().equals(message))
		        		{
							bool = true;
		        			fid = files.get(counter).getid();
		        			fname = files.get(counter).getname();
		        			fcontent = files.get(counter).getdata();
		        			fext = files.get(counter).getfileextension();
		        			File filetodownload = new File(fname);
		        			try
		        			{	
			        			String line = null;
		        				FileOutputStream fileoutputstream = new FileOutputStream(filetodownload);
		        				fileoutputstream.write(fcontent);
		        				fileoutputstream.close();
		        				String filelines ="";
		        				FileReader fileReader = new FileReader(filetodownload);
		                    	BufferedReader bufferedReader = new BufferedReader(fileReader);
		                    	while((line = bufferedReader.readLine()) != null)
		                    	{
		                        	filelines+=line+"\n";
		                    	}
		                    	if(message1.equals("true"))
		                    	{
									if(!onetimeonly)
									{
										onetimeonly = true;
                                        File filebacksend = new File("filebacktoclient.txt");
                                        FileWriter fw = new FileWriter(filebacksend);
                                        fw.write(filelines);
                                        fw.close();
										Socket so= new Socket(ipaddr, 5555);
										OutputStream outputstream = so.getOutputStream();
										DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
                                        FileInputStream fis = new FileInputStream(filebacksend);
                                        dataoutputstream.writeLong(filebacksend.length());
                                        byte[] writebuffer = new byte[512];
                                        int readbytes = 0;
                                        while((readbytes = fis.read(writebuffer))>=0)
                                        {
                                            dataoutputstream.write(writebuffer, 0, readbytes);
                                            dataoutputstream.flush();
                                        }
                                        fis.close();
										dataoutputstream.close();
									}
		                    	}
		                    	else
		                    	{
			                    	JOptionPane.showMessageDialog(frame, "not authenticated");
		                    	}
		        			}
		        			catch(Exception ece)
		        			{
			        			JOptionPane.showMessageDialog(frame, ece.toString());
		        			}
		        		}
		        	}
					if (!bool)
					{
						JOptionPane.showMessageDialog(frame, "No such file");
					}
					socket2 = ss2.accept(); 
	        		dataInputStream11 = new DataInputStream(socket2.getInputStream());
	        		String message2 = dataInputStream11.readUTF();
	        		ss2.close();
	        		socket2.close();
					Boolean flag = false;
					for(int i =0; i<files.size();i++)
		        	{
		        		if(files.get(i).getname().equals(message2))
		        		{
							files.remove(files.get(i));
							JOptionPane.showMessageDialog(frame, "Deleted successfully!");
							flag = true;
						}
					}	
					if(!flag)
					{
						JOptionPane.showMessageDialog(frame, "There is no such file!");
					}
					else
					{
						JFrame smallframe = new JFrame("New Contents.");
						smallframe.setSize(400, 400);
						smallframe.setLayout(new BoxLayout(smallframe.getContentPane(),BoxLayout.Y_AXIS));
						smallframe.setLocationRelativeTo(null);
						smallframe.getContentPane().setBackground(new Color(172,176,179));

						JLabel smalltitle = new JLabel("New Contents of Server: ");
						smalltitle.setFont(new Font("Arial", Font.BOLD, 25));
						smalltitle.setAlignmentX(Component.CENTER_ALIGNMENT);
						smallframe.add(smalltitle);
						
						JLabel[] labels=new JLabel[files.size()];
        				for (int i=0;i<files.size();i++)
						{
            				labels[i]=new JLabel(files.get(i).getname());
							labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
							labels[i].setFont(new Font("Arial", Font.PLAIN,20));
							smallframe.add(labels[i]);
						}
						smallframe.validate();
						smallframe.setVisible(true);
						smallframe.setAlwaysOnTop(true);
					}
				}
				catch(Exception ecep)
				{
					ecep.printStackTrace();
				}
			}
			private String decrypt(String line) 
			{
				try{
				String secretkey = "computernetworks";
    			String saltvalue = "networks";
				byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				IvParameterSpec ivspec = new IvParameterSpec(iv);
				SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec keyspec = new PBEKeySpec(secretkey.toCharArray(), saltvalue.getBytes(), 65536, 256);
				SecretKey skey = skf.generateSecret(keyspec);
				SecretKeySpec skeyspec = new SecretKeySpec(skey.getEncoded(), "AES");
				Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
				decrypt.init(Cipher.DECRYPT_MODE, skeyspec, ivspec);
				return new String(decrypt.doFinal(Base64.getDecoder().decode(line)));
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				return null;
			}
		});
		frame.add(title);
		frame.add(server);
		frame.add(scroll);
		frame.add(submit);
		frame.setVisible(true);
		
		serversocket = new ServerSocket(1234);
		ss = new ServerSocket(7777);
		ss1 = new ServerSocket(6666);
		ss2 = new ServerSocket(6969);
		
		while (true) 
		{
            try 
			{
                Socket socket = serversocket.accept();
				JOptionPane.showMessageDialog(frame, "Connection from: "+socket.getInetAddress().toString());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int fileNameLength = dataInputStream.readInt();
                if (fileNameLength > 0) 
				{
                    byte[] fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);
                    int fileContentLength = dataInputStream.readInt();
                    if (fileContentLength > 0) 
					{
                        byte[] fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
						jpFileRow.setBackground(new Color(172, 176, 179));
                        jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10,0, 10,0));
						jlFileName.setForeground(Color.BLACK);
                        if (getFileExtension(fileName).equalsIgnoreCase("txt")) {
                            jpFileRow.setName((String.valueOf(fileid)));
                            jpFileRow.addMouseListener(getMyMouseListener());
                            jpFileRow.add(jlFileName);
                            panel.add(jpFileRow);
                        	frame.validate();
                        } else 
						{
                            jpFileRow.setName((String.valueOf(fileid)));
                            jpFileRow.addMouseListener(getMyMouseListener());
                            jpFileRow.add(jlFileName);
                            panel.add(jpFileRow);
                            frame.validate();
                        }
                        files.add(new Filetype(fileid, fileName, fileContentBytes, getFileExtension(fileName)));
                        fileid++;
                    }
                }
            } catch (IOException e) 
			{
                e.printStackTrace();
            }
		}
	}
	public static MouseListener getMyMouseListener()
	{
		return new MouseListener()
				{
					public void mouseClicked(MouseEvent e)
					{
						JOptionPane.showMessageDialog(panel, "Cannot access without authentication!");
					}
					public void mousePressed(MouseEvent e)
					{}
					public void mouseReleased(MouseEvent e)
					{}
					public void mouseEntered(MouseEvent e)
					{}
					public void mouseExited(MouseEvent e)
					{}
				};
	}
	public static String getFileExtension(String filename)
	{
		int i = filename.lastIndexOf('.');
		if(i > 0)
		{
			return filename.substring(i+1); 
		}
		else
		{
			return "No extention found";
		}
	}
}