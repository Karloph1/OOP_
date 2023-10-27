package org.example;

public class Main{
    private static void Sift_Up(int len, int[]new_array){ //отсеивание вниз
        int x;
        if ((len-1)/2 >= 0 && new_array[len] < new_array[(len-1) / 2]){
            x = new_array[len];
            new_array[len] = new_array[(len-1)/2];
            new_array[(len-1)/2] = x;
            Sift_Up((len-1)/2, new_array);
        }
    }

    private static void Sift_Down(int current, int len, int[]new_array){ //отсеивание вверх
        int x;
        if (current*2+2 < len && new_array[current*2+1] < new_array[current] && new_array[current*2+2] < new_array[current]){
            if (new_array[current*2+1] < new_array[current*2+2]){
                x = new_array[current*2+1];
                new_array[current*2+1] = new_array[current];
                new_array[current] = x;
                Sift_Down(current*2+1, len, new_array);
            }
            else{
                x = new_array[current*2+2];
                new_array[current*2+2] = new_array[current];
                new_array[current] = x;
                Sift_Down(current*2+2, len, new_array);
            }
        }
        else if (current*2+1 < len && new_array[current*2+1] < new_array[current]){
            x = new_array[current*2+1];
            new_array[current*2+1] = new_array[current];
            new_array[current] = x;
            Sift_Down(current*2+1, len, new_array);
        }
        else if (current*2+2 < len && new_array[current*2+2] < new_array[current]){
            x = new_array[current*2+2];
            new_array[current*2+2] = new_array[current];
            new_array[current] = x;
            Sift_Down(current*2+2, len, new_array);
        }
    }

    public static void heapsort(int[] list) {
        int  size = list.length;
        int[] new_tree = new int[size];
        for (int i = 0; i < size; i++){ // записывание в новый
            new_tree[i] = list[i];
            Sift_Up(i, new_tree);
        }
        int x;
        for (int i = size-1; i >= 0; i--){ // отсев наименьшего
            x = new_tree[0];
            new_tree[0] = new_tree[i];
            new_tree[i] = x;
            Sift_Down(0, i, new_tree);
        }
        for(int i = 0; i < size; i++) // записываем полученный в который дали на входе
            list[i] = new_tree[size-1-i];
    }
}