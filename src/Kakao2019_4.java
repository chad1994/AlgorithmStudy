import java.util.*;

//무지의 먹방 라이브
public class Kakao2019_4 {

    public static void main(String args[]){

        int[] food_times = {3,1,2};
        long k = 5;

        System.out.println(solution(food_times,k));

    }


    public static int solution(int[] food_times, long k) {
        int answer = -1;
        ArrayList<Food> foodList = new ArrayList();
        Food[] foods = new Food[food_times.length];

        for(int i=0;i<food_times.length;i++){
            foods[i] = new Food(i+1,food_times[i]);
            foodList.add(foods[i]);
        }

        foodList.sort(Food.timeSort); //시간 오름차순으로 정렬한다.


        long pretime = 0; // 이전 최소시간 음식의 시간을 저장할 변수
        for(int i=0;i<foodList.size();i++){
            long diffTime = foodList.get(i).time - pretime; //현재 음식의 시간 - 이전 최소시간 (=삭제시킬 행의 높이를 결정짓게 됨)
            if(diffTime != 0){ //0이라면 전의 음식의 시간과 차이가 없기 떄문에 이미 삭제됐디고 봐야함.
                if(k >= (diffTime*(foodList.size()-i))){
                    k -= diffTime*(foodList.size()-i); //k가 삭제시킬 만큼 여유가 있을때 삭제
                    pretime = foodList.get(i).time; //현재 음식의 시간을 다음 반복을 위해 저장.
                }else{ // k가 한줄을 한번에 지울 여유가 없으므로 정답을 찾기위해 분기
                    foodList = new ArrayList(foodList.subList(i,foodList.size())); //시간을 다쓴 음식들을 제외하고 list 재초기화
                    foodList.sort(Food.indexSort); //남은 음식들 인덱스 순으로 정렬
                    long index = k % (foodList.size()); //리스트에서 찾고자 하는 음식의 인덱스 = (남은 시간 / 리스트 크기)의 나머지가 됨.
                    answer = foodList.get((int)index).index; //리스트의 해당 인덱스의 음식인덱스를 가져와서 리턴.
                    return answer;
                }
            }
        }


        return answer;
    }

    static class Food{ //인덱스와 시간을 동시에 저장하기 위한 Food class
        int index;
        long time;

        public Food(int index,long time){
            this.index = index;
            this.time = time;
        }

        static Comparator<Food> timeSort = new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                if(o1.time >= o2.time){
                    return 1;
                }else{
                    return -1;
                }
            }
        };

        static Comparator<Food> indexSort = new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                if(o1.index >= o2.index){
                    return 1;
                }else{
                    return -1;
                }
            }
        };


    }
}
