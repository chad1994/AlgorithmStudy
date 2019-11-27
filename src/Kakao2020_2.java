public class Kakao2020_2 {

    public static void main(String args[]){

        System.out.println(solution("()))((()"));

    }

    public static String solution(String p) {
        String answer = "";
        String[] uv;

        if(p.isEmpty()) { //1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
            return p;
        }

        if(isValid(p)){ //만약 p가 이미 올바른 괄호 문자열이라면 그대로 return 하면 됩니다.
            return p;
        }

        while(!isValid(p)){ // 과정 반복.
            uv = seperate(p); //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
            p=tranform(uv);
        }

        answer = p;
        return answer;
    }


    public static String tranform(String[] uv){ // 나눈 u와 v를 정해진 규칙에 따라 변환 한다.
        String result = "";
        String tempV = "";
        String[] uvRe;

        if(isValid(uv[0])){  // 문자열 u가 "올바른 괄호 문자열" 이라면
            result += uv[0];
            uvRe = seperate(uv[1]);
            result += tranform(uvRe);
        }else{               // 문자열 u가 "올바른 괄호 문자열" 이 아니라면
            tempV = solution(uv[1]);
            result += '('+tempV+')';
            uv[0] = converseConversion(uv[0]);
            result += uv[0];
        }

        return result;
    }

    public static String converseConversion(String u){ //균형 잡힌 u문자열을 앞뒤를 자르고 괄호를 뒤집는다.
        String temp="";
        u = u.substring(1,u.length()-1);
        for(int i=0;i<u.length();i++){
            if(u.charAt(i)=='('){
                temp+=')';
            }else{
                temp+='(';
            }
        }

        return temp;
    }

    public static String[] seperate(String p){ //u 와 v 로 분리
        String[] uv = new String[2];
        int count = 0;

        for(int i=0;i<p.length();i++){

            if(p.charAt(i)=='('){
                count++;
            }else{
                count--;
            }

            if(count == 0){ //0이 되는 순간 균형 잡힌 문자열로 간주.
                uv[0] = p.substring(0,i+1);
                uv[1] = p.substring(i+1,p.length());
                break;
            }
        }

        return uv;
    }



    public static boolean isValid(String p){ //올바른 문자열인지 유효성 체크
        int count = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='('){
                count++;
            }else{
                count--;
            }

            if(count<0){ // 카운트가 음수가 되는 순간 유효x
                return false;
            }
        }
        return true;
    }
}
