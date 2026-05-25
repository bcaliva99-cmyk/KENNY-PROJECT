import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
=========================================================
              DAILY SALES REPORTING SYSTEM
=========================================================

FEATURES:
✔ Add Sales Record
✔ View Sales Records
✔ Search Sales Record
✔ Update Sales Record
✔ Delete Sales Record
✔ Save Data to File
✔ Load Data from File
✔ Error Handling & Input Validation

TOPICS APPLIED:
✔ Methods
✔ Arrays
✔ Strings
✔ File Handling
✔ Error Handling

=========================================================
INSTRUCTIONS TO RUN IN VISUAL STUDIO CODE:

1. Open Visual Studio Code
2. Install Java Extension Pack
3. Create a file named:
   DailySalesReportingSystem.java
4. Copy and paste this code
5. Run the program

=========================================================
*/

public class DailySalesReportingSystem {

    // Maximum records
    static final int MAX = 100;

    // Arrays
    static String[] productNames = new String[MAX];
    static int[] quantities = new int[MAX];
    static double[] prices = new double[MAX];
    static double[] totals = new double[MAX];

    // Record counter
    static int count = 0;

    // Scanner object
    static Scanner scanner = new Scanner(System.in);

    // Main Method
    public static void main(String[] args) {

        // Load saved records
        loadData();

        int choice;

        do {

            displayMenu();

            choice = getIntInput();

            switch (choice) {

                case 1:
                    addRecord();
                    break;

                case 2:
                    viewRecords();
                    break;

                case 3:
                    searchRecord();
                    break;

                case 4:
                    updateRecord();
                    break;

                case 5:
                    deleteRecord();
                    break;

                case 6:
                    saveData();
                    break;

                case 7:
                    saveData();
                    System.out.println("\nProgram Closed Successfully!");
                    break;

                default:
                    System.out.println("\nInvalid Choice! Try Again.");
            }

        } while (choice != 7);
    }

    // =====================================================
    // DISPLAY MENU
    // =====================================================
    public static void displayMenu() {

        System.out.println("\n=================================================");
        System.out.println("         DAILY SALES REPORTING SYSTEM");
        System.out.println("=================================================");
        System.out.println("1. Add Sales Record");
        System.out.println("2. View Sales Records");
        System.out.println("3. Search Sales Record");
        System.out.println("4. Update Sales Record");
        System.out.println("5. Delete Sales Record");
        System.out.println("6. Save Records");
        System.out.println("7. Exit");
        System.out.println("=================================================");
        System.out.print("Enter Choice: ");
    }

    // =====================================================
    // ADD RECORD
    // =====================================================
    public static void addRecord() {

        if (count >= MAX) {

            System.out.println("\nStorage Full!");
            return;
        }

        scanner.nextLine();

        try {

            System.out.print("\nEnter Product Name: ");
            productNames[count] = scanner.nextLine();

            System.out.print("Enter Quantity Sold: ");
            quantities[count] = getIntInput();

            System.out.print("Enter Product Price: ");
            prices[count] = getDoubleInput();

            // Calculate total
            totals[count] = quantities[count] * prices[count];

            count++;

            System.out.println("\nSales Record Added Successfully!");

        } catch (Exception e) {

            System.out.println("\nError Adding Record!");
        }
    }

    // =====================================================
    // VIEW RECORDS
    // =====================================================
    public static void viewRecords() {

        if (count == 0) {

            System.out.println("\nNo Sales Records Found!");
            return;
        }

        double grandTotal = 0;

        System.out.println("\n================ SALES REPORT ====================");

        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n",
                "No", "Product", "Qty", "Price", "Total");

        System.out.println("--------------------------------------------------");

        for (int i = 0; i < count; i++) {

            System.out.printf("%-5d %-20s %-10d %-10.2f %-10.2f\n",
                    (i + 1),
                    productNames[i],
                    quantities[i],
                    prices[i],
                    totals[i]);

            grandTotal += totals[i];
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("GRAND TOTAL SALES: %.2f\n", grandTotal);
    }

    // =====================================================
    // SEARCH RECORD
    // =====================================================
    public static void searchRecord() {

        if (count == 0) {

            System.out.println("\nNo Records Available!");
            return;
        }

        scanner.nextLine();

        System.out.print("\nEnter Product Name to Search: ");
        String search = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (productNames[i].equalsIgnoreCase(search)) {

                System.out.println("\n=========== RECORD FOUND ===========");
                System.out.println("Product Name : " + productNames[i]);
                System.out.println("Quantity     : " + quantities[i]);
                System.out.println("Price        : " + prices[i]);
                System.out.println("Total Sales  : " + totals[i]);

                found = true;
            }
        }

        if (!found) {

            System.out.println("\nRecord Not Found!");
        }
    }

    // =====================================================
    // UPDATE RECORD
    // =====================================================
    public static void updateRecord() {

        if (count == 0) {

            System.out.println("\nNo Records Available!");
            return;
        }

        viewRecords();

        System.out.print("\nEnter Record Number to Update: ");
        int index = getIntInput() - 1;

        if (index < 0 || index >= count) {

            System.out.println("\nInvalid Record Number!");
            return;
        }

        scanner.nextLine();

        try {

            System.out.print("Enter New Product Name: ");
            productNames[index] = scanner.nextLine();

            System.out.print("Enter New Quantity: ");
            quantities[index] = getIntInput();

            System.out.print("Enter New Price: ");
            prices[index] = getDoubleInput();

            totals[index] = quantities[index] * prices[index];

            System.out.println("\nRecord Updated Successfully!");

        } catch (Exception e) {

            System.out.println("\nError Updating Record!");
        }
    }

    // =====================================================
    // DELETE RECORD
    // =====================================================
    public static void deleteRecord() {

        if (count == 0) {

            System.out.println("\nNo Records Available!");
            return;
        }

        viewRecords();

        System.out.print("\nEnter Record Number to Delete: ");
        int index = getIntInput() - 1;

        if (index < 0 || index >= count) {

            System.out.println("\nInvalid Record Number!");
            return;
        }

        // Shift records
        for (int i = index; i < count - 1; i++) {

            productNames[i] = productNames[i + 1];
            quantities[i] = quantities[i + 1];
            prices[i] = prices[i + 1];
            totals[i] = totals[i + 1];
        }

        count--;

        System.out.println("\nRecord Deleted Successfully!");
    }

    // =====================================================
    // SAVE DATA
    // =====================================================
    public static void saveData() {

        try {

            PrintWriter writer =
                    new PrintWriter(new FileWriter("sales.txt"));

            for (int i = 0; i < count; i++) {

                writer.println(
                        productNames[i] + "," +
                        quantities[i] + "," +
                        prices[i] + "," +
                        totals[i]
                );
            }

            writer.close();

            System.out.println("\nData Saved Successfully!");

        } catch (IOException e) {

            System.out.println("\nError Saving File!");
        }
    }

    // =====================================================
    // LOAD DATA
    // =====================================================
    public static void loadData() {

        File file = new File("sales.txt");

        if (!file.exists()) {

            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                productNames[count] = data[0];
                quantities[count] =
                        Integer.parseInt(data[1]);

                prices[count] =
                        Double.parseDouble(data[2]);

                totals[count] =
                        Double.parseDouble(data[3]);

                count++;
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("\nError Loading File!");
        }
    }

    // =====================================================
    // INTEGER INPUT VALIDATION
    // =====================================================
    public static int getIntInput() {

        while (true) {

            try {

                return Integer.parseInt(scanner.next());

            } catch (NumberFormatException e) {

                System.out.print("Invalid Input! Enter Number: ");
            }
        }
    }

    // =====================================================
    // DOUBLE INPUT VALIDATION
    // =====================================================
    public static double getDoubleInput() {

        while (true) {

            try {

                return Double.parseDouble(scanner.next());

            } catch (NumberFormatException e) {

                System.out.print("Invalid Input! Enter Valid Price: ");
            }
        }
    }
}