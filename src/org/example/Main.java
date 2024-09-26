package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {

        public static void main(String[] args) throws IOException {
            String filePath = "D:\\workspace\\demo\\fastapi_5.txt";  // Specify the file path

            Map<String, String> file2infor = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                boolean chunckBegins = false;
                String key = null;
                StringBuilder info = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("[Code Generator] Describing file")) {
                        chunckBegins = true;
                        key = line.substring(32, line.indexOf("...")).trim();
                    }

                    else if (chunckBegins && !line.startsWith("}")) {
                        info.append(line);
                    }
                    else if (chunckBegins && line.startsWith("}")) {
                        info.append(line + ",\n");
                        chunckBegins = false;
                        file2infor.put(key,info.toString());

                    }

                }
                System.out.println(file2infor);
            }

            StringBuilder json = new StringBuilder();
            json.append("{ \n" );



        }
}
