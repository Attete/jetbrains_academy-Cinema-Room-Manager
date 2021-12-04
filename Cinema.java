package cinema;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static int numberOfRows;
    static int numberOfSeatsInEachRow;
    static int totalNumberOfSeats;
    static int totalTicketsPrice;
    static int chosenRowNumber;
    static int chosenSeatNumber;
    static int ticketPrice;
    static int menuOptionSelection;
    static int purchasedTicketCounter;
    static int currentIncome;
    static char[][] grid;


    public static void main(String[] args) {
        createCinemaGrid();
        printMenu();

    }

    private static void createCinemaGrid() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeatsInEachRow = scanner.nextInt();
        totalNumberOfSeats = numberOfRows * numberOfSeatsInEachRow;

        // creates the Cinema grid
        grid = new char[numberOfRows][numberOfSeatsInEachRow];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInEachRow; j++) {
                grid[i][j] = 'S';
            }
        }
        calculateTicketPrice();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        menuOptionSelection = scanner.nextInt();
        switch (menuOptionSelection) {
            case 1: // Show the seats
                printCinemaHeader();
                printCinemaGrid(grid);
                printMenu();
                break;
            case 2: // Buy a ticket
                buyTicket(grid);
                printMenu();
                break;
            case 3: // Statistics
                statistics();
                printMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option");
        }
    }


    private static void calculateTicketPrice() { // method calculating individual ticket price and total cost of all tickets
        if (totalNumberOfSeats < 60) {
            ticketPrice = 10;
            totalTicketsPrice = totalNumberOfSeats * 10;
        } else if (numberOfRows % 2 == 0) { //even number of rows
            int temp = numberOfRows / 2;
            totalTicketsPrice = (temp * numberOfSeatsInEachRow * 10) + (temp * numberOfSeatsInEachRow * 8);
            if (chosenRowNumber <= temp) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else {                           // odd number of rows
                 // front seats
            if ((numberOfRows == 3 && chosenRowNumber <= 1) || (numberOfRows == 5 && chosenRowNumber <= 2)
                    || (numberOfRows == 7 && chosenRowNumber <= 3) || (numberOfRows == 9 && chosenRowNumber <= 4)) {
                ticketPrice = 10;
            } else { //back seats
                ticketPrice = 8;
            }

        }

        // calculates total cost of all tickets
        int temp = numberOfRows / 2;
        switch (temp) {
            case 0:
                totalTicketsPrice = numberOfSeatsInEachRow * 10;
                ticketPrice = 10;
                break;
            case 1:
                totalTicketsPrice = (numberOfSeatsInEachRow * 10) + (2 * numberOfSeatsInEachRow * 8);
                break;
            case 2:
                totalTicketsPrice = (2 * numberOfSeatsInEachRow * 10) + (3 * numberOfSeatsInEachRow * 8);
                break;
            case 3:
                totalTicketsPrice = (3 * numberOfSeatsInEachRow * 10) + (4 * numberOfSeatsInEachRow * 8);
                break;
            case 4:
                totalTicketsPrice = (4 * numberOfSeatsInEachRow * 10) + (5 * numberOfSeatsInEachRow * 8);
                break;
            default:
                System.out.println("Something went wrong");
        }
    }

    private static void buyTicket(char[][] grid) { // method selecting seat in the Cinema grid and marking it 'B' = taken
        System.out.println();
        System.out.println("Enter a row number:");
        chosenRowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenSeatNumber = scanner.nextInt();
        calculateTicketPrice();

        if (chosenRowNumber > numberOfRows || chosenSeatNumber > numberOfSeatsInEachRow) {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket(grid);
        } else if (grid[chosenRowNumber - 1][chosenSeatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(grid);
        } else  {
            grid[chosenRowNumber - 1][chosenSeatNumber - 1] = 'B';
            purchasedTicketCounter++;
            currentIncome += ticketPrice;
            System.out.println();
            System.out.println("Ticket price: $" + ticketPrice);
        }
    }

    private static void printCinemaHeader() { // method printing the Cinema header and 1st line of seat numbers
        System.out.println();
        System.out.println("Cinema:");
        // Print numbers at the top of the room plan
        System.out.print("  ");
        for (int i = 1; i <= numberOfSeatsInEachRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void printCinemaGrid(char[][] grid) { // method printing the Cinema grid
        for (int i = 0; i < numberOfRows; i++) { // row number
            System.out.print(i + 1 + " ");

            for (int j = 0; j < numberOfSeatsInEachRow; j++) { // columns
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void statistics() {
        System.out.println();
        calculateTicketPrice();
        System.out.println("Number of purchased tickets: " + purchasedTicketCounter);
        System.out.printf("Percentage: %.2f", (purchasedTicketCounter * 100.00) / totalNumberOfSeats);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalTicketsPrice);
    }
}

