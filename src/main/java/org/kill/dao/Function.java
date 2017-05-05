package org.kill.dao;

/**
 * Created by Ellen on 2017/5/3.
 */
public interface Function<T,E> {
    T callBack(E e);
}
