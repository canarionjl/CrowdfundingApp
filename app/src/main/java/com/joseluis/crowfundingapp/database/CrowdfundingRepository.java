package com.joseluis.crowfundingapp.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CrowdfundingRepository implements RepositoryContract {

    public static final String DB_FILE = "crowdfunding.db";
    public static final String JSON_FILE = "projects.json";
    public static final String JSON_ROOT = "projects";
    public static String TAG = CrowdfundingRepository.class.getSimpleName();
    private static CrowdfundingRepository INSTANCE;

    private final CrowdfundingDatabase database;
    private final Context context;


    private CrowdfundingRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, CrowdfundingDatabase.class, DB_FILE).build();
    }

    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CrowdfundingRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public void deleteTables() {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                database.clearAllTables();
            }
        });

    }


    @Override
    public void loadCrowdfundingProjectsList(final FetchCrowdfundingDataCallback callback) {


        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                boolean error = false;
                if (getProjectDao().loadProjects().size() == 0) {
                    error = !loadCrowdfundingFromJSON(loadJSONFromAsset());
                }

                if (callback != null) {
                    callback.onCrowdfundingDataFetched(error);
                }
            }
        });
    }


    //USERS

    @Override
    public void getUserList(final GetUserListCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setUserList(getUserDao().loadUsers());
                }
            }
        });

    }


    @Override
    public void insertUser(final UserItem user) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                getUserDao().insertUser(user);
            }
        });
    }


    //PROJECTS

    @Override
    public void getProjectList(final GetProjectListCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setProjectList(getProjectDao().loadProjects());
                }
            }
        });
    }


    //USER TO PROJECT LIST (RELATIONSHIP)

    @Override
    public void getUserToProjectListWithId(int id, final GetUserToProjectListCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    if (id > (-1))
                        callback.setUserToProjectList(getUserProjectJoinTableDao().loadUserProjectsWithUserId(id));
                }
            }
        });
    }


    @Override
    public void setUserToProjectListWithId(int userId, int projectId) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                getUserProjectJoinTableDao().insertUserProjectJoin(new UserProjectJoinTable(userId, projectId));
            }
        });

    }

    @Override
    public void deleteUserToProjectListWithId(UserProjectJoinTable userToProjectRelationship) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                getUserProjectJoinTableDao().deleteUserProjectJoin(userToProjectRelationship);
            }
        });

    }

    @Override
    public void getRelationshipBetweenUserAndProjectWithId (int userId,int projectId,RepositoryContract.GetRelationshipBetweenUserAndProjectWithId callback){
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                callback.setUserProjectJoinTable(getUserProjectJoinTableDao().getRelationshipBetweenUserAndProjectWithId(userId,projectId));
            }
        });


    }

        //DAOS

        private ProjectDao getProjectDao () {
            return database.projectDao();
        }

        private UserDao getUserDao () {
            return database.userDao();
        }


        private UserProjectJoinTableDao getUserProjectJoinTableDao () {
            return database.userProjectJoinTableDao();
        }


        //JSON MANAGING

        private boolean loadCrowdfundingFromJSON (String json){


            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            try {

                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

                if (jsonArray.length() > 0) {

                    final ProjectItem[] projects = gson.fromJson(jsonArray.toString(), ProjectItem[].class);

                    for (ProjectItem projectItem : projects) {
                        getProjectDao().insertProject(projectItem);
                    }
                    return true;
                }

            } catch (JSONException error) {
                Log.e(TAG, "error: " + error);
            }
            return false;
        }

        private String loadJSONFromAsset () {
            String json = null;

            try {
                InputStream is = context.getAssets().open(JSON_FILE);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException error) {
                Log.e(TAG, "error: " + error);
            }

            return json;
        }

    }


