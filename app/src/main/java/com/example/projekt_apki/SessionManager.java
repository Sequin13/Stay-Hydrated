package com.example.projekt_apki;

public class SessionManager {
    private static SessionManager instance;
    private String loggedInUser;
    private int userGoal;

    private SessionManager() {

    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLoggedInUser(String user) {
        loggedInUser = user;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setUserGoal(int goal) {
        userGoal = goal;
    }

    public int getUserGoal() {
        return userGoal;
    }
}

