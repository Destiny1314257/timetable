package com.tang.common.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBUtils {
    public static Connection getConnection() throws Exception {
        //读取配置文件
        InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties=new Properties();
        //加载
        properties.load(in);

        DataSource dataSource=DruidDataSourceFactory.createDataSource(properties);
        return dataSource.getConnection();
    }
    //查询所有数据
    public static <T> List<T> getList(Class<T> clazz, String sql, Object...args) throws SQLException {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> userList=null;
        try{
            conn = DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            rs = ps.executeQuery();

            //获得结果集元数据
            ResultSetMetaData rsmd=rs.getMetaData();
            //获得当前结果集的列数
            int colnum = rsmd.getColumnCount();

            userList=new ArrayList<>();

            while (rs.next()){

                //key存放列名，value存放列值，for循环完成之后，rowMap存放了一条记录
                Map<String,Object> rowMap= new HashMap<>();
                for (int i=1;i<=colnum;i++){
                    String columnName=rsmd.getColumnLabel(i);
                    Object columnValue=rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql.Date转成java.util.Date
                    if(columnValue instanceof Date){
                        Date date=(Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName,columnValue);
                }
                T bean=clazz.newInstance();
                for (Map.Entry<String,Object> entry:rowMap.entrySet()){
                    //列名
                    String propertyName=entry.getKey();
                    //值
                    Object propertyValue=entry.getValue();

                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }
                userList.add(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return userList;
    }
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //查询记录个数
    public static Integer getCount(String sql,Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Integer count=null;
        try{
            conn = DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            rs = ps.executeQuery();

            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps, rs);
        }
        return count;
    }

    public static boolean update(String sql, Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=0;
        try{
            conn = DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++){
                    //判断当前类型，是不是java.util.Date，转换成java.sql.Date
                    if(args[i] instanceof java.util.Date){
                        java.util.Date date=(java.util.Date)args[i];
                        //转换成java.sql.Date
                        args[i]=new Date(date.getTime());
                    }
                    ps.setObject(i+1,args[i]);
                }
            }
            count = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps,null);
        }
        return count>0?true:false;
    }

    //保存对象方法
    public static boolean save(String sql,Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=null;

        try{
            conn = DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            //返回更新的记录数
            count = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps,null);
        }
        return count!=null&&count>0?true:false;
    }
    //类型参数，类型变量，类型是可以变化 Teacher Student
    public static <T> T getSingleObj(Class<T> clazz, String sql, Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T bean=null;
        try{
            conn = DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            rs = ps.executeQuery();

            //获得结果集元数据
            ResultSetMetaData rsmd=rs.getMetaData();
            //获得当前结果集的列数
            int colnum = rsmd.getColumnCount();

            while (rs.next()){

                //key存放列名，value存放列值，for循环完成之后，rowMap存放了一条记录
                Map<String,Object> rowMap= new HashMap<>();
                for (int i=1;i<=colnum;i++){
                    String columnName=rsmd.getColumnLabel(i);
                    Object columnValue=rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql.Date转成java.util.Date
                    if(columnValue instanceof Date){
                        Date date=(Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName,columnValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                bean=clazz.newInstance();

                for (Map.Entry<String,Object> entry:rowMap.entrySet()){
                    String propertyName=entry.getKey();
                    Object propertyValue=entry.getValue();

                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps, rs);
        }
        return bean;
    }
}
