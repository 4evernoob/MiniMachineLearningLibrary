/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KMeans;

import com.Dataset.Dataset;
import com.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Da supa pc jr this need some remembering hahah
 */
public class Kmeans {

    double[][] means;

    //constructor with nCentroids and nFeatures
    public Kmeans(int nMeans, int nFeats) {
        means = new double[nMeans][nFeats];
    }

    //calculate centroids using a dataset and we chose how we initialize our centroids
    public void calculate(Dataset d, int randomInit, int nIterations) {
        //init weights random using min and max values in data    
        for (double[] mean : means) {
            init(mean, d.getData()[0].length, Util.minmax(d.getData()));
        }
        //some stuff
//        System.out.println("B4");
//        for (int i = 0; i < means.length; i++) {
//            System.out.println(Arrays.toString(means[i]));
//
//        }
        //we init cluster labels
        int clusterLabels[] = new int[d.getData().length];
        // stop criteria is number of iteratitions
        for (int c = 0; c < nIterations; c++) {
            //iterate data
            for (int i = 0; i < d.getData().length; i++) {
                //obtain closest centroid
                double minDis = Util.distance(d.getData()[i], means[0]);
                int tmp = 0;
                for (int j = 1; j < means.length; j++) {
                    double dist = Util.distance(d.getData()[i], means[j]);
                    if (minDis > dist) {
                        minDis = dist;
                        tmp = j;
                    }
                }
                clusterLabels[i] = tmp;
            }
            //Count ocurrences of each centroid            
            Map<Integer, Integer> map = new HashMap<>();
            //get number of ocurrences per class
            for (int i = 0; i < clusterLabels.length; i++) {
                if (!map.containsKey(clusterLabels[i])) {
                    map.put(clusterLabels[i], 1);
                } else {
                    map.put(clusterLabels[i], map.get(clusterLabels[i]) + 1);
                }
            }
            //populate centroids
            for (int i = 0; i < means.length; i++) {
                for (int j = 0; j < means[0].length; j++) {
                    means[i][j] = Util.mean(d.getData(), i, j, clusterLabels, map.get(clusterLabels[i]));
                }
            }
        }
//        System.out.println("After");
//        for (int i = 0; i < means.length; i++) {
//            System.out.println(Arrays.toString(means[i]));
//
//        }
    }
    //weight initialization
    double[] init(double[] mean, int nF,double []minmax) {
        double[] res = new double[nF];
        for (int i = 0; i < res.length; i++) {
            mean[i] = (Math.abs(minmax[1]-minmax[0]))* Math.random()+minmax[0];

        }
        return res;
    }
       //simple test
    public static void main(String[] args) {

        double[][] d = {{1.5, 2.1, 1.5}, {2, 1.5, 2}, {1.2, 2.5, 1.2}, {2, 1.3, 2}, {1 - .3, 1.5, 1 - .3}, {2.2, 1, 2.2}};
        int[] t = {0, 1, 0, 1, 0, 1};
        Dataset data = new Dataset(d, t);
        Kmeans dk = new Kmeans(2, data.getData()[0].length);
        dk.calculate(data, 0, 1000);
    }
}
