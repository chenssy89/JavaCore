package main.java.com.JVM.perm;

/**
 * 常量池GC策略：只要常量池中的常量没有被任何地方引用，就可以被回收
 * @author chenssy
 * @date 2016/11/19
 * @since v1.0.0
 */
public class PermGengc {

    public static void main(String[] args){
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            String t = String.valueOf(i).intern();
        }
    }
}
