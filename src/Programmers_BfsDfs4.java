import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Programmers_BfsDfs4 {

    public static boolean[] visited;
    public static List<Ticket> ticketList;
    public static List<Ticket> icnList;
    public static List<Integer> icnIndex;
    public static List<String> answerList;

    public static void main(String args[]) {
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] tickets = {{ "ICN", "COO" },{ "COO", "ICN" },{ "COO", "ICN" }};
//        String[][] tickets = {{"ICN", "A"}, {"A", "B"}, {"B", "C"}, {"C", "B"}, {"B", "D"},{"D","C"},{"C","ICN"}};
        String[] answer = solution(tickets);

        for(String s : answer){
            System.out.println(s);
        }
    }

    public static String[] solution(String[][] tickets) {
        String[] answer = {};
        ticketList = new ArrayList();
        icnList = new ArrayList<>();
        icnIndex = new ArrayList<>();


        for (int i = 0; i < tickets.length; i++) {
            ticketList.add(new Ticket(tickets[i][0], tickets[i][1]));
        }
        Collections.sort(ticketList, new Comparator<Ticket>() { //출발지(우선),도착지(차선)으로 정렬
            @Override
            public int compare(Ticket o1, Ticket o2) {
                if (o1.departure.equals(o2.departure)) {
                    return o1.destination.compareTo(o2.destination);
                } else {
                    return o1.departure.compareTo(o2.departure);
                }
            }
        });


        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).departure.equals("ICN")) { //출발지가 ICN인 티켓 뽑아서 따로 저장
                icnList.add(ticketList.get(i));
                icnIndex.add(i);
            }
        }


        for (int i = 0; i < icnList.size(); i++) { //ICN출발지 리스트를 기점으로 dfs반복
            answerList = new ArrayList<>();
            answerList.add(icnList.get(i).departure);
            visited = new boolean[tickets.length];
            visited[icnIndex.get(i)] = true;
            if (dfs(1, icnList.get(i), ticketList, visited)) {
                break; //true반환 되었다면 이미 찾았으며, 정렬을 통해 가장 알파벳 순으로 뽑았으므로 더이상 돌 필요 X
            }
        }


        answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static boolean dfs(int level, Ticket currentLocation, List<Ticket> ticketList, boolean[] visited) {
        boolean check = false;
        if (level == ticketList.size()) {
            answerList.add(currentLocation.destination);
            return true;
        }

        for (int i = 0; i < ticketList.size(); i++) {
            if (currentLocation.isConnected(ticketList.get(i)) && !visited[i]) { //방문하지 않은곳이며 현재 도착지와 연결된 출발지를 가진 티켓일 경우
                answerList.add(currentLocation.destination);
                visited[i] = true;
                if (dfs(level + 1, ticketList.get(i), ticketList, visited)) { // 더이상 불필요 하게 돌지 못하도록 loop 종료
                    check = true;
                    break;
                }

                //잘못된 경로라 빠져나오게 된 경우 되돌려줌
                answerList.remove(currentLocation.destination);
                visited[i] = false;

            }

        }

        return check;

    }


    static class Ticket {
        String departure;
        String destination;

        Ticket(String departure, String destination) {
            this.departure = departure;
            this.destination = destination;
        }

        public boolean isConnected(Ticket departure) {
            return this.destination.equals(departure.departure);
        }
    }
}
