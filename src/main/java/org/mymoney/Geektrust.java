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

        String filePath = args[0];
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        Portfolio portfolio = new Portfolio();
        while (sc.hasNextLine())
            PortfolioManagement.getUpdatedPortfolio(portfolio, sc.nextLine());
    }
}
