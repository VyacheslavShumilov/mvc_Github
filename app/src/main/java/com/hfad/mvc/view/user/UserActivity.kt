package com.hfad.mvc.view.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hfad.mvc.controller.user.IUserController
import com.hfad.mvc.controller.user.UserController
import com.hfad.mvc.databinding.ActivityUserBinding
import com.hfad.mvc.model.User
import com.squareup.picasso.Picasso

class UserActivity : AppCompatActivity(), IUserView {

    private var login: String = ""
    private var userController: IUserController? = null
    private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("LOGIN").toString()

        userController = UserController(this)
        (userController as UserController).onUser(login)
    }

    override fun successUser(user: User) {
        binding.userIdTextView.text = user.id.toString()
        binding.userLogTextView.text = user.login
        binding.userUrlTextView.text = user.url
        Picasso.get().load(user.avatar_url).into(binding.userImageView)
    }

    override fun progress(show: Boolean) {
        if (show) {
            binding.itemConstraint.visibility = View.VISIBLE
            binding.layoutNoConnection.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.layoutNoConnection.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
        }

    }

    override fun error(errMessage: String) {
        binding.errorMessage.text = errMessage
        binding.itemConstraint.visibility = View.GONE
        binding.layoutNoConnection.visibility = View.VISIBLE
        binding.btnClickReply.setOnClickListener {
            (userController as UserController).onUser(login)
        }
    }
}