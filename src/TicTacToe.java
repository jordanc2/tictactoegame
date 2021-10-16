import java.nio.charset.StandardCharsets;
import java.util.*;


public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},};

        printGameBoard(gameBoard);

        while (true) {
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.println("Enter your placement: (1 - 9)");
            int playerPosition = scanner.nextInt();
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position taken! Please enter a correct position.");
                playerPosition = scanner.nextInt();
            }

            placePiece(gameBoard,playerPosition, "player"); //player's turn

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                cpuPosition = random.nextInt(9) +1;
            }
            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }
    }

    /**
     * Prints game board.
     */
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) { //for each row inside of the gameboard
            for (char c : row) { //for each symbol inside of each row
                System.out.print(c); //print out the symbol
            }
            System.out.println();
        }

    }

    /**
     * This method place's either the player's or cpu's pieces on the game board
     * @param gameBoard creates the game board from the 2D char array
     * @param position position (1-9) player wishes to set game piece
     * @param user checks for player or cpu
     */
    public static void placePiece(char[][] gameBoard,  int position, String user) {

        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu"))
            symbol = 'O';
            cpuPositions.add(position);


        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftColumn);
        winningConditions.add(middleColumn);
        winningConditions.add(rightColumn);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List l : winningConditions) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry, maybe next time! Mate!";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "CAT!";
            }
        }
        return "";
    }

}
