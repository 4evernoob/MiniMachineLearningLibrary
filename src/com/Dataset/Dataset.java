/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dataset;

import java.util.Arrays;

/**
 *
 * @author Da supa pc jr
 */
public class Dataset {
    //data field
    private double[][] data;
    //tag field
    private int[] tags;
    //constructors
    public Dataset(double[][] d, int[] t) {
        data = d.clone();
        tags = t.clone();
    }

    public Dataset(double[][] d) {
        data = d.clone();
        tags = new int[data.length];
    }

    public double[] getD(int a) {
        return getData()[a];
    }

    public int[] getT() {
        return tags;
    }

    public int getNumberOfObservations() {
        return getData().length;
    }

    public void print() {
        for (int i = 0; i < getData().length; i++) {
            System.out.println(Arrays.toString(getData()[i]) + " " + tags[i]);

        }
    }

    /**
     * @return the data
     */
    public double[][] getData() {
        return data;
    }
}
