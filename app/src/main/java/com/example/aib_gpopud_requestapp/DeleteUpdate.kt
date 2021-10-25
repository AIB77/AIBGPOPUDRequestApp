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

class DeleteUpdate : AppCompatActivity() {

    lateinit var userid: EditText
    lateinit var name: EditText
    lateinit var location: EditText
    lateinit var deleteBTN: Button
    lateinit var updateBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_update)







        userid=findViewById(R.id.id)
        name=findViewById(R.id.du2edtname)
        location=findViewById(R.id.du2edtloc)
        deleteBTN=findViewById(R.id.button2delet)
        updateBTN=findViewById(R.id.button2update)


        updateBTN.setOnClickListener {

            var f = Users.UserDetails(name.text.toString(), location.text.toString(),userid.text.toString().toInt())
            updateusers(f, onResult =
            {
                name.setText("")
                location.setText("")
                //userid.setText("")
                Toast.makeText(applicationContext, "Update Success!", Toast.LENGTH_SHORT).show();
            })
        }

        deleteBTN.setOnClickListener {
            var f = Users.UserDetails(name.text.toString(), location.text.toString(),userid.text.toString().toInt())
            deleteusers (f, onResult =
            {
                name.setText("")
                location.setText("")
                userid.setText("")

                Toast.makeText(applicationContext, "Delete Success!", Toast.LENGTH_SHORT).show();
            })
        }

    }

    private fun deleteusers(f: Users.UserDetails, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteusers(userid.text.toString().toInt())?.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                Toast.makeText(applicationContext, "User Deleted", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateusers(f: Users.UserDetails, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.updateusers(userid)?.enqueue(object : retrofit2.Callback<Users.UserDetails> {
            override fun onResponse(call: retrofit2.Call<Users.UserDetails>, response: Response<Users.UserDetails>) {

                onResult()
                Toast.makeText(applicationContext, "User Updated", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: retrofit2.Call<Users.UserDetails>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
            }


        })




    }

    fun viewyserre(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}