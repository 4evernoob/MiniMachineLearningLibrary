/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naivebayes;

import com.Dataset.Dataset;
import com.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;


/**
 *
 * @author Da supa pc jr
 */
public class NaiveBayes {
    //means matrix
    private double paramsM[][];
   //variance matrix 
    private double paramsV[][];
    //apriori probabilities   
    private double apriori[];
    
    

    public NaiveBayes(int nClasses, int nFeats) {
        paramsM = new double[nClasses][nFeats];
        paramsV = new double[nClasses][nFeats];
    }

    public void intiializefromData(Dataset d) {
         Map<Integer, Integer> map = new HashMap<>();
         //get number of ocurrences per class
        for (int i = 0; i < d.getT().length; i++) {
            if (!map.containsKey(d.getT()[i])) {
                map.put(d.getT()[i], 1);
            } else {
                map.put(d.getT()[i], map.get(d.getT()[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
           //calculate means an variances of each class
            for (int j = 0; j < d.getData()[0].length; j++) {
                paramsM[entry.getKey()][j] = Util.mean(d.getData(), entry.getKey(), j, d.getT(), entry.getValue());
                paramsV[entry.getKey()][j] = Util.variance(d.getData(), entry.getKey(), j, d.getT(), entry.getValue());
            }
        }
        //declare apriori probabilities array
        apriori=new double[map.size()];
        //calculate apriori probabilities
        for (int i = 0; i < apriori.length; i++) {
            apriori[i]=map.get(i)/(d.getT().length*1.00);
            
        }

    }
    public int calculate(double vect[]) throws InputMismatchException{
        //check id vector is good enough to calculate something
        if(vect.length<paramsM[0].length){
        throw new InputMismatchException("No hay elementos suficientes");
        }
        // we must obtain the max value an that'll be our vector class
        double max= Double.MIN_VALUE;
        int classR=-1;
        for (int c=0;c<paramsM.length;c++) {
            //class probability is multiplication of probabilities
            double tmp=apriori[c];         
            for(int k=0;k<paramsM[0].length;k++){
                tmp*=Util.probGauss(vect[k], paramsM[c][k],paramsV[c][k]);               
            }
            //We check the obtained probability
            if(tmp>max){
            max=tmp;
            classR=c;
            }
        }
        //return the most likely class
        return classR;
    }
  public void print(){
      //print details of classifier
      System.out.println("Details of classifier:");      
        System.out.println("Mean");
        for (int i = 0; i < paramsM.length; i++) {
            System.out.println(i);
            System.out.println(Arrays.toString(paramsM[i]));

        }
        System.out.println("Variances");
        for (int i = 0; i < paramsV.length; i++) {
            System.out.println(i);
            System.out.println(Arrays.toString(paramsV[i]));

        }
        System.out.println("A priori probabilities :"+Arrays.toString(apriori));
  }
    public static void main(String[] args) {
        double[][] d = {{1.5, 2.1, 1.5}, {2, 1.5, 2}, {1.2, 2.5, 1.2}, {2, 1.3, 2}, {1-.3, 1.5, 1-.3}, {2.2, 1, 2.2}};
        int[] t = {0, 1, 0, 1, 0, 1};
        Dataset data = new Dataset(d, t);
        NaiveBayes nb = new NaiveBayes(2, 3);
        data.print();
        nb.intiializefromData(data);
        int temp= nb.calculate(new double[]{1,2,1});
        System.out.println("test de dato");
        System.out.println(temp);
    }

}
