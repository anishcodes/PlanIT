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

import java.util.ArrayList;

public class EditTable extends Activity {
    public ArrayList rowId;
    public ArrayList period;

    String text;
    EditText edt;
    Button editbtn;
    static final int RESULT=0;
    DBhelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nitk_table);
        db=new DBhelper(EditTable.this);

        Button v;
        TextView tv;
        rowId=db.getRowId();
        period=db.getPer();
        tv=(TextView)findViewById(R.id.index);
        tv.setText(DBhelper.gettablename());

        //Toast.makeText(getBaseContext(), val , Toast.LENGTH_LONG).show();
        int val;
        for(int i=0;i<rowId.size();i++)
        {
            val =Integer.valueOf((String) rowId.get(i));
            v=(Button)findViewById(val);
            v.setText((String)period.get(i));
        }


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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_table, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId())
    	{
    	case R.id.etinfo:
    		infoItems();
    		break;
    	}
		return true;
    	
    }
    private void infoItems(){
    	new Builder(this)
    	.setTitle("Info")
    	.setMessage("Used to edit the existing table and manage your weekly schedule.")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).show();
    }
}
