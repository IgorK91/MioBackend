package game.cash.miobackend;

import java.util.HashMap;

public class ContactMap extends HashMap<String, String> {

    public static final String FIRST = "первый";
    public static final String SECOND = "второй";


    // Конструктор с параметрами

      public ContactMap(String first, String second) {
            super();
            super.put(FIRST, first);
            super.put(SECOND, second);
        }

    }
