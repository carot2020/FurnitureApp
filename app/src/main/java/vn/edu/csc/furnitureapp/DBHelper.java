package vn.edu.csc.furnitureapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class DBHelper {
    Context context;
    Utils utils;

    String dbName = "FurnitureDB1.db";


    public DBHelper(Context context) {
        this.context = context;
        utils = new Utils(context);

    }

    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String sqlFurniture = "CREATE TABLE IF NOT EXISTS tblFurniture (" +
                " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT," +
                " Image TEXT," +
                " Description TEXT," +
                " CategoriesID INTEGER );";
        String sqlCategories = "CREATE TABLE IF NOT EXISTS tbtCategories (" +
                " CategoriesID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT," +
                " Image TEXT );";

        db.execSQL(sqlFurniture);
        db.execSQL(sqlCategories);
        closeDB(db);
    }

//    public void insertUser(UserModel user) {
//        SQLiteDatabase db = openDB();
//        ContentValues cv = new ContentValues();
//        cv.put("name", user.names);
//        cv.put("hometown", user.hometowns);
//        cv.put("flag", user.flag);
//        db.insert("tblUser", null, cv);
//        closeDB(db);
//    }
//
//    public void updateUser(UserModel user) {
//        SQLiteDatabase db = openDB();
//        ContentValues cv = new ContentValues();
//        cv.put("name", user.names);
//        cv.put("hometown", user.hometowns);
//        cv.put("flag", user.flag);
//        String[] id = {String.valueOf(user.id)};
//        int row = db.update("tblUser", cv, "id = ?", id);
//        closeDB(db);
//    }
//
//    public void deleteUser(UserModel user) {
//        String[] id = {String.valueOf(user.id)};
//        SQLiteDatabase db = openDB();
//        db.delete("tblUser", "id = ?", id);
//        closeDB(db);
//    }

    public ArrayList<Furniture> getALLFurniture() {
       // SQLiteDatabase db = openDB();
        SQLiteDatabase db = openDatabase();
        ArrayList<Furniture> arr = new ArrayList<>();
        String sql = "select * from tblFurniture";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image = csr.getString(2);
                    String description = csr.getString(3);
                    int categoriesID = csr.getInt(4);
                    arr.add(new Furniture(name,description,image,findByCatetgoriesID(categoriesID),id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Categories> getALLCategories(){
        SQLiteDatabase db = openDB();
        ArrayList<Categories> arr = new ArrayList<>();
        String sql = "select * from tbtCategories";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image = csr.getString(2);
                    arr.add(new Categories(name, image, id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public Categories findByCatetgoriesID(int id){
        ArrayList<Categories> arr = getALLCategories();
        for(Categories ca : arr){
            if(ca.getId() == id){
                return ca;
            }
        }
        return null;
    }

    public Categories addFurnitureToCategories(int categoriesId){
        Categories categories = findByCatetgoriesID(categoriesId);
        ArrayList<Furniture> arrFurniture = getALLFurniture();

        for(Furniture furniture : arrFurniture){
            if(furniture.getCategories().getId() ==  categoriesId){
                categories.getArrayList().add(furniture);
            }
        }
        return categories;
    }

    public void insertCategories(){
        ArrayList<Categories> arrCa = utils.getMockDataCategories();
        SQLiteDatabase db = openDB();

        for(Categories ca : arrCa) {
            ContentValues cv = new ContentValues();
            cv.put("Name", ca.getName());
            cv.put("Image", ca.getImage());
            db.insert("tbtCategories", null, cv);
        }
        closeDB(db);
    }

    public void insertFurniture(){
        ArrayList<Furniture> arrFurniture = utils.getMockDataFuniture();
        SQLiteDatabase db = openDB();
        Random random = new Random();

        for(Furniture fu : arrFurniture) {
            ContentValues cv = new ContentValues();
            cv.put("Name", fu.getName());
            cv.put("Image", fu.getImage());
            cv.put("Description", fu.getDescription());
            cv.put("CategoriesID",random.nextInt(4) + 1  );
            db.insert("tblFurniture", null, cv);
        }
        closeDB(db);
    }

//    private UserModel getUser(int uId) {
//        String sql="Select * from tblUser where id = "+ uId;
//        SQLiteDatabase db = openDB();
//
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        int id = cursor.getInt(0);
//        String name = cursor.getString(1);
//        String hometown= cursor.getString(2);
//        int flag = cursor.getInt(3);
//        closeDB(db);
//        return new UserModel( name, hometown,flag, id);
//    }

    public SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(dbName);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open(dbName);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();
    }

}
