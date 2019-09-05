import java.util.ArrayList;

import java.util.List;

//프로그래머스 연습
//완전탐색 - 모의고사
public class Programmers_BruteForce1 {
    public static int[] solution(int[] answers) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        int[] pattern1 = {1,2,3,4,5};
        int[] pattern2 = {2,1,2,3,2,4,2,5};
        int[] pattern3 = {3,3,1,1,2,2,4,4,5,5};
        int[] answerCount = {0,0,0};


        for(int i=0;i<answers.length;i++){
            if(answers[i]==pattern1[i%pattern1.length]){
                answerCount[0]++;
            }
            if(answers[i]==pattern2[i%pattern2.length]){
                answerCount[1]++;
            }
            if(answers[i]==pattern3[i%pattern3.length]){
                answerCount[2]++;
            }
        }

        int max = answerCount[0];
        for(int i=1;i<answerCount.length;i++){
            if(max<answerCount[i]){
                max = answerCount[i];
            }
        }

        for(int i=0;i<answerCount.length;i++){
            if(max==answerCount[i]){
                list.add(i);
            }
        }

        answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }



        return answer;
    }

    public static void main(String args[]){

        int[] answers = {1,3,2,4,2};
        solution(answers);
    }
}
