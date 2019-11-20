package com.example.firstassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    TextView txtCheckIn, txtCheckOut,txtRoom,txtDays,txtTotal,txtTotalVat,txtTotalSC;
    EditText etRoom;
    Button btnCalculate;
    boolean checkIn = false;
    boolean checkOut = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Location Spinner Part

        final Spinner cityDropDown = findViewById(R.id.spnCity);
        String location [] = {"Select City","Kathmandu","Bhaktapur","Dharan" , "Pokhara" , "Chitwan"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,location);
        cityDropDown.setAdapter(adapter);

//         Room Spinner Part


        final Spinner roomDropDown = findViewById(R.id.spnHotelType);
        String room [] = {"Select Room Type","Delux","Queen", "Master Suite"};
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,room);
        roomDropDown.setAdapter(adapter1);

        txtTotal=findViewById(R.id.txtTotal);
        txtTotalVat=findViewById(R.id.txtTotalVat);
        txtTotalSC=findViewById(R.id.txtTotalSC);
        etRoom=findViewById(R.id.etRoom);
        txtDays=findViewById(R.id.txtDays);
        btnCalculate=findViewById(R.id.btnCalculate);
        txtCheckIn=findViewById(R.id.txtCheckIn);
        txtCheckOut=findViewById(R.id.txtCheckOut);
        txtRoom=findViewById(R.id.txtRoomCost);



//        Check In Part Calling function for display calendar as well as validation part

        txtCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIn = true;
                checkOut = false;
                loadDatePicker();
            }
        });

//        Check Out Part

        txtCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIn = false;
                checkOut = true;
                loadDatePicker();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (roomDropDown.getSelectedItem().toString() == "Select Room Type"){
                    Toast.makeText(getApplicationContext(), "Select Room", Toast.LENGTH_LONG).show();
                    return;
                }

                if (roomDropDown.getSelectedItem().toString() == "Delux" ){
                    txtRoom.setText("2000");
                } else if(roomDropDown.getSelectedItem().toString() == "Queen") {
                    txtRoom.setText("3000");
                } else  {
                    txtRoom.setText("4000");
                }


//              Calculation part with result display

                txtDays.setText(etRoom.getText().toString()+" * "+ txtRoom.getText().toString());
                int total=Integer.parseInt(etRoom.getText().toString())* Integer.parseInt(txtRoom.getText().toString());
                int withvat=total + ((13*total)/100);
                txtTotal.setText(total+"");
                txtTotalVat.setText(withvat+ "");
                txtTotalSC.setText(withvat + ((10*withvat)/100)+ "");


            }
        });
            }

//            Function created for the date selection

    private void loadDatePicker(){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,year,month,day);
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year,int month, int dayOfMonth){
        String date = "Month/Day/Year: " + month + "/" + dayOfMonth + "/" + year;


        if(checkIn){
            txtCheckIn.setText(date);
            checkIn=false;
        }
        else{
            txtCheckOut.setText(date);
            checkOut=false;
        }


    }




}
