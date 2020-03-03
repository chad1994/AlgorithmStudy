import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon_15686 {

    static int N;
    static int M;
    static List<Pair<Integer, Integer>> chicken;
    static List<Pair<Integer, Integer>> house;
    static int minChickenDistance = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chicken = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    house.add(new Pair<>(i, j));
                } else if (n == 2) {
                    chicken.add(new Pair<>(i, j));
                }
            }
        }
        ArrayList<Pair<Integer,Integer>> list = new ArrayList<>();
        combination(list, chicken.size(), M, 0);

        System.out.println(minChickenDistance);

    }

    private static void combination(ArrayList<Pair<Integer,Integer>> combList, int n, int r, int target) {
        if (r == 0) {
            minChickenDistance = Math.min(minChickenDistance , calculateProfit(combList));
            return;
        }

        if (target == n) return;

        combList.add(chicken.get(target));
        combination(combList, n, r - 1, target + 1);//뽑는경우

        combList.remove(combList.size()-1);
        combination(combList, n, r, target+1); //안뽑는경우

    }

    private static int calculateProfit(ArrayList<Pair<Integer,Integer>> combList){
        int sumChickenDistance = 0;
        for(int i=0;i<house.size();i++){
            int min = Integer.MAX_VALUE;
            for(int j=0;j<M;j++){
                int a = Math.abs(house.get(i).getKey()-combList.get(j).getKey());
                int b = Math.abs(house.get(i).getValue()-combList.get(j).getValue());
                min = Math.min(a+b,min);
            }
                sumChickenDistance += min;
        }
        return sumChickenDistance;
    }
}
