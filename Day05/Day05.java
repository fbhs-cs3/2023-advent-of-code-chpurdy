import java.util.*;
import java.io.*;


class RangeThing {
    long start;
    long range;

    public RangeThing(long s, long r) {
        start = s;
        range = r;
    }

    public boolean inRange(long n) {
        return (n >= start && n < (start + range));
    }

    public String toString() {
        return start + " - " + (start + range-1);
    }
}


public class Day05 {
    
    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day05.txt"));
        long lowest = Integer.MAX_VALUE;
        HashMap<String, HashMap<RangeThing, RangeThing>> data = new HashMap<String, HashMap<RangeThing, RangeThing>>();
        String[] seeds = in.nextLine().substring(7).split(" ");
        in.nextLine(); // skip blank
        while(in.hasNextLine()) {
            String line = in.nextLine(); // 
            if(line.contains("map:")) { // must be 
                int end = line.indexOf("map:")-1;
                String fromToTo = line.substring(0,end);
                HashMap<RangeThing,RangeThing> mapping = new HashMap<RangeThing, RangeThing>();
                data.put(fromToTo, mapping);

                while(in.hasNextLine()) {
                    line = in.nextLine();
                    if(line.length() == 0) {
                        break;
                    }
                    
                    String[] maps = line.split(" ");
                    long from = Long.parseLong(maps[0]);
                    long to = Long.parseLong(maps[1]);
                    long range = Long.parseLong(maps[2]);
                    
                    mapping.put(new RangeThing(to, range), new RangeThing(from, range));
                }
               
            } else {
                System.out.println("ERROR IN SPACING SOMEHOW");
            }
        }

        for(String seed : seeds) {
            long num = Long.parseLong(seed);
            String at = "seed";
            System.out.print(at + ": " + num + " - ");
            while(!at.equals("location")) {
                for(String fromToTo : data.keySet()) {
                    if(fromToTo.startsWith(at)) {
                        int toIndex = fromToTo.lastIndexOf("-");
                        String to = fromToTo.substring(toIndex+1);
                        at = to;
                        HashMap<RangeThing, RangeThing> toFrom = data.get(fromToTo);
                        for(RangeThing rt : toFrom.keySet()) {
                            if(rt.inRange(num)) {
                                RangeThing tr = toFrom.get(rt);
                                num = tr.start + (num - rt.start);
                                break;
                            }
                        }
                        System.out.print(at + ": " + num + " - ");
                        break;
                    }
                }
            }
            if(num < lowest) lowest = num;
            System.out.println();
        }
        System.out.println(lowest);

    }

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("day05r.txt"));
        long lowest = Integer.MAX_VALUE;
        HashMap<String, HashMap<RangeThing, RangeThing>> data = new HashMap<String, HashMap<RangeThing, RangeThing>>();
        String[] seeds = in.nextLine().substring(7).split(" ");
        ArrayList<RangeThing> seedRanges = new ArrayList<RangeThing>();
        for(int i = 0; i < seeds.length; i+=2) {
            long from = Long.parseLong(seeds[i]);
            long range = Long.parseLong(seeds[i+1]);
            seedRanges.add(new RangeThing(from, range));
        }

        in.nextLine(); // skip blank
        while(in.hasNextLine()) {
            String line = in.nextLine(); // 
            if(line.contains("map:")) { // must be 
                int end = line.indexOf("map:")-1;
                String fromToTo = line.substring(0,end);
                HashMap<RangeThing,RangeThing> mapping = new HashMap<RangeThing, RangeThing>();
                data.put(fromToTo, mapping);

                while(in.hasNextLine()) {
                    line = in.nextLine();
                    if(line.length() == 0) {
                        break;
                    }
                    
                    String[] maps = line.split(" ");
                    long from = Long.parseLong(maps[1]);
                    long to = Long.parseLong(maps[0]);
                    long range = Long.parseLong(maps[2]);
                    
                    mapping.put(new RangeThing(to, range), new RangeThing(from, range));
                }
               
            } else {
                System.out.println("ERROR IN SPACING SOMEHOW");
            }
        }

        int loc =0 ;
        long num = loc;
        String at = "location";
        //System.out.print(at + " - " + num + " - ");
        while(true) {
            if(at.equals("seed")) {
                for(RangeThing rt : seedRanges) {
                    //System.out.println("Checking if " + num + "is in " + rt);
                    if(rt.inRange(num)) {
                        System.out.println("\n" + loc);
                        return;
                    }
                }
                //System.out.println();
                at = "location";
                loc += 1;
                num = loc;
                //System.out.print(at + " - " + loc + " - ");
            }

            for(String fromToTo : data.keySet()) {
                if(fromToTo.endsWith(at)) {
                    HashMap<RangeThing, RangeThing> toFrom = data.get(fromToTo);
                    for(RangeThing tf : toFrom.keySet()) {
                        if(tf.inRange(num)) {
                            num = toFrom.get(tf).start + (num - tf.start);
                            break;
                        }
                    }
                    int endIndex = fromToTo.indexOf("-");
                    at = fromToTo.substring(0, endIndex);
                    //System.out.print(at + " - " + num + " - ");
                }
            }
        }

    }
    
    public static void main(String[] args) throws IOException {
        part2();
    }
}