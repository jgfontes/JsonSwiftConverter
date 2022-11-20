import converters.swiftToJson;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        //Declaring varaibles
//        String swiftStr = """
//        - 1 : "name: Optional(\"Julio\")"
//        - 2 : "age: Optional(\"25\")"
//        - 2 : "age: Optional(\"26\")"
//        - 2 : "age: Optional(\"27\")"
//        - 3 : "person.location.state: Optional(\"NY\")"
//        - 4 : "person.location.country: Optional(\"US\")"
//        - 5 : "comment: Optional(\"Nice guy\")"
//                """;
//
        String swiftStr = """
        - 1 : "name.aa.cc: Optional(\"Julio1\")"
        - 1 : "name.aa.cc: Optional(\"Julio2\")"
        - 1 : "name.bb.aa: Optional(\"Julio3\")"
        - 1 : "name.b.bb: Optional(\"Julio4\")"
        - 1 : "name.b.x: Optional(\"Julio5\")"
        - 1 : "name.b.y: Optional(\"Julio6\")"
        - 1 : "name.a.b.c.d.e.f.g: Optional(\"Julio7\")"
        - 2 : "age.uuuu: Optional(\"21\")"
        - 2 : "age.aa: Optional(\"20\")"
        - 2 : "age.bb: Optional(\"25\")"
        - 2 : "age.bb.cc: Optional(\"41\")"
                """;

        TreeMap swiftHashMap = swiftToJson.convertFromSwiftToTreeMap(swiftStr);
        System.out.println(swiftToJson.convertFromTreeMapToJson(swiftHashMap));

    }
}
