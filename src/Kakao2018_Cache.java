import java.util.LinkedHashMap;
import java.util.Map;

public class Kakao2018_Cache {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}; // 5 5 5 5 5 5 5 5 5 5 = 50
        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}; // 5 5 5 1 1 1 1 1 1 = 21
        String[] cities3 = {"Jeju", "Jeju", "Seoul", "Seoul", "Seoul", "Seoul", "Pangyo", "Pangyo", "Jeju","POPO","seoul"}; // 5 1 5 1 1 1 5 1 1 5 5= 31

        System.out.println(solution(cacheSize,cities3));

    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0){
            return cities.length * 5;
        }

        LinkedHashMap<String,Integer> hashMap = new LinkedHashMap<>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return cacheSize > 0 ? (this.size() > cacheSize) : false;
            }
        };

        for(int i=0;i<cities.length;i++){
            if(hashMap.containsKey(cities[i].toLowerCase())){
                hashMap.remove(cities[i].toLowerCase());
                hashMap.put(cities[i].toLowerCase(),i);
                answer+=1;
            }else{
                hashMap.put(cities[i].toLowerCase(),i);
                answer+=5;
            }
        }


        return answer;
    }
}
