package rod.functionalitylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ExpandableListView2LevelsActivity extends Activity
{
    List<Map<String, String>> groupData;
    List<List<Map<String, String>>> childGroups;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_2_levels);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        createGroupDataList();
        createChildDataList();
        createAdapter();

        expandableListView.setAdapter(expandableListAdapter);
    }

    private void createAdapter()
    {
        expandableListAdapter = new SimpleExpandableListAdapter(

                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "ROOT_NAME" },
                new int[] { android.R.id.text1 },

                childGroups,
                android.R.layout.simple_expandable_list_item_2,
                new String[] { "CHILD_NAME", "CHILD_NAME" },
                new int[] { android.R.id.text1, android.R.id.text2 }
        );
    }

    private void createGroupDataList()
    {

        groupData = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("ROOT_NAME", "Group 1");
            }});

            add(new HashMap<String, String>() {{
                put("ROOT_NAME", "Group 2");
            }});
        }};
    }

    private void createChildDataList()
    {
        childGroups = new ArrayList<List<Map<String, String>>>();

        List<Map<String, String>> childGroupForFirstGroupRow = new ArrayList<Map<String, String>>(){{
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 1");
            }});
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 1");
            }});
        }};
        childGroups.add(childGroupForFirstGroupRow);

        List<Map<String, String>> childGroupForSecondGroupRow = new ArrayList<Map<String, String>>(){{
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 2");
            }});
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 2");
            }});
        }};
        childGroups.add(childGroupForSecondGroupRow);
    }
}
