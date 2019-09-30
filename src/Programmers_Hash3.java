

// 베스트 앨범

import java.util.*;

public class Programmers_Hash3 {

    public static void main(String args[]){

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500,600,150,800,2500};


        int a[] = solution(genres,plays);

    }

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        Map<Integer,String> genresMap = new HashMap<>();
        Map<Integer,Integer> playsMap = new HashMap<>();
        Set set = new HashSet();
        Map<Integer, String> genresRanking = new HashMap<>();

        for(int i=0;i<genres.length;i++){
            genresMap.put(i,genres[i]);
            playsMap.put(i,plays[i]);
            set.add(genres[i]);
        }

        for(int i=0;i<genres.length;i++){

        }





        return answer;

    }

}

