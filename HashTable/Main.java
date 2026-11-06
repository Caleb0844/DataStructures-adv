//hash tables has key value pairs
package HashTable;
import java.util.*;

public class Main {
    public static void main(String args[]){

        Hashtable<String, String> table = new Hashtable<String, String>(21);

        table.put("100", "Eleven");
        table.put("123", "Mike");
        table.put("321", "Dustin");
        table.put("555", "Luca");
        table.put("777", "Will");

       // table.remove(777);

        for(String key : table.keySet()){
            System.out.println(key.hashCode() % 21  + "\t" + key + "\t" + table.get(key));

        }
    }
    
}

// Hashtable = A datastructure that stores unique key to values eg <Interger, String>
//             Each key/value pair is nown as an entry
//             FAST Insertion, look up, deletion of key/value pairs
//             Not ideal for small datasets great with large datasets
// Hashing = takes a key and computes an interger (formula will vary based on the key and data type)
//           In a hashtable we use hash % capacity to calculate an index numer

//           key.hashCode() % capacity = index

// Bucket = an indexed storage location for one or more entries
//          Can store multiple entries in case of a collision between hash values (linked similay to linked list)

// Collision = hash function generates the same index for more than one key
//             less collisions = more efficiency

// Runtime complexity : Best Case 0(1)
//                      Worst CaseO(n)