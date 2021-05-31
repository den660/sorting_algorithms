package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.NumberArray;

public class JSONLoader {

    private static int[] jsonArray;
    private static int jsonSize;
    private int[] numbers;
    private static NumberArray numberArray;

    public JSONLoader(File path){

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);

            JSONArray numberList = (JSONArray) obj;
            jsonSize = numberList.size();
            jsonArray = new int[jsonSize];

            for (int k=0; k<jsonSize; k++ ){
                long longValue = (long) numberList.get(k);
                int intValue = (int)longValue;
                jsonArray[k] = intValue;
            }
            numberArray = new NumberArray(jsonSize);
            numbers = new int[jsonSize];
            for(int k=0; k<jsonSize; k++){
                numbers[k]=jsonArray[k];
                System.out.println(numbers[k]);
            }
            numberArray.setNumbers(numbers);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public static NumberArray getNumberArray() {
        return numberArray;
    }
}

