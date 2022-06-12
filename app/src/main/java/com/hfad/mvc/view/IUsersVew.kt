package com.hfad.mvc.view

import com.hfad.mvc.model.Users

interface IUsersVew {
    fun onSuccessList(users:ArrayList<Users>)
    fun error(errMessage:String)
    fun progress(show:Boolean)
}