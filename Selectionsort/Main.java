package Selectionsort;

public class Main {
    public static void main(String[] args) {
        int [] array = {4,7,5,2,1,9,3,8,6};

        selectionSort(array);
        for(int i : array){
        System.out.println(i);
        }
    }

    public static void selectionSort(int array[]){
        for(int i = 0; i<array.length; i++){
            int min = i;
            for(int j = i +1; j < array.length; j++){
                if(array[min] < array[j]){
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}
