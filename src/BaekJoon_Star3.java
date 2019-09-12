public class BaekJoon_Star3 {
    public static void main(String args[]){
        int n = 100;

        for(int i=n;i>0;i--){
            for(int j=i-1;j>0;j--){
                System.out.print(" ");
            }
            for(int j=0;j<n-(i-1);j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
