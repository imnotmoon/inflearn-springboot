package hello.hellospring.repository;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.util.Repository;

public class NewRepository implements Repository {
    @Override
    public void storeClass(JavaClass clazz) {

    }

    @Override
    public void removeClass(JavaClass clazz) {

    }

    @Override
    public JavaClass findClass(String className) {
        return null;
    }

    @Override
    public JavaClass loadClass(String className) throws ClassNotFoundException {
        return null;
    }

    @Override
    public JavaClass loadClass(Class clazz) throws ClassNotFoundException {
        return null;
    }

    @Override
    public void clear() {

    }
}
