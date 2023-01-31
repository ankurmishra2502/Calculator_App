package com.example.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var textView: TextView?= null
    var lastnumeric : Boolean =false
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView= findViewById(R.id.ans)
    }
    fun onDigit(view: View){
        textView?.append((view as Button).text)
        lastnumeric=true
    }
    fun onClear(view: View){
        textView?.text=""
    }
    fun onDecimalpoint(view: View){
        if(lastnumeric && !lastDot){
            textView?.append(".")
            lastnumeric=false
            lastDot=false
        }
    }
    fun onEqual(view:View){
        try {
            if(lastnumeric) {
                var textValue = textView?.text.toString()
                var prefix = ""
                if (textValue.startsWith("/") || textValue.startsWith("*")) {
                    textView?.text = "Invalid Operation"


                }else if(textValue.contains("Invalid Operation")){
                    textView?.text=""
                }
                else {
                    if (textValue.startsWith("-")) {
                        prefix = "-"
                        textValue = textValue.substring(1)
                    }
                    if (textValue.contains("-")) {
                        val splitval = textValue.split("-")
                        var one = splitval[0]
                        var two = splitval[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textView?.text = remove((one.toDouble() - two.toDouble()).toString())
                    } else if (textValue.contains("+")) {
                        val splitval = textValue.split("+")
                        var one = splitval[0]
                        var two = splitval[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textView?.text = remove((one.toDouble() + two.toDouble()).toString())
                    } else if (textValue.contains("*")) {
                        val splitval = textValue.split("*")
                        var one = splitval[0]
                        var two = splitval[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textView?.text = remove((one.toDouble() * two.toDouble()).toString())
                    } else if (textValue.contains("/")) {
                        val splitval = textValue.split("/")
                        var one = splitval[0]
                        var two = splitval[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        textView?.text = remove((one.toDouble() / two.toDouble()).toString())
                    }
                }
            }

        }
        catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
    private fun remove(result:String):String{
        var value= result
        if(result.contains(".0"))
             value =result.substring(0,result.length-2)

        return value

    }
    fun onOperator(view:View){
        textView?.text?.let {
            if(lastnumeric && !isOperatorAdded(it.toString())){
                textView?.append((view as Button).text)
                lastnumeric=false
                lastDot=false
            }
        }
    }
    private fun isOperatorAdded(value: String) :Boolean{
        return  if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||
                    value.contains("+")||
                    value.contains("-") ||
                    value.contains("*")
        }
    }
}