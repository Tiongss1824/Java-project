// B240036A
// Tiong Teck Hang
// BoS24-A1
// 8/12/2024
// Project
// Tic Tac Toe

// import scanner
// import random
import java.util.Random;
import java.util.Scanner;

public class BTPR1003_2024C_Project_B240036A{
    // create the shape of the board 3x3
    public static char[][] tttboard(){
        char[][] board = new char[3][3];
        int numberinboard = 1;

        // set up board
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = (char) (numberinboard + '0');
                numberinboard++;
            }
        }

        // return
        return board;
    }

    // user can see the board
    public static void showBoard(char[][] board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]);
                if(j < 2){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i < 2){
                System.out.println("-----");
            }
        }
    }

    // PLayer play
    public static boolean moving(char[][] board, int row, int column, char symbol){
        // player can put X or O if it is empty
        if(board[row][column] != 'X' && board[row][column] != 'O'){
            board[row][column] = symbol;
            return true;
        }
        else{
            System.out.println("Already taken, try another one.");
            return false;
        }
    }

    // check for winner
    public static boolean Winner(char board[][], char symbol){
        // to check who win on row or column
        for(int i = 0; i < 3; i++){
            if((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) || (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)){
                return true;
            }
        }

        // to check who win on bevel
        if((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) || (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)){
            return true;
        }
        return false;
    }

    //check whether all places are full with xo
    public static boolean BoardFull(char[][] board){
        // if not full return false, until full then return true
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] != 'X' && board[i][j] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }

    // For singleplayer
    public static int[] computerRandom(char[][] board) {
        Random computer = new Random();
        int row, column;
        boolean validMove = false;

        // Loop until a valid move is found
        do {
            row = computer.nextInt(3);
            column = computer.nextInt(3);

            // Check if the cell is not already occupied
            char cell = board[row][column];
            if (cell != 'X' && cell != 'O') {
                validMove = true;
            }
        } while (!validMove);

        return new int[]{row, column};
    }
    
    // The group that run the game, others are set up
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String playAgain;
        String mode;
        
        do{
            // Choose mode
            System.out.print("Which mode are you choosing, 'AI' or 'PLAYER'?: ");
            mode = input.next().toUpperCase();
        
            char[][] board = tttboard();
            boolean gameStarting = true;
            char currentPlayer = 'X';
    
            while (gameStarting) {
                showBoard(board);
        
                // Player or AI turn
                if (mode.equals("PLAYER") || (mode.equals("AI") && currentPlayer == 'X')){
                    System.out.println("Player " + currentPlayer + ", it's your turn.");
                    System.out.println("Enter a number to place your symbol:");
                    int position = input.nextInt();
        
                    // Validate the inputs to ensure is 1 to 9
                    if (position < 1 || position > 9){
                        System.out.println("The number is out of range, try again.");
                        continue;
                    }
        
                    // Convert position to row and column
                    int row = (position - 1) / 3;
                    int column = (position - 1) % 3;
        
                    // Validate move
                    if (!moving(board, row, column, currentPlayer)){
                        continue;
                    }
                }
                else if (mode.equals("AI") && currentPlayer == 'O'){
                    System.out.println("Computer's turn (O):");
                    int[] aiMove = computerRandom(board);
                    moving(board, aiMove[0], aiMove[1], 'O');
                }
        
                // Check for winner or draw
                if (Winner(board, currentPlayer)){
                    showBoard(board);
                    System.out.println("Player " + currentPlayer + " wins! Congratulations!");
                    gameStarting = false;
                }
                else if (BoardFull(board)){
                    showBoard(board);
                    System.out.println("It's a tie. Well done.");
                    gameStarting = false;
                }
                else{
                    // Switch player
                    if(currentPlayer == 'X'){
                        currentPlayer = 'O';
                    }
                    else{
                        currentPlayer = 'X';
                    }
                }
            }
        
            System.out.println("Do you want to play again? Y/N");
            playAgain = input.next();
        }
        while(playAgain.equalsIgnoreCase("Y"));
        
        System.out.println("Thank you for playing!");
        input.close();
    }
}