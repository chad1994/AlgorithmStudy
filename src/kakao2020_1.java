public class kakao2020_1 {
    public static void main(String args[]){

        System.out.println(solution("ababcdcdababcdcd"));

       //ababcbcbc
        //2abc2bc
        //aba3bc
    }

    public static int solution(String s) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        char firstChar = s.charAt(0);
        for(int i=1;i<s.length();i++){
            if(firstChar==s.charAt(i)){
                count++;
                if (i==s.length()-1){
                    sb.append(""+count+firstChar);
                }
            }else{
                if(count==1){
                    sb.append(firstChar);
                }else {
                    sb.append(""+count + firstChar);
                }
                count=1;
                firstChar=s.charAt(i);

                if (i==s.length()-1){
                    sb.append(""+firstChar);
                }
            }
        }



        return sb.length();
    }
}
