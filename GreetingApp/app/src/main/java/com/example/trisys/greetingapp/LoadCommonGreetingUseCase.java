package com.example.trisys.greetingapp;

import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by trisys on 22/5/18.
 */

public class LoadCommonGreetingUseCase {

  private final CommonGreetingRepository greetingRepository;

  @Inject
  public LoadCommonGreetingUseCase(CommonGreetingRepository greetingRepository) {
    this.greetingRepository = greetingRepository;
  }

  public Single<String> execute() {
    return greetingRepository.getGreeting();
  }
}
