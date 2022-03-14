package Gobang;

import java.util.Arrays;

/**
 * @author MTKLP9858
 */
public class Gobang {
    public Chess[][] board = new Chess[15][15];//[x][y]棋盘数组
    private int black;//黑色棋子数
    private int white;//白色棋子数
    public boolean blackAI;//黑色棋子AI开关
    public boolean whiteAI;//白色棋子AI开关

    public Gobang() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = new Chess();
            }
        }
        this.black = 0;
        this.white = 0;
        this.blackAI = false;
        this.whiteAI = false;
    }

    Gobang(Gobang gobang) {//复制传入的gobang
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = new Chess(gobang.board[i][j].mode, gobang.board[i][j].AI, gobang.board[i][j].num);
            }
        }
        this.black = gobang.black;
        this.white = gobang.white;
        this.blackAI = gobang.blackAI;
        this.whiteAI = gobang.whiteAI;
    }

    private int count() {//0,1,2=空,黑,白; //返回棋子总数
        this.black = 0;
        this.white = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (this.board[i][j].mode == 1) {
                    this.black++;
                }
                if (this.board[i][j].mode == 2) {
                    this.white++;
                }
            }
        }
        return this.black + this.white;
    }

    private int getRound() {//0,1,2=空,黑,白; //正准备下棋一方对应代号
        this.count();
        return this.black - this.white + 1;
    }

    public boolean setChess(int x, int y) {//放置棋子
        if (x < 0 || y < 0 || x >= 15 || y >= 15) {
            return false;
        }
        this.board[x][y].num = this.count() + 1;
        return this.board[x][y].setMode(this.getRound());
    }

    public boolean reSetChess(int x, int y) {//移除棋子
        if (x < 0 || y < 0 || x >= 15 || y >= 15) {
            return false;
        }
        this.board[x][y].num = 0;
        return this.board[x][y].resetMode();
    }

    public void printBoard() {//以mode数据输出棋盘
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(this.board[i][j].mode + " ");
            }
            System.out.println();
        }
        this.count();
        System.out.println("black:" + this.black + " white:" + this.white);
    }

    public void printBoardAI() {//以AI数据输出棋盘
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(this.board[i][j].AI + " ");
            }
            System.out.println();
        }
        this.count();
        System.out.println("black:" + this.black + " white:" + this.white);
    }

    private int judge(int x, int y) {//0,1,2=空,黑,白; //返回胜方代号或0
        if (x < 0 || y < 0 || x >= 15 || y >= 15) {
            return 0;
        }
        if (this.board[x][y].isEmpty()) {
            return 0;
        }
        short sum = 0;
        for (short i = -1; i <= 1; i++) {
            for (short j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                for (short k = -4; k <= 4; k++) {
                    try {
                        if (this.board[x + (i * k)][y + (j * k)].mode == this.board[x][y].mode) {
                            sum++;
                        } else {
                            sum = 0;
                        }
                    } catch (Exception ignored) {
                    }
                    if (sum == 5) {
                        return this.board[x][y].mode;
                    }
                }
            }
        }
        return 0;
    }


    public int[][] getRankingListOfGobangAI(Gobang base, int chessNumber) {//chessNumber:前n个点位返回
        // ?:下棋位  E:空（empty） W:己方（wo） N:对方（ni） _:任意 X:阻挡（界外） =:后接值（纯数字）
        String[] valueTable = new String[]{"____?WWWW=999999",//连五
                "___W?WWW_=999999",//连五
                "__WW?WW__=999999",//连五
                "___E?WWWE=300000",//活四
                "__EW?WWE_=300000",//活四
                "____?WWWE=2500",//死四A
                "___W?WWE_=2500",//死四A
                "__WW?WE__=2500",//死四A
                "_WWW?E___=2500",//死四A
                "____?WWEW=3000",//死四B
                "___W?WEW_=3000",//死四B
                "__WW?EW__=3000",//死四B
                "WWWE?____=3000",//死四B
                "____?WEWW=2600",//死四C
                "___W?EWW_=2600",//死四C
                "__EE?WWEE=3000",//活三
                "_EEW?WEE_=3000",//活三
                "____?WWEE=500",//死三A
                "___W?WEE_=500",//死三A
                "__WW?EE__=500",//死三A
                "___E?EWWE=800",//死三B
                "_EWE?WE__=800",//死三B
                "EWEW?E___=800",//死三B
                "____?EEWW=600",//死三C
                "_WEE?W___=600",//死三C
                "WEEW?____=600",//死三C
                "____?EWEW=550",//死三D
                "__WE?EW__=550",//死三D
                "_EEE?WEEE=650",//活二
                "____?WEEE=150",//死二A
                "___W?EEE_=150",//死二A
                "__EE?EWEE=250",//死二B
                "___E?EEWE=200",//死二C

                "____?NNNN=949999",//连五
                "___N?NNN_=949999",//连五
                "__NN?NN__=949999",//连五
                "___E?NNNE=285000",//活四
                "__EN?NNE_=285000",//活四
                "____?NNNE=2375",//死四A
                "___N?NNE_=2375",//死四A
                "__NN?NE__=2375",//死四A
                "_NNN?E___=2375",//死四A
                "____?NNEN=2850",//死四B
                "___N?NEN_=2850",//死四B
                "__NN?EN__=2850",//死四B
                "NNNE?____=2850",//死四B
                "____?NENN=2470",//死四C
                "___N?ENN_=2470",//死四C
                "__EE?NNEE=2850",//活三
                "_EEN?NEE_=2850",//活三
                "____?NNEE=475",//死三A
                "___N?NEE_=475",//死三A
                "__NN?EE__=475",//死三A
                "___E?ENNE=760",//死三B
                "_ENE?NE__=760",//死三B
                "ENEN?E___=760",//死三B
                "____?EENN=570",//死三C
                "_NEE?N___=570",//死三C
                "NEEN?____=570",//死三C
                "____?ENEN=522",//死三D
                "__NE?EN__=522",//死三D
                "_EEE?NEEE=617",//活二
                "____?NEEE=142",//死二A
                "___N?EEE_=142",//死二A
                "__EE?ENEE=237",//死二B
                "___E?EENE=190",//死二C
        };

        int[][] list = new int[chessNumber][3];//{x,y,AI}

        int round = base.getRound();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                base.board[x][y].AI = 0;
                if (base.board[x][y].mode != 0) {
                    base.board[x][y].AI = -1;
                    continue;//遍历整个棋盘，去掉所有空位
                }
                for (short i = -1; i <= 1; i++) {
                    for (short j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) {
                            continue;//遍历八个方向，去掉(0,0)
                        }
                        int AI = 0;
                        for (String s : valueTable) {//遍历估值表
                            short k;
                            for (k = 0; k <= 9; k++) {//遍历估值表的每个棋形字符
                                // 加try，报错了
                                try {
                                    switch (s.charAt(k)) {
                                        case '_':
                                        case '?':
                                        case '=':
                                            continue;
                                        case 'E':
                                            if (base.board[x + ((k - 4) * i)][y + ((k - 4) * j)].mode != 0) {
                                                k = 100;
                                            }
                                            break;
                                        case 'W':
                                            if (base.board[x + ((k - 4) * i)][y + ((k - 4) * j)].mode != round) {
                                                k = 100;
                                            }
                                            break;
                                        case 'N':
                                            if (base.board[x + ((k - 4) * i)][y + ((k - 4) * j)].mode == round) {
                                                k = 100;
                                            }
                                            if (base.board[x + ((k - 4) * i)][y + ((k - 4) * j)].mode == 0) {
                                                k = 100;
                                            }
                                            break;
                                        case 'X':
                                            try {
                                                int error = base.board[x + ((k - 4) * i)][y + ((k - 4) * j)].mode;
                                                k = 100;
                                            } catch (Exception ignored) {
                                                continue;
                                            }
                                        default:
                                    }
                                } catch (Exception ignored) {
                                    k = 101;
                                }
                            }
                            //这里开始拿数字，k==10
                            if (k == 10) {
                                String valueString = "";
                                while (k < s.length()) {//拿出值的字符
                                    valueString += s.charAt(k);
                                    k++;
                                }
                                if (Integer.parseInt(valueString) > AI) {//转换为数字，写入int AI里面，取最大值
                                    AI = Integer.parseInt(valueString);
                                }
                            }
                        }//此处遍历完估值表的一个方向
//                        if (AI != 0)
//                            System.out.println("AI:" + AI);
                        base.board[x][y].AI += AI;//把各个方向的值加起来
                    }
                }//此处遍历完该点的八个方向并写入 main.board[x][y].AI
//                if (base.board[x][y].AI != 0)
//                    System.out.println("base:" + base.board[x][y].AI + "\n");
                int r = 0;
                while (r < list.length && list[r][2] >= base.board[x][y].AI) {
                    r++;
                }
                if (r < list.length) {//main要替换r
                    for (int a = list.length - 1; a > r; a--) {
                        list[a][0] = list[a - 1][0];
                        list[a][1] = list[a - 1][1];
                        list[a][2] = list[a - 1][2];
                    }
                    list[r][0] = x;
                    list[r][1] = y;
                    list[r][2] = base.board[x][y].AI;
                }
            }
        }
        return list;
    }


    public int GobangAI(int baseDeep, int wide) {
        return 0;
    }

    public int run(int x, int y) {//规划函数，为了下棋怎么去下而设计的，组合和调用各类功能函数//只放一步棋，
        if ((this.getRound() == 1 && !blackAI) || (this.getRound() == 2 && !whiteAI)) {//到黑色棋走
            if (!this.setChess(x, y)) {
                if (x == -666 || y == -666) {
                    return 0;
                }
                return -1;
            }
        }
        if ((this.getRound() == 1 && blackAI) || (this.getRound() == 2 && whiteAI)) {
            try {
                x = getRankingListOfGobangAI(this, 1)[0][0];
                y = getRankingListOfGobangAI(this, 1)[0][1];
                setChess(x, y);
            } catch (Exception ignored) {
            }
        }
        this.printBoard();
        switch (this.judge(x, y)) {
            case 1://黑棋胜利
                return 1;
            case 2://白棋胜利
                return 2;
            default:
        }
        this.run(-666, -666);
        //让Ai下棋,这里递归进入run后，玩家部分setChess会失败，返回;这里AI下棋后（上方AI接口）应没有返回
        return 666;
    }
}

