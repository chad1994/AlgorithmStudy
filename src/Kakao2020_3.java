import java.util.Arrays;

//자물쇠와 열쇠
public class Kakao2020_3 {

    public static void main(String args[]){
        int key[][] = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int lock[][] = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key,lock));
    }

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int key_length = key.length;
        int lock_length = lock.length;
        int sheet_size = lock_length+(key_length*2)-2;
        int index_a = key_length-1; // 검사할 시트의 시작 인덱스
        int index_b = index_a+lock_length-1; // 검사할 시트의 마지막 인덱스

        int sheet[][] = new int[sheet_size][sheet_size];
        initSheet(sheet,lock,index_a,index_b);



        for(int rotate=0;rotate<4;rotate++){
            for(int i=0;i<sheet_size-key_length;i++){
                for(int j=0;j<sheet_size-key_length;j++){
                    for(int k=0;k<key_length;k++){
                        for(int l=0;l<key_length;l++){
                            sheet[i+k][j+l] += key[k][l];
                        }
                    }
                    int count = 0;
                    for(int a=index_a;a<=index_b;a++){
                        for(int b=index_a;b<=index_b;b++){
                            if(sheet[a][b]==1){
                                count++;
                            }
                        }
                    }
                    if(count==lock_length*lock_length){
                        return true;
                    }
                    initSheet(sheet,lock,index_a,index_b);
                }
            }
            key = rotateKey(key);
        }


        return answer;
    }

    public static void initSheet(int[][] sheet,int[][] lock,int index_a,int index_b){
        for(int i=0;i<sheet.length;i++){
            for(int j=0;j<sheet.length;j++){
                sheet[i][j] = 0;
            }
        }
        for(int a=index_a;a<=index_b;a++){
            for(int b=index_a;b<=index_b;b++) {
                sheet[a][b] = lock[a-index_a][b-index_a];
            }
        }
    }

    public static int[][] rotateKey(int[][] key){ //반 시계방향으로 돔

        int[][] temp = new int[key.length][key.length];
        for(int i=0;i<key.length;i++) {
            for (int j = 0; j < key.length; j++) {
                temp[i][j] = key[i][j];
            }
        }
        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                temp[i][j] = key[j][key.length-1-i]; // 10 11 12 , 00 01 02  20 10 00
            }
        }


        return temp;
    }
}
