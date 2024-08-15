package com.itmark.mypasswdbackend.entity.designpattern.factory.configFactory;
import java.io.InputStream;
import java.util.*;

/**
 * @description: 静态工厂+配置文件
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class ConfigCoffeeStaticFactory {
    private static String configPath = "configFactory.properties";

    // 1 加载配置文件获取配置文件中配置的全类名并创建类对象
    private static Map<String,ConfigCoffee> nameAndObjectMap = new HashMap<>();
    // 2 加载配置-只需要加载一次
    static {
        // 2.1 Properties 对象
        Properties properties = new Properties();
        // 2.2 load 因为配置文件在resources目录下所以要获取类路径：先获取字节码对象-获取类加载器-读取配置文件返回输入流
        InputStream resourceAsStream = ConfigCoffeeStaticFactory.class.getClassLoader().getResourceAsStream(configPath);
        try {
            properties.load(resourceAsStream);
            Set<Object> keySet = properties.keySet();
            for (Object key : keySet) {
                String classFullName = properties.getProperty((String) key);
                // 通过反射创建对象
                // 2.1 获取字节码对象
                Class<?> aClass = Class.forName(classFullName);
                // 2.2 获取类对象
                ConfigCoffee o = (ConfigCoffee)aClass.getDeclaredConstructor().newInstance();
                nameAndObjectMap.put((String)key,o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ConfigCoffee createCoffee(String beanName){
        // 通过名称获取对象
        return nameAndObjectMap.get(beanName);
    }

}
