package com.joseluis.crowfundingapp.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class CrowdfundingRepository implements RepositoryContract {

    public static final String DB_FILE = "crowdfunding.db";
    public static final String JSON_FILE = "Crowdfunding.json";
    public static final String JSON_ROOT = "projects";
    public static String TAG = CrowdfundingRepository.class.getSimpleName();
    private static CrowdfundingRepository INSTANCE;

    private CrowdfundingDatabase database;
    private Context context;


    private CrowdfundingRepository(Context context) {
        this.context = context;

        database = Room.databaseBuilder(
                context, CrowdfundingDatabase.class, DB_FILE
        ).build();

    }

    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CrowdfundingRepository(context);
        }

        return INSTANCE;
    }

    @Override
    public void loadCrowdfundingProjectsList(
            final boolean clearFirst, final FetchCrowdfundingDataCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (clearFirst) {
                    database.clearAllTables();
                }

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
    public void getUser(final int id, final GetUserCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setUser(getUserDao().loadUser(id));
                }
            }
        });
    }

    @Override
    public void getProject(final int id, final GetProjectCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setProject(getProjectDao().loadProject(id));
                }
            }
        });

    }

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

    @Override
    public void deleteUser(
            final UserItem User, final DeleteUserCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    getUserDao().deleteUser(User);
                    callback.onUserDeleted();
                }
            }
        });
    }

    @Override
    public void updateUser(
            final UserItem User, final UpdateUserCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    getUserDao().updateUser(User);
                    callback.onUserUpdated();
                }
            }
        });
    }


    @Override
    public void deleteProject(
            final ProjectItem Project, final DeleteProjectCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    getProjectDao().deleteProject(Project);
                    callback.onProjectDeleted();
                }
            }
        });
    }


    @Override
    public void updateProject(
            final ProjectItem Project, final UpdateProjectCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    getProjectDao().updateProject(Project);
                    callback.onProjectUpdated();
                }
            }
        });
    }


    private ProjectDao getProjectDao() {
        return database.projectDao();
    }

    private UserDao getUserDao() {
        return database.userDao();
    }


    private boolean loadCrowdfundingFromJSON(String json) {


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

            if (jsonArray.length() > 0) {

                final List<ProjectItem> projects = Arrays.asList(
                        gson.fromJson(jsonArray.toString(), ProjectItem[].class));

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

    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }

        return json;
    }

}


