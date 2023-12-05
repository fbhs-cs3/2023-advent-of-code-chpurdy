import java.util.*;
import java.io.*;

public class Day03 {
    
    public static void part1() throws IOException {
        Scanner in = new Scanner(new File("day03.txt"));
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
                    System.out.println(currNum);
                    if(row != 0) {
                        String above = schematic.get(row-1);
                        if(col > 0) {
                            if(above.charAt(col-1) != '.') {
                                isNum = true;
                            }
                        }
                        if(above.charAt(col) != '.') {
                            isNum = true;
                        }
                        if(col < above.length()-1) {
                            if(above.charAt(col+1) != '.') {
                                isNum = true;
                            }
                        }
                    }

                    if(col > 0) {
                        if(!Character.isDigit(thisRow.charAt(col-1)) && thisRow.charAt(col-1) !='.') {
                            isNum = true;
                        }
                    }

                    if(col < thisRow.length()-1) {
                        if(!Character.isDigit(thisRow.charAt(col+1)) && thisRow.charAt(col+1) !='.') {
                            isNum = true;
                        }
                    }

                    if(row != schematic.size()-1) {
                        String below = schematic.get(row+1);
                        if(col > 0) {
                            if(below.charAt(col-1) != '.') {
                                isNum = true;
                            }
                        }
                        if(below.charAt(col) != '.') {
                            isNum = true;
                        }
                        if(col < below.length()-1) {
                            if(below.charAt(col+1) != '.') {
                                isNum = true;
                            }
                        }
                    }
                    
                    col++;
                } else {
                    if(currNum.length() > 0) {
                        if(isNum) {
                            sum += Integer.parseInt(currNum);
                        }
                        currNum = "";
                        isNum = false;
                    }
                    col++;
                }
            }


            row++;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        part1();
    }
}