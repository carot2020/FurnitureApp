package vn.edu.csc.furnitureapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    static String filename="database";

    static ArrayList<Furniture> furnitureHistory = new ArrayList<>();

    public void addFurintureHistorry(Furniture furniture) {
        if(furnitureHistory.indexOf(furniture) > 0) {
            this.furnitureHistory.add(0, furniture);
        }
    }


    public ArrayList<Furniture> getFurnitureHistory(){ return this.furnitureHistory; }

    //lab 45

    public void WriteToFileInternal(ArrayList<Furniture> arrayList){
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Furniture> LoadFileInternal(){
        ArrayList<Furniture> arrayList = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<Furniture>) objectInputStream.readObject();
            Log.d("FurnitureApp", arrayList.size()+"");

            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //lab 23

    public ArrayList<Furniture> getMockDataFuniture(){
        ArrayList<Furniture> tmp = new ArrayList<>();
        tmp.add(new Furniture(context.getString(R.string.name_product_one),     context.getString(R.string.product_one),   "hinh_1.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_tow),     context.getString(R.string.product_tow),   "hinh_2.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five),    context.getString(R.string.product_five),  "hinh_5.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_one),     context.getString(R.string.product_one),   "hinh_1.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_tow),     context.getString(R.string.product_tow),   "hinh_2.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five),    context.getString(R.string.product_five),  "hinh_5.png"));
        return tmp;
    }

    public ArrayList<Categories> getMockDataCategories(){
        ArrayList<Categories> tmp = new ArrayList<>();
        tmp.add(new Categories("BedRoom",  "bed_room.png"));
        tmp.add(new Categories("LivingRoom", "living_room.png"));
        tmp.add(new Categories("MeetingRoom",  "meeting_room.png"));
        tmp.add(new Categories("Accessories",  "accessories.png"));
        return tmp;
    }

    public ArrayList<Furniture> getFurnitureFromCategories(int pos){
        ArrayList<Furniture> tmp = new ArrayList<>();
        switch (pos){
            case 0:
                tmp.add(new Furniture(context.getString(R.string.name_product_one),     context.getString(R.string.product_one),   "hinh_1.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_tow),     context.getString(R.string.product_tow),   "hinh_2.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
                break;
            case 1:
                tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_five),    context.getString(R.string.product_five),  "hinh_2.png"));
                break;
            case 2:
                tmp.add(new Furniture(context.getString(R.string.name_product_tow),     context.getString(R.string.product_tow),   "hinh_2.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_five),    context.getString(R.string.product_five),  "hinh_5.png"));
                break;
            case 3:
                tmp.add(new Furniture(context.getString(R.string.name_product_three),   context.getString(R.string.product_three), "hinh_3.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_four),    context.getString(R.string.product_four),  "hinh_4.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_five),    context.getString(R.string.product_five),  "hinh_5.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_one),     context.getString(R.string.product_one),   "hinh_1.png"));
                break;
        }

        return tmp;
    }

    //lab 07

    // utils
    public Bitmap convertStringToBitmapFromAccess(String filename){

        AssetManager assetManager = context.getAssets();

        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return  bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
