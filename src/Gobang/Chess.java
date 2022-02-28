package Gobang;

/**
 * @author MTKLP9858
 */
class Chess {
    protected int mode;//0:空,1:黑,2:白
    protected int AI;//AI估值的分数
    protected int num;//下棋的序号

    Chess() {
    }

    Chess(int mode) {
        this.mode = mode;
        this.AI = 0;
        this.num = 0;
    }

    Chess(int mode, int AI, int num) {
        this.mode = mode;
        this.AI = AI;
        this.num = num;
    }

    protected boolean isEmpty() {//判断是否为空
        return this.mode == 0;
    }

    protected boolean setMode(int mode) {//如果是空的，则放置棋子
        if (this.mode != 0) {
            return false;
        }
        if (mode == 1 || mode == 2) {
            this.mode = mode;
            return true;
        } else {
            return false;
        }
    }

    protected boolean resetMode() {//删除棋子
        if (this.mode == 0) {
            return false;
        }
        this.mode = 0;
        return true;
    }
}
