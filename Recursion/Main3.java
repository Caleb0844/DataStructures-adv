package Recursion;

public class Main3 {
    public static void main(String[] args) {
       System.out.println( power(5, 2));
    }

    private static int power(int num1, int num2) {
        if(num2 < 1) return 1;//base case
        return num1 * power(num1, num2 - 1);//recursive case
    }
    
}
