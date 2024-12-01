package firstpackage;

import java.avt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.applet.*;
import java.util.Scanner;

public class Lab {
    int fInt;
    double fFloat;
    String fString;
    public int getInt() {
        return fInt;
    }
    public double getFloat() {
        return fFloat;
    }
    public String getString() {
        return fString;
    }
    public int getStrlen() {
        return fString.length();
    }
    public void setInt(int arg) {
        this.fInt = arg;
        return;
    }
    public void setFloat(double arg) {
        this.fFloat = arg;
        return;
    }
    public void setString(String arg) {
        this.fString = arg;
        return;
    }
    public Lab() {
        this.fInt = 0;
        this.fFloat = 0.0;
        this.fString = "0";
    }
    public Lab(int a1, double a2, String a3) {
        this.fInt = (int)a1;
        this.fFloat = (double)a2;
        this.fString = (String)a3;
    }
    public Lab(Lab sec){
        this.fInt = sec.fInt;
        this.fFloat = sec.fFloat;
        this.fString = sec.fString;
    }

    static void kostyl_print(Lab yok) {
        printlab(yok.fInt, yok.fFloat, yok.fString);
        return;
    }

    static void printlab(int int1, double float1, String S1) {
        System.out.printf("Int:\t%8x; Float:\t%15f; Str:\t%s;\n", int1, float1, S1);
        return;
    }


    static Lab[] SortByInt(Lab arr[], int kol){
        Lab inn[] = new Lab[kol];
        for(int i = 0; i < kol; i++){
            inn[i] = arr[i];
        }
        for(int i = 0; i < kol - 1; i++) {
            for(int j = i + 1; j < kol; j++){
                if (inn[j].getInt() < inn[i].getInt()) {
                    Lab kst = new Lab(inn[i]);
                    inn[i].setInt(inn[j].getInt());
                    inn[i].setFloat(inn[j].getFloat());
                    inn[i].setString(inn[j].getString());
                    inn[j].setInt(kst.getInt());
                    inn[j].setFloat(kst.getFloat());
                    inn[j].setString(kst.getString());
                }
            }
        }
        return inn;
    }
    static Lab[] SortByFloat(Lab arr[], int kol){
        Lab inn[] = new Lab[kol];
        for(int i = 0; i < kol; i++){
            inn[i] = arr[i];
        }
        for(int i = 0; i < kol - 1; i++) {
            for(int j = i + 1; j < kol; j++){
                if (inn[j].getFloat() < inn[i].getFloat()) {
                    Lab kst = new Lab(inn[i]);
                    inn[i].setInt(inn[j].getInt());
                    inn[i].setFloat(inn[j].getFloat());
                    inn[i].setString(inn[j].getString());
                    inn[j].setInt(kst.getInt());
                    inn[j].setFloat(kst.getFloat());
                    inn[j].setString(kst.getString());
                }
            }
        }
        return inn;
    }
    static Lab[] SortByStrLen(Lab arr[], int kol){
        Lab inn[] = new Lab[kol];
        for(int i = 0; i < kol; i++){
            inn[i] = arr[i];
        }
        for(int i = 0; i < kol - 1; i++) {
            for(int j = i + 1; j < kol; j++){
                if (inn[j].getStrlen() < inn[i].getStrlen()) {
                    Lab kst = new Lab(inn[i]);
                    inn[i].setInt(inn[j].getInt());
                    inn[i].setFloat(inn[j].getFloat());
                    inn[i].setString(inn[j].getString());
                    inn[j].setInt(kst.getInt());
                    inn[j].setFloat(kst.getFloat());
                    inn[j].setString(kst.getString());
                }
            }
        }
        return inn;
    }

    public static String LineToFile(Lab kyo, int index) {
        StringBuilder sb = new StringBuilder("");
        //sb = "Line:\t" + index + "; Int:\t" + kyo.getInt() + "; Float:\t" + kyo.getFloat() + "; Str:\t" + kyo.getString() + ";\n";
        sb.append("Line:\t");
        sb.append(index);
        sb.append("; Int:\t");
        sb.append(kyo.getInt());
        sb.append("; Float:\t");
        sb.append(kyo.getFloat());
        sb.append("; Str:\t");
        sb.append(kyo.getString());
        sb.append(";\n");
        return sb.toString();
    }
    public static void main(String args[]) {
        try {
        System.out.println("Entre de numre du class objects: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Lab arr[] = new Lab[N];
        //FileWriter wr = new FileWriter("I:/OOP/l1/Result.txt", false);
        BufferedWriter wr = new BufferedWriter(new FileWriter("./Result.txt", false));
        //File file = new File(".//Result.txt");
        //file.createNewFile();
        //FileOutputStream os = new FileOutputStream(file, false);
        //OutputStreamWriter wr = new OutputStreamWriter(os);
        //PrintWriter wr = new PrintWriter("Result.txt", "utf-8");
        //wr.flush();
        System.out.println("Genuine array of objs:");
        wr.write("Genuine array of objs:\n");
        for(int i = 0; i < N; i++) {
            Random random = new Random();
            int rI = random.nextInt(0x7FFFFFFF);
            random = new Random();
            double rD = random.nextDouble();
            int pow = random.nextInt(0x0000FFFF);
            random = new Random();
            StringBuilder rS = new StringBuilder("");
            int n = random.nextInt(0b10000000);
            for(int j = 0; j < n; j++) {
                char Symb = (char)(random.nextInt(0x5E) + 0x20);
                rS.append(Symb);
            }
            arr[i] = new Lab(rI, rD*pow, rS.toString());
            kostyl_print(arr[i]);
            wr.write(LineToFile(arr[i], i));
        }
        System.out.println("Array of obj Sorted by Ints: ");
        wr.write("\nArray of obj Sorted by Ints:\n");
        Lab sorrt[] = SortByInt(arr, N);
        for(int i = 0; i < N; i++) {
            kostyl_print(sorrt[i]);
            wr.write(LineToFile(sorrt[i], i));
            //wr.printf("Line %4d; Int:\t%8x; Float:\t%15f; Str:\t%s;\n", i, sorrt[i].getInt(), sorrt[i].getFloat(), sorrt[i].getString());
        }
        System.out.println("Array of obj Sorted by Floats: ");
        wr.write("\nArray of obj Sorted by Floats:\n");
        sorrt = SortByFloat(arr, N);
        for(int i = 0; i < N; i++) {
            kostyl_print(sorrt[i]);
            wr.write(LineToFile(sorrt[i], i));
            //wr.printf("Line %4d; Int:\t%8x; Float:\t%15f; Str:\t%s;\n", i, sorrt[i].getInt(), sorrt[i].getFloat(), sorrt[i].getString());
        }
        System.out.println("Array of obj Sorted by StrLen: ");
        wr.write("\nArray of obj Sorted by Floats:\n");
        sorrt = SortByStrLen(arr, N);
        for(int i = 0; i < N; i++) {
            kostyl_print(sorrt[i]);
            wr.write(LineToFile(sorrt[i], i));
            //wr.printf("Line %4d; Int:\t%8x; Float:\t%15f; Str:\t%s;\n", i, sorrt[i].getInt(), sorrt[i].getFloat(), sorrt[i].getString());
        }
        //wr.flush();
        wr.close();
        
        //os.close();
        //file.close();
        }
    catch (IOException e) {
        System.err.printf("Das ist Exception AGAIN! :: ");
        System.err.print(e);
        e.printStackTrace();
    }
    } 
}