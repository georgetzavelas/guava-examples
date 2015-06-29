package com.google.guava.example;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutures {

    /*
     * A traditional Future represents the result of an asynchronous computation: a computation
     * that may or may not have finished producing a result yet. A Future can be a handle to an
     * in-progress computation, a promise from a service to supply us with a result.
     *
     * A ListenableFuture allows you to register callbacks to be executed once the computation
     * is complete, or if the computation is already complete, immediately. This simple addition
     * makes it possible to efficiently support many operations that the basic Future interface
     * cannot support.
     *
     * The basic operation added by ListenableFuture is addListener(Runnable, Executor),
     * which specifies that when the computation represented by this Future is done, the specified
     * Runnable will be run on the specified Executor.
     */

    @SuppressWarnings("unused")
    public static void usingListenableFuture() {
        // create a threadpool to execute the tasks
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        // submit the task to the threadpool to execute
        ListenableFuture<Explosion> explosion = service.submit(new Callable<Explosion>() {
            @Override
            public Explosion call() {
                return pushBigRedButton();
            }
        });
        Futures.addCallback(explosion, new FutureCallback<Explosion>() {
            // we want this handler to run immediately after we push the big red button!
            @Override
            public void onSuccess(Explosion explosion) {
                walkAwayFrom(explosion);
            }
            @Override
            public void onFailure(Throwable thrown) {
                battleArchNemesis(); // escaped the explosion!
            }
        });


        // we can chain asynch operations
        // define a function that will transform the object
        AsyncFunction<Explosion, Explosion> queryFunction = new AsyncFunction<Explosion, Explosion>() {
            @Override
            public ListenableFuture<Explosion> apply(Explosion initialExplosion) {
              return service.submit(new Callable<Explosion>() {
                  @Override
                  public Explosion call() {
                      return mapOutExplosion();
                  }
              });
            }
        };

        ListenableFuture<Explosion> queryFuture = Futures.transform(explosion, queryFunction, service);
    }


    public static Explosion pushBigRedButton() {
        // do stuff that takes a long or indeterminant amount of time
        return null;
    }
    public static Explosion mapOutExplosion() {
        // do stuff that takes a long or indeterminant amount of time
        return null;
    }

    public static void walkAwayFrom(Explosion explosion) {}
    public static void battleArchNemesis() {}

    class Explosion {

    }
}
