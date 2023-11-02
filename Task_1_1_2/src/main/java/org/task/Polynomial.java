package org.task;
import java.util.Arrays;
public class Polynomial{
    public int[] cfs;

    public Polynomial(){ // пустой многочлен
        cfs = new int[0];
    }
    public Polynomial(int[] list){ // непустой многочлен
        cfs = list;
    }
    public Polynomial plus(Polynomial list){ // сложение многочленов
        Polynomial tmp = new Polynomial(new int[Math.max(cfs.length, list.cfs.length)]);
        if (cfs.length == list.cfs.length){ //если равны по длине
            for (int i = 0; i < list.cfs.length; i++){
                tmp.cfs[i] = cfs[i] + list.cfs[i];
            }
            return tmp;
        }
        else{
            int tmpLen = 0;
            if (cfs.length < list.cfs.length){ //если первое меньше
                for (int i = 0; i < list.cfs.length - cfs.length; i++){
                    tmp.cfs[i] = list.cfs[i];
                    tmpLen++;
                }
                for (int i = 0; i < cfs.length; i++){
                    tmp.cfs[tmpLen] = cfs[i] + list.cfs[tmpLen];
                    tmpLen++;
                }

            }
            else{ //если первое больше
                for (int i = 0; i < cfs.length - list.cfs.length; i++){
                    tmp.cfs[i] = cfs[i];
                    tmpLen++;
                }
                for (int i = 0; i < list.cfs.length; i++){
                    tmp.cfs[tmpLen] = cfs[tmpLen] + list.cfs[i];
                    tmpLen++;
                }
            }
            return tmp;
        }
    }

    public Polynomial minus(Polynomial list){ // вычитание многочленов
        Polynomial tmp = new Polynomial(new int[Math.max(cfs.length, list.cfs.length)]);
        if (cfs.length == list.cfs.length){ // если равны
            for (int i = 0; i < list.cfs.length; i++){
                tmp.cfs[i] = cfs[i] - list.cfs[i];
            }
            return tmp;
        }
        else{
            int tmpLen = 0;
            if (cfs.length < list.cfs.length){ // если первое меньше
                for (int i = 0; i < list.cfs.length - cfs.length; i++){
                    tmp.cfs[i] = list.cfs[i] * -1;
                    tmpLen++;
                }
                for (int i = 0; i < cfs.length; i++){
                    tmp.cfs[tmpLen] = cfs[i] - list.cfs[tmpLen];
                    tmpLen++;
                }

            }
            else{ // если первое больше
                for (int i = 0; i < cfs.length - list.cfs.length; i++){
                    tmp.cfs[i] = cfs[i];
                    tmpLen++;
                }
                for (int i = 0; i < list.cfs.length; i++){
                    tmp.cfs[tmpLen] = cfs[tmpLen] - list.cfs[i];
                    tmpLen++;
                }
            }
            return tmp;
        }
    }

    public Polynomial times(Polynomial list){ // умножение многочленов
        if (cfs.length == 0 && list.cfs.length == 0)
            return new Polynomial();
        else{
            Polynomial tmp = new Polynomial(new int[(cfs.length-1)+(list.cfs.length-1)+1]);
            for (int i = 0; i < cfs.length; i++){
                for (int j = 0; j < list.cfs.length; j++)
                    tmp.cfs[tmp.cfs.length - (cfs.length-i-1)-(list.cfs.length-j-1) - 1] += cfs[i] * list.cfs[j];
            }
            return tmp;
        }
    }

    public int evaluate(int num){ // вычисление значения в точке
        int result = 0;
        for (int i = 0; i < cfs.length; i++){
            result += (int) (cfs[i] * Math.pow(num, cfs.length - i - 1));
        }
        return result;
    }

    public Polynomial differentiate(int num){ // взятие производной
        for (int i = 0; i < cfs.length; i++) {
            if (i == cfs.length - 1)
                cfs[i] = 0;
            else
                cfs[i] *= (cfs.length - i - 1);
        }
        int[] cfs1 = Arrays.copyOf(cfs, cfs.length-1);
        cfs = new int [cfs.length];
        cfs = Arrays.copyOf(cfs1, cfs1.length);

        if (num != 1)
            return differentiate(num-1);
        else{
            Polynomial tmp = new Polynomial();
            tmp.cfs = cfs;
            return tmp;
        }
    }

    public boolean compare(Polynomial list){ // сравнение многочленов
        if (cfs.length != list.cfs.length)
            return false;
        else{
            for (int i = 0; i < cfs.length; i ++){
                if (cfs[i] != list.cfs[i])
                    return false;
            }
            return true;
        }
    }

    public String toString(){ // получение строкового представления полинома
        String word = "";
        for (int i = 0; i < cfs.length; i++){
            if (cfs[i] != 0){
                if (cfs[i] > 0 && i != 0)
                    word += " + ";
                else if (cfs[i] < 0 && i == 0)
                    word += "-";
                else if (cfs[i] < 0)
                    word += " - ";

                if (i == cfs.length-1)
                    word += Integer.toString(Math.abs(cfs[i]));
                else if (i == cfs.length-2){
                    if (cfs[i] != 1)
                        word = word + Math.abs(cfs[i]) + "x";
                    else
                        word += "x";
                }
                else{
                    if (cfs[i] != 1 && cfs[i] != -1)
                        word = word + Math.abs(cfs[i]) + "x^" + Integer.toString(cfs.length-i-1);
                    else
                        word = word + "x^" + Integer.toString(cfs.length-i-1);
                }
            }
        }
        return word;
    }
}