package com.javaCore.java_thread.JUC.Atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceTest
 *
 * @author:chenssy
 * @date : 2017/3/28 9:25
 */
public class AtomicReferenceTest {
    private static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public int getOld() {
            return old;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "{name: " + getName() + ",old:"+getOld()+"}";
        }
    }

    private static AtomicReference<User> atomicReference = new AtomicReference<User>();

    public static void main(String[] args){
        User user = new User("chenssy",18);
        atomicReference.set(user);
        System.out.println(atomicReference.get().toString());
        User updateUser = new User("CHENSSY",22);
        atomicReference.compareAndSet(user,updateUser);
        System.out.println(atomicReference.get().toString());
    }

}
