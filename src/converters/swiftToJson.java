package converters;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class swiftToJson {
    static final String SWIFT_REGEX = """
            - [\\d]+ : \"(?<value>[\\w.]+): Optional\\(\"(?<key>[\\w\\s]+)\"\\)\"""";
    static Pattern swiftPattern = Pattern.compile(SWIFT_REGEX);

    public static TreeMap convertFromSwiftToTreeMap(String plainText) {
        TreeMap<String, ArrayList<String>> conversionMap = new TreeMap<String, ArrayList<String>>();
        Matcher matcher = swiftPattern.matcher(plainText);
        while(matcher.find()) {
            ArrayList<String> searchValues = conversionMap.get(matcher.group("value"));

            if(searchValues != null) {
                searchValues.add(matcher.group("key"));
            } else {
                conversionMap.put(matcher.group("value"), new ArrayList<String>(Collections.singleton(matcher.group("key"))));
            }
        }
        return conversionMap;
    }

    public static String convertFromTreeMapToJson(TreeMap<String, ArrayList<String>> treeMapConverted) {
        //Setting entries and final String
        Set<Map.Entry<String, ArrayList<String>>> entries = treeMapConverted.entrySet();
        ArrayList<String> values = new ArrayList<String>();
        String jsonString = "{\n";
        System.out.println(entries);

        //Declaring variables using in the comparison of the elements
        String[] lastIterationKeys = null;
        int equalityOrder = 0;
        int sizeLastIteration = 0;
        int sizeThisIteration = 0;

        for (Map.Entry<String, ArrayList<String>>entry : entries) {
            //Validate and add the key
            String[] keys = entry.getKey().split("\\.");
            sizeThisIteration = keys.length;

            //Setting the comparison variables
            equalityOrder = setEqualityOrder(keys, lastIterationKeys);

            //PRINTING FOR DEBUGGING PURPOSE
            System.out.println("equality Order: " + equalityOrder);
            System.out.println("sizeThisIter: " + sizeThisIteration);
            System.out.println("sizeLastIter: " + sizeLastIteration);

            //Logic for closing curly brackets
            for(int i = 0; i < (sizeLastIteration - equalityOrder); i++) {
                jsonString = jsonString.concat("},");
            }

            //Logic for opening curly brackets
            for (int i = 0; i < (sizeThisIteration - equalityOrder); i++) {
                jsonString = jsonString.concat("\"" + keys[i + equalityOrder] + "\": {");
            }
            jsonString = jsonString.concat("\n");
            System.out.println(jsonString + "\n\n");

            //Logic for adding the values in the Json
            for(String value: entry.getValue()) {
                jsonString = jsonString.concat("\"" + value + "\",");
            }

            //Prepare variables for next loop
            lastIterationKeys = keys;
            sizeLastIteration = keys.length;
        };
        //Logic for closing curly brackets
        while(sizeLastIteration > 0) {
            jsonString = jsonString.concat("}");
            sizeLastIteration --;
        }

        return jsonString;
    }

    private static int setEqualityOrder(String[] keys, String[] lastIterationKeys) {
        if(lastIterationKeys == null) {
            return 0;
        }

        int equalityOrder = 0;
        for (equalityOrder = 0; equalityOrder < Integer.min(keys.length, lastIterationKeys.length); equalityOrder ++) {
            if (!keys[equalityOrder].equals(lastIterationKeys[equalityOrder])) {
                break;
            }
        }
        return equalityOrder;
    }
}
