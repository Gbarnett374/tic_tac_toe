// Greg Barnett CSC 240 Tic Tac Toe 
package edu.ncc.tictactoe_day7;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.graphics.Color;


public class MainActivity extends Activity implements View.OnClickListener, ActionBar.OnNavigationListener {

	private Button[] btns;
	private boolean isX;
	private String currentLetter;
	int count = 0;
	private String player1;
	private String player2;
	private int player1color;
	private int player2color;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// create the array of buttons
		btns = new Button[9];

		// store the id of each button into the array
		int id = R.id.b00;
		for (int i=0; i<btns.length; i++)
		{
			btns[i] = (Button)findViewById(id++);
			btns[i].setOnClickListener(this);
		}
		// set the initial character to O
		isX = false;
		player1 = "X";
		player2 = "O";
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);



		SpinnerAdapter pColorsSpinnerAdapter =
				ArrayAdapter.createFromResource(this,R.array.player_colors_array,
						android.R.layout.simple_spinner_dropdown_item);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);



		actionBar.setListNavigationCallbacks(pColorsSpinnerAdapter,this);

		actionBar.setTitle("Tic Tac Toe");



		return true;
	}


	@Override
	public void onClick(View view) {
		int index = view.getId() - R.id.b00;

		
		
		if (isX) {
			currentLetter = player2;
			btns[index].setTextColor(player2color);
		}
		else {
			currentLetter = player1;
			btns[index].setTextColor(player1color);
		}
		
		isX = !isX;
		btns[index].setText(currentLetter);
		btns[index].setClickable(false);
		count++;
		
		
		if (count >=4 ) {
			
			results();
			
		}
	}


	@Override
	public boolean onNavigationItemSelected(int position, long itemId) {
		// TODO Auto-generated method stub



		switch (position) 
		{

		case 0:
			
					
			player1color = Color.RED;			
			player2color = Color.BLUE;	
			
			Toast.makeText(getApplicationContext(), "Color = RED & BLUE", Toast.LENGTH_LONG).show();
			break;

		case 1:
				
			player1color = Color.YELLOW;
			player2color = Color.GREEN;

			Toast.makeText(getApplicationContext(), "Color = Yellow/Green", Toast.LENGTH_LONG).show();
			break;

		case 2: 

			
			player1color = Color.CYAN;
			player2color = Color.MAGENTA;
			
			Toast.makeText(getApplicationContext(), "Color = CYAN/MAGENTA", Toast.LENGTH_LONG).show();
			break;		

		}

		for (int i = 0; i < btns.length; i++) {
			
			
			if (btns[i].getText().equals(player1)) {
				
				
				btns[i].setTextColor(player1color);
				
			}
			
			else if (btns[i].getText().equals(player2)) {
				
				
				btns[i].setTextColor(player2color);
				
			}
			
		}
	

		return false;

	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){

		case R.id.action_reset:


			for (int i = 0; i<btns.length; i++) {

				btns[i].setText("");
				btns[i].setClickable(true);
				count = 0;

			}
			break;
			
		case R.id.player_options:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose your players....");

			String [] choices = {"X and O", "P and Q", "Y and Z"};
			builder.setItems(choices, new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					
					
					
					switch (which)
					{
					
					case 0: 
						player1 = "X";
						player2 = "O";
						
						for (int i =0; i < btns.length; i++) {
							
							if ( (btns[i].getText() == "P") || (btns[i].getText() == "Y")) {
								
								btns[i].setText(player1);
								
							}
							
							else if ((btns[i].getText() == "Q") || (btns[i].getText() == "Z")) {
								
								
								btns[i].setText(player2);
								
							}				
							
						}
						
							
						
						break;
						
					case 1:
						
						
							player1 = "P";			
							player2 = "Q";
							
							for (int i =0; i < btns.length; i++) {
								
								if ( (btns[i].getText() == "X") || (btns[i].getText() == "Y")) {
									
									btns[i].setText(player1);
									
								}
								
								else if ((btns[i].getText() == "O") || (btns[i].getText() == "Z")){
									
									
									btns[i].setText(player2);
									
								}				
								
							}
							
							
						break;
					
					case 2:
		
							
							player1 = "Y";							
							player2 = "Z";
							
							
							for (int i =0; i < btns.length; i++) {
								
								if ( (btns[i].getText() == "P") || (btns[i].getText() == "X")) {
									
									btns[i].setText(player1);
									
								}
								
								else if ((btns[i].getText() == "Q") || (btns[i].getText() == "O")){
									
									
									btns[i].setText(player2);
									
								}				
								
							}
						
						break;
					
					}



				}
		});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			break;
			
			
		default:
			return super.onOptionsItemSelected(item);
			

		} // ends switch statement 
		
		return true;
		
	}// ends onOptionsSelected() 
	
private void results() {
		
		// test to see who has one the game 
		
		// check rows
	
		boolean winner = false;
		
		for (int i = 0; i <=6; i+=3) {
			
			
			if ((btns[i].getText().equals(btns[i+1].getText())) && (btns[i].getText().equals( btns[i+2].getText()))
				&& (btns[i].getText() != "")) {
					
					Toast.makeText(getApplicationContext(),btns[i].getText() + " wins", Toast.LENGTH_LONG).show(); 
					winner = true;
					// results.setText(btns[i].getText() + " wins");			
			}
			
		
		}
			
		
		// check columns
		if (!winner) {
			for (int i =0; i<=2;i++) {
			
				if ((btns[i].getText() == btns[i+3].getText()) && (btns[i].getText() == btns[i+6].getText()) 
						&& (btns[i].getText() != "")) {
				
					Toast.makeText(getApplicationContext(),btns[i].getText() + " wins", Toast.LENGTH_LONG).show(); 
					winner = true;
					//results.setText(btns[i].getText() + " wins");	
				
				}
			
			
			}
					
		}	
			//Check Diagonals 
			
			if (!winner) {
				
				if ((btns[0].getText() == btns[4].getText()) && (btns[0].getText() == btns[8].getText()) 
						&& (btns[0].getText() != "")) {
			
					Toast.makeText(getApplicationContext(),btns[0].getText() + " wins", Toast.LENGTH_LONG).show(); 
					winner = true;			
					//results.setText(btns[0].getText() + " wins");
				}
			
				else if ((btns[2].getText() == btns[4].getText()) && (btns[2].getText() == btns[6].getText()) 
						&& (btns[2].getText() != "")) {
				
						Toast.makeText(getApplicationContext(),btns[2].getText() + " wins", Toast.LENGTH_LONG).show(); 
						winner = true;
						//results.setText(btns[2].getText() + " wins");
				
				}
			}
								
		 if ( ( count > 8) && (!winner) ) {
					count = 0;
					Toast.makeText(getApplicationContext(),"The game is a draw!", Toast.LENGTH_LONG).show();
					//results.setText("Game is a draw!");
			}
				
				
					
	
	} // Ends the results method. 

} // ends main method 