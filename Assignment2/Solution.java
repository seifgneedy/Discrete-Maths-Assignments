import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    // (b^n) mod m
    /*
     * In this algorithm, we compute (b^n) first then mod m
     */
    static long naive1(long b, long n, long m) {
        long c = 1;
        for (long i = 1; i <= n; i++) {
            c = Math.multiplyExact(c, b);
        }
        c %= m;
        return c;
    }

    /*
     * In this algorithm, we compute the mod after every multiplication of b to
     * avoid overflow
     */
    static long naive2(long b, long n, long m) {
        long c = 1;
        for (long i = 1; i <= n; i++) {
            c = (Math.multiplyExact(c, b)) % m;
        }
        return c;
    }

    /*
    * We use the property of binary representation of n to reduce the time 
    * of the algorithm
    */
    static long fastExponentIterative(long b, long n, long m) {
        long result = 1;
        long power = b % m;
        while (n > 0) {
            if (n % 2 == 1)
                result = (Math.multiplyExact(result, power)) % m;
            n = n >> 1;
            power = (Math.multiplyExact(power, power)) % m;
        }
        return result;
    }

    /*
     * We use divide and conquer technique in this recursive approach As if b is
     * even : (b^n)%m = ((b^n/2)*(b^n/2))%m and if b is odd : (b^n)%m =
     * (b*(b^(n-1)))%c
     */
    static long fastExponentRecursive(long b, long n, long m) {
        if (b == 0)
            return 0;
        if (n == 0)
            return 1;
        long res;
        if (n % 2 == 0) {
            res = fastExponentRecursive(b, n / 2, m);
            res = (Math.multiplyExact(res, res)) % m;
        } else {
            res = ((b % m) * fastExponentRecursive(b, n - 1, m) % m) % m;
        }
        // res is negative and |res|<m so we add m to get the positive mod
        return (res + m) % m;
    }

    static long getRandomNumWithBits(int bits) {
        long initBase=(long)Math.pow(2, bits-1);
        long result = (long) (initBase * Math.random());

        return result+initBase;
    }
    static void writeDataInFile(File file) throws IOException {
        if(!file.exists()){
            String fileName=file.getName();
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            //In naive2 it take very long time to perform 32 bits
            int limit=fileName.equals("naive2.txt")?32:62;
            for (int i = 1;i<=limit; i ++) {
                long[] times =new long[10];
                boolean overflow=false;
                for(int j=0;j<times.length;j++){
                    try {
                        long randB=getRandomNumWithBits(i),randN=getRandomNumWithBits(i),randM=getRandomNumWithBits(i);
                        long start = System.nanoTime();
                        if(fileName.equals("naive1.txt"))
                            naive1(randB, randN,randM);
                        else if(fileName.equals("naive2.txt"))
                            naive2(randB, randN,randM);
                        else if(fileName.equals("fastExponentiationIterative.txt"))
                            fastExponentIterative(randB, randN,randM);
                        else
                            fastExponentRecursive(randB, randN,randM);
                        times[j] = System.nanoTime() - start;
                        
                    } catch (Exception e) {
                        overflow=true;
                        break;
                    }
                }
                if(overflow) 
                    break;
                out.write(i+","+getAverage(times)+"\n");       
            }
            out.close();
        }

    }
    static long getAverage(long[] arr){
        double average=0;
        for(int i=0;i<arr.length;i++)
            average+=(arr[i]/arr.length);
        return (long)average;
    }
    static void saveData() throws IOException {
        writeDataInFile(new File("naive1.txt"));
        writeDataInFile(new File("naive2.txt"));
        writeDataInFile(new File("fastExponentiationIterative.txt"));
        writeDataInFile(new File("fastExponentiationRecursive.txt"));
    }

    public static void main(String[] args) throws IOException {
        saveData();
        Scanner in = new Scanner(System.in);
        long b, n, m;
        System.out.println("The program is used to compute ((b^n)mod m) with 4 different methods");
        System.out.println("Time data is saved as .txt files");
        System.out.print("Enter b : ");
        b = in.nextLong();
        System.out.print("Enter n : ");
        n = in.nextLong();
        System.out.print("Enter m : ");
        m = in.nextLong();
        try {
            long result = naive1(b, n, m);
            System.out.println(result + "\t(naive1)");
        } catch (Exception e) {
            System.out.println("Overflow occured with naive1 method");
        }
        try {
            long result = naive2(b, n, m);
            System.out.println(result + "\t(naive2)");
        } catch (Exception e) {
            System.out.println("Overflow occured with naive2 method");
        }
        try {
            long result = fastExponentIterative(b, n, m);
            System.out.println(result + "\t(Fast Exponentiation Iterative)");
        } catch (Exception e) {
            System.out.println("Overflow occured with Fast Exponentiation Iterative method");
        }
        try {
            long result = fastExponentRecursive(b, n, m);
            System.out.println(result + "\t(Fast Exponentiation Recursive)");
        } catch (Exception e) {
            System.out.println("Overflow occured with Fast Exponentiation Recursive method");
        }

        in.close();
    }
}