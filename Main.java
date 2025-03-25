
package Main;
/**
 *
 * @author Tung Nguyen
 */

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.util.Vector;
import java.util.*;


public class Main
{
//global variables
//define a CheckingAccount object to keep trach of the
// account information.
 public static int flag;
 public static CheckOptionFrame frame;
 public static CheckingAccount a; 
 public static Vector<CheckingAccount>dataStore; 
 public static boolean saveflag = false;
 public static int AssocNum=0;
 public static String filename = "F\\test\\CheckingAccount.dat";
 public static JTextArea ta;

 
public static void main (String[] args)
{
// defines local variables





// get initial balance from the user
/*AccName = JOptionPane.showInputDialog("Enter the account name: ");
numstr=JOptionPane.showInputDialog("Enter your initial balance: ");
Balance=Double.parseDouble(numstr);
a= new CheckingAccount(AccName,Balance);
*/
dataStore= new Vector<CheckingAccount>();

frame = new CheckOptionFrame ("Checking Account operations ");
//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

ta = new JTextArea(50,100);
ta.setFont(new Font("Monospaced", Font.PLAIN,12));

frame.getContentPane().add(ta);
frame.pack();
frame.setVisible(true);


}

// method to get transaction code
// from the user
public static int getTransCode()
{
   int transcode;
   String numstr;
   String message;
   message="1 for check \n"
           +"2 for deposit\n"
           +"0 to end this session\n"
           +"Enter the tranansaction code: ";
   numstr = JOptionPane.showInputDialog(message);
   transcode=Integer.parseInt(numstr);
   return transcode;
}
public static int getcheckNumber()
{
    int checkNumber;
    String numstr;
    numstr=JOptionPane.showInputDialog("Enter the check number: ");
    checkNumber = Integer.parseInt(numstr);
    return checkNumber;
}
public static void addAccount()
{
    
    Double Balance;
String numstr, AccName;
AccName = JOptionPane.showInputDialog("Enter the account name: ");
numstr=JOptionPane.showInputDialog("Enter your initial balance: ");
Balance=Double.parseDouble(numstr);
a= new CheckingAccount(AccName,Balance);
dataStore.addElement(a); 
Collections.sort(dataStore);
//vector data will reference to object a
}


public static void listAccount()
{

        
        String message ="List of names of all accounts: \n";
        for ( int i=0;i<dataStore.size();i++)
        {
            message+=dataStore.get(i).getName();
            message+="\n";
        }
      
        ta.setText(message);
    
   
}
public static void findAccount()
{
  /* String name, message;
  
   
   name= JOptionPane.showInputDialog("Enter the account name: ");
    message ="Found account for "+ name;
    outerloop:
   for (int i = 0; i!= dataStore.size(); i++)
   {
   
       if (name.equals(dataStore.get(i).getName()))
       {
           ta.setText(message);
           a= dataStore.get(i);
           break outerloop;
       
       }
       else 
           ta.setText("No account found");
   }*/
     
   String name, message;
   int index;
   CheckingAccount test;
   name= JOptionPane.showInputDialog("Enter the account name: ");
   message ="Found account for "+ name;
   
   test=new CheckingAccount(name,0);
   index=Collections.binarySearch(dataStore, test);
   if(index>-1)
   {
       ta.setText(message);
       a= dataStore.get(index);
   }
   else
   {
       ta.setText("No account found");
   }
           
}


// method to get transaction amount
// from the user
public static double getTransAmt()
{
    double TransAmt;
    String numstr;
    numstr = JOptionPane.showInputDialog("Enter trans amount: ");
    TransAmt=Double.parseDouble(numstr);
    return TransAmt;

}



// Process the check in
public static void processCheck(double transAmt, int checkNum)
{
     String message;
     DecimalFormat fmt = new DecimalFormat ("0.00;(0.00)");
     AssocNum=a.gettransCount()-1;
     
    a.setServiceCharge(0.15,AssocNum);
 
    if (a.getBalance()<500&&flag==1)
         a.setServiceCharge(5,AssocNum);
    
    if (a.getBalance()<0)
        a.setServiceCharge(10,AssocNum);
    
    
    if (a.getBalance()>50&&flag==1)
    {
        message =a.getName()+"'s account\n"+
                "Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+
             "Service Charge: Below $500 --- charge $5.00"+"\n"+             
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
    }
       
        else if(a.getBalance()>0&&a.getBalance()<50&&flag==1)
        {
            message =a.getName()+"'s account\n"+"Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+
             "Service Charge: Below $500 --- charge $5.00"+"\n"+  
            " Warning: Balance below $50"+"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
        }
         else if(a.getBalance()<0&&flag==1)
    {
    message =a.getName()+"'s account\n"+"Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+
             "Service Charge: Below $500 --- charge $5.00"+"\n"+  
            " Warning: Balance below $50"+"\n"+
            "Service charge: Below $0 --- charge $10.00"+"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
    }
         else if(a.getBalance()<0&&flag!=1)
         {
             message =a.getName()+"'s account\n"+"Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance())+ "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+ 
            " Warning: Balance below $50"+"\n"+
            "Service charge: Below $0 --- charge $10.00"+"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
         }
         else if(a.getBalance()>0&&a.getBalance()<50&&flag!=1)
         {
         message =a.getName()+"'s account\n"+"Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+
            " Warning: Balance below $50"+"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
         }
    else
    
    {
        message =a.getName()+"'s account\n"+"Transaction: Check #"+checkNum+" in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Check --- charge $0.15" +"\n"+ 
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
    }
    
}

// process the deposit
public static void processDeposit(double depositCheck,double depositCash)
{
    DecimalFormat fmt = new DecimalFormat ("0.00;(0.00)");
    double transAmt = depositCheck+depositCash;
    AssocNum=a.gettransCount()-1;  //the transCount increase 1 when setbalance
                                   //which happen before process transaction.
    a.setServiceCharge(0.10,AssocNum);
    String message;
    if (a.getBalance()<50)
    {    
    message =a.getName()+"'s account\n"+"Transaction: Deposit in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Deosit --- charge $0.10" +"\n"+
            " Warning: Balance below $50"+"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
    }
    else 
    {
        message =a.getName()+"'s account\n"+"Transaction: Deposit in amount of $ " + fmt.format(transAmt) +"\n" +
             "Current Balance: $"+ fmt.format(a.getBalance()) + "\n"+
             "Service Charge: Deosit --- charge $0.10" +"\n"+
             "Total Service Charge: $"+fmt.format(a.getServiceCharge());
    JOptionPane.showMessageDialog(null, message);
    }
}

//input the transaction
public static void enterTransactions()
{
    
int transcode;
double transAmt=0, FinalBalance,depositCheck = 0,depositCash=0;
int checkNumber = 0;
String depositCheckstr,depositCashstr,message;
saveflag=false;
frame.setVisible(false);
    //get the transaction code from user
transcode=getTransCode();


//Check if user put invalid transaction code
if (transcode !=0&&transcode!=1&&transcode!=2)
    {
        JOptionPane.showMessageDialog(null, "Transcode is invalid, Please try again! ");
        transcode=getTransCode();

    }

// perform in a loop until the trans code = 0
// get the trans code from the user
// and process it with appropriate helper method
// When loop ends show final balance to user.

    

while (transcode!=0)
{
 if(transcode ==1)
 {
     checkNumber=getcheckNumber();
     transAmt=getTransAmt();
     a.setBalance(transAmt, transcode,checkNumber,0,0);
    
 }
 if (transcode ==2)
 {
   JTextField field1=new JTextField("");
   JTextField field2=new JTextField("");
   
   JPanel panel = new JPanel(new GridLayout(0,1));
   
   panel.add(new JLabel("Checks"));
   panel.add(field1);
   panel.add(new JLabel("Cash"));
   panel.add(field2);
 
   
   field1.addAncestorListener(new SetFocus());
   int result = JOptionPane.showConfirmDialog(null,panel,"Deposit",
           JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
   if (result == JOptionPane.OK_OPTION)
   {
       depositCheckstr=field1.getText();
       depositCashstr=field2.getText();
       
       depositCheck=Double.parseDouble(depositCheckstr);
       depositCash=Double.parseDouble(depositCashstr);
       
       
   }
   else{
       System.out.println("Cancelled");
       System.exit(0);
   }
   transAmt=depositCheck+depositCash;
    a.setBalance(transAmt, transcode,0,depositCheck,depositCash);
    
 }
//check for 1st time balance <$500
 if (a.getBalance()<500)
    flag=a.getflag();
 
 
 if (transcode==1)
    processCheck(transAmt,checkNumber);
 if (transcode==2)
    processDeposit(depositCheck,depositCash);
 
 transcode=getTransCode();
 
}


DecimalFormat fmt = new DecimalFormat ("0.00;(0.00)");



FinalBalance = a.getBalance()-a.getServiceCharge();

message="Transaction: End"+"\n"+
            " Current Balance: $"+fmt.format(a.getBalance())+"\n"+
            "Totala Service Charge: $"+fmt.format(a.getServiceCharge())+"\n"+
            "Final Balance: $"+fmt.format(FinalBalance)+"\n";
JOptionPane.showMessageDialog(null, message);
frame.setVisible(true);
}


public static void listTransactions()
{
    NumberFormat fm1= NumberFormat.getCurrencyInstance();
        String message ="";
        String message2="";
        int num;
       
        message+="Account: "+a.getName()+"\n"
                +"Balance: "+fm1.format(a.getBalance())+"\n"
                +"Total Service Charge: "+fm1.format(a.getServiceCharge())+"\n\n"
                +"List of all transactions: \n\n"
                +"ID           Type                    Amount\n";
        for(num=0;num<a.gettransCount();num++)
        {
        if (a.getTrans(num).getTransId()==1)
            message2="Check";
        if (a.getTrans(num).getTransId()==2)
            message2="Deposit"; 
        if (a.getTrans(num).getTransId()==3)
            message2="Svc. Chrg.";
        message +=String.format("%-2d           %-10s          %10s\n",num,message2,fm1.format(a.getTrans(num).getTransAmount()));
        }
       
        ta.setText(message);
       
     
        
}

    
public static void listChecks()
{
    NumberFormat fm1= NumberFormat.getCurrencyInstance();
       
        String message ="";
        int num;
        double Total=0;
        
        message+="Listing all Checks for "+a.getName()+"\n"+"ID     Check            Amount\n\n";
        for(num=0;num<a.gettransCount();num++)
        {
            
            if (a.getTrans(num).getTransId()==1)
            {message+=String.format("%-2d     %-5d   %15s\n",num,a.getTrans(num).getCheckNumber(),fm1.format(a.getTrans(num).getTransAmount()));
            Total+=a.getTrans(num).getTransAmount();
        }
        }
        message+="                        ------\n";
        message+="Total";
        message+=String.format("%25s",fm1.format(Total));
        ta.setText(message);
        
}

    
public static void listDeposits()
{
    NumberFormat fm1= NumberFormat.getCurrencyInstance();
        
        String message ="";
        int num;
        double Total =0;
        
        message+="Listing all deposits for "+a.getName()+"\n"+"ID     Type     Checks       Cash     Amount\n";
        for(num=0;num<a.gettransCount();num++)
        {
            if (a.getTrans(num).getTransId()==2)
            {message+=String.format("%-5d  %-7s  %-7s%10s %10s\n",num,"Deposit",fm1.format(a.getTrans(num).getCheck()),fm1.format(a.getTrans(num).getCash()),fm1.format(a.getTrans(num).getTransAmount()));
             Total+=a.getTrans(num).getTransAmount();
        }
        }
        message+="                                     -------\n";
        message+="Total";
        message+=String.format("%39s",fm1.format(Total));
        ta.setText(message);
        
        
}
public static void listServiceCharge()
{
NumberFormat fm1= NumberFormat.getCurrencyInstance();
       
        String message ="",message1="";
        int num;
        double Total=0;
       
        message+="List of Charges for "+a.getName()+"\n"+"      Assoc\n"+"ID    Trans   ServiceCharge       Amount\n";
        for(num=0;num<a.gettransCount();num++)
        {
            if (a.getTrans(num).getTransId()==3)
            {
                if (a.getTrans(num).getTransAmount()==0.10)
                    message1="Deposit Charge";
                else if(a.getTrans(num).getTransAmount()==0.15)
                    message1="Withdraw Charge";
                else if(a.getTrans(num).getTransAmount()==5)
                    message1="Under $500";
                else
                    message1="Under $0.00";
                
                message+=String.format("%-2d      %-2d    %-15s %10s\n",num,a.getTrans(num).getAssocNumber(),message1,fm1.format(a.getTrans(num).getTransAmount()));
                Total+=a.getTrans(num).getTransAmount();
            }
        }
        message+="                                   -----\n";
        message+="Total";
        message+=String.format("%35s",fm1.format(Total));
        
        ta.setText(message);
        
}

public static void readFile() 
   {  
        
        chooseFile(1);	
	try
		{
			FileInputStream fis = new
			    FileInputStream(filename);
			ObjectInputStream in = new
				       ObjectInputStream(fis);
                         dataStore = (Vector<CheckingAccount>)in.readObject();
                        

			in.close();

		}	
		catch(ClassNotFoundException e)	
                 { 
                     System.out.println(e);
                 }

                catch (IOException e) 
                 { 
                     System.out.println(e);
                 }
        saveflag=true;
   }
   public static void writeFile() 
   {  
    
               
        chooseFile(2);
      	try
		{
			FileOutputStream fos = new
			    FileOutputStream(filename);
			ObjectOutputStream out = new
				       ObjectOutputStream(fos);
                 
                        
                        out.writeObject(dataStore);
                       
                        
                        out.close();
		
		}	
	catch(IOException e)	
                { 
                     System.out.println(e);
                }
        saveflag=true;
 
   }
   public static void chooseFile(int ioOption) 
   {  
      int status, confirm;       
                
      String  message = "Would you like to use the current default file: \n" +
                          filename;
      confirm = JOptionPane.showConfirmDialog (null, message);
      if (confirm == JOptionPane.YES_OPTION)
          return;
      JFileChooser chooser = new JFileChooser();
      if (ioOption == 1)
          status = chooser.showOpenDialog (null);
      else
          status = chooser.showSaveDialog (null);
      if (status == JFileChooser.APPROVE_OPTION)
      {
          File file = chooser.getSelectedFile();
          filename = file.getPath();
      }
   }

private static class SetFocus implements AncestorListener{
    public void ancestorAdded (AncestorEvent e){
        JComponent component = e.getComponent();
        component.requestFocusInWindow();
    }
    public void ancestorMoved (AncestorEvent e){
        
    }
    public void ancestorRemoved(AncestorEvent e){
        
    }
}


}


