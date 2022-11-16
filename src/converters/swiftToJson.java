package converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
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

//    public static String convertFromTreeMapToJson(TreeMap<String, String> treeMapConverted) {
//
//    }
}
