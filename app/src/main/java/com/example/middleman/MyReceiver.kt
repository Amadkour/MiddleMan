package com.example.middleman

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.emitter.accessLayer.model.User
import com.example.middleman.viewModel.UserViewModel
import com.google.gson.Gson


class MyReceiver : BroadcastReceiver() {
    val address = "localhost"
    val port = 9999
    private val userModel= UserViewModel()

    companion object{
        private var client: Client? = null
    }


    override fun onReceive(context: Context, intent: Intent) {

        if (intent.extras != null) {
            val user: User? = intent.getParcelableExtra<User>("item")
//            Toast.makeText(context, user?.email, Toast.LENGTH_LONG).show()
            println("-------------------(Client )------------------")

            if (user != null) {
                println()
                println()
                println()
                println("=======================(update Variable )======================")
                println()
                println()
                println()
                userModel.setUser(user)
            }
            val middleManIntent = Intent()
            //---------------------(send data item to MiddleMan App)-----------//

            middleManIntent.putExtra(
                "item",user)
            middleManIntent.action = "com.madkour.middleMan"
            context.sendBroadcast(middleManIntent)
            //---------------------(pop MiddleMan app to  front)-----------//
//
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
               if(client==null) {
                   println("*******************************")
                   println("*******************************( New Client )*******************************")
                   println("*******************************")
                   client = Client(address, port)
               }
            try {
                val userString: String? =Gson().toJson(user)
                if (userString != null) {
                    client!!.run(userString=userString)
                }

            }catch(ex:Exception) {

            }

        }

    }

}