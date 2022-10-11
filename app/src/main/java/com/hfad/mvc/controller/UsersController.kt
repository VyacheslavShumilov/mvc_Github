package com.hfad.mvc.controller

import com.hfad.mvc.model.Users
import com.hfad.mvc.services.Api
import com.hfad.mvc.view.IUsersVew
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersController(val usersView: IUsersVew) : IUsersController {

    private var api = Api.create()

    override fun onUsersList() {
        usersView.let { view ->
            view.progress(true)
            api.getUsers().enqueue(object : Callback<ArrayList<Users>> {
                override fun onResponse(call: Call<ArrayList<Users>>, response: Response<ArrayList<Users>>
                ) {
                    if (response.isSuccessful) {
                        view.progress(false)
                        response.body()?.let { data ->
                            view.onSuccessList(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                    view.progress(false)
                    view.error("Check internet connection")
                }
            })
        }
    }
}