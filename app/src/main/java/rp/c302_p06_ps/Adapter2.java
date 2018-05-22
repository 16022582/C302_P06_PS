package rp.c302_p06_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter2 extends ArrayAdapter<Details>{
    private ArrayList<Details> details;
    private Context context;
    private TextView name, text1,text2,text3;

    public Adapter2(Context context, int resource, ArrayList<Details> objects) {
        super(context, resource, objects);
        details = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View detailView = inflater.inflate(R.layout.detailrow, parent, false);

        name =  detailView.findViewById(R.id.nameView);
        text1 =  detailView.findViewById(R.id.text1);
        text2 =  detailView.findViewById(R.id.text2);
        text3 =  detailView.findViewById(R.id.text3);

        Details currentEntry = details.get(position);


        name.setText(currentEntry.getTitle());
        text1.setText(currentEntry.getDesc());
        text2.setText(currentEntry.getCreated());
        text3.setText(currentEntry.getImage());

        return detailView;
    }
}

