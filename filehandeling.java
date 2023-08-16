import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

interface fileMethods {
     ArrayList<item> getAllMenuItems();

     void addOrdersToFile();

     ArrayList<item> getAllOrderItems();
    
     void updateOrder();

     void generateReport();

     int generateOrderId();
    
}

public class filehandeling {

        public  ArrayList<item> getAllMenuItems() {
            try (BufferedReader reader = new BufferedReader(new FileReader("menuitems.csv"))) {
                String line;
                boolean isFirstLine = true;
                // item[] menuList;
                ArrayList<item> menuList = new ArrayList<item>();
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Skip the header line
                    }

                    String[] menuItems = line.split(",");
                    int id = Integer.parseInt(menuItems[0].trim());
                    String name = menuItems[1].trim();
                    double price = Double.parseDouble(menuItems[2].trim());
                    menuList.add(new item(id, name, price));
                }
                return menuList;
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                return null;
            }
        }
        
        public void addOrdersToFile(orderItem order) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("orders.csv", true));
                File f = new File("orders.csv");
                if (f.length() == 0) {
                    bw.write("OrderId,MenuItems,Total Quantities,Date,TotalPrice,Status");
                    bw.newLine();
                }
                String menuItems = order.menuItems.toString().replaceAll("\\[|\\]|,","");
                bw.write(order.OrderId + "," + menuItems + "," + order.quantities + "," + order.date + "," +order.totalBill +","
                        + order.status);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
        
        public ArrayList<item> getAllOrderItems() {
            try (BufferedReader reader = new BufferedReader(new FileReader("orders.csv"))) {
                String line;
                boolean isFirstLine = true;
                // item[] menuList;
                ArrayList<item> orderList = new ArrayList<item>();
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %15s %15s %15s %15s", "OrderId", "MenuItems", "Total Quantities",
                            "Date", "TotalPrice", "Status");
                             System.out.println();
                    System.out.println("---------------------------------------------------------------------------------------");
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Skip the header line
                    }

                    String[] menuItems = line.split(",");
                    int id = Integer.parseInt(menuItems[0]);
                    String items = menuItems[1].trim();
                    int quantity = Integer.parseInt(menuItems[2]);
                    String date = menuItems[3].trim();
                    double tatalPrice = Double.parseDouble(menuItems[4].trim());
                    String status = menuItems[5].trim();

                    System.out.printf("%5s %15s %15s %15s %15s %15s", id, items, quantity,
                            date, tatalPrice, status);
                            System.out.println();
                }
                return orderList;
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                return null;
            }
        }
        
        public void updateOrder(int updateId) {
            try (BufferedReader reader = new BufferedReader(new FileReader("orders.csv"))) {
                String line;
                boolean isFirstLine = true;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Skip the header line
                    }

                    String[] menuItems = line.split(",");
                     int id = Integer.parseInt(menuItems[0]);
                    String items = menuItems[1].trim();
                    int quantity = Integer.parseInt(menuItems[2]);
                    String date = menuItems[3].trim();
                    double tatalPrice = Double.parseDouble(menuItems[4].trim());
                    String status = menuItems[5].trim();

                    if (id == updateId) {
                        String updatedString =  id + "," + items + "," + quantity + "," + date + ","+ tatalPrice + "," + "Cancelled";
                            Path path = Paths.get("orders.csv");
                            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                            lines.set(counter + 1, updatedString);
                            Files.write(path, lines, StandardCharsets.UTF_8);        
                    }
                    counter++;
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }

        public int generateOrderId() {
            try {
                Path filePath = Paths.get("orders.csv");
                Charset charset = StandardCharsets.UTF_8;
                List<String> allItems = Files.readAllLines(filePath, charset);
                System.out.println("size" + allItems.size());
                if (allItems.size() == 0) {
                    return 0;
                } else if (allItems.size() == 2) {
                    return 1;
                } else {

                    return allItems.size() - 1;
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                return 0;
            }
        }
       
          public void generateReport() {
            try (BufferedReader reader = new BufferedReader(new FileReader("orders.csv"))) {
                String line;
                boolean isFirstLine = true;
                int totalQuantity = 0;
                double totalRevenue = 0;
                String Date="";
                    System.out.println("---------------------------------------------------");
                    System.out.printf("%15s %15s %15s", "Date", "Total Items Sold", "Total Revenue");
                             System.out.println();
                    System.out.println("--------------------------------------------------- ");
                    while ((line = reader.readLine()) != null) {
                        if (isFirstLine) {
                            isFirstLine = false;
                            continue; // Skip the header line
                        }

                        String[] menuItems = line.split(",");
                        String status = menuItems[5].trim();
                        if(status ==  "Completed"){
                        Date = menuItems[3].trim();
                        totalQuantity += Integer.parseInt(menuItems[2]);
                        totalRevenue +=  Double.parseDouble(menuItems[4].trim());
                        }

                    }
                 System.out.printf("%5s %15s %15s", Date, totalQuantity, totalRevenue);
                 System.out.println();
                 System.out.println();
                   System.out.println();
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
    
            }
        }

}
