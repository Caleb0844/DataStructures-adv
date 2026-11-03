package QuickSort;

public class Main {
    public static void main(String[] args) {
        int [] array = {3,6,2,8,7,9,1,4,5};

        quickSort(array, 0, array.length -1);

        for(int i:array){
            System.out.println(i + " ");
        }
    }

    private static void quickSort(int[] array, int start, int end) {
    
    if(end <=start) return ; //bas case

    int pivot = partition(array, start, end);
    quickSort(array, start, pivot -1);
    quickSort(array, pivot + 1, end);
    }
        private static int partition(int[] array, int start, int end) {

            int pivot = array[end];
            int i = start - 1;

            for(int j = start; j<= end -1;j++){
                if(array[j] < pivot){
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            i++;
            int temp = array[i];
            array[i] = array[end];
            array[end] = temp;

            return i; 

    }
}
// quick sort = moves smaller elements in the left of the pivot.
//             recursively divide array into two partitions

//             runtime complexity = Best case O(n (log n))
//                                 Average case O(n(log n))
//                                 Worst case O(n(pow)2)
// space complexity = O(log(n)) due to recursion
