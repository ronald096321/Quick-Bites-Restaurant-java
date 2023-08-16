import java.util.ArrayList;

interface MenuItems {
    public void showMenuItems();
}

class item {
    int menuId;
    String name;
    double price;
    String Category = "";
    String Description = "";

    item(int id, String name, double price) {
        this.menuId = id;
        this.name = name;
        this.price = price;
    }
}

public class menuitem extends filehandeling implements MenuItems {
    
    public void showMenuItems() {
        filehandeling file = new filehandeling();
        ArrayList<item> menuList = file.getAllMenuItems();
        System.out.println("-----------------------------------");  
        System.out.printf("%5s %12s %10s", "Menu ID", "Name", "Price");
        System.out.println();
        System.out.println("-----------------------------------");  
        for (item item : menuList) {
            System.out.printf("%5s %12s %10s", item.menuId, item.name, item.price);
            System.out.println();
        }
        System.out.println("-----------------------------------");  
    }


}
