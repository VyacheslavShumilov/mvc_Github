package com.hfad.mvc.view.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.mvc.R
import com.hfad.mvc.controller.user.IUserController
import com.hfad.mvc.controller.user.UserController
import com.hfad.mvc.model.User

class UserActivity : AppCompatActivity(), IUserView {

    private var login: String = ""

    private var userController : IUserController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val args = intent.extras
        login = args?.get("LOGIN").toString()

        userController = UserController(this)
        (userController as UserController).onUser(login)

    }

    override fun successUser(user: User) {

    }

    override fun progress(show: Boolean) {

    }

    override fun error() {

    }
}