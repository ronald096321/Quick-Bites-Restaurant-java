import java.util.Scanner;

class restarantApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Quick-Bites Restaurant");
        boolean whileContinue = true;
        while (whileContinue) {
                    System.out.println("1 to Place Order");
                    System.out.println("2 to Cancel Order");
                    System.out.println("3 to Track and report daily collection");
                    System.out.println("4 to Exit");
                    System.out.print("Please select your option :");
                    int optionScelected = sc.nextInt();
                      order order = new order();
                    if(optionScelected < 4){
                        switch (optionScelected) {
                            case 1:
                                order.placeOrder();
                                System.out.println();
                                System.out.println();
                                break;
                            case 2:
                                order.cancelOrder();
                                System.out.println();
                                System.out.println();
                                break;
                            case 3:
                                order.trackCollection();
                                System.out.println();
                                System.out.println();
                                break;
                            default:
                                break;
                        }
                    } else {
                        whileContinue = false;
                        break;
                    }
            
        }
    }
}