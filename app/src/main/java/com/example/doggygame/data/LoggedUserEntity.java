package com.example.doggygame.data;

public class LoggedUserEntity {
    private static int id;
    private static String name;
    private static String email;

    private static int bestScore;

    public static void logUser(int Id, String Email, String Name, int BestScore){
        id = Id;
        name = Name;
        email = Email;
        bestScore = BestScore;
    }

    public static void logoutUser(){
        id = -1;
        name = "";
        email = "";
        bestScore = 0;
    }

    public static int getUserId(){
        return id;
    }

    public static String getUserName(){
        return name;
    }

    public static String getUserEmail(){
        return email;
    }

    public static int getBestScore(){return bestScore;}

    public static void setBestScore(int bestScore){LoggedUserEntity.bestScore = bestScore;}

    public static void setUserEmail(String email){
        LoggedUserEntity.email = email;
    }
}
