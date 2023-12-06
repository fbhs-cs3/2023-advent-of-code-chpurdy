import java.util.*;
import java.io.*;

public class Day04 {
    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day04r.txt"));
        double total = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] cards = line.substring(line.indexOf(":")+2).split("\\|");
            System.out.println(Arrays.toString(cards));
            HashSet<Integer> nums = new HashSet<Integer>();
            for(String n : cards[0].split("\\s+")) {
                if(n.length() > 0)
                    nums.add(Integer.parseInt(n));
            }

            int count = 0;
            for(String n : cards[1].split("\\s+")) {
                if(n.length() >0)
                    if (nums.contains(Integer.parseInt(n))) count++;
            }
            if(count >= 1) {
                System.out.println(Math.pow(2,count-1));
                total += Math.pow(2,count-1);
            } else { 
                System.out.println(0);
            }
        }
        System.out.println(total);

    }

    public static void part2() throws IOException {
        ArrayList<Integer> cardPoints = new ArrayList<Integer>();
       HashMap<Integer, Integer> cardNums = new HashMap<Integer, Integer>();

        Scanner in = new Scanner(new File("day04r.txt"));
        double total = 0;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] cards = line.substring(line.indexOf(":")+2).split("\\|");
            //System.out.println(Arrays.toString(cards));
            HashSet<Integer> nums = new HashSet<Integer>();
            for(String n : cards[0].split("\\s+")) {
                if(n.length() > 0)
                    nums.add(Integer.parseInt(n));
            }

            int count = 0;
            for(String n : cards[1].split("\\s+")) {
                if(n.length() >0)
                    if (nums.contains(Integer.parseInt(n))) count++;
            }
            if(count >= 1) {
                cardPoints.add(count);
            } else { 
                cardPoints.add(0);
            }
        }
        for(int i = 0; i < cardPoints.size(); i++) {
            cardNums.put(i+1,1);
        }
        //System.out.println(cardNums);
        //System.out.println(cardPoints);
        for(int i = 0; i < cardPoints.size(); i++) {
            int points = cardPoints.get(i);
            int numCards = cardNums.get(i+1);
            for(int k = 0; k < numCards; k++) {
                for(int j = 1; j <= points; j++) {
                    int c = i+1 + j;
                    cardNums.put(c,cardNums.get(c)+1);
                }
            }
        }
        //System.out.println(cardNums);
        
        int count = 0;
        for(Integer n : cardNums.keySet()) {
            count += cardNums.get(n);
        }
        System.out.println(count);
    }


    public static void main(String[] args) throws IOException {
        part2();
    }
}