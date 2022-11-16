package converters;

import java.util.ArrayList;

public interface IConverter {
    final String SWIFT_REGEX = """
            - [\\d]+ : \"(?<value>[\\w.]+): Optional\\(\"(?<key>[\\w]+)\"\\)\"""";
    ArrayList<String> convertValueList = new ArrayList<String>();
    ArrayList<String> convertKeyList = new ArrayList<String>();


}
