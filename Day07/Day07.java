import java.util.*;
import java.io.*;

public class Day07 {

    static class Hand implements Comparable<Hand>{
        String cards;
        HashMap<String, Integer> counts;
        static final String order = "AKQJT98765432";

        public Hand(String cards) {
            this.cards = cards;
            counts = new HashMap<String, Integer>();
            for(int i = 0; i < cards.length(); i++) {
                String c = cards.substring(i,i+1);
                if(counts.keySet().contains(c)) {
                    counts.put(c, counts.get(c)+1);
                } else {
                    counts.put(c, 1);
                }
            }
        }

        public boolean isFive() {
            for(String c : counts.keySet()) {
                if(counts.get(c) == 5) return true;
            }
            return false;
        }

        public boolean isFour() {
            for(String c : counts.keySet()) {
                if(counts.get(c) == 4) return true;
            }
            return false;
        }

        public boolean isFull() {
            boolean has3 = false;
            boolean has2 = false;
            for(String c : counts.keySet()) {
                if(counts.get(c) == 3) has3 = true;
                if(counts.get(c) == 2) has2 = true;
            }
            return has3 && has2;
        }

        public boolean isThree() {
            boolean has3 = false;
            boolean has2 = false;
            for(String c : counts.keySet()) {
                if(counts.get(c) == 3) has3 = true;
                if(counts.get(c) == 2) has2 = true;
            }
            return has3 && !has2;
        }

        public boolean isTwoPair() {
            boolean has2 = false;
            for(String c : counts.keySet()) {
                if(!has2) {
                    if(counts.get(c) == 2) has2 = true;
                } else if(counts.get(c) == 2) return true;
            }
            return false;
        }

        public boolean isOnePair() {        
            for(String c : counts.keySet()) {
                if(counts.get(c) == 2) return true;
            }
            return false;
        }

        public int compareHigh(Hand other) {
            for(int i = 0; i < cards.length(); i++) {
                String c1 = cards.substring(i,i+1);
                String c2 = other.cards.substring(i,i+1);

                int i1 = order.indexOf(c1);
                int i2 = order.indexOf(c2);

                if(i1 != i2) return (i2 - i1);
            }
            return 0;
        }
        
        public int compareTo(Hand other) {
            if(isFive() && !other.isFive()) return 1;
            if(!isFive() && other.isFive()) return -1;
            if(isFive() && other.isFive()) return compareHigh(other);

            
            if(isFour() && !other.isFour()) return 1;
            if(!isFour() && other.isFour()) return -1;
            if(isFour() && other.isFour()) return compareHigh(other);

            if(isFull() && !other.isFull()) return 1;
            if(!isFull() && other.isFull()) return -1;
            if(isFull() && other.isFull()) return compareHigh(other);

            if(isThree() && !other.isThree()) return 1;
            if(!isThree() && other.isThree()) return -1;
            if(isThree() && other.isThree()) return compareHigh(other);

            if(isTwoPair() && !other.isTwoPair()) return 1;
            if(!isTwoPair() && other.isTwoPair()) return -1;
            if(isTwoPair() && other.isTwoPair()) return compareHigh(other);

            if(isOnePair() && !other.isOnePair()) return 1;
            if(!isOnePair() && other.isOnePair()) return -1;
            if(isOnePair() && other.isOnePair()) return compareHigh(other);

            return compareHigh(other);
        }

        public String toString() {
            return cards;
        }
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day07r.txt"));
        ArrayList<Hand> hands = new ArrayList<Hand>();
        HashMap<Hand, Long> bids = new HashMap<Hand, Long>();
        
        while(in.hasNextLine()) {
            String[] line = in.nextLine().split(" ");
            Hand h = new Hand(line[0]);
            bids.put(h, Long.parseLong(line[1]));
            hands.add(h);
        }

        Collections.sort(hands);
        long total = 0;
        for(int i = 0; i < hands.size(); i++) {
            System.out.println(hands.get(i));
            total += (i+1) * bids.get(hands.get(i));
        }
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        part1();
    }

}