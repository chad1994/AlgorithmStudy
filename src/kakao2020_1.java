public class kakao2020_1 {
    public static void main(String args[]){
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    public static int solution(String s) {

        int answer = s.length();
        for(int i=1;i<=(s.length()/2);i++){ //자를 단위 (1 ~ 길이/2 까지) -> 절반이상이면 의미 없음.
            StringBuilder compressedStr = new StringBuilder();
            String subStr = s.substring(0,i); // 저장할 임시 문자열
            int equalCount = 1; // 문자열 압축 개수

            for(int j=i;(j+i)<=s.length();j+=i){
                if(subStr.equals(s.substring(j,j+i))){ // 0~i,i~i+j를 반복 비교 하여 카운트
                    equalCount++;
                }else{
                    if(equalCount==1){
                        compressedStr.append(subStr);
                    }else{
                        compressedStr.append(equalCount+subStr);
                    }
                    subStr = s.substring(j,j+i);
                    equalCount = 1;
                }
            }
            if(equalCount!=1){ // 붙이지 못하고 나온 문자열 추가
                compressedStr.append(equalCount+subStr);
            }else{
                compressedStr.append(subStr);
            }

            compressedStr.append(s.substring((s.length()/i)*i,s.length())); // 남은 문자열 추가. ex) 길이11문자열 3개 단위로 나눌때 2개 남으므로 9~11까지 substring 붙여줌


            if(answer>compressedStr.length()){
                answer = compressedStr.length();
            }
        }

        return answer;
    }
}
