//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.Scanner;
import java.util.ArrayList;
public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  String name;
  Product[] stock; //Array to hold all products
  double revenue;
  private int salesNumber ;
  ArrayList <Product> currentCart;
  private double cartAmount;
  private double dollarPerSale;

  
  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    salesNumber = 0;
    currentCart = new ArrayList<>();
    cartAmount = 0.0;
  }
  
  public String getName(){
    return name;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     return true;
    }
    return false;
  }
  
  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      revenue += stock[index].sellUnits(amount);
    }
  }
  
  public double getRevenue(){
    return revenue;
  }
  public int getSalesNumber() { return salesNumber;}
  public double getCartAmount() { return cartAmount; }
  public double getDollarPerSale() {return dollarPerSale;}

  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }

  public void addToCart(int index){
    // This temp variable holds the number of times the item was added to cart in one go.
    int temp = 0;
    Product p = stock[index];
    for(int i = 0; i < currentCart.size(); i++){
      if(p == currentCart.get(i)){
        temp += 1;
      }
    }
    // If and only if the stock is greater than 0 and the number of times it's been added is less than stock quantity, method adds product to cart.
    if(stock[index].getStockQuantity() > 0 && (temp < stock[index].getStockQuantity())) {
      currentCart.add(p);
      cartAmount += stock[index].getPrice();
    }
  }

  public void removeFromCart(int index){
    cartAmount -= currentCart.get(index).getPrice();
    currentCart.remove(index);
  }

  public void completeSale(){
    salesNumber += 1;
    for(Product p : currentCart){
      for(int i = 0; i < stock.length; i++){
        if(p == stock[i] && (stock[i].getStockQuantity() > 0)){
          sellProducts(i, 1);
        }
      }
    }
    // Clears cart after sale, sets the cart value to zero and calculates $/Sale value.
    currentCart.clear();
    cartAmount = 0;
    dollarPerSale = revenue / salesNumber;
  }

  public Product[] determineMostPopular(){
    // I created an array of size 3 called popItemArray to hold the 3 most popular items.
    Product[] popItemArray = new Product[3];
    // First it iterates through stock array and adds each item ( except null ) to a temporary ArrayList called tempArray I created.
    ArrayList <Product> tempArray = new ArrayList<>();
    for(int i = 0; i < stock.length; i++){
      if(stock[i] != null){
        Product p = stock[i];
        tempArray.add(p);
      }
    }
    /* Iterates through tempArray and finds item with most popularity by checking its sold quantity variable
    and adds it to the popItemArray I created, and then removes it from tempArray. Largest variable hold the highest sold quantity and
    the j variable holds the corresponding index for the product which has highest sold. */
    int largest = 0;
    int j = 0;
    for(int i = 0; i < tempArray.size(); i++){
      if (tempArray.get(i).getSoldQuantity() > largest){
        j = i;
        largest = tempArray.get(i).getSoldQuantity();
      }
    }
    popItemArray[0] = tempArray.get(j);
    tempArray.remove(j);

    // Does the same thing, but since the most popular was removed, the second most popular is the most popular now.
    int largest2 = 0;
    int j2 = 0;
    for(int i = 0; i < tempArray.size(); i++){
      if (tempArray.get(i).getSoldQuantity() > largest2){
        j2 = i;
        largest2 = tempArray.get(i).getSoldQuantity();
      }
    }
    popItemArray[1] = tempArray.get(j2);
    tempArray.remove(j2);

    // Repeat for third most popular item.
    int largest3 = 0;
    int j3 = 0;
    for(int i = 0; i < tempArray.size(); i++){
      if (tempArray.get(i).getSoldQuantity() > largest3){
        j3 = i;
        largest3 = tempArray.get(i).getSoldQuantity();
      }
    }
    popItemArray[2] = tempArray.get(j3);
    tempArray.remove(j3);

    return popItemArray;
  }

} 