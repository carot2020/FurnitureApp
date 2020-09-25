package vn.edu.csc.furnitureapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;
    DBHelper dbHelper;


    String url = "http://192.168.0.101:5000/Furniture";
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        dbHelper = new DBHelper(getContext());

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        //lab 23
        //arrayList = utils.getMockDataFuniture();

        //lab 6
        //arrayList = dbHelper.getALLFurniture();

        Log.i("ABC", arrayList.size()+"");

        //lab 78
        arrayList = new ArrayList<>();

        furnitureAdapter = new FurnitureAdapter(getContext(),arrayList);

        listView.setAdapter(furnitureAdapter);

        getAPIFurniture();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("furniture", arrayList.get(i));
                startActivity(intent);
            }
        });



    }



    @Override
    public void onPause() {
        super.onPause();
        utils.WriteToFileInternal(arrayList);
    }

    public void getAPIFurniture(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("furnitures");
                    for (int i = 0; i< jsonArray.length() ; i++ ){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Furniture furniture = new Furniture();
                        furniture.setId(jsonObject.getInt("furnitureId"));
                        furniture.setName(jsonObject.getString("name"));
                        furniture.setImage(jsonObject.getString("image"));
                        furniture.setDescription(jsonObject.getString("description"));
                        furniture.setCategotiesID(jsonObject.getInt("categoriesId"));
                        arrayList.add(furniture);
                    }
                    furnitureAdapter.notifyDataSetChanged();
                    Log.i("APIHelper", arrayList.size()+"") ;
                } catch (JSONException e) {

                    Log.i("APIHelper", e.getMessage()) ;
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("APIHelper", error.getMessage()) ;
            }
        });
        requestQueue.add(jsonObjectRequest);
        Log.i("APIHelper", arrayList.size()+"") ;

    }
}