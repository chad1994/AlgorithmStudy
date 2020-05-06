import java.util.ArrayList;
import java.util.HashMap;

public class Kakao2019Intern_HotelRoom {

    public static void main(String[] args) {

        long k = 10;
        long[] room_number = {1,3,4,1,3,1};
        long k2 = 1000000000001L;
        long[] room_number2 = {1,1000000000000L,1000000000000L,1,3,1};
        long[] answer = solution(k2,room_number2);

        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }
    }

    /*
    *  효율성 5,6,7 실패 원인 분석 :
    *  Node 클래스를 만들어 roomNum, parentNum 을 필드로 주어 0~k 까지 크기로 배열로 만듦.
    *  10^12 개로 주어진 최대값에 따라 배열 생성시 메모리 초과.. (당연한)
    *  hashMap 을 사용하여 key(roomNum)가 존재한다면 이미 배정된 방으로 처리하여
    *  value(parent)로 (나올 때 까지 연속해서) redirect 해줌.
    *  찾았다면 거쳐간 key값들에 대해 찾은 (빈방번호+1) 하여 value값 갱신.
    */

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        HashMap<Long,Long> map = new HashMap<>(); // <방번호, parent>


        for(int i=0;i<room_number.length;i++){
            ArrayList<Long> nodeList = new ArrayList<>(); // 이미 예약된 방일 경우, 모아놓고 한번에 parent를 갱신할 거쳐가는 방번호들의 리스트
            long roomNum = room_number[i];

            if(!map.containsKey(roomNum)){ //빈방일 경우
                map.put(roomNum,roomNum+1);
                answer[i] = roomNum;
            }else{ // 빈방이 아닐 경우
                long parent = map.get(roomNum); // 해당 방번호의 부모노드를 가져옴
                nodeList.add(roomNum); // 부모위치를 나중에 갱신해야 하므로 해당 방번호를 저장.

                while(true){
                    if(!map.containsKey(parent)){ //위와 동일 (빈방일 경우)
                        map.put(parent,parent+1);
                        answer[i] = parent;
                        break;
                    }else{ // 위와 동일 (빈방이 아닐 경우)
                        nodeList.add(parent);
                        parent = map.get(parent); // 빈방이 나올 때 까지 부모노드 방번호의 부모노드를 호출.
                    }
                }

                for(int j=0;j<nodeList.size();j++){
                    map.put(nodeList.get(j),parent+1); // 저장한 방번호들의 부모노드를 결과적으로 찾은 방번호+1 로 갱신.
                }
            }
        }


        return answer;
    }
}
