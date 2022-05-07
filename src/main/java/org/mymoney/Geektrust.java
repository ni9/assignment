package org.mymoney;

import org.mymoney.Impl.Entity.Portfolio;
import org.mymoney.Impl.Service.PortfolioManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Assignment to cover mymoney application!
 *
 */
public class Geektrust
{
    public static void main( String[] args ) throws IOException {
        // System.out.println( "Hello World!" );

        String filePath = args[0];
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        Portfolio portfolio = new Portfolio();
        while (sc.hasNextLine())
            PortfolioManagement.getUpdatedPortfolio(portfolio, sc.nextLine());


        // initialise portfolio.

        // read file and retain them as list of strings.

        // loop the strings and call:
            // portfolio = OperationsHandler.executeOperation(portfolio, string)
                // operation = OperationsHandler.evaluateOperation(string)
                // Switch (operation):
                    // Case "ALLOCATE"
                        // AllocateOperationManager.initialise(string)
                        // AllocationOperationManager.fetchValues()
                        // ChangeOperationManager.update()               -- not part of requirement at this point in time.

                    // Case "SIP"
                        // SipOperationManager.initialise(string)
                        // SipOperationManager.fetchValues()
                        // ChangeOperationManager.update()               -- not part of requirement at this point in time.

                    // Case "CHANGE"
                        // ChangeOperationManager.initialise(string)
                        // ChangeOperationManager.fetchValues()
                        // ChangeOperationManager.update()               -- updating hashMap by appending monthly change rate.

                    // Case "BALANCE"
                        // BalanceOperationManager.initialise(string)    -- Post initialisation of first [Allocate && change].
                        // BalanceOperationManager.fetchValues()
                        // BalanceOperationManager.update()              -- updating hashMap by appending monthly balance.

                    // Case "REBALANCE"
                        // ReBalanceOperationManager.initialise(string)
                        // ReBalanceOperationManager.fetchValues()
                        // BalanceOperationManager.update()              -- updating last month's calculated values via initial percentages(June/Dec).

                // in-case of the BALANCE and REBALANCE, print the values.
                // simply return to the calling function.
    }
}

// use enums, interfaces, error handling, tests, optimise implementation later.