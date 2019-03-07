/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

/**
 *
 * @author Da supa pc jr
 */
public class Util {
    //calculate variance for a determined feature and class
    public static double variance(double[][] val, int classe, int vari, int[] tags, int number) {
        double mean = mean(val, classe, vari, tags, number);
        double var = 0;
        for (int i = 0; i < val.length; i++) {
            if (classe == tags[i]) {
                var = (val[i][vari] - mean) * (val[i][vari] - mean);
            }
        }
        return var / (number - 1);
    }
    //calculate mean fot a determined feature and class
    public static double mean(double[][] val, int classe, int var, int[] tags, int number) {
        double mean = 0;
        for (int i = 0; i < val.length; i++) {
            if (classe == tags[i]) {
                mean += val[i][var];
            }
        }
        return mean / (number);
    }
//calculate pdf mean 0, variance 1
    public static double probGauss(double x) {
        return Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
    }
//calculate pdf dfor mean!=0 and variance!=1
    public static double probGauss(double x, double mu, double sigma) {
        return probGauss((x - mu) / sigma) / sigma;
    }
    //calculate distance between vectors using euclidean distance
    public static double distance(double a[], double[]b){
    double dist=0;
        for (int i = 0; i < b.length; i++) {
            dist+=(a[i]-b[i])*(a[i]-b[i]);
            
        }
        return Math.sqrt(dist);
    }
}
