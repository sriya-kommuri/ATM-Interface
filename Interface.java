import java.awt.event.*;//for action listener
import java.io.*;//for file handling
import java.util.*;//for collections
import java.awt.*;//for fonts and colours
import javax.swing.*;//for gui

class Balance implements ActionListener 
{
	JFrame frame;
	JLabel label1,label2,label3;
	JPanel panel;
	JButton ok;
	Double bal=-1.0; // we have to change the number into string
	Font myFont = new Font("CALIBRI",Font.BOLD,17);
	Font myFont2 = new Font("CALIBRI",Font.BOLD,12);
	int id;

	Balance(int res,int min)
	{
		
		try
		{
			id=res;
			frame=new JFrame("Account Balance");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(350, 250);
			frame.setLayout(null);
			
			label1=new JLabel("Your Account Balance is:");
			label1.setFont(myFont);
			label1.setForeground(Color.black);
			
			label2=new JLabel();
			label2.setFont(myFont);
			label2.setForeground(Color.red);
			label1.setBounds(80, 30, 1000, 100);
			label2.setBounds(130, 60, 1000, 100);
			
			label3=new JLabel();
			label3.setFont(myFont);
			label3.setForeground(Color.red);
			
			if(min==1)
			{
				label3.setText("Minimum balance required is Rs 1500");
			}
			
			label3.setBounds(44, -4, 1000, 100);
			
			ok = new JButton("OK");
			ok.setFont(myFont2);
			ok.setForeground(Color.black);
			ok.setBounds(135, 150, 50, 30);

			
		FileInputStream f11 = new FileInputStream("login_pass.txt");
		DataInputStream d11 = new DataInputStream(f11);
		
		for(int i=0;i<4;i++)
		{
			int a=d11.readInt();
            int b = d11.readInt();
            double c = d11.readDouble();
            if(res==a)
            {
            	label2.setText(String.valueOf(c));
            	break;
            }
		}
		
		f11.close();
		d11.close();
		
		ok.addActionListener(this);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(ok);
		

		frame.setVisible(true);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		Main_Menu ob1=new Main_Menu(id);
	}

}


class DEPOSIT_WITHDRAW implements ActionListener{

	JFrame frame;
	JLabel text;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[6];
	JButton exitButton,dzButton,dotButton, entButton, delButton, clrButton;
	JPanel panel;
	
	Font myFont = new Font("CALIBRI",Font.BOLD,30);
	Font myFont2 = new Font("CALIBRI",Font.BOLD,20);
	Font myFont3 = new Font("CALIBRI",Font.BOLD,14);
	
	double num=-1;
	int res=-1;
	int operator,min=0;
	
	DEPOSIT_WITHDRAW(String st, int id, int var){
		res=id;
		operator = var;
		frame = new JFrame(st);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 370);
		frame.setLayout(null);
		
		text = new JLabel("ENTER THE AMOUNT YOU WANT TO "+st+":");
		text.setFont(myFont3);
		text.setBounds(50, 15, 300, 30);
		text.setForeground(Color.RED);
		
		textfield = new JTextField();
		textfield.setBounds(50, 40, 300, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);
		
		
		exitButton = new JButton("EXT");
		dzButton = new JButton("00");
		dotButton = new JButton(".");
		entButton = new JButton("ENT");
		delButton = new JButton("DEL");
		clrButton = new JButton("CLR");
		
		entButton.setBackground(Color.green);
		clrButton.setBackground(Color.yellow);
		delButton.setBackground(Color.red);
		
		functionButtons[0] = exitButton;
		functionButtons[1] = dzButton;
		functionButtons[2] = dotButton;
		functionButtons[3] = entButton;
		functionButtons[4] = delButton;
		functionButtons[5] = clrButton;
		
		for(int i =0;i<6;i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont2);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i =0;i<10;i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 200);
		panel.setLayout(new GridLayout(4,4,10,10));

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(clrButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(delButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(entButton);
		panel.add(dotButton);
		panel.add(numberButtons[0]);
		panel.add(dzButton);
		panel.add(exitButton);
		
		frame.add(panel);
		frame.add(text);
		frame.add(textfield);
		frame.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<10;i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		if(e.getSource()==dzButton) {
			textfield.setText(textfield.getText().concat(String.valueOf("00")));
		}
		if(e.getSource()==dotButton) {
			textfield.setText(textfield.getText().concat("."));
		}
		if(e.getSource()==exitButton)
		{
			frame.dispose();
			Main_Menu ob1=new Main_Menu((int) res);
		}
		if(e.getSource()==entButton) {
			try
			{
				num=Double.parseDouble(textfield.getText());
				FileInputStream f11 = new FileInputStream("login_pass.txt");
				DataInputStream d11 = new DataInputStream(f11);
				
				ArrayList<Integer> l_id=new ArrayList<Integer>();
				ArrayList<Integer> l_pass=new ArrayList<Integer>();
				ArrayList<Double> l_bal=new ArrayList<Double>();
				
				for(int i=0;i<4;i++)
				{
					int a=d11.readInt();
		            int b = d11.readInt();
		            double c = d11.readDouble();
		            l_id.add(a);
		            l_pass.add(b);
		            l_bal.add(c);
				}
				
				switch(operator) {
				case 0:
					for(int i=0;i<4;i++)
					{
						if(l_id.get(i)==res)
						{
							double z=l_bal.get(i);
							z+=num;
							l_bal.set(i,z);//replacing balance
							break;
						}
					}
					break;
				case 1:
					for(int i=0;i<4;i++)
					{
						if(l_id.get(i)==res)
						{
							double z=l_bal.get(i);
							z-=num;
							if(z<1500)
							{
								min=1;
							}
							else
							{
								l_bal.set(i,z);//replacing balance
							}
							break;
						}
					}
					break;
				}
				f11.close();
				d11.close();
                
				FileOutputStream f1 = new FileOutputStream("login_pass.txt");
				DataOutputStream d1 = new DataOutputStream(f1);
				for(int i=0;i<4;i++)
				{
					d1.writeInt(l_id.get(i));
					d1.writeInt(l_pass.get(i));	
					d1.writeDouble(l_bal.get(i));
				}
				f1.close();
				d1.close();
				frame.dispose();
				Balance ob4=new Balance(res,min);
				
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
			
		}
		if(e.getSource()==clrButton) {
			textfield.setText("");
		}
		if(e.getSource()==delButton) {
			String string = textfield.getText();
			textfield.setText("");
			for(int i=0;i<string.length()-1;i++) {
				textfield.setText(textfield.getText()+string.charAt(i));
			}
		}
	}
}

class Main_Menu implements ActionListener{
	
	JFrame frame;
	JRadioButton savingsbutton, currentbutton;
	JLabel title,txt, BANKNAME;
	JButton deposit, withdraw, cashbal, logout;
	JPanel panel;
	
	Font myFont = new Font("CALIBRI",Font.BOLD,12);
	Font myFont2 = new Font("CALIBRI",Font.BOLD,20);
	Font myFont3 = new Font("CALIBRI",Font.BOLD,15);
	Font myFont4 = new Font("CALIBRI",Font.BOLD,30);
	
	int id=-1;
	Main_Menu(int id1){
		id=id1;
		frame = new JFrame("MAIN MENU");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
        panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        frame.add(panel);
        panel.setLayout(null);
        
        BANKNAME = new JLabel("SRM BANK");
        BANKNAME.setFont(myFont4);
        BANKNAME.setBounds(130, 10, 200, 50);
        
        title = new JLabel("MAIN MENU");
        title.setFont(myFont2);
        title.setBounds(140, 50, 200, 50);
        	
        
        deposit = new JButton("Deposit");
        deposit.setFont(myFont);
        deposit.setBounds(25, 100, 100, 50);
        panel.add(deposit);
        
        withdraw = new JButton("Withdraw");
        withdraw.setFont(myFont);
        withdraw.setBounds(260, 100, 100, 50);
        
        cashbal = new JButton("Cash Balance");
        cashbal.setFont(myFont);
        cashbal.setBounds(25, 200, 100, 50);
        
        logout = new JButton("Cancel");
        logout.setFont(myFont);
        logout.setBounds(260, 200, 100, 50);
        
        txt = new JLabel("Select your account:");
        txt.setFont(myFont2);
        txt.setBounds(25, 270, 200, 50);
        
        
        savingsbutton = new JRadioButton("Savings Account");
        savingsbutton.setFont(myFont3);
        savingsbutton.setBounds(50, 300, 150, 50);
        
        currentbutton = new JRadioButton("Current Account");
        currentbutton.setFont(myFont3);
        currentbutton.setBounds(210, 300, 150, 50);
        
        ButtonGroup group = new ButtonGroup();
        group.add(savingsbutton);
        group.add(currentbutton);
        
        panel.add(BANKNAME);
        panel.add(txt);
        panel.add(savingsbutton);
        panel.add(currentbutton);
        panel.add(title);
        panel.add(withdraw);
        panel.add(cashbal);
        panel.add(logout);
        
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        cashbal.addActionListener(this);
        logout.addActionListener(this);
        savingsbutton.addActionListener(this);
        currentbutton.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == deposit)
        {
        	frame.dispose();
        	DEPOSIT_WITHDRAW ob2=new DEPOSIT_WITHDRAW("DEPOSIT",id,0);
        	
        }
        else if(e.getSource() == withdraw)
        {
        	frame.dispose();
        	DEPOSIT_WITHDRAW ob2=new DEPOSIT_WITHDRAW("WITHDRAW",id,1);
        }
        else if(e.getSource() == cashbal)
        {
        	frame.dispose();
        	Balance ob3=new Balance(id,0);
        }
        else if(e.getSource() == logout) {
        	frame.dispose();
        	login_page ob4=new login_page();
        }
        else if(e.getSource() == savingsbutton) {
        	System.out.println("Savings");
        }
        else if(e.getSource() == currentbutton) {
        	System.out.println("Current");
        }
    	
    }

}

class login_page implements ActionListener{

	JFrame frame;
	JPanel panel;
	JButton button;
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JLabel l1,l2,l3,l4;
	Font myFont1 = new Font("CALIBRI",Font.BOLD,14);
	Font myFont = new Font("CALIBRI",Font.BOLD,20);
	
	login_page()
	{
		frame = new JFrame("Login Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(430, 300);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		l3=new JLabel("SRM Bank");
		l3.setFont(myFont);
		l3.setForeground(Color.black);
		l3.setBounds(170,20,100,40);
		
		button=new JButton("Submit");
		button.setFocusable(false);
		button.setBounds(174,190,80,30);
		
		l1 = new JLabel("Account Number: ");
		l1.setFont(myFont1);
		l1.setBounds(40,80,120,30);
		l1.setForeground(Color.black);
		
		l2 = new JLabel("Enter Pin: ");
		l2.setFont(myFont1);
		l2.setBounds(40,120,100,30);
		l2.setForeground(Color.black);
		
		tf1.setBounds(160,83,150,24);
		tf2.setBounds(160,122,150,24);
		
		l4=new JLabel("");
		l4.setForeground(Color.black);
		
		panel=new JPanel();
		panel.setBounds(0,0, 430, 300);
		panel.setBackground(Color.white);
		
		button.addActionListener(this); 
		
		frame.add(button);	
		frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(l4);
		frame.add(panel);
		frame.add(tf1);
		frame.add(tf2);

	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button)
		{
			try
	    	{
	    		int id,id1=-1;
		    	int pass,p;
		    	double amt;
	    		FileInputStream fout=new FileInputStream("login_pass.txt");
	    		DataInputStream dout=new DataInputStream(fout);

	    		String st1=tf1.getText();
				String st2=tf2.getText();
				
				int a=Integer.parseInt(st1);  
				int b=Integer.parseInt(st2);
				int c=0;
				
				for(int i=0;i<=3;i++)
				{
					id=dout.readInt();
					pass=dout.readInt();
					amt=dout.readDouble();
					if((id==a)&&(pass==b))
					{
						id1=id;
						p=pass;
						c++;
						break;
					}
				}
				  
				if(c==1)
				{
					l4.setText("Login Successful...!!");
					l4.setBounds(162,155,1000,30);
					frame.dispose();
					Main_Menu ob1=new Main_Menu(id1);
				}
				else
				{
					l4.setText("Login Failed...!!");
					l4.setBounds(174,155,1000,30);
				}
	    		
	    	}
	    	catch(Exception ex)
	    	{
	    		System.out.println(ex);
	    	}
		}
	}

}

public class Interface 
{
	Interface()
	{ 
		try
		{
			FileOutputStream fout=new FileOutputStream("login_pass.txt");
			DataOutputStream dout=new DataOutputStream(fout);
			dout.writeInt(3700);
			dout.writeInt(1111);
			dout.writeDouble(10000.00);
			dout.writeInt(3750);
			dout.writeInt(2222);
			dout.writeDouble(15000.00);
			dout.writeInt(3840);
			dout.writeInt(3333);
			dout.writeDouble(17000.00);
			dout.writeInt(3870);
			dout.writeInt(4444);
			dout.writeDouble(19000.00);
			fout.close();
			dout.close();
			System.out.println("Input successfull");
		}
		catch(Exception ei)
		{
			System.out.println(ei);
		}
		login_page obj=new login_page();
		
	}
	public static void main(String[] args)
	{
		Interface ob=new Interface();
	}
}