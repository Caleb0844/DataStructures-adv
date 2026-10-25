package BubbleSort;

public class Main {
    public static void main(String[] args) {

        int [] array = {1,6,9,2,5,3,8,4,7};

        bubbleSort(array);

        for(int i : array){
            System.out.println(i);
        }
    }
    public static void bubbleSort(int array[]) {
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length -i -1; j++){
                if(array[j] < array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j + 1] = temp;
                }

            }
        }
        
    }
    
}
