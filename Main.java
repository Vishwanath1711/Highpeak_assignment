import java.io.*;// importing necessary libraries
import java.util.*;
//class of goodies that HR wants to distribute
class Item {
  String name;
  int price;
//name and price of all goodies
  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;//making name of the goodie with price as string or one item in collection of goodies 
  }
}

public class Main {
  public static void main(final String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("C:\\Users\\Lenovo\\Music\\input.txt");//reading input from file       
    Scanner sc=new Scanner(fis);
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);//number of employees to whom HR will distribute the goodies
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();//collections to store the name and price of goodies

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));// adding items or goodies to the collection until no goodie is left
    }
    sc.close();
// sorting the items in collection based on the minimum difference 
    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; 
      } 
    });

    int min_diff = goodies_items.get(goodies_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

//returning the goodies with the minimum difference of price
      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    
//writing output to file 
    FileWriter fw = new FileWriter("C:\\Users\\Lenovo\\Music\\output.txt");
    fw.write("The goodies selected for distribution are:\n\n");

//displaying the goodies with the minimum price difference to distribute employees 
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }
 
    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }
}