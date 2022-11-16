import converters.swiftToJson;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        //Declaring varaibles
        String swiftStr = """
        - 1 : "name: Optional(\"Julio\")"
        - 2 : "age: Optional(\"25\")"
        - 2 : "age: Optional(\"26\")"
        - 2 : "age: Optional(\"27\")"
        - 3 : "person.location.state: Optional(\"NY\")"
        - 4 : "person.location.country: Optional(\"US\")"
        - 5 : "comment: Optional(\"Nice guy\")"
                """;

        TreeMap swiftHashMap = swiftToJson.convertFromSwiftToTreeMap(swiftStr);
        System.out.println(swiftHashMap);

        System.out.println(swiftHashMap.containsKey("age"));
    }
}
