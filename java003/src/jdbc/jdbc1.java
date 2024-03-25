package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //JDBC快速入门
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        String url="jdbc:mysql://127.0.0.1:3306/mybase001";
        String username="root";
        String password="123456";
        DriverManager.getConnection(url,username,password);
        //3.定义sql
        String sql="";
        //4.获取执行sql的对象 Statement
        //Statement statement = conn.createStatement;
        //5.执行sql
        //int count = statement.executeUpdate(sql);//受影响的行数
        //6.处理结果
        //System.out.println(count);
        //7.释放资源
        //statement.close();
        //conn.close();
    }
}
