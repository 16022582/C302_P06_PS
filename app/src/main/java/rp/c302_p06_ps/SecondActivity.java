package rp.c302_p06_ps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private ListView lvCategories;
    ArrayList<Details> alCategories = new ArrayList<Details>();
    ArrayAdapter<Details> aa;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        intent.getIntExtra("id",0);


    }
    protected void onResume() {
        super.onResume();
        lvCategories = findViewById(R.id.listView);
        aa = new Adapter2(this, R.layout.detailrow, alCategories);
        lvCategories.setAdapter(aa);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06/getPhotoStoreByCategory.php?category_id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();

    }

    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int detailID = jsonObj.getInt("category_id");
                            String detailTitle = jsonObj.getString("title");
                            String detailDesc = jsonObj.getString("description");
                            String detailImage = jsonObj.getString("image");
                            String detailCreated = jsonObj.getString("created_by");
                            Details newDet = new Details(detailTitle,detailDesc,detailImage,detailCreated);
                            alCategories.add(newDet);

                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aa.notifyDataSetChanged();
                }
            };


}

