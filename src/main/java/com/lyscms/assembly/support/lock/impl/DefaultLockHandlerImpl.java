package com.lyscms.assembly.support.lock.impl;

import com.lyscms.assembly.support.lock.LockHandler;

import java.util.HashSet;
import java.util.Set;

public class DefaultLockHandlerImpl implements LockHandler {

    private Set<String> keySet = new HashSet<>();

    /**
     * 加锁
     *
     * @return
     * @author sunkl
     * @date 2020/3/18 17:33
     */
    @Override
    public boolean lock(String idempotentId) {
        if (keySet.contains(idempotentId)) {
            return false;
        } else {
            synchronized (this) {
                if (keySet.contains(idempotentId)) {
                    return false;
                } else {
                    keySet.add(idempotentId);
                    return true;
                }
            }
        }
    }

    /**
     * 解锁
     *
     * @return
     * @author sunkl
     * @date 2020/3/18 17:32
     */
    @Override
    public boolean unLock(String idempotentId) {
        if (keySet.contains(idempotentId)) {
            synchronized (this) {
                keySet.remove(idempotentId);
            }
        }
        return true;
    }
}
