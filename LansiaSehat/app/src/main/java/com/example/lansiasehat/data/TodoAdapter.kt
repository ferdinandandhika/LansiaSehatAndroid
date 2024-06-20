package com.example.lansiasehat.ui.rencana

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R
import com.example.lansiasehat.model.Todo

class TodoAdapter(
    private val onDeleteClick: (Todo) -> Unit,
    private val onEditClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos: List<Todo> = listOf()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.todoTitle)
        val description: TextView = itemView.findViewById(R.id.todoDescription)
        val date: TextView = itemView.findViewById(R.id.todoDate)
        val time: TextView = itemView.findViewById(R.id.todoTime)
        val category: TextView = itemView.findViewById(R.id.todoCategory)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val editButton: View = itemView.findViewById(R.id.editButton)
        val gambarKategori: ImageView = itemView.findViewById(R.id.gambarKategori)
        val itemLayout: RelativeLayout = itemView.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.title.text = todo.title
        holder.description.text = todo.description
        holder.date.text = todo.date
        holder.time.text = todo.time
        holder.category.text = todo.category

        // Set image based on category
        val context = holder.itemView.context
        val images = context.resources.obtainTypedArray(R.array.gambarcategory_array)
        val categoryArray = context.resources.getStringArray(R.array.category_array)
        val categoryIndex = categoryArray.indexOf(todo.category)
        if (categoryIndex >= 0) {
            holder.gambarKategori.setImageResource(images.getResourceId(categoryIndex, -1))
        }
        images.recycle()

        holder.deleteButton.setOnClickListener {
            onDeleteClick(todo)
        }

        holder.editButton.setOnClickListener {
            onEditClick(todo)
        }

        holder.itemLayout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("todo", todo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }
}