package com.example.middleman

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.middleman.MainActivity.Companion.context
import com.example.middleman.viewModel.UserViewModel
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

class Client(address: String, port: Int) {
    private val connection: Socket = Socket(address, port)
    private var reader:Scanner=  Scanner(connection.getInputStream())
    private val writer= connection.getOutputStream()
    private var connected: Boolean = true

    init {
        println("Connected to server at $address on port $port")
    }


    fun run(userString: String) {
        println("=====================( New Run )=============")

        write(userString)
        read()
        print("close Client")
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun read() {
        var calback = ""
        while (connected) {
            calback = reader.nextLine()
            println(calback)

//            Toast.makeText(MainActivity.context, calback, Toast.LENGTH_LONG).show()
            if (calback == "OK") {
//                connection.close()
//                val middleManIntent = Intent()
//                middleManIntent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
//                middleManIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                middleManIntent.action = "android.intent.action.VIEW"
//                middleManIntent.component =
//                    ComponentName.unflattenFromString("com.example.receiver/com.example.receiver.MainActivity")
//                context?.startActivity(middleManIntent)
            }

        }
    }
}