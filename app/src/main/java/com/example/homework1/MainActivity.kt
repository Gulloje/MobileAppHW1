package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    private val lowerBound = 10;
    private val upperBound = 100;
    private var score = 0;
    private var questionsAsked = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createEquation();
    }


    fun resetGame(view: View) {
        createEquation();
    }

    fun checkCorrect(view: View) {
        val op1 = findViewById<TextView>(R.id.operand1).text.toString().toInt()
        val op2 = findViewById<TextView>(R.id.operand2).text.toString().toInt()
        val answer = findViewById<TextView>(R.id.answer).text.toString().toInt()
        //println(op1);
        //println(R.id.answer.toString().toInt())
        if (view.id == R.id.btnPlus && (op1 + op2 == answer) ||
            view.id == R.id.btnMinus && (op1 - op2 == answer) ||
            view.id == R.id.btnMult && (op1 * op2 == answer)) {
            score++;
            //println("correct");
        } else {
            //println("incorrect");

        }
        questionsAsked++;
        findViewById<TextView>(R.id.score).text = "Score: $score/$questionsAsked"
        createEquation();
    }

    private fun createEquation() {

        var op1 = getRandomNumber(lowerBound + 1, upperBound);
        var op2 = getRandomNumber(lowerBound, op1-1);

        findViewById<TextView>(R.id.operand1).text = "$op1"
        findViewById<TextView>(R.id.operand2).text = "$op2"
        var operation = getRandomNumber(1,3);
        when(operation) {
            1 -> findViewById<TextView>(R.id.answer).text = "${op1 + op2}"
            2 -> findViewById<TextView>(R.id.answer).text = "${op1 - op2}"
            3 -> findViewById<TextView>(R.id.answer).text = "${op1 * op2}"
        }

    }

    //return a random number with range [min, max]
    private fun getRandomNumber(min: Int, max: Int): Int {
        return Random().nextInt(max-min+1) + min;
    }
}