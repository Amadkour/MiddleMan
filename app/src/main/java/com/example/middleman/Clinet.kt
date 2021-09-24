package com.example.middleman

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.middleman.MainActivity.Companion.context
import com.example.middleman.viewModel.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

class Client(address: String, port: Int) {
    private val connection: Socket = Socket(address, port)
    private var reader:Scanner=  Scanner(connection.getInputStream())
    private val writer= connection.getOutputStream()
    private var connected: Boolean = true
    private val intent = Intent()

    init {
        println("Connected to server at $address on port $port")
    }


    fun run(userString: String) {
        connected=true

            write(userString)
            read()


    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))

            println("-----------------Pop to the Receiver")

            popApp(intent,"receiver")



    }

    private fun read() {
        var status = ""
        while (connected) {
            status = reader.nextLine()
            println(status)
            connected=false
            thread {
                callback(intent,status)
            }.run()



        }

    }
    fun popApp(intent:Intent,app:String) {
        intent.flags =
            Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "android.intent.action.VIEW"
        intent.component =
            ComponentName.unflattenFromString("com.example.$app/com.example.$app.MainActivity")
        context?.startActivity(intent)
    }
    fun callback(EmitterIntent:Intent,status:String){
        EmitterIntent.putExtra("status", status)
        EmitterIntent.action = "com.madkour.middleMan"
        context?.sendBroadcast(EmitterIntent)
    }
}