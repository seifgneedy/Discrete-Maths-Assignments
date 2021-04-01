import java.util.*;
public class Solution{
    
    static List<Integer> sieveOfEratosthenes(int limit){
        List<Integer> result=new ArrayList<>(limit);
        //listing all numbers till the limit
        for(int i=2;i<=limit;i++)
            result.add(i);
        for(int i=0;i<result.size();i++){
            if(result.get(i)>Math.sqrt(limit))
                break;
            for(int j=i+1;j<result.size();j++){
                if(result.get(j)%result.get(i)==0){
                    result.remove(j);
                    j--;
                }
            }
        }
        return result;
    }

    static boolean trialDivision(int number){
        for(int i=2;i<=Math.sqrt(number);i++){
        //checking all numbers under sqrt instead of consuming time in getting primes
            if(number%i==0)
                return false;
        }
        return true;
    }
    //gcd(a,b)=sa+tb
    static int[] extendedEuclideanAlgorithm(int a,int b){
        int s0=1,s1=0,t0=0,t1=1;
        while(b!=0){
            int q=a/b,temp;
            //Sj=Sj-2-Qj-1Sj-1
            temp=s1;
            s1=s0-s1*q;
            s0=temp;
            //Tj=Tj-2-Qj-1Tj-1
            temp=t1;
            t1=t0-t1*q;
            t0=temp;

            temp=b;
            b=a%b;
            a=temp;
        }
        int[] result=new int[3];
        result[0]=a;result[1]=s0;result[2]=t0;
        return  result;
    }

    static int chineseRemainderTheorem(List<Integer> aList,List<Integer> mList){
        long mProduct=1;
        List<Integer> MK = new ArrayList<>();
        //m =mKist1+mList2+...mListk
        for(int m:mList)
            mProduct*=m;
        //MK=m/mListk for k=1,2,..,k
        for(int i=0;i<aList.size();i++)
            MK.add((int) (mProduct / mList.get(i)));
        //inverseList represents yk (inverse of MK mod mListk)
        List<Integer> inverseList = new ArrayList<>();
        //using extended euclidean algorithm to calculate multiplicated inverse
        for(int i=0;i<mList.size();i++){
            int inverse=extendedEuclideanAlgorithm(MK.get(i), mList.get(i))[1];
            if(inverse<0)
                inverse+=mList.get(i);
            inverseList.add(inverse);
        }
        //X = ak* MK*inverseListk
        int result=0;
        for(int i=0;i<aList.size();i++)
            result+=aList.get(i)*MK.get(i)*inverseList.get(i);
        return (int) (result % mProduct);
    }
    /*
    returns true if it's probably prime
    returns false if it's not prime
    */
    static boolean millerTest(int number){
        if(number<=1||number%2==0)
            return false;
     //  as it's working from n>3
        if(number<=3)
            return true;
        //number-1=2^k * r
        int d=number-1;
        while(d%2==0)
            d/=2;
        for(int i=0;i<10;i++){
            //getting random number in range [2,..,n-1]
            int a=2+(int) (Math.random()%(number-4));
            //x= a^d %number 
            int x=modularExponentiaton(a, d, number);
            while(d!=number-1){
                x=(x*x)%number;
                d*=2;
                if(x==1)
                    return false;
                if(x==number-1)
                    return true;
            }
        }

        return false;
    }
    static int modularExponentiaton(int b, int n, int m) {
        int c = 1;
        for (int i = 1; i <= n; i++) {
            c = c*b % m;
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
    try{    for(;;){
            System.out.println("Welcome to assignment 3");
            System.out.println("Choose method :");
            System.out.printf("1) sieve of Eratosthenes.\n2) Trial Division.\n3) extended Euclidean Algorithm.\n4) Chinese remainder theorem.\n5) Miller's test\n");
            System.out.println("6) Exit.");
            System.out.print("Enter choice :");
            int choice=in.nextInt();
            switch(choice){
                case 1:
                    System.out.println();
                    System.out.print("Enter the required limit:");
                    int limit=in.nextInt();
                    System.out.print("The primes till "+limit+" are :\n"+sieveOfEratosthenes(limit).toString());
                    break;
                case 2: 
                    System.out.print("Enter the number :");
                    int number=in.nextInt();
                    if(trialDivision(number))
                        System.out.println("The number "+number+" is prime.");
                    else
                        System.out.println("The number "+number+" is not prime.");
                    break;
                case 3: 
                    System.out.print("Enter the first number :");
                    int a=in.nextInt();
                    System.out.print("Enter the second number :");
                    int b=in.nextInt();
                    int[] result=extendedEuclideanAlgorithm(a, b);
                    System.out.println("GCD is :"+result[0]);
                    System.out.println("Bezout's coefficients are : s = "+result[1]+" t = "+result[2]);
                    break;
                case 4:
                    System.out.println("As equations has the form :");
                    System.out.println("\tX=a1(mod m1) ... X=an(mod mn).");
                    System.out.println("Enter input in this form : (a1,m1),(a2,m2),..,(an,mn)");
                    String input=in.next();
                    String[] points=input.split(",");
                    List<Integer> aList=new ArrayList<>();
                    List<Integer> mList=new ArrayList<>();
                    for(int i=0;i<points.length;i+=2){
                        int first=Integer.valueOf(points[i].trim().substring(1).trim());
                        int second=Integer.valueOf(points[i+1].trim().substring(0,points[i+1].trim().length()-1).trim());
                        aList.add(first);
                        mList.add(second);
                    }
                    System.out.print("The solution is : "+chineseRemainderTheorem(aList, mList));
                    break;
                case 5:
                    System.out.print("Enter The number you want to test :");
                    int n=in.nextInt();
                    if(millerTest(n))
                        System.out.println("The number "+n+" is probably prime.");
                    else
                        System.out.println("The number "+n+" is not prime");
                    break;
                case 6: 
                    System.out.println("It ain't much but honest work");
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }

           System.out.print("\n\nEnter -1 to exit or any other number to continue :");
           int x=in.nextInt();
           if(x==-1)
                break;
        }
    }catch(Exception e){
        System.out.println("Invalid Input ..., Goodbye");
    }
    }
}