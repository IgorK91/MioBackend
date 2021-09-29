package game.cash.miobackend;

import java.util.HashMap;

public class ContactMap extends HashMap<String, String> {

    static final String FIRST = "первый";
    static final String SECOND = "второй";


    // Конструктор с параметрами

      public ContactMap(String first, String second) {
            super();
            super.put(FIRST, first);
            super.put(SECOND, second);
        }

    }
