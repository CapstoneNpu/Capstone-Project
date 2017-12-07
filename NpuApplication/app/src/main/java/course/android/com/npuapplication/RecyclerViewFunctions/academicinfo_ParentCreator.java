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

public class academicinfo_ParentCreator {
    static academicinfo_ParentCreator academicinfo_parentCreator;
    List<acdemicinfo_Parent> _titleParents;

    public academicinfo_ParentCreator(Context context) {

        _titleParents = new ArrayList<>();
       // List<String> mainCat = getCategory();
        HashMap<String,String> mainCat = getParentCategory();
        Set set = mainCat.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            acdemicinfo_Parent parent = new acdemicinfo_Parent(mentry.getKey().toString(),mentry.getValue().toString());
            _titleParents.add(parent);

        }
//        for (String categoryName : mainCat) {
//            acdemicinfo_Parent parent = new acdemicinfo_Parent(categoryName);
//            _titleParents.add(parent);
//        }
//        for (int i=1;i<mainCat.l;i++)
//        {
//            acdemicinfo_Parent parent = new acdemicinfo_Parent(String.format("Caller #%d",i));
//            _titleParents.add(parent);
//        }


    }

    public static academicinfo_ParentCreator get(Context context) {
        if (academicinfo_parentCreator == null)
            academicinfo_parentCreator = new academicinfo_ParentCreator(context);

        return academicinfo_parentCreator;
    }

    public HashMap<String,String> getParentCategory() {

        HashMap<String, String> hmapparent = new HashMap<String, String>();
        hmapparent.put("Foundation Requirements","(9.0/9.0)");
        hmapparent.put("Software Engineering Course Requirements","(6.0/12.0)");

        hmapparent.put("Capstone Course","(3.0/3.0)");
        hmapparent.put("Electives","(9.0/12.0)");
        return hmapparent;
    }
    public List<String> getCategory() {
        List<String> categortyList = new ArrayList<String>(Arrays.asList("Foundation Requirements", "Software Engineering Course Requirements", "Electives", "Capstone Course"));
        return categortyList;
    }

    public List<acdemicinfo_Parent> getAll() {
        return _titleParents;
    }



}