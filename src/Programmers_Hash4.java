

// 베스트 앨범


import java.util.*;

public class Programmers_Hash4 {

    public static void main(String args[]){

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500,600,150,800,2500};


        int a[] = solution(genres,plays);


        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

    }

    public static int[] solution(String[] genres, int[] plays) {

        Map<Integer,String> genreMap = new HashMap<>(); //index / Genre
        Map<Integer,Integer> playCountMap = new HashMap<>();  //index / playCount
        Set<String> set = new HashSet(); //genre Set
        Map<Integer, String> genresRanking = new HashMap<>(); // playCount / Genre

        for(int i=0;i<genres.length;i++){
            genreMap.put(i,genres[i]);
            playCountMap.put(i,plays[i]);
            set.add(genres[i]);
        }

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()){
            String g = iter.next();
            int playCount = 0;
            for (int i = 0; i < genres.length; i++) {
                if(g.equals(genreMap.get(i))){
                    playCount+=playCountMap.get(i);
                }
            }
            genresRanking.put(playCount,g);
        }

        //장르별로 플레이수 별로 모은 hashmap treemap 으로 내림차순 정렬
        TreeMap<Integer,String> treeMap = new TreeMap(Collections.reverseOrder());
        treeMap.putAll(genresRanking);


        //장르별로 플레이 카운트 순서 정렬하여 resultList에 입력.
        ArrayList<Integer> resultList = new ArrayList<>();


        Iterator<String> genreIter = treeMap.values().iterator();
        while(genreIter.hasNext()) {
            ArrayList<Integer> temp = new ArrayList<>(); //장르별로 가장 높은 값 저장할 임시 리스트
            int[] tempIndex;
            String g = genreIter.next();
            for (int i = 0; i < genreMap.size(); i++) {
                if(g.equals(genreMap.get(i))){
                    temp.add(playCountMap.get(i));
                }
            }
            Collections.sort(temp,Collections.reverseOrder()); //내림차순 정렬
            tempIndex = new int[temp.size()];


            for(int i=0;i< playCountMap.size();i++){
                if(genreMap.get(i).equals(g)){
                    if(playCountMap.get(i)==temp.get(0)){
                        tempIndex[0] = i;
                    }
                    if(temp.size()>1 && playCountMap.get(i)==temp.get(1)){
                        tempIndex[1] = i;
                    }
                }
            }

            resultList.add(tempIndex[0]); //첫번쨰로 높은 인덱스 추가
            if(tempIndex.length>1){ //두번째가 존재하는지 확인후 인덱스 추가
                resultList.add(tempIndex[1]);
            }
        }

        int[] answer = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++){
            answer[i] = resultList.get(i);
        }


        return answer;

    }

}

