import jodd.json.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) throws IOException {
        HashMap<String, ArrayList<Country>> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String content = readFile("countries.txt");
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] columns = line.split("\\|");
            String abrv = columns[0];
            String name = columns[1];

            Country c = new Country(name, abrv);

            String firstLetter = String.valueOf(name.charAt(0));
            ArrayList<Country> list = map.get(firstLetter);
            if (list == null) {
                list = new ArrayList();
                list.add(c);
                map.put(firstLetter, list);

            } else {
                list.add(c);
            }

        }
        System.out.println("Type a letter...any letter...");
        String letterchoice = scanner.nextLine().toLowerCase();
        ArrayList<Country> answerlist = map.get(letterchoice);
        String newFileName = String.format("%s_countries.txt", letterchoice);

        if (map.containsKey(letterchoice)) {
            String newLine = "";
            for (Country a : map.get(letterchoice)) {
                newLine += String.format("%s %s \n", a.name, a.abr);

                System.out.println(newLine);
            }
            writeFile(newFileName,newLine);
        }
    }

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }
    static void writeFile (String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error...Error...Error");
            }
        }
    }

