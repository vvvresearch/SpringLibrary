package ru.vvvresearch.otuslibrary.shell;

import java.util.List;

public class CommandHelper {

    public static String getStringFromList(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null && list.size() > 0) {
            list.forEach(stringBuilder::append);
            return stringBuilder.toString();
        } else {
            return "Not found";
        }
    }
}
