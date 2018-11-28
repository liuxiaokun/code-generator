package com.cloudoer.framework.code.generator.db;

import com.cloudoer.framework.code.generator.config.Jdbc;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
@Slf4j
public class MysqlHelper {

    private static Map<String, Table> tableMap = new HashMap<>();
    private static Map<String, Column> columnMap = new HashMap<>();

    public static List<Table> getTables(Jdbc jdbc) throws Exception {

        List<Table> tables = new ArrayList<>();
        List<String> tableNames = new ArrayList<>();
        Map<String, String> comments = new HashMap<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbc.getUrl(), jdbc.getUsername(), jdbc.getPassword());

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLE STATUS ");

        while (resultSet.next()) {
            String tableName = resultSet.getString("Name");
            tableNames.add(tableName);
            comments.put(tableName, resultSet.getString("Comment"));
        }
        resultSet.close();

        for (String tableName : tableNames) {
            Table table = new Table();
            table.setName(tableName);
            table.setComment(comments.get(tableName));

            //查询表下面的列信息
            resultSet = statement.executeQuery("SHOW FULL FIELDS FROM  `" + tableName + "`");
            List<Column> columns = new ArrayList<>();

            while (resultSet.next()) {
                Column column = new Column();
                column.setField(resultSet.getString("Field"));
                String type = resultSet.getString("Type");

                if (!type.contains("(")) {
                    column.setType(type);
                } else {
                    String shortType = type.substring(0, type.indexOf("("));
                    column.setType(shortType);

                    if ("char".equals(type) || "varchar".equals(type)) {
                        column.setLength(Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(")"))));
                    }
                }
                column.setNullAble("YES".equals(resultSet.getString("Null")));
                column.setKey(resultSet.getString("Key"));
                column.setDefaultValue(resultSet.getString("Default"));
                column.setComment(resultSet.getString("Comment"));
                column.setTable(table);
                columns.add(column);
                columnMap.put(tableName + "." + column.getField(), column);
            }
            table.setColumns(columns);
            tables.add(table);
            tableMap.put(tableName, table);
        }
        return tables;
    }

    public static void main(String[] args) throws Exception {
        getTables(new Jdbc());
    }
}
