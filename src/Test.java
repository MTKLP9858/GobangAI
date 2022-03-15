import Gobang.Gobang;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author MTKLP
 */
public class Test {
    public static void main(String[] args) {
        Gobang g = new Gobang();


//      这是我想做深度搜索时候模拟一个初始的棋盘
//      预放置棋子
        g.setChess(5, 6);
        g.setChess(4, 6);
        g.setChess(5, 7);
        g.setChess(4, 7);
        g.setChess(5, 8);
        g.setChess(4, 8);
        g.printBoard();


//        int[][] m = g.getRankingListOfGobangAI(g,6);
//        System.out.println("end:" + Arrays.deepToString(m));
        int m = g.deepSearch(g, 4, 5, false);//未完成
        System.out.println("end:" + m);


//        Scanner sc = new Scanner(System.in);
//        g.whiteAI = true;
//        while (true) {
//            int runtime = g.run(sc.nextInt(), sc.nextInt());//直接输入坐标玩，规则无禁手，【黑棋应该是先手】||0:空,1:黑(玩家),2:白(机器)
//            switch (runtime) {
//                case -1:
//                    System.out.println("非法的输入");
//                    break;
//                case 0:
//                    System.out.println("继续");
//                    break;
//                case 1://黑棋胜利
//                    System.out.println("黑棋胜利");
//                    return;
//                case 2://白棋胜利
//                    System.out.println("白棋胜利");
//                    return;
//                default:
//            }
//        }
    }
}

