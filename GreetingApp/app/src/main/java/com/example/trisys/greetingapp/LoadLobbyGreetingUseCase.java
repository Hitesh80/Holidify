package com.example.trisys.greetingapp;

import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by trisys on 22/5/18.
 */

public class LoadLobbyGreetingUseCase {
  private final LobbyGreetingRepository greetingRepository;

  @Inject
  LoadLobbyGreetingUseCase(LobbyGreetingRepository greetingRepository) {
    this.greetingRepository = greetingRepository;
  }

  Single<String> execute() {
    return greetingRepository.getGreeting();
  }
}
