/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author tung_nguyen
 */

public class CheckOptionFrame extends JFrameL
{
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private JMenu FileMenu, AccountMenu, TransactionMenu;
    private JMenuItem readFile, writeFile, addAccount, listTransactions,
    listChecks, listDeposits, listServiceCharges, listAccount, findAccount, addTransaction;
    private JMenuBar bar;
    public CheckOptionFrame(String title)
    {
        super (title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      


        FileMenu = new JMenu("File");
        MenuListener ml = new MenuListener();

        readFile = new JMenuItem("Read from file");
        readFile.addActionListener(ml);
        FileMenu.add(readFile);

        writeFile = new JMenuItem("Write to file");
        writeFile.addActionListener(ml);
        FileMenu.add(writeFile);

        AccountMenu = new JMenu("Account");

        addAccount = new JMenuItem("Add new Account");
        addAccount.addActionListener(ml);
        AccountMenu.add(addAccount);
        
        listAccount =new JMenuItem("List Accounts");
        listAccount.addActionListener(ml);
        AccountMenu.add(listAccount);

        listTransactions= new JMenuItem ("List accounts transaction");
        listTransactions.addActionListener(ml);
        AccountMenu.add(listTransactions);

        listChecks = new JMenuItem("List all checks");
        listChecks.addActionListener(ml);
        AccountMenu.add(listChecks);

        listDeposits=new JMenuItem("List all deposits");
        listDeposits.addActionListener(ml);
        AccountMenu.add(listDeposits);

        listServiceCharges=new JMenuItem("List all service charges");
        listServiceCharges.addActionListener(ml);
        AccountMenu.add(listServiceCharges);

        findAccount=new JMenuItem("Find an account");
        findAccount.addActionListener(ml);
        AccountMenu.add(findAccount);

        TransactionMenu=new JMenu("Transactions");

        addTransaction=new JMenuItem("Add Transactions");
        addTransaction.addActionListener(ml);
        TransactionMenu.add(addTransaction);

        bar = new JMenuBar();
        bar.add(FileMenu);
        bar.add(AccountMenu);
        bar.add(TransactionMenu);
        setJMenuBar(bar);

    }
    private class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String source = event.getActionCommand();
            if (source.equals("Read from file"))
                Main.readFile();
            else if (source.equals("Write to file")) {
                Main.writeFile();   
            }
            else if (source.equals("Add new Account") ) {
                Main.addAccount();
            }
            else if (source.equals("List Accounts"))
            {
                Main.listAccount();
            }
            else if (source.equals("List accounts transaction")) {
                Main.listTransactions();
            }
            else if (source.equals("List all checks")) {
                Main.listChecks();
            }
            else if (source.equals("List all deposits")) {
                Main.listDeposits();
            }
            else if (source.equals("List all service charges")) {
                Main.listServiceCharge();
            }
            else if (source.equals("Find an account")) {
                Main.findAccount();
            }
            else if (source.equals("Add Transactions"))
            {
                Main.enterTransactions();
            }
        }
    }




}
