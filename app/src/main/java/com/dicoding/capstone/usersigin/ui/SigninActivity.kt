package com.dicoding.capstone.usersigin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.databinding.ActivitySigninBinding
import com.dicoding.capstone.ui.MainActivity
import com.dicoding.capstone.usersigin.viewmodel.ViewModelFactory

class SigninActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySigninBinding
    private lateinit var viewModel: SigninViewModel
    var isExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this,factory)[SigninViewModel::class.java]

        binding.signInBtn.setOnClickListener {
            if (validation()) {
                viewModel.getUser().observe(this, { t ->
                    for (i in t.indices) {
                        if (t[i].email == binding.edtEmail.text.toString()) {
                            binding.edtEmail.setText("")
                            if (t[i].password == binding.edtPassword.text.toString()) {
                                binding.edtPassword.setText("")
                                val intent = Intent(this@SigninActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@SigninActivity,
                                    " Password is Incorrect ",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            isExist = true
                            break
                        } else {
                            isExist = false
                        }
                    }
                    if (!isExist) {
                        Toast.makeText(this@SigninActivity, " User Not Registered ", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        binding.clickToSigUp.setOnClickListener {
            val intent = Intent(this@SigninActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }


    private fun validation(): Boolean {

        if (binding.edtEmail.text.isNullOrEmpty()) {
            Toast.makeText(this@SigninActivity, " Enter Email ", Toast.LENGTH_LONG).show()
            return false
        }

        if (binding.edtPassword.text.isNullOrEmpty()) {
            Toast.makeText(this@SigninActivity, " Enter Password ", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}