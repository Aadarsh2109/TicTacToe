import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void printBoard(HashMap<Integer,Character> map){
        int changeLine = 0;
        for(int i = 1; i<=9; i++){
            System.out.print("|");
            System.out.print(map.get(i));
            System.out.print("|");
            changeLine++;
            if(changeLine==3){
                System.out.println();
                changeLine = 0;
            }
        }
        return;
    }
    public static boolean calculatePosswin(int i, HashMap<Integer,Integer> map, char player){
        int product = 0;
        if(player == 'x'){
            product = 25;
        }
        else {
            product = 9;
        }
        if(i==1){
            int horizontal = map.get(2) * map.get(3);
            int vertical = map.get(4) * map.get(7);
            int digonal = map.get(5) * map.get(9);
            if(horizontal == product || vertical == product || digonal == product){
                return true;
            }
        }
        if(i==2){
            int horizontal = map.get(1) * map.get(3);
            int vertical = map.get(5) * map.get(8);
            if(vertical == product || horizontal == product){
                return true;
            }
        }
        if(i==3){
            int horizontal = map.get(2) * map.get(1);
            int vertical = map.get(6) * map.get(9);
            int digonal = map.get(5) * map.get(7);
            if(horizontal == product || vertical == product || digonal == product){
                return true;
            }
        }
        if(i==4){
            int horizontal = map.get(1) * map.get(7);
            int vertical = map.get(5) * map.get(6);
            if(vertical == product || horizontal == product){
                return true;
            }
        }
        if(i==5){
            int horizontal = map.get(2) * map.get(8);
            int vertical = map.get(4) * map.get(6);
            int diagonal1 = map.get(1) * map.get(9);
            int diagonal2 = map.get(3) * map.get(7);
            if(horizontal == product || vertical == product || diagonal1 == product || diagonal2 == product){
                return true;
            }
        }
        if(i==6){
            int horizontal = map.get(9) * map.get(3);
            int vertical = map.get(5) * map.get(4);
            if(vertical == product || horizontal == product){
                return true;
            }
        }
        if(i==7){
            int horizontal = map.get(8) * map.get(9);
            int vertical = map.get(4) * map.get(1);
            int digonal = map.get(5) * map.get(3);
            if(horizontal == product || vertical == product || digonal == product){
                return true;
            }
        }
        if(i==8){
            int horizontal = map.get(7) * map.get(9);
            int vertical = map.get(5) * map.get(2);
            if(vertical == product || horizontal == product){
                return true;
            }
        }
        if(i==9){
            int horizontal = map.get(7) * map.get(8);
            int vertical = map.get(3) * map.get(6);
            int digonal = map.get(5) * map.get(1);
            if(horizontal == product || vertical == product || digonal == product){
                return true;
            }
        }
        return false;
    }
    public static int posswin(HashMap<Integer,Character> map, char player){
        HashMap<Integer,Integer> mapInt = new HashMap<>();
        for(int i = 1; i<=9; i++){
            char temp = map.get(i);
            int val = 0;
            if(temp == 'o'){
                val = 3;
            }
            else if(temp == 'x'){
                val = 5;
            }
            else {
                val = 2;
            }
            mapInt.put(i,val);
        }
        for(int i = 1; i<=9; i++){
            if(mapInt.get(i)==2){
                boolean temp = calculatePosswin(i,mapInt,player);
                if(temp){
                    return i;
                }
            }
        }
        return -1;
    }
    public static int make2(HashMap<Integer,Character> map){
        if(map.get(5)=='-'){
            return 5;
        }
        Random random = new Random();
        int temp = random.nextInt(4);
        if(temp == 0){
            if(map.get(1)=='-'){
                return 1;
            }
        }
        if(temp == 1){
            if(map.get(3)=='-'){
                return 3;
            }
        }
        if(temp == 2){
            if(map.get(7)=='-'){
                return 7;
            }
        }
        if(temp == 3){
            if(map.get(9)=='-'){
                return 9;
            }
        }
        return -1;
    }
    public static int makeAnywhere(HashMap<Integer,Character> map){
        Random random = new Random();
        while(true){
            int temp = random.nextInt(8)+1;
            if(map.get(temp)=='-'){
                return temp;
            }
        }
    }
    public static boolean checkGameOver(HashMap<Integer,Character> map){
        int count = 0;
        for(int i = 1; i<=9; i++){
            if(map.get(i) != '-'){
                count++;
            }
        }
        if(count==9){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        HashMap<Integer,Character> boardMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Random randomMove = new Random();
        int num = 49;
        for(int i = 1; i<=9; i++){
            boardMap.put(i,(char)num);
            num++;
        }
        System.out.println("Welcome to Tic-Tac-Toe");
        System.out.println("Use number showen in the board to make a move on that particular place.");
        printBoard(boardMap);
        for(int i = 0; i <= 9; i++){
            boardMap.put(i,'-');
        }
        System.out.println("This is the initial view of the empty board.");
        printBoard(boardMap);
        char player = '-';
        while(true){
            System.out.print("Please select your symbol 'x' or 'o' : ");
            player = sc.next().charAt(0);
            if(!(player=='x'||player=='o')){
                System.out.println("Invalid Selection!");

            }
            else {
                break;
            }
        }

        char computer = 'o';
        if(player == 'o'){
            computer = 'x';
        }
        System.out.println("Now, Let's do a toss to decide who will play first!");
        int randomNumber = randomMove.nextInt(1000);
        if((randomNumber&1)==1){
            randomNumber = 1;
        }
        else {
            randomNumber = 0;
        }
        System.out.print("To choose Head press 0 and to choose Tail press 1 : ");
        int toss = sc.nextInt();
        System.out.println();
        System.out.print("Result of the toss is : ");
        if(randomNumber == 1){
            System.out.println("Tail");
        }
        else {
            System.out.println("Head");
        }
        int moveCount = 1;
        if(toss == randomNumber){
            System.out.println("You have won the toss, now you will play first.");
        }
        else{
            System.out.println("You have lost in toss now computer will play first.");
            int move = randomMove.nextInt(9) + 1;
            boardMap.put(move,computer);
            System.out.println("After first move of computer :");
            printBoard(boardMap);
        }

        while(moveCount<=5){
            System.out.print("Please select the position where you want to mark you symbol : ");
            int position = sc.nextInt();
            if(position<1 || position>9){
                System.out.println("Invalid move");
                continue;
            }
            else if(boardMap.get(position) != '-'){
                System.out.println("This position is already occupied.");
                continue;
            }
            else{
                System.out.println();
                int check = posswin(boardMap,player);
                boardMap.put(position,player);
                if(position==check){
                    printBoard(boardMap);
                    System.out.println("You have won the game!");
                    return;
                }
                if(checkGameOver(boardMap)){
                    break;
                }
                int posswinComputer = posswin(boardMap,computer);
                if(posswinComputer!=-1){
                    boardMap.put(posswinComputer,computer);
                    printBoard(boardMap);
                    System.out.println("Computer have won the game!");
                    return;
                }
                int posswinPlayer = posswin(boardMap,player);
                if(posswinPlayer!=-1){
                    boardMap.put(posswinPlayer,computer);
                }else{
                    int make = make2(boardMap);
                    if(make!=-1){
                        boardMap.put(make,computer);
                    }
                    else {
                        make = makeAnywhere(boardMap);
                        boardMap.put(make,computer);
                    }
                }
                System.out.println("After your move computer has make his own move.");
                System.out.println("Now the board is as :");
                printBoard(boardMap);
                if(checkGameOver(boardMap)){
                    break;
                }
                moveCount++;
            }
        }
        System.out.println("Its draw between you and computer!");
        System.out.println("Thanks for playing.");
    }
}
