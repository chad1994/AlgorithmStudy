import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Kakao2018_ChuseokTraffic {

    public static void main(String[] args) {
        String input1[] = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(input1));

        String input2[] = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(input2));

        String input3[] = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(solution(input1));

    }

    public static int solution(String[] lines) {
        int answer = Integer.MIN_VALUE;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        Date[] startDate = new Date[lines.length];
        Date[] endDate = new Date[lines.length];


        for(int i = 0; i < lines.length; i++) {
            try {
                String[] line = lines[i].split(" ");
                String dateStr = line[0] + " " + line[1];
                int procMilli = (int) (Double.parseDouble(line[2].substring(0, line[2].length()-1)) * -1000);
                endDate[i] = dateFormat.parse(dateStr);
                startDate[i] = addMilli(endDate[i], procMilli + 1);
            } catch(ParseException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < endDate.length; i++) {
            int maxTemp = 0;
            long endTimeMilliI = endDate[i].getTime();
            long finTimeMilli = addMilli(endDate[i], 1000).getTime();

            for(int j = 0; j < startDate.length; j++) {
                long startTimeMilli = startDate[j].getTime();
                long endTimeMilliJ = endDate[j].getTime();
                if((finTimeMilli - startTimeMilli) <= 0) {
                    continue;
                }
                if(((endTimeMilliI - startTimeMilli) <= 0 && (finTimeMilli - startTimeMilli) > 0)
                        || ((endTimeMilliI - endTimeMilliJ) <= 0 && (finTimeMilli - endTimeMilliJ) > 0)
                        || ((endTimeMilliI - startTimeMilli) > 0 && (endTimeMilliI - endTimeMilliJ) <= 0)) {
                    maxTemp++;
                }
            }
            answer = Math.max(answer, maxTemp);
        }

        return answer;
    }

    private static Date addMilli(Date date, int procMilli) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MILLISECOND, procMilli);
        return c.getTime();
    }
}
