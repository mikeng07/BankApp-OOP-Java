/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Tung Nguyen
 */
public class CheckingAccount extends Account implements Comparable <CheckingAccount>

{
private ArrayList<Transaction> transList;  
// keeps a list of Transaction objects for the account
 
private int transCount;   
// the count of Transaction objects and used as the ID for each transaction
private double depositCheck,depositCash;                          
private int checkNumber;   
private int flag; 
private double totalServiceCharge;
public CheckingAccount(String name,double initialBalance)
{
    super(name,initialBalance);
transCount =0;
totalServiceCharge = 0;
transList = new ArrayList<Transaction>(); //create object
}
public int getflag()
{
    return flag;
}

public void setBalance(double transAmt, int tCode, int checkNumber, double depositCheck, double depositCash)
{
if(tCode == 1)
{
    balance -= transAmt;
    if (balance<500)
        flag++;
    transList.add( new Check(1,transAmt,transCount,checkNumber)); 
    transCount++;
}
else if(tCode == 2)
{
    balance +=transAmt;
    transList.add( new Deposit(2,transAmt,transCount,depositCheck,depositCash));
    transCount++;
}

}
public double getBalance()
{
return balance;
}
public double getServiceCharge()
{
return totalServiceCharge;
}
public void setServiceCharge(double currentServiceCharge,int AssocNum)
        
{
totalServiceCharge = totalServiceCharge + currentServiceCharge;
transList.add( new ServiceCharge(3,currentServiceCharge,transCount,AssocNum));
transCount++;
}

//public void addTrans( Transaction newTrans) 
//{
// adds a transaction object to the transList
//}

 
public int gettransCount()  
{
//returns the current value of transCount;
return transCount;    
}

public Transaction getTrans(int i) 
{
    
    return transList.get(i);
// returns the i-th Transaction object in the list 
    
}

public int compareTo(CheckingAccount other)
{
    
   return (this.name).compareTo(other.name);
}

    
}

