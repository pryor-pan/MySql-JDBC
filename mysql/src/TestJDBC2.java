import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Remarks:             JDBC查找数据
 * Author:panlai
 * :Date:2021/6/3
 */
public class TestJDBC2 {
    public static void main(String[] args) throws SQLException {
        //1.先和数据库建立连接
        //a）.先创建一个数据源DataSource
        DataSource dataSource = new MysqlDataSource();
        //b).给数据源设置一些属性（为了告诉代码，数据库服务在哪呢）
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/rocket2021?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("14789");
        // c).通过getconnection来创建连接
        Connection connection = dataSource.getConnection();

        //2、拼装sql代码
        String sql = "select *from exam_result";
        PreparedStatement statement = connection.prepareStatement(sql);

        //3、发货
        ResultSet resultSet = statement.executeQuery();

        //4、通过resultset获取到结果内容，遍历结果集合
        //每次调用next就类似于i++
        //当整个结果集遍历完毕之后，next就返回false
        while (resultSet.next()){
            //可以获取到当前记录（当前的每一行）
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double chinese = resultSet.getDouble("chinese");
            double math = resultSet.getDouble("math");
            double english = resultSet.getDouble("english");
            System.out.println(id+"," +name + ","+chinese +"," +math +","+english);
        }
        //必须保证列名相匹配，入果不匹配就无法获取到结果了。
        //5.释放资源
        statement.close();
        connection.close();


    }


}
