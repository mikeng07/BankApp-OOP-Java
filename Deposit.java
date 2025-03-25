/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author tung
 */
public class Deposit extends Transaction {
  private double DepositCash,DepositCheck;
public Deposit (int tId, double tAmt, int tCount, double DepositCheck, double DepositCash) 
{
super(tCount, tId, tAmt);
this.DepositCash=DepositCash;
this.DepositCheck=DepositCheck;
}

public double getCheck() {
return DepositCheck;
}

public double getCash()
{
    return DepositCash;
}}


