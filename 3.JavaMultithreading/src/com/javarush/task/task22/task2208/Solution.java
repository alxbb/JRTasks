package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}

Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Russia");
        map.put("city", "Moscow");
        map.put("age", "1");

        System.out.println(getQuery( map ));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        boolean start = true;
        for(Map.Entry<String, String> pair : params.entrySet() ){
            if(pair.getValue() == null) continue;
            if (!start) sb.append(" and ");
            else start = false;

            sb.append(String.format("%s = '%s'", pair.getKey(), pair.getValue()));
        }

        return sb.toString();
    }
}
