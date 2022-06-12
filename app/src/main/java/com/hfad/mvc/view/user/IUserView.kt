package com.hfad.mvc.view.user

import com.hfad.mvc.model.User

interface IUserView {
    fun successUser(user: User)
    fun progress(show:Boolean)
    fun error()
}