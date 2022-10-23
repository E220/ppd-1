package locks;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockManager {
    private final Map<Integer, ReadWriteLock> quantityLocks;
    private final ReadWriteLock billListLock;

    public LockManager(Collection<Integer> ids) {
        this.quantityLocks = new HashMap<>(ids.size());
        ids.forEach(id -> quantityLocks.put(id, new ReentrantReadWriteLock()));
        this.billListLock = new ReentrantReadWriteLock();
    }

    public void lockForRead(Collection<Integer> ids) {
//        System.out.println("readLock" + Arrays.toString(ids.toArray()));
        ids.stream().sorted().forEach(id -> quantityLocks.get(id).readLock().lock());
    }

    public void lockForWrite(Collection<Integer> ids) {
//        System.out.println("writeLock" + Arrays.toString(ids.toArray()));
        ids.stream().sorted().forEach(id -> quantityLocks.get(id).writeLock().lock());
    }

    public void unlockAfterRead(Collection<Integer> ids) {
//        System.out.println("un-readLock" + Arrays.toString(ids.toArray()));
        ids.stream().sorted().forEach(id -> quantityLocks.get(id).readLock().unlock());
    }

    public void unlockAfterWrite(Collection<Integer> ids) {
//        System.out.println("un-writeLock" + Arrays.toString(ids.toArray()));
        ids.stream().sorted().forEach(id -> quantityLocks.get(id).writeLock().unlock());
    }

    public void lockBillsForRead() {
        billListLock.readLock().lock();
    }

    public void lockBillsForWrite() {
        billListLock.writeLock().lock();
    }

    public void unlockBillsAfterRead() {
        billListLock.readLock().unlock();
    }

    public void unlockBillsAfterWrite() {
        billListLock.writeLock().unlock();
    }
}
