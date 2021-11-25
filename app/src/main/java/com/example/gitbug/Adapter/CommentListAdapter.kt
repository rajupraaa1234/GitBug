package com.example.gitbug.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitbug.R

import com.example.gitbug.Response.CommentResponse
import com.example.gitbug.Utility.util.extractDate
import com.example.gitbug.Utility.util.getDaysBetweenDates
import com.example.gitbug.Utility.util.getIntervalInWord
import com.example.gitbug.Utility.util.getTodayDate


class CommentListAdapter(var context: Context, var arr: List<CommentResponse>) :
    RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {
    // Binds the given View to the position. The View can be a View previously retrieved via onCreateViewHolder it whould be iterate for all item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.comment_layout, parent, false)
        return ViewHolder(view)
    }

    //will be used to display items of the adapter using onBindViewHolder
    @SuppressLint("LongLogTag")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = arr[position]
        if(user.body==null){
            holder.body.text = ""
        }else{
            holder.body.text = user.body
        }
        var name : String = ""
        if(user!=null && user.user.login!=null){
            name = user.user.login;
        }
        if(user.created_at!=null){
            var date : String = extractDate(user.created_at.toString().trim())
            var currDate : String  = getTodayDate()
            var day : Long = getDaysBetweenDates(date,currDate)
            name = name + " commented " + getIntervalInWord(day) + " ago";
        }

        holder.Username.text = name
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    // it wholud be initialize the layout views so that we can access easly in onBindViewHolder method
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Username: TextView
        var body: TextView
        init {
            Username = itemView.findViewById(R.id.CuserName)
            body = itemView.findViewById(R.id.Cbody)
        }
    }
}