import java.util.HashMap;

public class Kakao2019Intern_HotelRoom {

    public static void main(String[] args) {

        long k = 10;
        long[] room_number = {1,3,4,1,3,1};
        long[] answer = solution(k,room_number);

        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }
    }


    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        boolean[] roomChecker = new boolean[(int)k+1];

        Node[] nodes = new Node[(int)k+1];
        for(long i=0;i<k+1;i++){
            nodes[(int)i] = new Node(i,i+1);
        }

        for(long i=0;i<room_number.length;i++){
            long roomNum = room_number[(int)i];
            if(!roomChecker[(int)roomNum]){
                roomChecker[(int)roomNum] = true;
//                nodes[(int)roomNum].parentIndex = roomNum+1;
                answer[(int)i] = room_number[(int)i];
            }else{
                long j = nodes[(int)roomNum].parentIndex;
                while(true){
                    if(!roomChecker[(int)j]){
                        roomChecker[(int)j] = true;
                        nodes[(int)j].parentIndex = j+1;
                        nodes[(int)roomNum].parentIndex = j+1;
                        answer[(int)i] = j;
                        break;
                    }else{
                        j = nodes[(int)j].parentIndex;
                    }
                }
            }
        }

        return answer;
    }

    static class Node{
        long index;
        long parentIndex;

        public Node(long index, long parentIndex) {
            this.index = index;
            this.parentIndex = parentIndex;
        }
    }
}
