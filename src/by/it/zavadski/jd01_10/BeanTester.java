package by.it.zavadski.jd01_10;

import java.lang.reflect.Method;

public class BeanTester {
    public static void main(String[] args) throws Exception{
    Class<?> beanClass=Bean.class;
    Class<Param> paramClass=Param.class;
    Method[] methods=beanClass.getDeclaredMethods();
        for (Method method : methods)
            if (method.isAnnotationPresent(paramClass)) {
                Param annotation = method.getAnnotation(paramClass);
                int aParam = annotation.a();
                int bParam = annotation.b();
                //Object newBeanInstance = beanClass.getConstructor().newInstance();
                Object invokeObject = method.invoke(beanClass.getConstructor().newInstance(), aParam, bParam);
                System.out.printf("%s %s\n", method.getName(), invokeObject.toString());
            }
    }
   public BeanTester() {}
}