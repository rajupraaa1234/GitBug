package com.example.gitbug.UI.HomeScreen.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitbug.Adapter.RecyclerViewAdapter
import com.example.gitbug.ApiService.ApiServices
import com.example.gitbug.CallBack.BugListItemCallListner
import com.example.gitbug.R
import com.example.gitbug.Repository.BugRepository
import com.example.gitbug.ViewModal.BugViewModel
import com.example.gitbug.ViewModal.BugViewModelFactory

class HomeActivity : AppCompatActivity(),BugListItemCallListner {
    lateinit var recyclerView: RecyclerView
    private val TAG = "MainActivity"
    private val retrofitService = ApiServices.getInstance()
    lateinit var viewModel: BugViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        FetchDataFromServerInOtherWay()
    }

    private fun init() {
        recyclerView = findViewById<RecyclerView>(R.id.bugListRecycler)
        viewModel =
            ViewModelProvider(this, BugViewModelFactory(BugRepository(retrofitService))).get(
                BugViewModel::class.java
            )
    }

    private fun FetchDataFromServerInOtherWay() {
        viewModel.bugList.observe(this, Observer {
            val recyclerViewAdapter = RecyclerViewAdapter(this, it, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "Error -----------------------> ")
        })
        viewModel.getAllIssueList()
    }

    override fun onClick(id: Int, title: String, body: String, CommentNumber: Int) {
        val intent = Intent(this, CommentListActivity::class.java)
        intent.putExtra("CommentId",id)
        intent.putExtra("title",title)
        intent.putExtra("body",body)
        intent.putExtra("CNum",CommentNumber)
        startActivity(intent)
    }
}

