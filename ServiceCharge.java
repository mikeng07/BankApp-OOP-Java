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
public class ServiceCharge extends Transaction{
    
    private int associatedTransNumber;
    public ServiceCharge (int tId, double tAmt, int tCount, int associatedTransNumber)
    {
        super(tCount, tId, tAmt);
        this.associatedTransNumber=associatedTransNumber;
    }
    
    /**
     *
     * @return
     */
    public int getAssocNumber()
    {
        return associatedTransNumber;
    }
}
