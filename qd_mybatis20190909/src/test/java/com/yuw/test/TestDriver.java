package com.yuw.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class TestDriver {
    /**
     * 使用mybatis读取数据库的内容
     * 执行流程：
     * 创建SqlSessionFactory对象，然后创建 sqlSession对象，
     * SQLSession对象使用动态代理模式，执行底层的JDBC的PreparedStatment对象执行sql语句；
     * SQL语句从指定id的或者绑定接口方法的sql语句块中转换过来的；
     * 将查询结果转换为指定类型；
     */
    @Test
    public void test01() {
        //String resource = "org/mybatis/example/mybatis-config.xml";
        // 使用输入流对象加载mybatis的xml配置文件
        InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
        // 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session2 = sqlSessionFactory.openSession();
        // 2、创建一个sqlsession对象，用户执行mapper映射文件中的查询语句块
        // try-catch 异常处理的with形式,要求写在()中的对象，必须实现 AutoCloseable 接口
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ///3、使用session对象进行数据库查询操作
            /*
            selectByPrimaryKey： 是mybatis框架的mapper映射文件的sql语句块的id
            也就是，session通过id为“selectByPrimaryKey”语句块去进行数据库操作
             */
            Object obj = session.selectOne("selectByPrimaryKey", 1); // 通过主键字段值进行查询操作
            System.out.println("查询结果：" + obj);
        }
    }
}
