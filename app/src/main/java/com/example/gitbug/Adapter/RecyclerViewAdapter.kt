package com.example.gitbug.Adapter

import android.content.Context
import com.example.gitbug.Response.BugResponse
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.gitbug.R
import com.example.gitbug.Utility.util
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.gitbug.CallBack.BugListItemCallListner
import com.github.siyamed.shapeimageview.CircularImageView

class RecyclerViewAdapter(var context: Context, var arr: List<BugResponse>,var itemClickListener: BugListItemCallListner) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    // Binds the given View to the position. The View can be a View previously retrieved via onCreateViewHolder it whould be iterate for all item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.bugitem, parent, false)
        return ViewHolder(view)
    }

    //will be used to display items of the adapter using onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = arr[position]
        if (user != null) {
            holder.Username.text = user.user.login
            holder.Upadate_date.text = util.extractDate(user.updated_at)
            if (user.body != null) {
                var desc : String = user.body.trim();
                if(desc.length>=200){
                    desc = desc.substring(0,200) + "...";
                }
                holder.description.text = desc
            } else {
                holder.description.visibility = View.GONE
                holder.textNotAvailable.visibility = View.VISIBLE
            }
            Glide.with(context)
                .load(user.user.avatar_url)
                .into(holder.imageView)
            holder.Btitle.text = user.title


            holder.itemLin.setOnClickListener { itemClickListener.onClick(
                if(user.number==null) -1 else user.number,
                if(user.title==null) "" else user.title,
                if(user.body==null) "" else user.body,
                if(user.comments==null) -1 else user.comments)
            }
            //holder.itemLin.setOnClickListener { itemClickListener.onClick(user?.number,user?.title,if(user.body.isEmpty()) "" else user.body,user?.comments) }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    // it wholud be initialize the layout views so that we can access easly in onBindViewHolder method
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Username: TextView
        var Upadate_date: TextView
        var description: TextView
        var imageView: CircularImageView
        var textNotAvailable: TextView
        var Btitle : TextView
        var itemLin : LinearLayout

        init {
            Username = itemView.findViewById(R.id.username)
            Upadate_date = itemView.findViewById(R.id.Udate)
            description = itemView.findViewById(R.id.desc)
            imageView = itemView.findViewById(R.id.Uimage)
            textNotAvailable = itemView.findViewById(R.id.decNotAvailable)
            Btitle = itemView.findViewById(R.id.Btitle)
            itemLin = itemView.findViewById(R.id.ItemLin)
        }
    }
}