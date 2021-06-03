package com.company;

public class State implements Comparable<State>
{
    private static final String DELIMETER = "\t";

    private String stateShortcut;
    private String stateName;
    private float fullVat;
    private float reducedVat;
    private boolean specialVat;

    public State(String stateShortcut, String stateName, float fullVat, float reducedVat, boolean specialVat) {
        this.stateShortcut = stateShortcut;
        this.stateName = stateName;
        this.fullVat = fullVat;
        this.reducedVat = reducedVat;
        this.specialVat = specialVat;
    }

    public static State parseState(String record) throws StateException
    {
        String[] item = record.split(DELIMETER);
        int numberOfItems = item.length;

        if(numberOfItems != 5) throw new StateException(" Wrong number of items " + record);
        {
            String stateShortcut = item[0];
            String stateName = item[1];
            float fullVat = Float.parseFloat(item[2]);
            float reducedVat = Float.parseFloat(item[3]);
            boolean specialVat = Boolean.parseBoolean(item[4]);

            return new State(stateShortcut, stateName, fullVat, reducedVat, specialVat);
        }
    }

    public String getStateShortcut() {
        return stateShortcut;
    }

    public void setStateShortcut(String stateShortcut) {
        this.stateShortcut = stateShortcut;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public float getFullVat() {
        return fullVat;
    }

    public void setFullVat(float fullVat) {
        this.fullVat = fullVat;
    }

    public float getReducedVat() {
        return reducedVat;
    }

    public void setReducedVat(float reducedVat) {
        this.reducedVat = reducedVat;
    }

    public boolean isSpecialVat() {
        return specialVat;
    }

    public void setSpecialVat(boolean specialVat) {
        this.specialVat = specialVat;
    }

    @Override
    public String toString()
    {
        return getStateName() + " (" + getStateShortcut() + ") " + ": " + getFullVat() + " %";
    }

    @Override
    public int compareTo(State stateVat)
    {
        if(this.getFullVat() > stateVat.getFullVat())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
