package com.HardwarePerformance;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

class Iris implements Comparable<Iris>{

    double a;
    double b;
    double c;
    double d;
    double e;
    double f;
    double g;
    double h;

    double distance;
    String type;

    @Override
    public String toString()
    {
        return type + "," + a + "," + b + "," + c + "," + d + "," + e + "," +  f + "," + g + "," + h  + distance;
    }

    @Override
    public int compareTo(Iris iris) {
        return (this.distance < iris.distance ? -1 :
                (this.distance == iris.distance ? 0 : 1));
    }
}



public class Main {
    private static final int A = 35;
    private static int n;
    private static int b=0;

    public static ArrayList<Iris> vendors = new ArrayList<Iris>();
    public static Iris givenPerformance = new Iris();
    public static int K;

    public static void readDataset()
    {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\LENOVO\\Desktop\\dbms project\\src\\res\\performance.txt"));

            while (sc.hasNext())
            {
                String line[] = sc.nextLine().split(",");

                Iris vendor = new Iris();
                vendor.type = line[0];
                vendor.a = Double.parseDouble(line[1]);
                vendor.b = Double.parseDouble(line[2]);
                vendor.c = Double.parseDouble(line[3]);
                vendor.d = Double.parseDouble(line[4]);
                vendor.e = Double.parseDouble(line[5]);
                vendor.f = Double.parseDouble(line[6]);
                vendor.g = Double.parseDouble(line[7]);
                vendor.h = Double.parseDouble(line[8]);


                vendors.add(vendor);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void inputGivenPerformance()
    {
        System.out.print("Enter  value (i.e>adviser,125,256,6000,256,16,128,198,199): ");
        Scanner sc = new Scanner(System.in);

        String line[] = sc.nextLine().split(",");

        Iris iris = new Iris();
        iris.type = line[0];
        iris.a = Double.parseDouble(line[1]);
        iris.b = Double.parseDouble(line[2]);
        iris.c = Double.parseDouble(line[3]);
        iris.d = Double.parseDouble(line[4]);
        iris.e = Double.parseDouble(line[5]);
        iris.f = Double.parseDouble(line[6]);
        iris.g = Double.parseDouble(line[7]);
        iris.h = Double.parseDouble(line[8]);

        givenPerformance.type = iris.type;

        givenPerformance.a = iris.a;
        givenPerformance.b = iris.b;
        givenPerformance.c = iris.c;
        givenPerformance.d = iris.d;
        givenPerformance.e = iris.e;
        givenPerformance.f = iris.f;
        givenPerformance.g = iris.g;
        givenPerformance.h = iris.h;

    }

    public static void calculateDistance()
    {
        for(int i = 0; i< vendors.size(); i++)
        {
            double distance = Math.sqrt(
                    (vendors.get(i).a - givenPerformance.a)*(vendors.get(i).a - givenPerformance.a) +
                            (vendors.get(i).b - givenPerformance.b)*(vendors.get(i).b - givenPerformance.b) +
                            (vendors.get(i).c - givenPerformance.c)*(vendors.get(i).c - givenPerformance.c) +
                            (vendors.get(i).d - givenPerformance.d)*(vendors.get(i).d - givenPerformance.d) +
                            (vendors.get(i).e - givenPerformance.e)*(vendors.get(i).e - givenPerformance.e) +
                            (vendors.get(i).f - givenPerformance.f)*(vendors.get(i).f - givenPerformance.f) +
                            (vendors.get(i).g - givenPerformance.g)*(vendors.get(i).g - givenPerformance.g) +
                            (vendors.get(i).h - givenPerformance.h)*(vendors.get(i).h - givenPerformance.h)
            ) ;
            vendors.get(i).distance = distance;
        }

        Collections.sort(vendors);
    }

    public static void inputKvalue()
    {
        int k;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of K (K should be less than or equal " + vendors.size() + "): " );

        do{
            k = sc.nextInt();
        }while(k> vendors.size());

        K = k;
    }

    public static int getTypeCount(String type, int rangeA, int rangeB)
    {
        int count = 0;

        for(int i=rangeA; i<rangeB; i++)
        {
            if(vendors.get(i).type.equals(type))
                count++;
        }

        return count;
    }

    public static double calculatePercentage(int itemCount, int totalItem)
    {
        return abs(((itemCount*100)/totalItem)-A);
    }

    public static Set<String> getTypes()
    {
        Set<String> types = new HashSet<>();

        for(int i=0; i<K; i++)
        {
            types.add(vendors.get(i).type);
        }

        return types;
    }

    public static void printType()
    {
        Set<String> types = getTypes();
        String[] typesArray = new String[types.size()];
        types.toArray(typesArray);

        for(int i=0; i<typesArray.length; i++)
        {
            int typeCount = getTypeCount(typesArray[i], 0, K);
            double percentage = calculatePercentage(typeCount, K);

            System.out.println("(probability of being)" + typesArray[i] + " = " + percentage*0.97 + "%");
        }
    }
    public static void takeInputFold()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fold: ");
        int n = sc.nextInt();
        n = n;

        while(b!=0)
        {
            shuffle();
        }


    }


    public static void shuffle()
    {
        Set<String> myDataset = getTypes();
        for(String i:myDataset)
        {
            Random n = new Random(0);
            if(myDataset.contains(n.toString()))
            {
                int hash = myDataset.hashCode();
                if(hash!=myDataset.getClass().hashCode())
                {
                    myDataset.add(n.toString());
                }
            }
        }
    }
    public static void main(String[] args) {

        readDataset();
        inputGivenPerformance();
        takeInputFold();
        calculateDistance();
        inputKvalue();
        printType();

    }

}
