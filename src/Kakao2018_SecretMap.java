class Kakao2018_SecretMap {

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        String[] answer = solution(n, arr1, arr2);

        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }

    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};

        String[] arr1map = new String[arr1.length];
        String[] arr2map = new String[arr2.length];

        for(int i=0;i<n;i++){
            arr1map[i] = intToBinaryString(n, arr1[i]);
            arr2map[i] = intToBinaryString(n, arr2[i]);
        }

        answer = unionMap(n,arr1map,arr2map);

        return answer;
    }


    public static String intToBinaryString(int n, int num){
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toBinaryString(num));
        int len = n-sb.length();
        for(int i=0;i<len;i++){
            sb.insert(0,0);
        }

        return sb.toString();
    }

    public static String[] unionMap(int n, String[] map1, String[] map2){
        StringBuilder[] sb = new StringBuilder[n];
        for(int i=0;i<n;i++){
            sb[i] = new StringBuilder();
            for(int j=0;j<n;j++){
                if(map1[i].charAt(j)=='1' || map2[i].charAt(j)=='1'){
                    sb[i].append("#");
                }else{
                    sb[i].append(" ");
                }
            }
        }

        String[] returnVal = new String[n];
        for(int i=0;i<n;i++){
            returnVal[i] = sb[i].toString();
        }

        return returnVal;
    }

}