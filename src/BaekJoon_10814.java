import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_10814 {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Person p[] = new Person[n];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            p[i] = new Person(Integer.parseInt(st.nextToken()),st.nextToken());
        }


        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.age>o2.age){
                    return 1;
                }else if (o1.age<o2.age){
                    return -1;
                }else{
                    return 0;
                }
            }
        };

        Arrays.sort(p,comparator);

        for(int i=0;i<p.length;i++){
            System.out.println(p[i].age+" "+p[i].name);
        }
    }

    static class Person{
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

    }
}
