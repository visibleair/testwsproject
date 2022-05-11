package com.example.myapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.myapp.R
import com.example.myapp.User
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.databinding.ActivitySignInBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

private lateinit var binding: ActivitySignInBinding
private lateinit var request: Request
private var client = OkHttpClient()
private lateinit var gson: Gson
private lateinit var formBody: FormBody

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var validateEmail = Regex("[a-z0-9]@[a-z0-9]{4,10}+\\.+[a-z]{1,3}")

        binding = ActivitySignInBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var url = "https://food.madskill.ru/auth/login"
        var email = "vasya@mail.com"
        var password = "qwerty"

        formBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()
        request = Request.Builder().url(url).post(formBody).build()
        binding.buttoninput.setOnClickListener{

            if (validateEmail.find(binding.emailText.text.toString()) != null){
                client.newCall(request).enqueue(object: Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Toast.makeText(this@SignIn, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        gsonBuilder(response.body!!.string())
                    }

                })
                startActivity(Intent(this, MainActivity::class.java))
            }

        }
        gson = Gson()
    }

    private lateinit var token: Array <User>
    private fun gsonBuilder(str: String){
        var userId = gson.fromJson(str, User::class.java)
        Log.i("tag1", "onResponse: ${userId.token}")
    }
}