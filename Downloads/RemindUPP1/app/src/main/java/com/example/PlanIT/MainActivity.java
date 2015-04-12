package com.example.PlanIT;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public DBhelper mydb;
    private ListView obj;
    Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        b=savedInstanceState;
		setContentView(R.layout.activity_main);
		//Toast.makeText(this, "Welcone NERD!", 3000).show();
		

        mydb = new DBhelper(this);
        ArrayList array_list = mydb.getTableNames();

        ArrayAdapter arrayAdapter =
                new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        //adding it to the list view.
        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {


                String item = ((TextView)view).getText().toString();
                DBhelper.settablename(item);
                startActivity(new Intent(MainActivity.this,EditTable.class));


                /*
                LayoutInflater layoutInflater = LayoutInflater.from(con);
                View promptView = layoutInflater.inflate(R.layout.okay, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

                // set prompt_subject.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                startActivity(new Intent(MainActivity.this,EditTable.class));
                            }
                        })
                        .setNegativeButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int id) {
                                    mydb.delete();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();*/

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();


                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });


		final Button exit=(Button)findViewById(R.id.exi);
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v== exit ){
					finish();
				}
			}
		});
	}



    public void tablename(View v)
    {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.new_table, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompt_subject.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);
        final EditText input = (EditText) promptView.findViewById(R.id.userInput);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        String text= input.getText().toString();
                        DBhelper.settablename(text);
                        mydb.createTable();
                        startActivity(new Intent(MainActivity.this,NewTable.class));

                        onCreate(b);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId())
    	{
    	case R.id.action_settings:
    		versionItems();
    		break;
    	case R.id.info:
    		infoItems();
    		break;
    	case R.id.about_us:
    		aboutItems();
    		break;
    	}
		return true;
    	
    }
    private void versionItems(){
    	new Builder(this)
    	.setTitle("Version")
    	.setMessage("This is the first version")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).show();
    }

    private void infoItems(){
    	new Builder(this)
    	.setTitle(" APP Info")
    	.setMessage("Used for managing your weekly class time table schedule.")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        }).show();
    }
    	private void aboutItems(){
        	new Builder(this)
        	.setTitle("About Us")
        	.setMessage("Credits:\n"+"1.Ashish Kumar Singh\n2.Naresh Choudhary\n3.Rohan")
        	.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            }).show();
    }

}
