import Gobang.Gobang;

import java.util.Scanner;

/**
 * @author MTKLP
 */
public class Test {
    public static void main(String[] args) {
        Gobang g = new Gobang();


//      这是我想做深度搜索时候模拟一个初始的棋盘
//        预放置棋子
//        g.setChess(5, 6);
//        g.setChess(4, 6);
//        g.setChess(5, 7);
//        g.setChess(4, 7);
//        g.setChess(5, 8);
//        g.setChess(4, 8);


//        g.GobangAI(5, 3);//未完成


        Scanner sc = new Scanner(System.in);
        g.whiteAI = true;
        while (true) {
            int t = g.run(sc.nextInt(), sc.nextInt());//直接输入坐标玩，规则无禁手，【黑棋应该是先手】||0:空,1:黑(玩家),2:白(机器)
            System.out.println("\n");
        }
    }
}

