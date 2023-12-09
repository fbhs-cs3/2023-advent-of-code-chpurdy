import java.util.*;
import java.io.*;

public class Day09 {

    public static boolean allZero(ArrayList<Long> ns) {
        for(long n : ns) {
            if (n != 0) return false;
        }
        return true;
    }
    public static ArrayList<Long> solve(ArrayList<Long> nums) {
       
        boolean allZero = true;
        for(long n : nums) {
            if(n != 0) allZero = false;
        }

        if(allZero) {
            nums.add(0L);
            return nums;
        }

        ArrayList<Long> res = new ArrayList<Long>();
        for(int i = 0; i < nums.size()-1; i++) {
            res.add(nums.get(i+1)-nums.get(i));
        }
        ArrayList<Long> l = solve(res);
        res.add(res.get(res.size()-1) + l.get(l.size()-1));
         System.out.println(res);
        return res;
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day09r.txt"));
        Long total = 0L;
        
        while(in.hasNextLine()) {
            ArrayList<ArrayList<Long>> ns = new ArrayList<ArrayList<Long>>();
            String[] ss = in.nextLine().split(" ");
            ArrayList<Long> curr = new ArrayList<Long>();
            for(String s :  ss) {
                curr.add(Long.parseLong(s));
            }   
            ns.add(curr);

            while(!allZero(curr)) {
                ArrayList<Long> next = new ArrayList<Long>();
                for(int i = 0; i < curr.size()-1; i++) {
                    next.add(curr.get(i+1)-curr.get(i));
                }
                ns.add(next);
                curr = next;
            }

            for(int i = ns.size()-1; i > 0; i--) {
                ArrayList<Long> last = ns.get(i);
                ArrayList<Long> befo = ns.get(i-1);
                befo.add(last.get(last.size()-1) + befo.get(befo.size()-1));
            }
            //System.out.println(ns);
            ArrayList<Long> top = ns.get(0);
            total += top.get(top.size()-1);
        }
        
        System.out.println(total);

    }


    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("day09r.txt"));
        Long total = 0L;
        
        while(in.hasNextLine()) {
            ArrayList<ArrayList<Long>> ns = new ArrayList<ArrayList<Long>>();
            String[] ss = in.nextLine().split(" ");
            ArrayList<Long> curr = new ArrayList<Long>();
            for(String s :  ss) {
                curr.add(Long.parseLong(s));
            }   
            ns.add(curr);

            while(!allZero(curr)) {
                ArrayList<Long> next = new ArrayList<Long>();
                for(int i = 0; i < curr.size()-1; i++) {
                    next.add(curr.get(i+1)-curr.get(i));
                }
                ns.add(next);
                curr = next;
            }

            for(int i = ns.size()-1; i > 0; i--) {
                ArrayList<Long> last = ns.get(i);
                ArrayList<Long> befo = ns.get(i-1);
                befo.add(0, befo.get(0) - last.get(0));
            }
            //System.out.println(ns);
            ArrayList<Long> top = ns.get(0);
            total += top.get(0);
        }
        
        System.out.println(total);

    }

    public static void main(String[] args) throws IOException {
        part2();
    }
}