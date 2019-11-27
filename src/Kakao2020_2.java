import java.util.Stack;

public class Kakao2020_2 {

    public static void main(String args[]){

        System.out.println(solution("()))((()"));

    }

    public static String solution(String p) {
        String answer = "";
        String[] uv;

        if(p.isEmpty()) {
            return p;
        }

        if(isValid(p)){
            return p;
        }

        while(!isValid(p)){
            uv = seperate(p);
            p=tranform(uv);
        }

        answer = p;

        return answer;
    }

    public static String tranform(String[] uv){
        String result = "";
        String tempV = "";
        String[] uvRe;

        if(isValid(uv[0])){
            result += uv[0];
            uvRe = seperate(uv[1]);
            result += tranform(uvRe);
        }else{
            tempV = solution(uv[1]);
            result += '('+tempV+')';
            uv[0] = transU(uv[0]);
            result += uv[0];
        }

        return result;
    }

    public static String transU(String u){
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

    public static String[] seperate(String p){
        String[] uv = new String[2];
        int count = 0;

        for(int i=0;i<p.length();i++){

            if(p.charAt(i)=='('){
                count++;
            }else{
                count--;
            }

            if(count == 0){
                uv[0] = p.substring(0,i+1);
                uv[1] = p.substring(i+1,p.length());
                break;
            }
        }

        return uv;
    }



    public static boolean isValid(String p){
        int count = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='('){
                count++;
            }else{
                count--;
            }

            if(count<0){
                return false;
            }
        }
        return true;
    }
}
