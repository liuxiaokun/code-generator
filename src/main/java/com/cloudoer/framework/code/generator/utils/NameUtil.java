package com.cloudoer.framework.code.generator.utils;

import com.cloudoer.framework.code.generator.db.Table;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
public class NameUtil {

    private static List<String> unCountables = new ArrayList<>();
    private static Map<Integer, Rule> plurals = new TreeMap<>();
    private static Map<String, String> irregulars = new HashMap<>();
    private static Map<Integer, Rule> singulars = new TreeMap<>();


    static {
        plurals.put(0, new Rule("(ax|test)is$", "$1es"));
        plurals.put(1, new Rule("(octop|vir)us$", "$1i"));
        plurals.put(2, new Rule("(alias|status)$", "$1es"));
        plurals.put(3, new Rule("(bu)s$", "$1es"));
        plurals.put(4, new Rule("(buffal|tomat)o$", "$1oes"));
        plurals.put(5, new Rule("([ti])um$", "$1a"));
        plurals.put(6, new Rule("sis$", "ses"));
        plurals.put(7, new Rule("(?:([^f])fe|([lr])f)$", "$1$2ves"));
        plurals.put(8, new Rule("(hive)$", "$1s"));
        plurals.put(9, new Rule("([^aeiouy]|qu)y$", "$1ies"));
        plurals.put(10, new Rule("([^aeiouy]|qu)ies$", "$1y"));
        plurals.put(11, new Rule("(x|ch|ss|sh)$", "$1es"));
        plurals.put(12, new Rule("(matr|vert|ind)ix|ex$", "$1ices"));
        plurals.put(13, new Rule("([m|l])ouse$", "$1ice"));
        plurals.put(14, new Rule("(ox)$", "$1es"));
        plurals.put(15, new Rule("(quiz)$", "$1zes"));
        plurals.put(16, new Rule("s$", "s"));
        plurals.put(17, new Rule("$", "s"));
        singulars.put(0, new Rule("s$", ""));
        singulars.put(1, new Rule("(n)ews$", "$1ews"));
        singulars.put(2, new Rule("([ti])a$", "$1um"));
        singulars.put(3, new Rule("((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$", "$1$2sis"));
        singulars.put(4, new Rule("(^analy)ses$", "$1sis"));
        singulars.put(5, new Rule("([^f])ves$", "$1fe"));
        singulars.put(6, new Rule("(hive)s$", "$1"));
        singulars.put(7, new Rule("(tive)s$", "$1"));
        singulars.put(8, new Rule("([lr])ves$", "$1f"));
        singulars.put(9, new Rule("([^aeiouy]|qu|or)ies$", "$1y"));
        singulars.put(10, new Rule("(s)eries$", "$1eries"));
        singulars.put(11, new Rule("(m)ovies$", "$1ovie"));
        singulars.put(12, new Rule("(x|ch|ss|sh)es$", "$1"));
        singulars.put(13, new Rule("([m|l])ice$", "$1ouse"));
        singulars.put(14, new Rule("(bus)es$", "$1"));
        singulars.put(15, new Rule("(o)es$", "$1"));
        singulars.put(16, new Rule("(shoe)s$", "$1"));
        singulars.put(17, new Rule("(cris|ax|test)es$", "$1is"));
        singulars.put(18, new Rule("([octop|vir])i$", "$1us"));
        singulars.put(19, new Rule("(alias|status)es$", "$1"));
        singulars.put(20, new Rule("^(ox)es", "$1"));
        singulars.put(21, new Rule("(vert|ind)ices$", "$1ex"));
        singulars.put(22, new Rule("(matr)ices$", "$1ix"));
        singulars.put(23, new Rule("(quiz)zes$", "$1"));
        irregulars.put("person", "people");
        irregulars.put("man", "men");
        irregulars.put("child", "children");
        irregulars.put("sex", "sexes");
        irregulars.put("move", "moves");
        unCountables.add("equipment");
        unCountables.add("information");
        unCountables.add("rice");
        unCountables.add("money");
        unCountables.add("species");
        unCountables.add("series");
        unCountables.add("fish");
        unCountables.add("sheep");
    }

    /**
     * 实现通过表名生成Domain类名的方法
     *
     * @param capitalize 首字母是否大写
     * @param singular   是否转为单数
     * @return
     */
    public static String genEntityClassName(boolean capitalize, boolean singular, String tableName) {

        if (tableName.startsWith("t_")) {
            tableName = tableName.substring(2);
        }
        String[] nameArray = tableName.split("_");
        StringBuilder stringBuilder = new StringBuilder();

        if (nameArray.length > 1) {
            stringBuilder.append(nameArray[0]);

            for (int i = 1; i < nameArray.length; i++) {
                char[] chars = nameArray[i].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                chars.toString();
                stringBuilder.append(capitalize(nameArray[i]));
            }
        } else {
            stringBuilder.append(tableName);
        }
        tableName = stringBuilder.toString();
        if (capitalize) {
            tableName = capitalize(tableName);
        }
        if (singular) {
            tableName = singularize(tableName);
        }
        return tableName;
    }

    public static String getTableComment(Table table) {
        String fieldname = genEntityClassName(true, true, table.getName());
        String label = capitalize(fieldname);
        String comment = table.getComment();
        if (StringUtils.hasLength(comment)) {
            int index = comment.indexOf(",");
            if (index > 0) {
                comment = comment.substring(0, index);
            }
            label = comment;
        }
        return label;
    }

    public static String capitalize(String s) {
        char[] chars = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static String singularize(String word) {

        if (unCountables.contains(word.toLowerCase())) {
            return word;
        } else {
            for (int i = singulars.size() - 1; i >= 0; --i) {
                Rule r = singulars.get(i);
                String key = r.key;
                String value = r.value;
                Matcher matcher = Pattern.compile(key, 2).matcher(word);

                if (matcher.find()) {
                    return matcher.replaceAll(value);
                }
            }
            return word;
        }
    }

    static class Rule {
        String key;
        String value;

        Rule(String k, String v) {
            this.key = k;
            this.value = v;
        }
    }
}
