package com.example.aib_gpopud_requestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUser : AppCompatActivity() {

    lateinit var UserName: EditText
    lateinit var UserLocation: EditText
    lateinit var UserID:EditText
    lateinit var SaveBTN: Button
    lateinit var ViewBTN: Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        UserName = findViewById(R.id.edt3name)
        UserLocation = findViewById(R.id.edt3loc)
        UserID=findViewById(R.id.edt3id)
        SaveBTN = findViewById(R.id.ffadd3btn)
        ViewBTN=findViewById(R.id.ff3btnv)


        SaveBTN.setOnClickListener{

            if(UserLocation.text.isNotBlank()&&UserName.text.isNotBlank())
            {

                var addinguser = Users.UserDetails(UserName.text.toString(), UserLocation.text.toString(),UserID.text.toString().toInt())

                addSingleuser(addinguser, onResult =
                {
                    UserName.setText("")
                    UserLocation.setText("")
                    UserID.setText(0)
                    Toast.makeText(applicationContext, "The User Save Success!", Toast.LENGTH_SHORT).show();
                })

            }
            else
            {
                Toast.makeText(applicationContext, "Enter User Information", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun addSingleuser(addinguser: Users.UserDetails, onResult: () -> Unit)
    {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


        if (apiInterface != null)
        {
            apiInterface.addUser(addinguser).enqueue(object : Callback<Users.UserDetails>
            {
                override fun onResponse(
                    call: Call<Users.UserDetails>,
                    response: Response<Users.UserDetails>
                ) {

                    onResult()

                }

                override fun onFailure(call: Call<Users.UserDetails>, t: Throwable)
                {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();


                }
            })
        }
    }


    fun viewusers(view: android.view.View)
    {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}