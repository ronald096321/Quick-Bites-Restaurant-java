import java.util.ArrayList;
import java.util.Scanner;

interface orders {
    void placeOrder();

    void cancelOrder();

    void trackCollection();

}

class selectedItem {
    int itemId;
    int quantity;

    selectedItem(int id ,int quantity) {
        this.itemId = id;
        this.quantity = quantity;
    }
}

class orderItem {
    ArrayList<String> menuItems;
    int quantities;
    double totalBill;
    int OrderId = 0;
    String date = "";
    String status = "";

    orderItem(ArrayList<String> menu , int quantity , double bill , int id , String date , String status) {
        super();
        this.menuItems = menu;
        this.quantities = quantity;
        this.totalBill = bill;
        this.OrderId = id;
        this.date = date;
        this.status = status;
    }
}

public class order extends filehandeling implements orders {
    order() {
        super();
    }
    @Override
    public void placeOrder() {
        Scanner sc = new Scanner(System.in);
       menuitem menu = new menuitem();
       ArrayList<selectedItem> itemArray = new ArrayList<selectedItem>();
       menu.showMenuItems();
        ArrayList<item> menuList = super.getAllMenuItems();
       System.out.println("Please select the Item from the above menu list");
       boolean addItem = true;
       while (addItem) {
           System.out.println("Press 1 to add item");
           System.out.println("Press 2 to place order");
           System.out.print("Please select your option : ");
           int option = sc.nextInt();
           if (option == 1) {
            System.out.println();
            System.out.print("Please type the Menu ID to select the item to order :");
            int itemSelected = sc.nextInt();
            System.out.print("Please selecte the quantity :");
            int quantity = sc.nextInt();
            itemArray.add(new selectedItem(itemSelected - 1 , quantity));
            System.out.println("Item added successfully !");
            System.out.println();
           } else if(option == 2) {
               if (itemArray.size() > 0) {
                   ArrayList<String> menuItems = new ArrayList<String>();
                   int totalQuantity = 0;
                   double totalPrice = 0;
                   for (selectedItem item : itemArray) {
                       int index = item.itemId;
                       item menu1 = menuList.get(index);
                       menuItems.add(menu1.name);
                   }
                   for (selectedItem item : itemArray) {
                       totalQuantity += item.quantity;
                   }
                    for (selectedItem item : itemArray) {
                       int index = item.itemId;
                       item menu1 = menuList.get(index);
                       totalPrice += menu1.price;
                   }
                   
                   orderItem ode = new orderItem(menuItems, totalQuantity, totalPrice, super.generateOrderId() + 1, "16-08-23", "Completed");
                   super.addOrdersToFile(ode);

                   //    ode.
                   System.out.println("Total Bill :" + totalPrice);
                   System.out.println("Order successfully Placed !");
                   break;
               } else {
                System.out.println("Please add item to place the order ");
              }
           } 
       }

    }
     @Override
     public void cancelOrder() {
         Scanner sc = new Scanner(System.in);
        super.getAllOrderItems();
        System.out.print("Please selecte the order id from above order list to cancel :");
        int id = sc.nextInt();
        super.updateOrder(id);
        System.out.println("Order cancelled");
    }
    @Override
    public void trackCollection(){
        super.generateReport();
    }
}
