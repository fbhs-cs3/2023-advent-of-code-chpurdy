import java.util.*;
import java.io.*;

public class Day03 {
    
    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day03r.txt"));
        ArrayList<String> schematic = new ArrayList<String>();
        while(in.hasNextLine()) {
            schematic.add(in.nextLine());
        }

        int sum = 0;
        int row = 0;
        int col = 0;
        System.out.println(schematic);
        while(row < schematic.size()) {
            String currNum = "";
            boolean isNum = false;
            String thisRow = schematic.get(row);
            while(col < thisRow.length()) {
                if(Character.isDigit(thisRow.charAt(col))) {
                    currNum += thisRow.charAt(col);
                    //System.out.println(currNum);

                    // look above for special character
                    if(row != 0) {
                        String above = schematic.get(row-1);
                        if(col > 0) {
                            if(above.charAt(col-1) != '.' ) {
                                isNum = true;
                            }
                        }
                        if(above.charAt(col) != '.') {
                            isNum = true;
                        }
                        if(col < above.length()-1) {
                            if(above.charAt(col+1) != '.' ) {
                                isNum = true;
                            }
                        }
                    }

                    // check left
                    if(col > 0) {
                        if(!Character.isDigit(thisRow.charAt(col-1)) && thisRow.charAt(col-1) !='.') {
                            isNum = true;
                        }
                    }

                    // check right
                    if(col < thisRow.length()-1) {
                        if(!Character.isDigit(thisRow.charAt(col+1)) && thisRow.charAt(col+1) !='.') {
                            isNum = true;
                        }
                    }

                    // check below
                    if(row != schematic.size()-1) {
                        String below = schematic.get(row+1);
                        if(col > 0) {
                            if(below.charAt(col-1) != '.' ) {
                                isNum = true;
                            }
                        }
                        if(below.charAt(col) != '.' ) {
                            isNum = true;
                        }
                        if(col < below.length()-1) {
                            if(below.charAt(col+1) != '.' ) {
                                isNum = true;
                            }
                        }
                    }
                    
                    col++;
                } else {
                    if(currNum.length() > 0) {
                        if(isNum) {
                            System.out.println(currNum);
                            sum += Integer.parseInt(currNum);
                        }
                        currNum = "";
                        isNum = false;
                    }
                    col++;
                }
            }

            // end of line finished
            if(currNum.length() > 0) {
                if(isNum) {
                    System.out.println(currNum);
                    sum += Integer.parseInt(currNum);
                }
                currNum = "";
                isNum = false;
            }


            row++;
            col = 0;
        }
        System.out.println(sum);
    }

    public static void part2() throws IOException {
        Scanner in = new Scanner(new File("day03r.txt"));
        ArrayList<String> schematic = new ArrayList<String>();
        while(in.hasNextLine()) {
            schematic.add(in.nextLine());
        }
        long result = 0;
        for(int r = 0; r < schematic.size(); r++) {
            String row = schematic.get(r);
            for(int c = 0; c < row.length(); c++) {
                if(row.charAt(c) == '*') {
                    //System.out.println("Possible gear found: " + r + " " + c);
                    int numAdj = 0;
                    ArrayList<Integer> adjacents = new ArrayList<Integer>();
                    
                    if(r > 0) {
                        String above = schematic.get(r-1);
                        if(c > 0) {
                            if(Character.isDigit(above.charAt(c-1))) {
                                // find left end of number
                                int left = above.lastIndexOf(".",c-1)+1;
                                int right = above.indexOf(".",c-1);
                                if(right == -1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }
                        }
                        if(Character.isDigit(above.charAt(c))) {
                            if(c > 0 && !Character.isDigit(above.charAt(c-1)) || c==0) {
                                int left = above.lastIndexOf(".",c)+1;
                                int right = above.indexOf(".",c);
                                if(right == -1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }   
                        }
                        if(c < above.length()-1) {
                            if(!Character.isDigit(above.charAt(c)) && Character.isDigit(above.charAt(c+1))) {
                                int left = above.lastIndexOf(".",c+1)+1;
                                int right = above.indexOf(".",c+1);
                                if(right == -1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }
                        }
            
                    }

                    if(r < schematic.size()-1) {
                        String above = schematic.get(r+1);  // above is actually below here
                        if(c > 0) {
                            if(Character.isDigit(above.charAt(c-1))) {
                                // find left end of number
                                int left = above.lastIndexOf(".",c-1)+1;
                                int right = above.indexOf(".",c-1);
                                if(right == -1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }
                        }
                        if(Character.isDigit(above.charAt(c))) {
                            if(c > 0 && !Character.isDigit(above.charAt(c-1)) || c==0) {
                                int left = above.lastIndexOf(".",c)+1;
                                int right = above.indexOf(".",c);
                                if(right==-1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }   
                        }
                        if(c < above.length()-1) {
                            if(!Character.isDigit(above.charAt(c)) && Character.isDigit(above.charAt(c+1))) {
                                int left = above.lastIndexOf(".",c+1)+1;
                                int right = above.indexOf(".",c+1);
                                if(right == -1) right = above.length();
                                adjacents.add(Integer.parseInt(above.substring(left,right)));
                            }
                        }
                    }

                    if(c > 0) {
                        if(Character.isDigit(row.charAt(c-1))) {
                            int left = row.lastIndexOf(".",c-1) + 1;
                            adjacents.add(Integer.parseInt(row.substring(left,c)));
                        }
                    }

                    if(c < row.length()-1) {
                        if(Character.isDigit(row.charAt(c+1))) {
                            int right = row.indexOf(".",c+1);
                            if(right == -1) right = row.length();
                            adjacents.add(Integer.parseInt(row.substring(c+1,right)));
                        }
                    }

                    if(adjacents.size() == 2) {
                        int a1 = adjacents.get(0);
                        int a2 = adjacents.get(1);                   
                        //System.out.println("Gear found: " + a1 + " " + a2);
                        result += a1 * a2;
                    }

                

                }
            }

            
        }
        System.out.println(result);

    }


    public static void main(String[] args) throws IOException {
        part2();
    }
}