package com.example.o_o.afc;

public class Max {
    public int maxEm,maxMem,maxV;

    public Max() {
        maxEm=-1;
        maxMem=-1;
        maxV=-1;
    }

    public Max(int maxEm, int maxMem, int maxV) {
        this.maxEm = maxEm;
        this.maxMem = maxMem;
        this.maxV = maxV;
    }

    public int getMaxEm() {
        return maxEm;
    }

    public void setMaxEm(int maxEm) {
        this.maxEm = maxEm;
    }

    public int getMaxMem() {
        return maxMem;
    }

    public void setMaxMem(int maxMem) {
        this.maxMem = maxMem;
    }

    public int getMaxV() {
        return maxV;
    }

    public void setMaxV(int maxV) {
        this.maxV = maxV;
    }
}
