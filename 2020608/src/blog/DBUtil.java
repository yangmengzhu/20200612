package blog;

/*
 * @program: 2020608
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -08 18 :58
 */

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource = null;
    static {
        initDataSource();
    }
    private static void initDataSource() {
        MysqlDataSource mysqlDataSource=new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("sg7188916");
        mysqlDataSource.setDatabaseName("huojianban_boke");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        dataSource=mysqlDataSource;
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
