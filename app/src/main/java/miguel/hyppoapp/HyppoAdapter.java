package miguel.hyppoapp;

import android.app.Application;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

import miguel.hyppoapp.R;

/**
 * Created by Miguel on 12/24/2015.
 */
public class HyppoAdapter extends ArrayAdapter<ParseObject> {
    private final List<ParseObject> registros;
    private final Context context;

    private ParseQuery<ParseObject> localQuery;
    private ParseObject mood = null;
    private List <ParseObject> conditionsList;

    private TextView dateLabel, moodLabel, conditionLabel1, conditionLabel2, conditionLabel3;
    private ParseRelation relation;

    public HyppoAdapter(Context context, int resource, List<ParseObject> registros) {
        super(context, resource, registros);
        this.registros = registros;
        this.context=context;



    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
*/
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_registro, parent, false);
        }

        ParseObject registro = getItem(position);
        localQuery = ParseQuery.getQuery("State");
        localQuery.fromLocalDatastore();
        relation = registro.getRelation("condition");
        conditionsList = null;

        /*
        Instanciación textviews
         */
        dateLabel = (TextView) convertView.findViewById(R.id.dateLabel);
        moodLabel = (TextView) convertView.findViewById(R.id.moodLabel);
        conditionLabel1 = (TextView) convertView.findViewById(R.id.painLabel);
        conditionLabel2= (TextView) convertView.findViewById(R.id.sleepLabel);
        conditionLabel3 = (TextView) convertView.findViewById(R.id.mentalLabel);


        //Descarga del remoto la lista de condiciones
        try {
            conditionsList = relation.getQuery().find();
            mood = registro.getParseObject("Relation_Mood");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //registro.fetchInBackground();

        TextView [] conditionLabels = {conditionLabel1,conditionLabel2,conditionLabel3};

        int contador=0;
        for(ParseObject condition:conditionsList){
            String conditionId=condition.getObjectId();
            conditionLabels[contador].setText(ParseHandler.getInstance().conditionMap.get(conditionId));
            contador++;
        }
        String returnKey = ParseHandler.getInstance().returnKey(mood.getObjectId());
        moodLabel.setText(returnKey);
        dateLabel.setText(registro.getCreatedAt().toString());



        return convertView;


    }
}
