package com.example.lansiasehat.ui.rencana

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lansiasehat.databinding.FragmentRencanaBinding
import com.example.lansiasehat.db.TodoDatabase
import com.example.lansiasehat.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RencanaFragment : Fragment() {
    private lateinit var db: TodoDatabase
    private lateinit var adapter: TodoAdapter
    private var _binding: FragmentRencanaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRencanaBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = binding.recyclerViewAlarms
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TodoAdapter(
            onDeleteClick = { todo -> deleteTodo(todo) },
            onEditClick = { todo -> editTodo(todo) }
        )
        recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            val intent = Intent(activity, RencanaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TODO)
        }

        db = Room.databaseBuilder(
            requireContext(),
            TodoDatabase::class.java, "todo-database"
        ).build()

        loadTodos()

        return view
    }

    private fun loadTodos() {
        GlobalScope.launch(Dispatchers.IO) {
            val todos = db.todoDao().getAllTodos()
            withContext(Dispatchers.Main) {
                adapter.setTodos(todos)
                checkEmptyState()
            }
        }
    }

    private fun deleteTodo(todo: Todo) {
        showDeleteConfirmationDialog(todo)
    }

    private fun deleteTodoConfirmed(todo: Todo) {
        GlobalScope.launch(Dispatchers.IO) {
            db.todoDao().delete(todo)
            loadTodos()
        }
    }

    private fun showDeleteConfirmationDialog(todo: Todo) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Apakah anda yakin ingin menghapus rencana ini?")
            .setPositiveButton("Iya") { dialog, id ->
                deleteTodoConfirmed(todo)
            }
            .setNegativeButton("Tidak") { dialog, id ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun editTodo(todo: Todo) {
        val intent = Intent(activity, RencanaActivity::class.java)
        intent.putExtra("todo", todo)
        startActivityForResult(intent, REQUEST_CODE_EDIT_TODO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE_ADD_TODO || requestCode == REQUEST_CODE_EDIT_TODO) && resultCode == AppCompatActivity.RESULT_OK) {
            loadTodos()
        }
    }

    private fun checkEmptyState() {
        if (adapter.itemCount == 0) {
            binding.recyclerViewAlarms.visibility = View.GONE
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.recyclerViewAlarms.visibility = View.VISIBLE
            binding.emptyView.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_CODE_ADD_TODO = 1
        const val REQUEST_CODE_EDIT_TODO = 2
    }
}