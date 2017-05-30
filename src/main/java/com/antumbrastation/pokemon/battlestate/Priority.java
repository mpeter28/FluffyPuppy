package com.antumbrastation.pokemon.battlestate;

public class Priority implements Comparable<Priority> {

    private int priority;
    private boolean pursuit;
    private boolean switchOut;

    public Priority(int priority) {
        this.priority = priority;
        pursuit = false;
        switchOut = false;
    }

    public Priority(int priority, boolean pursuit, boolean switchOut) {
        this.priority = priority;
        this.pursuit = pursuit;
        this.switchOut = switchOut;
    }

    public Priority duplicate() {
        return new Priority(priority, pursuit, switchOut);
    }

    @Override
    public int compareTo(Priority opposing) {
        if (switchOut && opposing.isPursuit())
            return -1;
        else if (opposing.isSwitchOut() && pursuit)
            return 1;
        else
            return priority - opposing.getPriority();
    }

    public int getPriority() {
        return priority;
    }

    public boolean isPursuit() {
        return pursuit;
    }

    public boolean isSwitchOut() {
        return switchOut;
    }
}
