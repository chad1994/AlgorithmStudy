import java.util.*;

public class Kakao2019Intern_Tuple {

    public static void main(String[] args) {
        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        String s2 = "{{20,111},{111}}";
        solution2(s);
    }

    // 스플릿 해서 하나하나 순차적으로 비교하며 풀이.. (성공 했지만 너무 오래걸린다고 생각했음)
    public static int[] solution(String s) {
        int[] answer;
        ArrayList<Integer> answerList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int listIndex = 0;

        String[] splitBracket = s.split("[{}]"); // { } (배열) 단위로 분리
        String[] splitComma;
        for (int i = 2; i < splitBracket.length; i++) {
            if (!splitBracket[i].equals(",")) { // , 단위로(배열안의 원소) 분리
                list.add(new ArrayList<>());
                splitComma = splitBracket[i].split(",");
                for (int j = 0; j < splitComma.length; j++) {
                    list.get(listIndex).add(Integer.parseInt(splitComma[j])); //list안의 list에 담는다
                }
                listIndex++;
            }
        }

        Collections.sort(list, new Comparator<ArrayList<Integer>>() { //list들의 크기순서로 정렬
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return Integer.compare(o1.size(), o2.size());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (!answerList.contains(list.get(i).get(j))) { // 없으면 담아줌.
                    answerList.add(list.get(i).get(j));
                    break;
                }
            }
        }

        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }


    // 배열단위로 나눠서 생각하지 않아도 결과적으로 빈도수가 가장 큰 원소가 제일 앞에 위치하기 때문에 빈도수를 인덱스로 해서 저장하면 된다고 생각,
    // hashMap의 key를 원소, value를 빈도수( length - 빈도수 = answer의 index)로 생각하고 풀이.
    // 묘책이라 생각했지만, 결과적으로 시간 효율에 큰 변화가 없음...ㅠ

    public static int[] solution2(String s) {
        int[] answer = {};
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        String[] split = s.split("[{,}]"); // 모든 원소(숫자)로 분리시킴
        for (int i = 2; i < split.length; i++) {
            if (!split[i].equals("")) { // 숫자만 확인
                int num = Integer.parseInt(split[i]);
                hashMap.put(num, hashMap.getOrDefault(num,0)+1); // 없으면 1, 있으면 기존 수에 +1 히여 저장
            }
        }

        answer = new int[hashMap.size()];
        Iterator<Integer> iter = hashMap.keySet().iterator();
        while(iter.hasNext()){
            int key = iter.next();
            answer[answer.length - hashMap.get(key)] = key; //  answer의 인덱스 = answer.length - key의 빈도수 , answer의 값 = key
        }

        return answer;
    }
}
