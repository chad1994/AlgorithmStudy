import javafx.scene.paint.Material;

import java.util.ArrayList;
import java.util.LinkedList;

//기둥과 보
public class Kakao2020_5 {

    public static void main(String args[]){
        int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int n = 5;

        int[][] answer = solution(n,build_frame);

        for(int i=0;i<answer.length;i++){
            for(int j=0;j<answer[i].length;j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] solution(int n, int[][] build_frame) {

        Material material[][] = new Material[n+1][n+1];
        for(int i=0;i<material.length;i++){
            for(int j=0;j<material.length;j++){
                material[i][j] = new Material();
            }
        }


        for(int i=0;i<build_frame.length;i++){
            if(build_frame[i][3]==0){//삭제
                if(build_frame[i][2]==0){//기둥

                    material[build_frame[i][0]][build_frame[i][1]].gidung=0;
                }else{//보
                    material[build_frame[i][0]][build_frame[i][1]].bo=0;
                }
            }else{ //설치
                if(build_frame[i][2]==0){//기둥
                    material[build_frame[i][0]][build_frame[i][1]].gidung=1;
                }else{//보
                    material[build_frame[i][0]][build_frame[i][1]].bo =1;
                }
            }
        }

        int usefulDataCount = 0 ;
        ArrayList<Integer> xList = new ArrayList();
        ArrayList<Integer> yList = new ArrayList();
        LinkedList<Integer> materialType = new LinkedList<>();

        for(int i=0;i<material.length;i++){
            for(int j=0;j<material.length;j++){
                if(material[i][j].gidung==1){
                    usefulDataCount++;
                    xList.add(i);
                    yList.add(j);
                    materialType.add(0);
                }
                if(material[i][j].bo==1){
                    usefulDataCount++;
                    xList.add(i);
                    yList.add(j);
                    materialType.add(1);
                }
            }
        }

        int[][] result = new int[usefulDataCount][3];

        for(int i=0;i<result.length;i++){
            result[i][0] = xList.get(i);
            result[i][1] = yList.get(i);
            result[i][2] = materialType.poll();
        }


        return result;
    }


    static class Material{
        int gidung = 0;
        int bo = 0;

    }


}
