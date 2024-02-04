package com.example.homework1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    //for range of operands
    private val lowerBound = 10;
    private val upperBound = 100
    //track score
    private var score = 0;
    private var questionsAsked = 0;

    private var feedback = ""
    private var correctStreak = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createEquation();
    }


    fun resetGame(view: View) {
        createEquation();
        score = 0
        correctStreak = 0
        questionsAsked = 0
        findViewById<TextView>(R.id.feedback).text = ""
        findViewById<TextView>(R.id.score).text = "Score: $score/$questionsAsked"
    }

    fun checkCorrect(view: View) {
        val op1 = findViewById<TextView>(R.id.operand1).text.toString().toInt()
        val op2 = findViewById<TextView>(R.id.operand2).text.toString().toInt()
        val answer = findViewById<TextView>(R.id.answer).text.toString().toInt()

        //println(op1);
        //println(R.id.answer.toString().toInt())
        if (view.id == R.id.btnPlus && (op1 + op2 == answer) ||
            view.id == R.id.btnMinus && (op1 - op2 == answer) ||
            view.id == R.id.btnMult && (op1 * op2 == answer) ||
            view.id == R.id.btnDivide && (op1 / op2 == answer)) {
            score++;
            correctStreak++
            feedback = "Correct!"
            findViewById<TextView>(R.id.feedback).setTextColor(Color.parseColor("#00FF00"));

        } else {
            //val myTextView = findViewById<TextView>(R.id.text_view_id)
            //myTextView.setTextColor(Color.parseColor("#FF0000"))
            findViewById<TextView>(R.id.feedback).setTextColor(Color.parseColor("#FF0000"));
            correctStreak = 0


        }
        findViewById<TextView>(R.id.feedback).text = "$feedback"
        println(feedback)
        if (correctStreak == 3) {
            Toast.makeText(this, "Sheeeeesh, you kinda smart.", Toast.LENGTH_SHORT).show()
            correctStreak = 0
        }
        questionsAsked++;
        findViewById<TextView>(R.id.score).text = "Score: $score/$questionsAsked"
        createEquation();
    }

    private fun createEquation() {

        var op1 = getRandomNumber(lowerBound + 1, upperBound);
        var op2 = getRandomNumber(lowerBound, op1-1);


        var answerText = findViewById<TextView>(R.id.answer)
        var operation = getRandomNumber(1,4);
        when(operation) {
            1 -> {
                answerText.text = "${op1 + op2}"
                feedback = "Wrong: $op1 + $op2 = ${op1+op2}"
            }
            2 -> {
                answerText.text = "${op1 - op2}"
                feedback = "Wrong: $op1 - $op2 = ${op1-op2}"
            }
            3 -> {
                answerText.text = "${op1 * op2}"
                feedback = "Wrong: $op1 * $op2 = ${op1*op2}"
            }
            4 -> {
                op2 = pickDivisor(op1);
                println("op2 = " + op2);
                answerText.text = "${op1 / op2}"
                feedback = "Wrong: $op1 / $op2 = ${op1/op2}"
            }

        }
        //set the operand text after in case division was picked
        findViewById<TextView>(R.id.operand1).text = "$op1"
        findViewById<TextView>(R.id.operand2).text = "$op2"

    }

    //return a random number with range [min, max]
    private fun getRandomNumber(min: Int, max: Int): Int {
        return Random().nextInt(max-min+1) + min;
    }
    //need method to get all possible divisors of op1, then pick a random one
    private fun pickDivisor(x: Int): Int {
        var divisors = emptyArray<Int>(); //https://stackoverflow.com/questions/29743160/how-to-create-an-empty-array-in-kotlin
        for(i in 1..x){ //https://kotlinlang.org/docs/control-flow.html i didnt know syntax
            if(x % i ==0) { //https://kotlinandroid.org/kotlin/kotlin-add-element-to-array/#:~:text=To%20add%20an%20element%20to,and%20element%20as%20right%20operand.
                divisors += i;
            }
        }
        println(divisors[divisors.size-1])
        val divisorToPick = getRandomNumber(0,divisors.size-1);

        return divisors[divisorToPick];
    }
}