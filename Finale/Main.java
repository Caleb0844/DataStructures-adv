package Finale;
//Find he runtime of a programme

public class Main {
    public static void main(String[] args) {
        
        long start = System.nanoTime();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //programme

        long duration = (System.nanoTime() - start)/1000000;
        System.out.println(duration + "ms");
    }
    
}
