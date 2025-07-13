package com.briiiqtt.stockking.util;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericTask<T> implements Callable<T> { // 작업 큐 만들어볼까해서.. 일단 안씀
    private final Supplier<T> work;
    private final Consumer<T> onSuccess;

    public GenericTask(Supplier<T> work, Consumer<T> onSuccess) {
        this.work = work;
        this.onSuccess = onSuccess;
    }

    @Override
    public T call() {
        T result = work.get();
        onSuccess.accept(result);
        return result;
    }
}
