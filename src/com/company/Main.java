package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static final String INPUT_FILE = "stateVat.txt";
    private static final String OUTPUT_FILE = "vat-over-20.txt";

    public static void main(String[] args)
    {
        ReadWriteFile readWriteFile = new ReadWriteFile();

        readWriteFile.importFromFile(INPUT_FILE);

        //1.
        for (int i = 0; i < readWriteFile.size(); i++)
        {
            State state = readWriteFile.getState(i);
            //System.out.println(state);
        }

        //2.
        for (int i = 0; i < readWriteFile.size(); i++)
        {
            State state = readWriteFile.getState(i);
            float fullVat = readWriteFile.getState(i).getFullVat();
            boolean specialVat = readWriteFile.getState(i).isSpecialVat();

            if(fullVat > 20 && !specialVat)
            {
                readWriteFile.stateList.add(state);
                //System.out.println(state);
            }

            else
            {
                readWriteFile.smallerThanTwenty.add(state.getStateShortcut());
            }
        }

        //3.
        Collections.sort(readWriteFile.stateList);

        for (State state : readWriteFile.stateList)
        {
            System.out.println(state);
        }

        //4.
        System.out.println("=============================");
        System.out.println("Rate VAT 20 % or lower or using special rate: " + readWriteFile.smallerThanTwenty);

        readWriteFile.exportToFile(OUTPUT_FILE);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VAT: ");

        String enteredVat = scanner.nextLine();

        if(enteredVat.equals(""))
        {
            enteredVat = "20";
            for(State state : readWriteFile.allStatesList)
            {
                if(state.getFullVat() > 20)
                System.out.println(state);
                readWriteFile.customStateVatList.add(state);
            }
        }
        else
        {
            for(State state : readWriteFile.allStatesList)
            {
                if(state.getFullVat() > Integer.parseInt(enteredVat))
                {
                    System.out.println(state);
                    readWriteFile.customStateVatList.add(state);
                }
            }
        }
        readWriteFile.exportCustomVatToFile(enteredVat + ".txt");

    }
}
