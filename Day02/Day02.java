import java.util.*;
import java.io.*;


public class Day02 {

    public static void main(String[] args) throws IOException {
        part2();
    }

    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day02d.txt"));
        int sum = 0;
        HashMap<String, Integer> allowed = new HashMap<String, Integer>();
        allowed.put("red",12);
        allowed.put("green",13);
        allowed.put("blue",14);

        while(in.hasNextLine()) {
            String line = in.nextLine();
            boolean isValid = true;
            int colon = line.indexOf(":");
            int id = Integer.parseInt(line.substring(5,colon));

            String[] rounds = line.substring(colon +1).split(";");
            for(String round : rounds) {
                String[] cubes = round.split(",");
                for(String cube : cubes) {
                    String[] actualCube = cube.strip().split(" ");
                    int num = Integer.parseInt(actualCube[0]);
                    if(num > allowed.get(actualCube[1])) {
                        isValid = false;
                    }
                }
            }

            if (isValid) {
                sum += id;
            }
        }
        System.out.println(sum);
    }
    
    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("day02d.txt"));
        long total = 0;
        HashMap<String, Integer> allowed = new HashMap<String, Integer>();
        

        while(in.hasNextLine()) {
            allowed.put("red",0);
            allowed.put("green",0);
            allowed.put("blue",0);
            String line = in.nextLine();
            int colon = line.indexOf(":");
            int id = Integer.parseInt(line.substring(5,colon));

            String[] rounds = line.substring(colon +1).split(";");
            for(String round : rounds) {
                String[] cubes = round.split(",");
                for(String cube : cubes) {
                    String[] actualCube = cube.strip().split(" ");
                    int num = Integer.parseInt(actualCube[0]);
                    if(num > allowed.get(actualCube[1])) {
                        allowed.put(actualCube[1],num);
                    }
                }
            }
            total += allowed.get("red") * allowed.get("green") * allowed.get("blue");
        }
        System.out.println(total);
    }
}
