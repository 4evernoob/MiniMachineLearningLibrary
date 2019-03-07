/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;
//  QuickSort to order results for KNN

public class SpecialSort {

    static int partition(int tag[], double arr[], int low, int high) {
        double pivot = arr[high];
        int i = (low - 1); // index of smaller element 
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                int tmpi = tag[i];
                tag[i] = tag[j];
                tag[j] = tmpi;
            }
        }
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        int tmpi = tag[i + 1];
        tag[i + 1] = tag[high];
        tag[high] = tmpi;
        return i + 1;
    }

   public static void qSort(int tag[], double arr[], int low, int high) {
        if (low < high) {
            int pi = partition(tag, arr, low, high);
            qSort(tag, arr, low, pi - 1);
            qSort(tag, arr, pi + 1, high);
        }
    }
}
