import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Remarks:     jdbc插入修改删除
 * Author:panlai
 * :Date:2021/6/1
 */
public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        //1.先和数据库建立连接
        //a）.先创建一个数据源DataSource
        DataSource dataSource = new MysqlDataSource();
        //b).给数据源设置一些属性（为了告诉代码，数据库服务在哪呢）
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/rocket2021?characterEncoding=utf8");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("14789");
        // c).通过getconnection来创建连接
        Connection connection = dataSource.getConnection();

        //2.拼装sql语句(淘宝卖家进行装箱打包)
        String sql = "insert into exam_result values(9,'panlai',60,67,87)";
        //实际上需要的是一个“语句对象”
        //可以帮助我们很方便的动态构造出一个sql来~
        PreparedStatement statement = connection.prepareStatement(sql);

        //3、执行sql语句(真正的发货)
        //当前执行的是insert 语句，也是用executeUpdate来完成
        //delete和update也是使用executeUpdate来完成
        //如果当前执行的是select语句，使用executeQuery来完成。
        //因为增删改来说需要executeUpdate 的返回值就表示影响到几行数据。
        int ret = statement.executeUpdate();
        System.out.println(ret);

        //4、收尾工作！！释放前面申请到的资源。
        //虽然不释放问题也不大，但是得等待垃圾回收机制去清理这些资源，手动关闭会更及时。
        statement.close();
        connection.close();

    }
}
