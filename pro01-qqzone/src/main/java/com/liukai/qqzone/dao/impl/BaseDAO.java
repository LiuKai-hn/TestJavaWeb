package com.liukai.qqzone.dao.impl;

import com.liukai.myssm.exception.DAOException;
import com.liukai.util.ConnUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author：liukai
 * @Date：2023/8/15 13:55
 */
public abstract class BaseDAO<T> {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;

    //T的Class对象
    private Class entityClass;

    public BaseDAO() {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        /**
         * getGenericSuperclass() 如果是泛型类型，则可以获取实际继承的子类泛型类型
         *  com.liukai.test_extends.Fruit<com.liukai.test_extends.Peer>
         */
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        /**
         * 如果泛型类型是多个 :
         * public class Fruit<T,V> {
         *
         * }
         * 那么 actualTypeArguments 就是多个
         * class com.liukai.test_extends.Peer
         * class com.liukai.test_extends.Cat
         */
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型，因为只有一个泛型类型，所以只取一个
        Type actualType = actualTypeArguments[0];

        // 获取泛型类型
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO BaseDAO 出错了 ");
        }

    }

    protected Connection getConn() {
        return ConnUtil.getconn();
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {

    }

    // 拼接 sql中的参数
    public void setParams(PreparedStatement psmt, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {

                try {

                    psmt.setObject(i + 1, params[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DAOException("BaseDAO setParams 出错了 ");
                }

            }
        }

    }

    //执行更新，返回影响行数
    //执行更新，返回影响行数
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");

        conn = getConn();
        try {


            if (insertFlag) {
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();

            if (insertFlag) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDao executeUpdate 出错了");
        }


        //return 0;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDao load 出错了");
        }

        return null;
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();

        try {
            //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
            Field field = clazz.getDeclaredField(property);

            if (field != null) {

                field.setAccessible(true);
                String typeName = field.getType().getName();
                // 如果字段为POJO类型
                if (isMyType(typeName)){
                    Class<?> typeNameClazz = Class.forName(typeName);
                    // 获取构造方法
                    Constructor<?> constructor = typeNameClazz.getDeclaredConstructor(Integer.class);
                    propertyValue = constructor.newInstance(propertyValue);
                }else if (propertyValue.getClass().toString().equals("class java.time.LocalDateTime")){ // 处理sql中datatime 和java中Date类型不匹配问题
                    propertyValue = Timestamp.valueOf((LocalDateTime) propertyValue);
                }else {
                    field.set(obj, propertyValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDao setValue 出错了");
        }

    }
    // 判断字段类型是否为自定义的POJO
    private static boolean isMyType(String typeName){
        if("java.lang.Integer".equals(typeName) ||
                "java.lang.String".equals(typeName) ||
                "java.util.Date".equals(typeName) ||
                "java.sql.Date".equals(typeName)){
            return false;
        }else {
            return true;
        }
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDao executeComplexQuery 出错了");

        }
        return null;
    }

    //执行查询，返回List
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDao executeQuery 出错了");
        }
    }

}
