import java.util.ArrayList;
import java.util.List;

public class Kakao2020_4 {

    public static void main(String args[]){

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

//        int[] answer = solution(words,queries);
//
//        for(int i=0;i<answer.length;i++){
//            System.out.println(answer[i]);
//        }

    }

//    public static int[] solution(String[] words, String[] queries) { 1차 답안 : 정확성 100 , 효율성 40
//        int[] answer = new int[queries.length];
//        boolean isCorrect = false;
//        int count = 0;
//
//        for(int i=0;i<queries.length;i++){
//            count = 0;
//            for(int j=0;j<words.length;j++){
//                isCorrect = true;
//                if(queries[i].length()==words[j].length()){
//                    for(int k=0;k<queries[i].length();k++){
//                        if(queries[i].charAt(k)!='?' && queries[i].charAt(k)!=words[j].charAt(k)){
//                            isCorrect = false;
//                            break;
//                        }
//                    }
//                    if(isCorrect){
//                        count++;
//                    }
//                }
//            }
//            answer[i] = count;
//            count =0;
//        }
//
//
//        return answer;
//    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];


        return answer;
    }

    public class Constants{
        public static final int ALPHABET_SIZE = 26;
    }

    public class TrieNode {

        private String character;   //  알파벳 문자, 키 값
        private int value;          //  데이터
        private TrieNode[] children;    //  하위 노드 배열 변수
        private boolean leaf;       //  leaf 노드 여부 확인

        // 생성자
        public TrieNode(String character) {
            this.character = character;     // 알파벳
            this.children = new TrieNode[Constants.ALPHABET_SIZE];
        }

        // getter, setter, toString

        public TrieNode getChild(int index) {
            return children[index];
        }

        public void setChild(int index, TrieNode trieNode, int value) {
            trieNode.setValue(value);
            this.children[index] = trieNode;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public TrieNode[] getChildren() {
            return children;
        }

        public void setChildren(TrieNode[] children) {
            this.children = children;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        @Override
        public String toString() {
            return this.character;
        }
    }

    public class Trie {

        private TrieNode root;  // 루트 노드
        private int indexOfSingleChild; // 단일 노드의 인덱스

        // 생성자
        public Trie() {
            this.root = new TrieNode(""); // 루트 노드 빈문자열로 초기화
        }


        public void insert(String key, int value) {

            TrieNode tempNode = root;   // 루트 노드로 초기화

            // 입력한 key의 길이 만큼 반복
            for (int i = 0; i < key.length(); i++) {

                char c = key.charAt(i);    // 알파벳 추출
                int asciiIndex = transformASCIIIndex(c); // 추출한 알파벳을 배열 인덱스에 맞게 저장할 수 있도록 ascii 값 변환

                // 추출한 알파벳을 가진 하위노드가 존재 하지 않으면
                if (tempNode.getChild(asciiIndex) == null) {
                    TrieNode node = new TrieNode(String.valueOf(c));    // 새로운 노드 생성
                    tempNode.setChild(asciiIndex, node, value); // 하위노드로 세팅
                    tempNode = node;                            // 하위 노드로 이동
                } else {
                    tempNode = tempNode.getChild(asciiIndex);   // 하위 노드로 이동
                }
            }

            tempNode.setLeaf(true); // 알파벳 문자들의 삽입이 완료되고 마지막 노드를 leaf 노드로 설정
        }


        private int transformASCIIIndex(char c) {
            return c - 'a';
        }


        // 탐색 : 해당 키가 존재하는지 여부 반환
        public boolean search(String key) {

            TrieNode trieNode = root; // 루트 노드로 초기화

            // 입력한 key의 길이만큼 반복
            for (int i = 0; i < key.length(); i++) {

                int asciiIndex = transformASCIIIndex(key.charAt(i)); // 추출한 알파벳을 배열 인덱스에 맞게 ascii 값 변환

                if (trieNode.getChild(asciiIndex) == null) {    // 추출한 알파벳을 가진 하위 노드가 존재하지 않으면 false 리턴
                    return false;
                } else {
                    trieNode = trieNode.getChild(asciiIndex);   // 하위 노드로 이동
                }
            }

            return true;
        }


        // 탐색 : 해당 키값에 해당하는 값 반환
        public Integer searchAsMap(String key) {

            TrieNode trieNode = root; // 루트 노드로 초기화

            // 입력한 key의 길이만큼 반복
            for (int i = 0; i < key.length(); i++) {

                int asciiIndex = transformASCIIIndex(key.charAt(i)); // 추출한 알파벳을 배열 인덱스에 맞게 ascii 값 변환

                if (trieNode.getChild(asciiIndex) != null) {    // 추출한 알파벳을 가진 하위 노드가 존재하면
                    trieNode = trieNode.getChild(asciiIndex);   // 하위 노드로 이동
                } else {    // 존재하지 않으면 null 반환
                    return null;
                }
            }

            return trieNode.getValue();
        }


        // Autocomplete : 자동완성
        public List<String> allWordsWithPrefix(String prefix) {

            TrieNode trieNode = root;   // 루트 노드로 초기화

            List<String> allWords = new ArrayList<>();

            // 접두사 길이 만큼 반복 수행
            for (int i = 0; i < prefix.length(); i++) {
                int asciiIndex = transformASCIIIndex(prefix.charAt(i)); // 추출한 알파벳을 배열 인덱스에 맞게 ascii 값 변환
                trieNode = trieNode.getChild(asciiIndex);               // 하위 노드로 이동
            }

            collect(trieNode, prefix, allWords); // 접두사 이후의 단어들 모음
            return allWords;
        }


        // 자동완성 단어 수집
        private void collect(TrieNode node, String prefix, List<String> allWords) {
            // 노드가 null 이면 메서드 종료
            if (node == null) {
                return;
            }
            // leaf 노드이면 allWords에 저장
            if (node.isLeaf()) {
                allWords.add(prefix);
            }
            // 노드의 자식노드의 수 만큼 반복 수행
            for (TrieNode childNode : node.getChildren()) {
                if (childNode == null) {
                    continue;
                }
                // 자식노드의 알파벳
                String childCharacter = childNode.getCharacter();
                collect(childNode, prefix + childCharacter, allWords); // 접두사 + 추출한 알파벳, 반복수행을 위해 재귀호출
            }
        }


        // 정렬
        public void sort() {
            List<String> list = allWordsWithPrefix("");
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }


        // 가장 긴 접두사 반환
        public String longestCommonPrefix() {
            TrieNode trieNode = root; // 루트 노드로 초기화
            String longestCommonPrefix = ""; // 빈 문자열로 초기화

            // 하위노드가 여러개 이거나 leaf 노드일 때까지 반복 수행
            while (countNumOfChildren(trieNode) == 1 && !trieNode.isLeaf()) {
                trieNode = trieNode.getChild(indexOfSingleChild);
                longestCommonPrefix = longestCommonPrefix + String.valueOf((char) (indexOfSingleChild + 'a'));
            }
            return longestCommonPrefix;
        }


        // 하위 노드의 갯수 반환
        private int countNumOfChildren(TrieNode trieNode) {
            int numOfChildren = 0; // 하위 노드 개수 0으로 초기화

            // 하위 노드의 개수 만큼 반복 수행
            for (int i = 0; i < trieNode.getChildren().length; i++) {
                // 하위 노드가 존재하면
                if (trieNode.getChild(i) != null) {
                    numOfChildren++; // 하위 노드 1 증가
                    indexOfSingleChild = i; // 단일 노드의 인덱스
                }
            }
            return numOfChildren;
        }
    }
}
