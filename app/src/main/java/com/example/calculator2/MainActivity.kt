package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    var lastnumeric : Boolean = false
    var lastdot  : Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.tvinput)
    }
    fun ondigit(view: View){
//        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        tvinput?.append((view as Button).text)

        lastnumeric = true
    }

    fun onoperator(view: View){
        tvinput?.text?.let {

            if(lastnumeric && !isoperatoradded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }
        }
    }

    fun onclear(view: View){
        tvinput?.text = " "
    }

    fun ondecimal(view: View){
        if (lastnumeric && !lastdot){
            tvinput?.append(".")
            lastdot = true
            lastnumeric = false
        }
    }

    fun isequal(view: View){
        if (lastnumeric){
            var tvValue = tvinput?.text.toString()
            var prefix =""
            try {

                if(tvValue.startsWith("-")){
                    prefix ="-"
                  tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvinput?.text = remove((one.toDouble() - two.toDouble()).toString())
                    }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = remove((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = remove((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = remove((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    private fun remove(result: String): String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0 , result.length-2)
        }
        return value
    }

    fun isoperatoradded(value: String) : Boolean{
        return if(  value.startsWith("-")){
            false
        }else{
            value.contains("*") ||
                    value.contains("/") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}


