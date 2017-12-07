package course.android.com.npuapplication.RecyclerViewFunctions;
import android.content.Context;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Iterator;
import java.util.Set;
/**
 * Created by pranali on 12/6/17.
 */

public class Finalgrades_ParentCreator {

    static Finalgrades_ParentCreator finalgrades_parentCreator;
    List<Finalgrades_Parent> _titleParents;

    public Finalgrades_ParentCreator(Context context) {
        _titleParents = new ArrayList<>();

        HashMap<String,String> mainCat = getParentCategory();
        Set set = mainCat.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            Finalgrades_Parent parent = new Finalgrades_Parent(mentry.getKey().toString(),mentry.getValue().toString());
            _titleParents.add(parent);

        }
    }
    public static Finalgrades_ParentCreator get(Context context) {
        if (finalgrades_parentCreator == null)
            finalgrades_parentCreator = new Finalgrades_ParentCreator(context);

        return finalgrades_parentCreator;
    }

    public HashMap<String,String> getParentCategory() {

        HashMap<String, String> hmapparent = new HashMap<String, String>();
        hmapparent.put("2016 Fall","4.0");
        hmapparent.put("2017 Spring","3.9");
        hmapparent.put("2017 Summer","4.0");
        hmapparent.put("2017 Fall","3.8");
        return hmapparent;
    }


    public List<Finalgrades_Parent> getAll() {
        return _titleParents;
    }
}
