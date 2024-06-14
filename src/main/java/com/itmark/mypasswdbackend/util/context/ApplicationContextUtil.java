package com.itmark.mypasswdbackend.util.context;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware{

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * 注册java源码代表的类到spring容器中
     * @param clazz
     */
    public void register( Class<?> clazz) throws ClassNotFoundException {
        register( null, clazz);
    }

    /**
     * 注册java源码代表的类到spring容器中
     * @param beanName
     * @param clazz
     */
    public void register( String beanName, Class<?> clazz) throws ClassNotFoundException {
        /**
         * 如果beanName传null，则赋值类的全限定名
         */
        if(beanName == null) {
            beanName = StrUtil.lowerFirst(clazz.getName());
        }

        /**
         * 将applicationContext转换为ConfigurableApplicationContext
         */
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        /**
         * 获取bean工厂并转换为DefaultListableBeanFactory
         */
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        /**
         * 万一已经有了这个BeanDefinition了，先remove掉,不然一次容器启动没法多次调用,这里千万别用成
         * defaultListableBeanFactory.destroySingleton()了，BeanDefinition的注册只是放在了beanDefinitionMap中，还没有
         * 放入到singletonObjects这个map中，所以不能用destroySingleton()，这个是没效果的
         */
        if (defaultListableBeanFactory.containsBeanDefinition(beanName)) {
            defaultListableBeanFactory.removeBeanDefinition(beanName);
        }
        /**
         * 使用spring的BeanDefinitionBuilder将Class对象转成BeanDefinition
         */
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        /**
         * 以指定beanName注册上面生成的BeanDefinition
         */
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());

    }

    /**
     * 使用spring上下文拿到指定beanName的对象
     */
    public <T> T getBean(String beanName) {
        return (T) ((ConfigurableApplicationContext) applicationContext).getBeanFactory().getBean(beanName);
    }

    /**
     * 使用spring上下文拿到指定类型的对象
     */
    public <T> T getBean( Class<T> clazz) {
        return (T) ((ConfigurableApplicationContext) applicationContext).getBeanFactory().getBean(clazz);
    }


}
