package com.hjb.ck;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CkUtil implements InitializingBean {

    @Value("${clickhouse.address}")
    private  String clickhouseAddress;

    @Value("${clickhouse.username}")
    private  String clickhouseUsername;

    @Value("${clickhouse.password}")
    private  String clickhousePassword;

    @Value("${clickhouse.db}")
    private  String clickhouseDB;

    @Value("${clickhouse.socketTimeout}")
    private  Integer clickhouseSocketTimeout;

    private ClickHouseConnection connection = null;

    public List<Map<String, String>> execSql(String sql) throws SQLException {
        ResultSet clickHouseResultSet = null;
        try {
            clickHouseResultSet =  connection.createStatement().executeQuery(sql);
            ResultSetMetaData resultSetMetaData = clickHouseResultSet.getMetaData();
            List<Map<String, String>> result = new ArrayList<>();
            while (clickHouseResultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    map.put(resultSetMetaData.getColumnName(i), clickHouseResultSet.getString(resultSetMetaData.getColumnName(i)));
                }
                result.add(map);
            }
            clickHouseResultSet.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败: " + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(connection == null){
            ClickHouseProperties properties = new ClickHouseProperties();
            properties.setUser(clickhouseUsername);
            properties.setPassword(clickhousePassword);
            properties.setDatabase(clickhouseDB);
            properties.setSocketTimeout(clickhouseSocketTimeout);
            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(clickhouseAddress,properties);
            try {
                connection = clickHouseDataSource.getConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
