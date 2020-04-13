import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Kakao2018_TheSongJustNow {


    public static void main(String[] args) {

        String m = "ABCDEFG";
        String m2 = "CC#BCC#BCC#BCC#B";

        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        System.out.println(solution(m2,musicinfos2));
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        int answerPlayTime = Integer.MIN_VALUE;

        ArrayList<Music> musicList = new ArrayList<>();

        for(int i=0;i<musicinfos.length;i++){
            String infos[] = musicinfos[i].split(",");
            musicList.add(new Music(toTime(infos[0]),toTime(infos[1]),infos[2],infos[3]));
        }

        m = sharpToLowerCase(m);             // m과 MusicInfos의
        for(int i=0;i<musicList.size();i++){ // "?#" 코드를 lowerCase 문자 한개로 변경
            musicList.get(i).code = sharpToLowerCase(musicList.get(i).code);
        }



        for(int i=0;i<musicList.size();i++){
            StringBuilder fullCode = new StringBuilder();
            for(int j=0;j<musicList.get(i).getPlayTime();j++){ // 재생시간동안 반복 재생되는 전체 코드 만들기
                String code = musicList.get(i).code;
                fullCode.append(code.charAt(j%code.length()));
            }

            if(fullCode.toString().contains(m)){ //전체 재생 코드에 기억한 코드가 들어있는지 확인
                if(!answer.equals("")){                                     //조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다.
                    if(musicList.get(i).getPlayTime() > answerPlayTime){    //재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
                        answer = musicList.get(i).title;
                        answerPlayTime = musicList.get(i).getPlayTime();
                    }
                }else{
                    answer = musicList.get(i).title;
                    answerPlayTime = musicList.get(i).getPlayTime();
                }
            }
        }

        if(answer.equals("")){ // 조건이 일치하는 음악이 없을 때에는 `(None)`을 반환한다.
            answer = "(None)";
        }


        return answer;
    }

    public static LocalTime toTime(String time){ //시간 변환
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String sharpToLowerCase(String code){ // ex) A# -> a
        StringBuilder sb = new StringBuilder(code);
        for(int j=0;j<sb.length();j++){
            if(sb.charAt(j)=='#'){
                sb.replace(j-1,j,String.valueOf(sb.charAt(j-1)).toLowerCase());
                sb.deleteCharAt(j);
            }
        }
        return sb.toString();
    }

    static class Music{
        LocalTime startTime;
        LocalTime endTime;
        String title;
        String code;

        public Music(LocalTime startTime, LocalTime endTime, String title, String code) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.title = title;
            this.code = code;
        }

        public int getPlayTime(){
            return (endTime.toSecondOfDay() - startTime.toSecondOfDay()) / 60;
        }
    }
}
