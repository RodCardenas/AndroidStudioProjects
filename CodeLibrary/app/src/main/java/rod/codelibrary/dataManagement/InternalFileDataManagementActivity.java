package rod.codelibrary.dataManagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import rod.codelibrary.R;


public class InternalFileDataManagementActivity extends Activity implements OnClickListener{

    private String filename = "WeighTrackerDataFile.txt";
    File myInternalFile;
    //File myExternalFile;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data_management);

        myInternalFile = new File(getApplicationContext().getFilesDir(), filename);

        Button saveToInternalStorage = (Button) findViewById(R.id.getLocationButton);
        saveToInternalStorage.setOnClickListener(this);

        Button readFromInternalStorage = (Button) findViewById(R.id.readButton);
        readFromInternalStorage.setOnClickListener(this);

//        Button saveToExternalStorage =
//                (Button) findViewById(R.id.saveExternalStorage);
//        saveToExternalStorage.setOnClickListener(this);
//
//        Button readFromExternalStorage =
//                (Button) findViewById(R.id.getExternalStorage);
//        readFromExternalStorage.setOnClickListener(this);
//
//        //check if external storage is available and not read only
//        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
//            saveToExternalStorage.setEnabled(false);
//        }
//        else {
//            myExternalFile = new File(getExternalFilesDir(filepath), filename);
//        }
    }

    public void onClick(View v)
    {

        EditText myInputText = (EditText) findViewById(R.id.currentLocation);
        //EditText myInputText2 = (EditText) findViewById(R.id.currentBMI);
        TextView responseText = (TextView) findViewById(R.id.instructionsField);
        String myData = "";

        switch (v.getId())
        {
            case R.id.getLocationButton:
                try {
                    FileOutputStream fos = new FileOutputStream(myInternalFile);
                    fos.write(myInputText.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myInputText.setText("");
                responseText.setText("Data saved to Internal Storage...");
                break;

            case R.id.readButton:
                try {
                    FileInputStream fis = new FileInputStream(myInternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null)
                    {
                        myData = myData + strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myInputText.setText(myData);
                responseText.setText("Data retrieved from Internal Storage...");
                break;
//            case R.id.saveExternalStorage:
//                try {
//                    FileOutputStream fos = new FileOutputStream(myExternalFile);
//                    fos.write(myInputText.getText().toString().getBytes());
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                myInputText.setText("");
//                responseText
//                        .setText("MySampleFile.txt saved to External Storage...");
//                break;
//
//            case R.id.getExternalStorage:
//                try {
//                    FileInputStream fis = new FileInputStream(myExternalFile);
//                    DataInputStream in = new DataInputStream(fis);
//                    BufferedReader br =
//                            new BufferedReader(new InputStreamReader(in));
//                    String strLine;
//                    while ((strLine = br.readLine()) != null) {
//                        myData = myData + strLine;
//                    }
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                myInputText.setText(myData);
//                responseText
//                        .setText("MySampleFile.txt data retrieved from Internal Storage...");
//                break;

        }
    }

//    private static boolean isExternalStorageReadOnly() {
//        String extStorageState = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean isExternalStorageAvailable() {
//        String extStorageState = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
//            return true;
//        }
//        return false;
//    }

}

