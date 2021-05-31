package gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    public JSONReader(String path){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);

            JSONArray numberList = (JSONArray) obj;
            int[] customArray = new int[numberList.size()];

            for (int k=0; k<numberList.size(); k++ ){
                long longValue = (long) numberList.get(k);
                int intValue = (int)longValue;
                customArray[k] = intValue;
                System.out.println(intValue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
    }


        }

