package com.example.doggygame.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserRepositoryManager extends DatabaseContext {

    public UserRepositoryManager(@Nullable Context context) {
        super(context);
    }

    public void insertUser(String Name, String Email, String Password, int BestScore) {
        String insertQ = "INSERT INTO user(" +
                "Name, Email, Password, BestScore" +
                ") VALUES(?, ?, ?, ?)";
        _myDB.execSQL(insertQ, new Object[]{
                Name, Email, Password, BestScore
        });
    }


    public void updateUserEmail(int Id, String Email) {
        String updateQ = "UPDATE user " +
                "SET Email = '" + Email + "' " +
                "WHERE ID = " + Id + ";";
        _myDB.execSQL(updateQ);
    }


    public void updateUserPassword(int Id, String Password) {
        String updateQ = "UPDATE user " +
                "SET Password = '" + Password + "' " +
                "WHERE ID = " + Id + ";";
        _myDB.execSQL(updateQ);
    }

    public void updateUserBestScore(int UserId, int Score) {
        String updateQ = "UPDATE user " +
                "SET BestScore = '" + Score + "' " +
                "WHERE ID = " + UserId + ";";
        _myDB.execSQL(updateQ);
    }

    public void deleteUser(int id) {
        String deleteQ = "DELETE FROM user " +
                "WHERE ID = " + id + ";";
        _myDB.execSQL(deleteQ);
    }

    public String getUserPassword(int id) {
        String readQ = "SELECT Password FROM user WHERE ID = " + id + ";";
        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        ArrayList<String> resultArrayList = convertToArrayList(result, 1);
        if (resultArrayList.size() == 0){return "";}

        return resultArrayList.get(0);
    }

    public String getUserId(String email){
        String readQ = "SELECT ID FROM user " +
                "WHERE Email = '" + email + "';";

        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        ArrayList<String> resultArrayList = convertToArrayList(result, 1);
        if (resultArrayList.size() == 0){return "";}

        return resultArrayList.get(0);
    }

    public String getUserName(int id){
        String readQ = "SELECT Name FROM user " +
                "WHERE ID = " + id + ";";

        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        ArrayList<String> resultArrayList = convertToArrayList(result, 1);
        if (resultArrayList.size() == 0){return "";}

        return resultArrayList.get(0);
    }

    public ArrayList<String> getAllUserEmails() {
        String readQ = "SELECT Email FROM user;";
        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        return convertToArrayList(result, 1);
    }

    public ArrayList<String> getAllUsersBestScore() {
        String readQ = "SELECT Name, BestScore FROM user ORDER BY BestScore DESC;";
        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        return convertToArrayList(result, 2);
    }

    public int getUserBestScore(int UserId){
        String readQ = "SELECT BestScore FROM user " +
                "WHERE ID = " + UserId + ";";

        @SuppressLint("Recycle") Cursor result = _myDB.rawQuery(readQ, null);

        ArrayList<String> resultArrayList = convertToArrayList(result, 1);
        if (resultArrayList.size() == 0){return 0;}

        return Integer.parseInt(resultArrayList.get(0));
    }

    private ArrayList<String> convertToArrayList(Cursor result, int columnCount){
        ArrayList<String> resultArrayList = new ArrayList<>();
        while (result.moveToNext()) {
            for (int index = 0; index < columnCount; index++){
                resultArrayList.add(result.getString(index));
            }
        }

        return resultArrayList;
    }

}
