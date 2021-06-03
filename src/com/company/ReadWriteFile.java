package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFile
{
    ArrayList<State> allStatesList = new ArrayList<>();
    ArrayList<State> stateList = new ArrayList<>();
    ArrayList<String> smallerThanTwenty = new ArrayList<>();
    ArrayList<State> customStateVatList = new ArrayList<>();

    public void addStateToList(State state)
    {
        allStatesList.add(state);
    }

    public void removeStateFromList(State state)
    {
        allStatesList.remove(state);
    }

    public int size()
    {
        return allStatesList.size();
    }

    public ArrayList<State> getAllStatesList()
    {
        return allStatesList;
    }

    public State getState(int index)
    {
        return allStatesList.get(index);
    }

    public void importFromFile(String file)
    {
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file))))
        {
            while(scanner.hasNextLine())
            {
                String nextLine = scanner.nextLine();
                State state = State.parseState(nextLine);
                addStateToList(state);
            }
        } catch (FileNotFoundException e)
        {
            System.err.println("File " + file + " not found. " + e.getLocalizedMessage());

        } catch (StateException e) {
            System.err.println("Error in " + file + e.getLocalizedMessage());
        }
    }

    public void exportToFile(String file)
    {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file))))
        {
            for(State state : stateList)
            {
                writer.println(state);
            }
            writer.println("=============================");
            writer.println("Rate VAT 20 % or lower or using special rate: " + smallerThanTwenty);

        } catch (IOException e)
        {
            System.err.println("Error in writing " + file + e.getLocalizedMessage());
        }
    }

    public void exportCustomVatToFile(String file)
{
    try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file))))
    {
        for(State state : customStateVatList)
        {
            writer.println(state);
        }

    } catch (IOException e)
    {
        System.err.println("Error in writing " + file + e.getLocalizedMessage());
    }
}
}
