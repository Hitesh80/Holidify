package com.example.trisys.greetingapp;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by trisys on 22/5/18.
 */

public class SchedulersFacade {

  @Inject
  public SchedulersFacade() {
  }

  /**
   * IO thread pool scheduler
   */
  public Scheduler io() {
    return Schedulers.io();
  }

  /**
   * Computation thread pool scheduler
   */
  public Scheduler computation() {
    return Schedulers.computation();
  }

  /**
   * Main Thread scheduler
   */
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}
