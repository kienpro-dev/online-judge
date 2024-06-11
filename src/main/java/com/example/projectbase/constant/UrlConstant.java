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
    public static final String GET_USER_PARAM = PRE_FIX;
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";
    public static final String FORGOT_PASSWORD = PRE_FIX + "/forgot-password";

    private User() {
    }
  }

  public static class Exercise {
    private static final String PRE_FIX = "/exercise";

    public static final String GET_EXERCISES = PRE_FIX;
    public static final String CREATE_EXERCISES = PRE_FIX;
    public static final String GET_EXERCISE = PRE_FIX + "/{exerciseId}";
    public static final String SUBMIT_CODE = PRE_FIX + "/{exerciseId}" + "/submit";
  }

  public static class Submission {
    private static final String PRE_FIX = "/submission";

    public static final String GET_SUBMISSIONS = PRE_FIX;
    public static final String CREATE_SUBMISSION = PRE_FIX;
    public static final String GET_SUBMISSION = PRE_FIX + "/{submissionId}";
  }

  public static class Board {
    private static final String PRE_FIX = "/board";

    public static final String GET_USER_BOARD = PRE_FIX + "/user";
  }

  public static class Blog {
    private static final String PRE_FIX = "/blog";

    public static final String GET_BLOGS = PRE_FIX;
    public static final String CREATE_BLOG = PRE_FIX;
  }

}
