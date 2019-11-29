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


        for(int rotate=0;rotate<4;rotate++){ //4번의 회전 반복
            for(int i=0;i<=sheet_size-key_length;i++){ // 키가 0번부터 전체 시트에서 키길이를 뺀 만큼의 인덱스까지만 가면되므로 그만큼 반복
                for(int j=0;j<=sheet_size-key_length;j++){ //   ,,
                    for(int k=0;k<key_length;k++){
                        for(int l=0;l<key_length;l++){
                            sheet[i+k][j+l] += key[k][l]; // 키가 위치한 곳의 시트와 더하기 실행
                        }
                    }

                    int count = 0;
                    for(int a=index_a;a<=index_b;a++){
                        for(int b=index_a;b<=index_b;b++){
                            if(sheet[a][b]==1){ //lock부분에 1이 몇개인지 검사
                                count++;
                            }
                        }
                    }
                    if(count==lock_length*lock_length){ //count 가 lock의 원소 개수와 동일하다면 잠금 해제
                        return true;
                    }
                    initSheet(sheet,lock,index_a,index_b); //시트를 초기화 한후 다시 반복
                }
            }
            key = rotateKey(key); //키 돌림.
        }


        return answer;
    }

    public static void initSheet(int[][] sheet,int[][] lock,int index_a,int index_b){
        /*
        lock이 4x4(1) , key가 3x3(0) 이라면
        sheet는 0 0 0 0 0 0 0 0
               0 0 0 0 0 0 0 0
               0 0 1 1 1 1 0 0
               0 0 1 1 1 1 0 0
               0 0 1 1 1 1 0 0
               0 0 1 1 1 1 0 0
               0 0 0 0 0 0 0 0
               0 0 0 0 0 0 0 0 와 같이 초기화.
         */
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

    public static int[][] rotateKey(int[][] key){ //키를 반 시계방향으로 돌림

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
