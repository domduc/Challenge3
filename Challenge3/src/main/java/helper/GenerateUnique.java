package helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateUnique {
    public static String getUniqueDate() {
        SimpleDateFormat _date = new SimpleDateFormat("yyMMddhhmmssMs");
        String aa = _date.format(new Date());
        return aa;
    }
}
