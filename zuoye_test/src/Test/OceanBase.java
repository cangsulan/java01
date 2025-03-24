import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OceanBase {
    // 创建数据库连接
    public static Connection GetConnection(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sourceURL = "jdbc:mysql://127.0.0.1:2881/tpc";
            Connection conn = DriverManager.getConnection(sourceURL, username, password);
            System.out.println(conn.getAutoCommit());

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 新建表 customer_t1
    public static void CreateTable(Connection conn, Statement sm) {
        try {
            // 新建表
            sm.executeUpdate(
                    "create table customer_t1 (c_customer_sk integer, c_customer_name varchar(32))");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 插入数据
    public static void InsertData(Connection conn, Statement sm) {
        try {
            sm.executeUpdate("insert into customer_t1 values (1,1)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 更新数据
    public static void UpdateData(Connection conn, Statement sm) {
        try {
            sm.executeUpdate(
                    "update customer_t1 set c_customer_name = 'new data' where c_customer_sk = 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询数据, 并输出结果
    public static void PrintData(Connection conn, Statement sm) {
        try {
            ResultSet rs = sm.executeQuery("select * from customer_t1");
            while (rs.next()) {
                String name = rs.getString("c_customer_name");
                String id = rs.getString("c_customer_sk");
                System.out.println("name: " + name + " id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Statement newStatement(Connection conn) {
        try {
            Statement sm = conn.createStatement();

            return sm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        try {
            Connection connection = GetConnection("root", "123456");

            Statement sm = newStatement(connection);

            // 创建表
            CreateTable(connection, sm);

            // 插入数据
            InsertData(connection, sm);

            // 更新数据
            UpdateData(connection, sm);

            // 打印数据
            PrintData(connection, sm);

            // 关闭连接
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}