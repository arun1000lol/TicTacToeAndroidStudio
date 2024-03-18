package lccc.cgsd130.ourtictachelp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainFMActivity extends AppCompatActivity {
 // public boolean turnAS;
 public String turnAS = "O";
 public int numberAS;
 public int buttonId;
 public TextView resultAS;
 public int[] gameStateAS = {2,2,2,2,2,2,2,2,2,2};
 public int[][] winningPositionsAS = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7},
                                    {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};

    ArrayList<ImageButton> buttonsFM=new ArrayList<ImageButton>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ticfm);
        Button.OnClickListener ourListenerFM= new Button.OnClickListener() {
            @Override
            public void onClick(View vFM) {
                    doButtonsFM(vFM);
            }//close the onClick
        };//close the class OnClickListener

        //store the button references in the array
       //buttonsFM.add((ImageButton)findViewById(R.id.imageButton1));

        String constructedIDFM=new String("imageButton");

        Resources resFM=this.getResources();
        resultAS = findViewById(R.id.resultText);
        int idFM;

        for (int iFM=1; iFM<=9; iFM++)
        {
            idFM=resFM.getIdentifier(constructedIDFM+String.valueOf(iFM),
                    "id", this.getPackageName());
            ImageButton temp=(ImageButton)findViewById(idFM);
            temp.setOnClickListener(ourListenerFM);
            buttonsFM.add(temp);
            buttonId++;
        }
    }//close the onCreate

    public void doButtonsFM(View buttonPressedFM) {

/*
        if (buttonPressedFM == buttonsFM.get(0)) {
            Toast.makeText(this, turnAS + " preseed the button 0",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(1)) {
            Toast.makeText(this, turnAS + " preseed the button 1",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(2)) {
            Toast.makeText(this, turnAS + " preseed the button 2",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(3)) {
            Toast.makeText(this, turnAS + " preseed the button 3",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(4)) {
            Toast.makeText(this, turnAS + " preseed the button 4",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(5)) {
            Toast.makeText(this, turnAS + " preseed the button 5",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(6)) {
            Toast.makeText(this, turnAS + " preseed the button 6",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(7)) {
            Toast.makeText(this,  turnAS + " pressed the button 7",
                    Toast.LENGTH_LONG).show();
        }
        if (buttonPressedFM == buttonsFM.get(8)) {
            Toast.makeText(this, turnAS + " preseed the button 8",
                    Toast.LENGTH_LONG).show();
        }

 */

        String buttonState = buttonPressedFM.getResources().getResourceEntryName(buttonPressedFM.getId());
        int gameStatePointerAS = Integer.parseInt(buttonState.substring(buttonState.length()-1));
        ImageButton theButtonFM = (ImageButton) buttonPressedFM;
        if (!theButtonFM.isActivated()) {
            if (!turnAS.contains("X")) {
                theButtonFM.setImageResource(R.drawable.tico);

                turnAS = "X";
                theButtonFM.setActivated(true);
                numberAS++;


                gameStateAS[gameStatePointerAS] = 0;
            } else {
                theButtonFM.setImageResource(R.drawable.ticx);
                //buttonsFM.set(buttonId, theButtonFM);
                turnAS = "O";
                theButtonFM.setActivated(true);
                numberAS++;
                gameStateAS[gameStatePointerAS] = 1;

            }
        }
        if (numberAS == 9) {
            Toast.makeText(this, "Nobody won", Toast.LENGTH_LONG).show();
            resultAS.setVisibility(View.VISIBLE);
            resultAS.setText("Nobody won");
            theButtonFM.setEnabled(false);
        }
        if(GameCheckAS()){
            for(int i = 0; i <= 8; i++){
                buttonsFM.get(i).setEnabled(false);
            }

            if(!turnAS.contains("O")){
                Toast.makeText(this, "O has won!", Toast.LENGTH_LONG).show();
                resultAS.setVisibility(View.VISIBLE);
                resultAS.setText("O has won!");


            }
            else{
                resultAS.setVisibility(View.VISIBLE);
                Toast.makeText(this, "X has won!", Toast.LENGTH_LONG).show();
                resultAS.setText("X has won!");

            }
        }


    }

    public boolean GameCheckAS(){
        boolean winnerResult = false;
        for(int [] winningPosition : winningPositionsAS){
            if(gameStateAS[winningPosition[0]] == gameStateAS[winningPosition[1]] && gameStateAS[winningPosition[1]]
                                               == gameStateAS[winningPosition[2]] && gameStateAS[winningPosition[0]] != 2){
            winnerResult = true;
            }

        }
        return winnerResult;
    }




}//close Activity class
