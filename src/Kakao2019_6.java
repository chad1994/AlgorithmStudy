import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 매칭 점수
public class Kakao2019_6 {

    /*
    한 웹페이지에 대해서 기본점수, 외부 링크 수, 링크점수, 그리고 매칭점수를 구할 수 있다.
    한 웹페이지의 기본점수는 해당 웹페이지의 텍스트 중, 검색어가 등장하는 횟수이다. (대소문자 무시)
    한 웹페이지의 외부 링크 수는 해당 웹페이지에서 다른 외부 페이지로 연결된 링크의 개수이다.
    한 웹페이지의 링크점수는 해당 웹페이지로 링크가 걸린 다른 웹페이지의 기본점수 ÷ 외부 링크 수의 총합이다.
    한 웹페이지의 매칭점수는 기본점수와 링크점수의 합으로 계산한다.
     */

    private static HashMap<String,ArrayList<Page>> linkedMap = new HashMap<>(); //  < a 태그 url(KEY) : 참조하는 페이지(VALUE) >

    public static void main(String[] args) {

        String word = "muzi";
        String pages[] = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};

        String pages2[] = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(solution(word, pages2));

    }


    public static int solution(String word, String[] pages) {
        int answer = 0;
        Page[] p = new Page[pages.length];

        for (int i = 0; i < pages.length; i++) {
            p[i] = new Page(pages[i].toLowerCase(), i);
            p[i].setUrl();
            p[i].setDefaultScore(word.toLowerCase());
            p[i].setExternalLink();

        }


        for(int i=0;i<pages.length;i++){
            p[i].setMatchingScore();
        }

        Arrays.sort(p, new Comparator<Page>() {
            @Override
            public int compare(Page o1, Page o2) {
                return Double.compare(o2.matchingScore,o1.matchingScore);
            }
        });



        return p[0].index;
    }


    static class Page {
        String html;
        String url;
        int index;
        int defaultScore = 0;
        int externalLink=0;
        double matchingScore=0;

        public Page(String html, int index) {
            this.html = html;
            this.index = index;
        }

        public void setUrl(){ // 정규식으로 url 추출.
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(html);
            while(matcher.find()){
                url = matcher.group(1);
            }
        }

        public void setDefaultScore(String word){ // 정규식으로 포함 단어 검색
            Pattern pattern = Pattern.compile("([^a-z]"+word+"[^a-z])");
            Matcher matcher = pattern.matcher(html);
            while(matcher.find()){
                matcher.group(1);
                defaultScore++;
            }
            System.out.println(defaultScore);
        }

        public void setExternalLink(){ //정규식으로 외부 링크 추출.
            Pattern pattern = Pattern.compile("<a href=\\\"https://(.+?)\\\">");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String link = matcher.group(1);
                externalLink++;
                if(linkedMap.containsKey(link)){ // linkedMap에 해당 링크(key)와 객체 자신(value)을 추가하여 갱신
                    ArrayList list = linkedMap.get(link);
                    list.add(this);
                    linkedMap.put(link,list);
                }else{ //linkedMap에 해당 링크(key)와 객체 자신(value)을 추가하여 초기화
                    ArrayList list = new ArrayList();
                    list.add(this);
                    linkedMap.put(link,list);
                }
            }
        }

        public void setMatchingScore(){ // 결과적으로 매칭 스코어 계산.
            double linkScore=0;
            if(linkedMap.containsKey(url)){
                ArrayList<Page> list = linkedMap.get(url);
                for(Page page : list){
                    linkScore += (double)page.defaultScore/page.externalLink;
                }
            }

            matchingScore = (double)defaultScore + linkScore;

            System.out.println(matchingScore);

        }

    }

}
