    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.HashMap;
    import java.util.StringTokenizer;

    public class BaekJoon_1316 {
        public static void main(String args[]){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int n = Integer.parseInt(br.readLine());
                int result = 0;

                for(int i=0;i<n;i++){
                    String str = br.readLine();
                    char recentChar=str.charAt(0);
                    HashMap map = new HashMap();
                    boolean isGroup = false;
                    map.put(recentChar,0);

                    if(str.length()==1){
                        isGroup=true;
                    }else {
                        for (int j = 1; j < str.length(); j++) {
                            if (str.charAt(j) != recentChar) {
                                if (map.containsKey(str.charAt(j))) {
                                    break;
                                } else {
                                    recentChar = str.charAt(j);
                                    map.put(recentChar, 0);
                                }
                            }
                            if (j == str.length() - 1) {
                                isGroup = true;
                            }
                        }
                    }
                    if(isGroup){
                        result++;
                    }
                }

                System.out.println(result);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
