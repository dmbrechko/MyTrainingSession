package com.example.mytrainingsession

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytrainingsession.databinding.ActivityExerciseBinding
import kotlin.system.exitProcess

class ExerciseActivity : AppCompatActivity() {
    private val viewModel by viewModels<ExercisesViewModel>()
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exercise: Exercise
    private lateinit var timer: CountDownTimer
    private var isDone = false
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        init(intent)

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        init(intent)
    }

    fun init(intent: Intent) {
        isDone = false
        currentIndex = intent.getIntExtra(KEY_INDEX, 0)
        exercise = viewModel.getExercise(currentIndex)
        binding.apply {
            timer = object : CountDownTimer(exercise.duration * SECOND, SECOND) {
                override fun onTick(millisUntilFinish: Long) {
                    val minutes = (millisUntilFinish / SECOND) / MINUTE
                    val seconds = (millisUntilFinish / SECOND) % MINUTE
                    timerTV.text = String.format(getString(R.string.timer), minutes, seconds)
                }

                override fun onFinish() {
                    isDone = true
                    if (viewModel.isOutOfBounds(currentIndex + 1)) {
                        startBTN.isEnabled = false
                        startBTN.text = getString(R.string.last_done)
                    } else {
                        startBTN.isEnabled = true
                        startBTN.text = getString(R.string.go_next)
                    }
                }
            }
            nameTV.text = exercise.name
            gifGIV.setImageResource(exercise.gifResourceId)
            descriptionTV.text = exercise.description
            timerTV.text = getString(R.string.time_left_0)
            startBTN.isEnabled = true
            startBTN.text = getString(R.string.start_exercise)
            startBTN.setOnClickListener {
                if (isDone) {
                    val intent = Intent(this@ExerciseActivity, ExerciseActivity::class.java).apply {
                        putExtra(KEY_INDEX, currentIndex + 1)
                    }
                    startActivity(intent)
                } else {
                    timer.start()
                    startBTN.text = getString(R.string.training)
                    startBTN.isEnabled = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_exit -> {
                finish()
                exitProcess(0)
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val KEY_INDEX = "key index"
        const val MINUTE = 60
        const val SECOND = 1000L
    }
}