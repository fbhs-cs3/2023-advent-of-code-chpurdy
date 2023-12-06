import java.util.*;
import java.io.*;


// https://adventofcode.com/2023/day/1

public class Day01 {

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day01.txt"));
        long total = 0;
        while(in.hasNextLine()) {
            String n = in.nextLine();
            String res = "";
            for(int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                if(c >= '0' && c <='9') {
                    res += c;
                    break;
                }
            }

            for(int i = n.length()-1;i>=0;i--) {
                char c = n.charAt(i);
                if(c >= '0' && c <='9') {
                    res += c;
                    break;
                }
            }
            
            System.out.println(res);
            total += Integer.parseInt(res);
        }
        System.out.println(total);
    }

    public static void part2() throws IOException {
        HashMap<String, Integer> numVals = new HashMap<String, Integer>();
        numVals.put("one",1);
        numVals.put("two",2);
        numVals.put("three",3);
        numVals.put("four",4);
        numVals.put("five",5);
        numVals.put("six",6);
        numVals.put("seven",7);
        numVals.put("eight",8);
        numVals.put("nine",9);
        Scanner in = new Scanner(new File("day01.txt"));
        long total = 0;
        while(in.hasNextLine()) {
            boolean foundFirst = false;
            boolean foundSecond = false;
            String n = in.nextLine();
            String res = "";
            for(int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                if(c >= '0' && c <='9') {
                    res += c;
                    foundFirst = true;
                    break;
                }
                for(int j=1; j < 6; j++) {
                    if(i+j >= n.length()) break;
                    String s = n.substring(i,i+j);
                    if(numVals.containsKey(s)) {
                        res += numVals.get(s);
                        foundFirst = true;
                        break;
                    }
                }
                if(foundFirst) break;
            }

             for(int i = n.length()-1;i>=0;i--) {
                char c = n.charAt(i);
                if(c >= '0' && c <='9') {
                    res += c;
                    foundSecond=true;
                    break;
                }

                for(int j=1; j < 6; j++) {
                    if(i+j > n.length()) break;
                    String s = n.substring(i,i+j);
                    if(numVals.containsKey(s)) {
                        res += numVals.get(s);
                        foundSecond = true;
                        break;
                    }
                }
                if(foundSecond) break;
            }
            System.out.println(res);
            total += Integer.parseInt(res);
        }
        System.out.println(total);
    }

    public static void part1take2() throws IOException {
        Scanner in = new Scanner(new File("day01s.txt"));
        long total = 0;
        while(in.hasNextLine()) {
            String n = in.nextLine();
            n = n.replaceAll("\\D","");
            total += (n.charAt(0)-'0')*10 + n.charAt(n.length()-1)-'0';
        }
        System.out.println(total);
    }


    public static void main(String[] args) throws IOException {
        part1take2();
    }


}