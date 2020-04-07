
import java.sql.Array;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Kakao2018_ShuttleBus {

    public static void main(String[] args) {

        int n = 2;
        int t = 10;
        int m = 3;
        String[] timetable = {"09:00", "08:59", "09:05", "09:03", "08:49"};
        String[] timetable2 = {"08:00", "08:01", "08:02", "08:03"};
        String[] timetable3 = {"00:01", "00:01", "00:01", "00:01", "00:01"};
        String[] timetable4 = {"09:10", "09:09", "08:00"};
        String[] timetable5 = {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        String[] timetable6 = {"09:00", "09:00", "09:00", "09:10"};
        String[] timetable7 = {"09:00", "09:00", "09:00", "09:01", "09:01", "09:01"};


        LocalTime time = LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime time2 = LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println("solution : " + solution(n, t, m, timetable7));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        // n회 t분간격 m명

        Arrays.sort(timetable); //시간 오름차순 정렬

        int currentSequence = 0; //현재 회차
        LocalTime currentBusTime = toLocalTime("09:00"); //현재 회차 버스출발 시간..


        LinkedList<LocalTime> timeList = new LinkedList<>();
        for (int i = 0; i < timetable.length; i++) {
            LocalTime time = toLocalTime(timetable[i]); //String -> LocalTime으로 저장
            timeList.push(time);
        }

        LinkedHashMap<LocalTime, ArrayList<LocalTime>> map = new LinkedHashMap<>(); //순서유지를 위해 LinkedHashMap사용
        //<버스시간, 탑승한 크루들 도착시간 리스트>

        while (currentSequence < n && !timeList.isEmpty()) { //모든 크루를 탈 수 있는 시간대의 버스에 대입.
            if (!timeList.peekLast().isAfter(currentBusTime)) {
                if (map.containsKey(currentBusTime)) {
                    if (map.get(currentBusTime).size() >= m) {
                        currentBusTime = currentBusTime.plusMinutes(t);
                        currentSequence++;
                    } else {
                        map.get(currentBusTime).add(timeList.pollLast());
                    }
                } else {
                    map.put(currentBusTime, new ArrayList<>());
                    map.get(currentBusTime).add(timeList.pollLast());
                }
            } else {
                currentBusTime = currentBusTime.plusMinutes(t);
                currentSequence++;
            }
        }

        ArrayList<LocalTime> keyList = new ArrayList(map.keySet());
        ArrayList<ArrayList<LocalTime>> valueList = new ArrayList(map.values());

        if (keyList.size() == 0) {
            return currentBusTime.minusMinutes(t).toString(); // 아무도 탑승 하지 않았기 때문에 마지막 버스시간을 리턴.
        } else {
            for (int i = keyList.size() - 1; i >= 0; i--) { // 가장 늦은 버스시간부터 검사를 시작
                if (valueList.get(i).size() < m) { // 빈자리가 있는지 확인,
                    return keyList.get(i).toString(); // 있다면, 그 버스 시간에 타면 되므로 버스 시간 리턴.
                } else { //없다면 가장 마지막에 탄 사람 보다 1분 먼저 오면 되므로 -1분 하여 리턴.
                    return valueList.get(i).get(valueList.get(i).size() - 1).minusMinutes(1).toString();
                }
            }
        }

        return null;
    }

    public static LocalTime toLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }

}
