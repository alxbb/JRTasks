import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        System.out.println(Math.random()*4);

        int[][] tile = {{1,2,3,4},{10,11,12,13}};
//        tile[0] = new int[]{1, 2, 3, 4};
//        tile[1] = new int[]{5, 6, 7, 8};

        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[i].length; j++) {
                System.out.print(tile[i][j]);
            }
            System.out.println();
        }

    }
}
