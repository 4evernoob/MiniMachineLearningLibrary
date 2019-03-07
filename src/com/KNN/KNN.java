/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KNN;

import com.Dataset.Dataset;
import com.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Da supa pc jr
 */
public class KNN {

    double results[];
    int classR[];
//use dataset row size as nRes to store and processing of results
    
    public KNN(int nRes) {
        results = new double[nRes];
        classR = new int[nRes];
    }

    public int calculate(double[] vector, Dataset d, int neighbors) {
        //this scheme is only valid with odd neigbors numbers
        if(neighbors%2!=1){
        throw  new IllegalArgumentException("This value is not valid");
        }
        //process our vector
        for (int i = 0; i < d.getT().length; i++) {
            results[i] = Util.distance(vector, d.getData()[i]);
            classR[i] = d.getT()[i];
        }
        //sort our results
        SpecialSort.qSort(classR, results, 0, results.length - 1);
        //how many different classes we have
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < d.getT().length; i++) {
            if (!map.containsKey(d.getT()[i])) {
                map.put(d.getT()[i], 1);
            } 
        }
        //force max num of neighbors
        if (neighbors > results.length) {
            neighbors = results.length;
        }
        //poll ocurrences
        int r[] = new int[map.size()];
        for (int c = 0; c < neighbors; c++) {
            r[classR[c]]++;
        }
        //obtain who's the winner
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int c = 0; c < r.length; c++) {
            if (max < r[c]) {
                max = r[c];
                idx = c;
            }
        }
            System.out.println(r[idx]);
        return idx;
    }

    public void print() {
        System.out.println("Results");
        System.out.println(Arrays.toString(results));
        System.out.println("Classes");
        System.out.println(Arrays.toString(classR));
    }

    public static void main(String[] args) {
        double[][] d = {{1.5, 2.1, 1.5}, {2, 1.5, 2}, {1.2, 2.5, 1.2}, {2, 1.3, 2}, {1 - .3, 1.5, 1 - .3}, {2.2, 1, 2.2}};
        int[] t = {0, 1, 0, 1, 0, 1};
        Dataset data = new Dataset(d, t);
        KNN test = new KNN(6);
        int temp = test.calculate(new double[]{2, 1, 2}, data, 3);
        System.out.println(temp);
    }

    

}
