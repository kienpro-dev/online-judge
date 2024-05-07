package com.example.projectbase.constant;

public class UrlConstant {

  public static class Auth {
    private static final String PRE_FIX = "/auth";

    public static final String LOGIN = PRE_FIX + "/login";
    public static final String LOGOUT = PRE_FIX + "/logout";
    public static final String REGISTER = PRE_FIX + "/register";

    private Auth() {
    }
  }

  public static class User {
    private static final String PRE_FIX = "/user";

    public static final String GET_USERS = PRE_FIX;
    public static final String GET_USER = PRE_FIX + "/{userId}";
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";

    private User() {
    }
  }

  public static class Exercise {
    private static final String PRE_FIX = "/exercise";

    public static final String GET_EXERCISES = PRE_FIX;
    public static final String GET_EXERCISE = PRE_FIX + "/{exerciseId}";
  }

}
