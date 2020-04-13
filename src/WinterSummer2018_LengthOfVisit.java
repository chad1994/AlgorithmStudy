import java.awt.*;
import java.util.HashSet;

public class WinterSummer2018_LengthOfVisit {


    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println(solution(dirs2));
    }

    static int[] directionX = {0, 0, -1, 1}; // U D L R
    static int[] directionY = {1, -1, 0, 0};
    static Point currentPosition = new Point(0, 0);
    static Point changePosition = new Point(0, 0);
    static HashSet<String> visitingRecord = new HashSet<>();
    static int answer = 0;

    public static int solution(String dirs) {

        for (int i = 0; i < dirs.length(); i++) {
            switch (dirs.charAt(i)) {
                case 'U':
                    movePosition(0);
                    break;
                case 'D':
                    movePosition(1);
                    break;
                case 'L':
                    movePosition(2);
                    break;
                case 'R':
                    movePosition(3);
                    break;
            }
        }

        return answer;
    }


    public static void movePosition(int dir) {
        if (currentPosition.x + directionX[dir] < -5 || currentPosition.x + directionX[dir] > 5 || currentPosition.y + directionY[dir] < -5 || currentPosition.y + directionY[dir] > 5) {
            return;
        } else {
            changePosition.x = currentPosition.x + directionX[dir];
            changePosition.y = currentPosition.y + directionY[dir];
            recordLoad();
            currentPosition.x = changePosition.x;
            currentPosition.y = changePosition.y;
        }
    }

    public static void recordLoad() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append("" + currentPosition.x + currentPosition.y + changePosition.x + changePosition.y);
        sb2.append("" + changePosition.x + changePosition.y + currentPosition.x + currentPosition.y);
        if (!visitingRecord.contains(sb1.toString()) && !visitingRecord.contains(sb2.toString())) {
            answer += 1;
        }
        visitingRecord.add(sb1.toString());
        visitingRecord.add(sb2.toString());
    }

}
