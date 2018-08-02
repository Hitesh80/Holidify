package com.example.trisys.greetingapp;

import io.reactivex.Single;

/**
 * Created by trisys on 22/5/18.
 */

public class LobbyGreetingRepository {

  Single<String> getGreeting() {
    return Single.just("Hello from LobbyGreetingRepository");
  }
}
