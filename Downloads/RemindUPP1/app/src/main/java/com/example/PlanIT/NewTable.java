package com.example.PlanIT;

import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTable extends Activity {

	String text;
	EditText edt;
	Button editbtn;
	static final int RESULT=0;
	DBhelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nitk_table);
        db=new DBhelper(NewTable.this);
        TextView tv;
        tv=(TextView)findViewById(R.id.index);
        tv.setText(DBhelper.gettablename());

	}
	public void back(View v)
	{
		finish();
	}

	public void edit(View v)	{

		editbtn=(Button)v;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.prompt_subject, null);
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
                        text= input.getText().toString();
                        editbtn.setText(text);
                        int i=editbtn.getId();
                        db.insertSubject(i, text);
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
    //public void
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_table, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId())
    	{
    	case R.id.esinfo:
    		versionItems();
    		break;
    	}
		return true;
    	
    }
    private void versionItems(){
    	new Builder(this)
    	.setTitle("Info")
    	.setMessage("Create your weekly time table here.\nPlan your week well.")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).show();
    }

}
