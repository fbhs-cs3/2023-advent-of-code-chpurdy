import java.util.*;
import java.io.*;

public class Day06 {

    public static int waysToBeat(long dist, long time) {
        int numWays = 0;
        for(int heldDown = 1; heldDown < time; heldDown++) {
            if((time - heldDown) * heldDown > dist) numWays++;
        }
        return numWays;
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day06r.txt"));

        // get rid of headers
        String line1 = in.nextLine().substring(5);
        String line2 = in.nextLine().substring(9);

        Scanner lineParse = new Scanner(line1);
        ArrayList<Integer> times = new ArrayList<Integer>();
        ArrayList<Integer> dists = new ArrayList<Integer>();
        while(lineParse.hasNextInt()) {
            times.add(lineParse.nextInt());
        }

        lineParse = new Scanner(line2);
        while(lineParse.hasNextInt()) {
            dists.add(lineParse.nextInt());
        }


        long prod = 1;

        for(int race = 0; race < dists.size(); race++) {
            int dist = dists.get(race);
            int time = times.get(race);
            int numWays = waysToBeat(dist, time);
            System.out.println(dist + "mm - " + time + " ms - " + numWays);
            prod *= numWays;
        }

        System.out.println(prod);
    }

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("day06r.txt"));
        String line1 = in.nextLine().substring(5);
        String line2 = in.nextLine().substring(9);

        line1 = line1.replaceAll(" ","");
        line2 = line2.replaceAll(" ","");

        long time = Long.parseLong(line1);
        long dist = Long.parseLong(line2);

        System.out.print(time + " " + dist + " ");
        System.out.println(waysToBeat(dist, time));
    }

    public static void main(String[] args) throws IOException {
        part2();
    }
}