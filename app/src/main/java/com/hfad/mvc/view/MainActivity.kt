package com.hfad.mvc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hfad.mvc.adapter.AdapterUsers
import com.hfad.mvc.controller.IUsersController
import com.hfad.mvc.controller.UsersController
import com.hfad.mvc.databinding.ActivityMainBinding
import com.hfad.mvc.model.Users

class MainActivity : AppCompatActivity(), IUsersVew {

    private var usersController: IUsersController? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersController = UsersController(this)
        (usersController as UsersController).onUsersList()
    }

    override fun onSuccessList(users: ArrayList<Users>) {
        val adapterUsers = AdapterUsers(users)
        binding.recyclerView.adapter = adapterUsers
    }

    override fun progress(show: Boolean) {
        if (show) {
            binding.layoutNotConnection.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.layoutNotConnection.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun error(errMessage: String) {
        binding.layoutNotConnection.visibility = View.VISIBLE
        binding.btnClickReply.setOnClickListener {
            (usersController as UsersController).onUsersList()
        }
    }
}