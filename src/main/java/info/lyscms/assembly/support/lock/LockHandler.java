package info.lyscms.assembly.support.lock;

/**
 * 锁处理接口
 *
 * @author sunkl
 * @version 2020/3/18 17:31
 */
public interface LockHandler {

    /**
     * 加锁
     *
     * @return
     * @author sunkl
     * @version 2020/3/18 17:33
     */
    boolean lock(String idempotentId);

    /**
     * 解锁
     *
     * @return
     * @author sunkl
     * @version 2020/3/18 17:32
     */
    boolean unLock(String idempotentId);
}
