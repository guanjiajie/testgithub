package demo.algorithm;

public class BackTracking {
    static int n=4; //皇后数量
    static int total; //总的解决数量
    static int[] queen = new int[n*n];

    private static boolean isOk(int row){
        for (int col = 0; col<row; col++){
            if (queen[row] == queen[col] || queen[row] - row == queen[col] - col || queen[row] + row == queen[col]+col){
                return false;
            }
        }
        return true;
    }


    public static void back_tracking(int row){
        if (row == n){
            total++;
            for (int i=0; i<n; i++){
                System.out.print("("+i+","+queen[i]+") ");
            }
            System.out.println();
        }else {

            for (int col = 0; col < n; col++) {
                queen[row] = col;
                if (isOk(row))
                    back_tracking(row + 1);
            }
        }
    }

    public static void main(String[] args){
        back_tracking(0);
        System.out.println(total);
    }
}
