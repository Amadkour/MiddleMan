package com.example.middleman.viewModel

import androidx.lifecycle.ViewModel
import com.example.emitter.accessLayer.model.Address
import com.example.emitter.accessLayer.model.Company
import com.example.emitter.accessLayer.model.Geo
import com.example.emitter.accessLayer.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private var _mutablelivedata = MutableStateFlow<User>(
        User(
            -1, "Dummy", "Dummy", "Dummy@dummy.com",
            Address("dummy", "dummy", "dummy", Geo("", "")), "", Company("", "", "")
        )
    )
    val user: StateFlow<User> = _mutablelivedata.asStateFlow()
    fun setUser( user:User){
        _mutablelivedata.value=user
    }

}