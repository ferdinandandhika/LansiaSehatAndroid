package com.example.lansiasehat.ui.rencana

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.lansiasehat.R
import com.example.lansiasehat.db.TodoDatabase
import com.example.lansiasehat.model.Todo
import com.example.lansiasehat.receiver.NotificationReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RencanaActivity : AppCompatActivity() {
    private lateinit var db: TodoDatabase
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var categorySpinner: Spinner
    private var todoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo-database"
        ).build()

        dateEditText = findViewById(R.id.dateEdt)
        timeEditText = findViewById(R.id.timeEdt)
        categorySpinner = findViewById(R.id.spinnerCategory)

        dateEditText.setOnClickListener { showDatePickerDialog() }
        timeEditText.setOnClickListener { showTimePickerDialog() }

        // Set up the spinner with the category array
        ArrayAdapter.createFromResource(
            this,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        // Check if we are updating an existing task
        val todo = intent.getParcelableExtra<Todo>("todo")
        if (todo != null) {
            todoId = todo.id
            findViewById<EditText>(R.id.titleInpLay).setText(todo.title)
            findViewById<EditText>(R.id.taskInpLay).setText(todo.description)
            dateEditText.setText(todo.date)
            timeEditText.setText(todo.time)
            val categoryPosition = (categorySpinner.adapter as ArrayAdapter<String>).getPosition(todo.category)
            categorySpinner.setSelection(categoryPosition)
        }

        findViewById<View>(R.id.saveBtn).setOnClickListener {
            saveTask()
        }

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            dateEditText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            timeEditText.setText(selectedTime)
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun setAlarm(todo: Todo) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationReceiver::class.java).apply {
            putExtra("todo", todo)
        }
        val pendingIntent = PendingIntent.getBroadcast(this, todo.id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.YEAR, todo.date.split("/")[2].toInt())
            set(Calendar.MONTH, todo.date.split("/")[1].toInt() - 1)
            set(Calendar.DAY_OF_MONTH, todo.date.split("/")[0].toInt())
            set(Calendar.HOUR_OF_DAY, todo.time.split(":")[0].toInt())
            set(Calendar.MINUTE, todo.time.split(":")[1].toInt())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            } else {
                // Tampilkan pesan atau minta izin dari pengguna
            }
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }

    private fun saveTask() {
        val title = findViewById<EditText>(R.id.titleInpLay).text.toString()
        val description = findViewById<EditText>(R.id.taskInpLay).text.toString()
        val date = dateEditText.text.toString()
        val time = timeEditText.text.toString()
        val category = categorySpinner.selectedItem.toString()

        val todo = Todo(
            id = todoId ?: 0,
            title = title,
            description = description,
            date = date,
            time = time,
            category = category
        )

        GlobalScope.launch(Dispatchers.IO) {
            if (todoId == null) {
                db.todoDao().insert(todo)
            } else {
                db.todoDao().update(todo)
            }
            withContext(Dispatchers.Main) {
                setAlarm(todo)
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}