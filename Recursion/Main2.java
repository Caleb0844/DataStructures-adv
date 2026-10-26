package Recursion;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    private static int factorial(int nums) {
        if(nums < 1) return 1; //base case
        return nums * factorial(nums - 1);//recursive case

    }

    
}
