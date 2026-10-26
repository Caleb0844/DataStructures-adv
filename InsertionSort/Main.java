package InsertionSort;

public class Main {
    public static void main(String[] args) {
        int[] array = {4,2,8,5,7,9,1,3,6};

        insertionSort(array);
        for(int i : array){
            System.out.println(i);
        }
    }

    public static void insertionSort(int array[]){
        for(int i = 0; i < array.length; i++){
            int temp = array[i];
            int j = i - 1;

            while(j>=0 && array[j] <temp){
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = temp;

                }
            }
        }
    
    

