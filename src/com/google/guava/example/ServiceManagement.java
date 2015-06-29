package com.google.guava.example;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.AbstractScheduledService.Scheduler;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;

public class ServiceManagement {

    /* The normal lifecycle of a Service is
     *  Service.State.NEW to
     *  Service.State.STARTING to
     *  Service.State.RUNNING to
     *  Service.State.STOPPING to
     *  Service.State.TERMINATED
     *  A stopped service may not be restarted. If the service fails where starting, running, or stopping, it goes into state Service.State.FAILED.
     */

    public void usingServiceManagerWithOneService() {
        MyExecutionThreadService service = new MyExecutionThreadService();
        service.startAsync().awaitRunning();
        // do stuff

        service.stopAsync();
        service.awaitTerminated();
    }


    public void usingServiceManagerWithMultipleService() {
        List<MyExecutionThreadService> services = Lists.newArrayList();
        services.add(new MyExecutionThreadService());
        services.add(new MyExecutionThreadService());

        ServiceManager serviceManager = new ServiceManager(services);
        serviceManager.addListener( new ServiceManager.Listener() {
            @Override
            public void failure(Service service) {}
            @Override
            public void healthy() {}
            @Override
            public void stopped() {}
        });
        serviceManager.startAsync().awaitHealthy();

        serviceManager.isHealthy();  // check if all services are running
        serviceManager.servicesByState();  // return a consistent snapshot of all the services indexed by their state
        serviceManager.startupTimes(); // returns a map from Service under management to how long it took for that service to start in milliseconds.

        // do stuff

        serviceManager.stopAsync();
        serviceManager.awaitStopped();
    }

    // The AbstractIdleService skeleton implements a Service which does not need to perform any action
    // while in the "running" state -- and therefore does not need a thread while running -- but has startup and shutdown actions to perform
    class MyIdleService extends AbstractIdleService {
        @Override
        protected void startUp() {
            // do something during startup
        }
        @Override
        protected void shutDown() {
            // do something during shutdown
        }
    }

    // An AbstractExecutionThreadService performs startup, running, and shutdown actions in a single thread.
    // You must override the run() method, and it must respond to stop requests. For example, you might perform actions in a work loop:
    class MyExecutionThreadService extends AbstractExecutionThreadService {
        @Override
        protected void run() throws Exception {
            while (isRunning()) {
                // perform a unit of work
            }
        }
    }

    // An AbstractScheduledService performs some periodic task while running.
    // Subclasses implement runOneIteration() to specify one iteration of the task, as well as the familiar startUp and shutDown() methods.
    volatile Scheduler scheduler = Scheduler.newFixedDelaySchedule(0, 10, TimeUnit.MILLISECONDS);
    class MyScheduledService extends AbstractScheduledService {
        @Override
        protected void runOneIteration() throws Exception {
            // perform a unit of work
        }

        @Override
        protected Scheduler scheduler() {
            // Typically, you will use one of the provided schedules from AbstractScheduledService.Scheduler:
            // either newFixedRateSchedule(initialDelay, delay, TimeUnit) or newFixedDelaySchedule(initialDelay, delay, TimeUnit),
            // corresponding to the familiar methods in ScheduledExecutorService.
            // Custom schedules can be implemented using CustomScheduler; see the Javadoc for details.
            return scheduler;
        }
    }

    // When you need to do your own manual thread management, override AbstractService directly.
    // Typically, you should be well served by one of the above implementations, but implementing AbstractService is recommended when,
    // for example, you are modeling something that provides its own threading semantics as a Service,
    // you have your own specific threading requirements.
    // NOTE: Your doStart and doStop, methods should be fast. If you need to do expensive initialization, such as reading files,
    // opening network connections, or any operation that might block, you should consider moving that work to another thread.
    class MyService extends AbstractService {

        @Override
        protected void doStart() {
            // called directly by the first call to startAsync(), your doStart() method should perform all initialization
            // and then eventually call notifyStarted() if start up succeeded or notifyFailed() if start up failed.
        }

        @Override
        protected void doStop() {
            // called directly by the first call to stopAsync(), your doStop() method should shut down your service and then
            // eventually call notifyStopped() if shutdown succeeded or notifyFailed() if shutdown failed.
        }
    }
}
