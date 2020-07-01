package com.example.githubuserapi.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapi.UserItems
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.json.JSONObject

class MainViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<UserItems>>()

    fun setUser(users: String) {
        val listUsers = ArrayList<UserItems>()

        val url = "https://api.github.com/search/users?q=$users"

        Fuel.get(url)
            .header("Authorization", "1a6cfe6400a0305f3cfa98868c5235b6d8e5498a")
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val e = result.error
                    }
                    is Result.Success -> {
                        val data = result.get()
                        val responseObject = JSONObject(data)
                        val list = responseObject.getJSONArray("list")

                        for (i in 0 until list.length()) {
                            val user = list.getJSONObject(i)
                            val userItems = UserItems()
                            userItems.id = user.getInt("id")
                            userItems.username = user.getString("login")
                            userItems.avatar = user.getString("avatar_url")
                            userItems.organization = user.getString("organizations_url")
                            listUsers.add(userItems)
                        }
                    }
                }
            }
    }

    fun getUser(): LiveData<ArrayList<UserItems>> {
        return listUser
    }
}