package com.hfad.mvc.controller.user

import com.hfad.mvc.model.User
import com.hfad.mvc.services.Api
import com.hfad.mvc.view.user.IUserView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserController(val userView: IUserView): IUserController {

    private var api = Api.create()

    override fun onUser(login: String) {
        userView.let { view ->
            view.progress(true)
            api.getUser(login).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        view.progress(false)
                        response.body()?.let { data ->
                            view.successUser(data)
                        }
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    view.progress(false)
                    view.error("Проверьте подключение к интернету и повторите попытку")
                }
            })
        }
    }
}