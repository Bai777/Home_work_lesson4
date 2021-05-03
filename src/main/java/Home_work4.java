import java.util.Random;
import java.util.Scanner;

public class Home_work4 {

    private static final Scanner SCANNER_DOT_HUMAN = new Scanner(System.in);
    private static final Random randomDotAI = new Random();
    private static char[][] map;
    private static int axisY;
    private static int axisX;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_MAP_EMPTY = '.';


    public static void humanIsWalking(){
        int y, x;

        do {
            System.out.println("You go to human, coordinates Y and X");
            y = SCANNER_DOT_HUMAN.nextInt() - 1;
            x = SCANNER_DOT_HUMAN.nextInt() - 1;
        }while (forValid(y, x));
            map[y][x] = DOT_HUMAN;

    }

    public static void aiIsWalking(){
        int y, x;

        do {
            y = randomDotAI.nextInt(axisY);
            x = randomDotAI.nextInt(axisX);
        }while (forValid(y, x));
        //System.out.println("Компьютер походил в точку " + (y + 1) + " " + (x + 1));

        map[y][x] = DOT_AI;
    }

    private static boolean forValid(int y, int x) {
        if (y < 0 || y >= map.length || x < 0 || x >= map.length) return true;
        return map[y][x] != DOT_MAP_EMPTY;
    }

    public static void sizeMap(){
        System.out.println("Input game size map format\nfirst axis X\nsecond axis Y >>>>");
        Scanner sizeGameMap = new Scanner(System.in);
        while (true) {
            axisX = sizeGameMap.nextInt();
            axisY = sizeGameMap.nextInt();
            if (1 < axisX && 1 < axisY) {
                initMap(axisY, axisX);
                printMap(axisY, axisX);
                break;
            } else System.out.println("There is no point in playing!!!\nEnter correct data");
        }

    }

    public static void initMap(int axisY, int axisX){
        map = new char[axisY][axisX];
        for (int i = 0; i < axisY; i++) {
            for (int j = 0; j < axisX; j++) {
                map[i][j] = DOT_MAP_EMPTY;
            }
        }
    }

    public static void printMap(int axisY, int axisX){

        for (int i = 0; i <= axisX; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int x = 0; x < axisY; x++) {
            System.out.print((x + 1) + "|");

            for (int y = 0; y < axisX; y++) {
                System.out.print(map[x][y] + "|");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void main(String[] args) {
        startGame();
    }

    public static void returnStartGame(){
        int pressToPlayer;
        System.out.println("Would like to play again? Press to 1-yes, 0-no >>>>");
        Scanner scanner = new Scanner(System.in);
        pressToPlayer = scanner.nextInt();
        switch (pressToPlayer){
            case 1:
                startGame();
            case 0:
                System.exit(0);
            default:
                System.out.println("You entered an incorrect value!!!");
        }
    }

    private static void startGame() {
        sizeMap();
        while (true) {
            humanIsWalking();
            printMap(axisY, axisX);
            if (determinesTheWinner()) {
                System.out.println("Winner is Human");
                break;
            }

            if ((isFullMap())){
                System.out.println("Draw");
                break;
            }

            aiIsWalking();
            printMap(axisY, axisX);
            if (determinesTheWinner()){
                System.out.println("Winner is AI");
                break;
            }

            if ((isFullMap())){
                System.out.println("Draw");
                break;
            }
        }
        returnStartGame();
    }

    private static boolean isFullMap() {
        for (int y = 0; y < axisY; y++) {
            for (int x = 0; x < axisX; x++) {
                if (map[y][x] == DOT_MAP_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean determinesTheWinner() {
        // диагонали
        int diagonal_0_N = 0;
        int diagonal_N_N = 0;

        for (int i = 0; i < map.length; i++) {
            diagonal_0_N += map[i][i];
            diagonal_N_N += map[i][map.length - 1 - i];
        }

        if (diagonal_0_N == DOT_HUMAN * axisX || diagonal_0_N == DOT_AI * axisX || diagonal_0_N == DOT_HUMAN * axisY || diagonal_0_N == DOT_AI * axisY)
            return true;

        if (diagonal_N_N == DOT_HUMAN * axisX || diagonal_N_N == DOT_AI * axisX || diagonal_N_N == DOT_HUMAN * axisY || diagonal_N_N == DOT_AI * axisY)
            return true;


        // горизонтали и вертикали
        for (int i = 0; i < map.length; i++) {
            int chek_i = 0;
            int chek_j = 0;
            for (int j = 0; j < map.length; j++) {
                chek_i += map[i][j];
                chek_j += map[j][i];
            }

            if (chek_i == DOT_HUMAN * axisX || chek_i == DOT_AI * axisX || chek_i == DOT_HUMAN * axisY || chek_i == DOT_AI * axisY)
                return true;

            if (chek_j == DOT_HUMAN * axisX || chek_j == DOT_AI * axisX || chek_j == DOT_HUMAN * axisY || chek_j == DOT_AI * axisY)
                return true;
        }



        //горизонталь
//        if(map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
//        if(map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
//        if(map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;
//        //вертикаль
//        if(map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
//        if(map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
//        if(map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;
//        //диагональ
//        if(map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
//        if(map[2][0] == symbol && map[1][1] == symbol && map[0][2] == symbol) return true;
        return false;
    }
}
