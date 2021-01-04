package utils;

/**
 * @author shuai.yang
 */
public interface Result<T> {
    T data() throws Exception;

    T nullIfFail();

    int status();

    String desc();

    boolean suc();
}
