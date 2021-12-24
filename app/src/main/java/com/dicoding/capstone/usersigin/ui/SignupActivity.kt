package com.dicoding.capstone.usersigin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivitySignupBinding
import com.dicoding.capstone.usersigin.data.User
import com.dicoding.capstone.usersigin.viewmodel.ViewModelFactory

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SigninViewModel
    var isExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[SigninViewModel::class.java]

        binding.btnSignUp.setOnClickListener {
            if (validation()) {
                viewModel.getUser().observe(this, { t ->
                    for (i in t.indices) {
                        if ((t[i].email == binding.edtEmail.text.toString())) {
                            isExist = true
                            break
                        } else {
                            isExist = false
                            continue
                        }
                    }

                    if (isExist) {
                        Toast.makeText(
                            this@SignupActivity,
                            " User Already Registered !!! ",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val name = binding.edtName.text.toString()
                        val address = binding.edtAddress.text.toString()
                        val noTelephone = binding.edtNoTelephone.text.toString()
                        val email = binding.edtEmail.text.toString()
                        val password = binding.edtPassword.text.toString()
                        val user = User(0, name, address, noTelephone, email, password)
                        viewModel.insert(user)
                        Toast.makeText(
                            this@SignupActivity,
                            " User  Registered Successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
                        startActivity(intent)
                    }
                })
            }
        }
        binding.backToSignIn.setOnClickListener {
            val intent = Intent(this@SignupActivity, SigninActivity::class.java)
            startActivity(intent)
        }


    }

    private fun validation(): Boolean {
        if (binding.edtName.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Name ", Toast.LENGTH_LONG).show()
            return false
        }

        if (binding.edtAddress.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Address ", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.edtNoTelephone.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Mobile Number ", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.edtEmail.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Email ", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.edtPassword.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Password ", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}