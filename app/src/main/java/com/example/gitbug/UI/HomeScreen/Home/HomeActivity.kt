package com.example.gitbug.UI.HomeScreen.Home

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitbug.Adapter.RecyclerViewAdapter
import com.example.gitbug.ApiService.ApiServices
import com.example.gitbug.CallBack.BugListItemCallListner
import com.example.gitbug.CallBack.CommonDialogListner
import com.example.gitbug.Dialog.CustomDialog
import com.example.gitbug.NetworkHandler.Network
import com.example.gitbug.R
import com.example.gitbug.Repository.BugRepository
import com.example.gitbug.ViewModal.BugViewModel
import com.example.gitbug.ViewModal.BugViewModelFactory
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity(),BugListItemCallListner, CommonDialogListner {
    lateinit var recyclerView: RecyclerView
    private val TAG = "MainActivity"
    private val retrofitService = ApiServices.getInstance()
    lateinit var viewModel: BugViewModel
    lateinit var progressBar: ProgressBar
    lateinit var customDialog : CustomDialog
    lateinit var noIssueFoundTxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        if(Network.isNetworkConnected(this)){
            FetchDataFromServerInOtherWay()
        }else{
            customDialog.setDailog()
            progressBar.visibility = View.GONE
        }
    }

    private fun init() {
        recyclerView = findViewById<RecyclerView>(R.id.bugListRecycler)
        progressBar = findViewById(R.id.progressBar)
        noIssueFoundTxt = findViewById(R.id.noIssuetxt)
        customDialog = CustomDialog(this)
        viewModel =
            ViewModelProvider(this, BugViewModelFactory(BugRepository(retrofitService))).get(
                BugViewModel::class.java
            )
    }

    private fun FetchDataFromServerInOtherWay() {
        progressBar.visibility = View.VISIBLE
        viewModel.bugList.observe(this, Observer {
            val recyclerViewAdapter = RecyclerViewAdapter(this, it, this)
            if(it==null || it.size==0){
                progressBar.visibility = View.GONE
                noIssueFoundTxt.visibility=View.VISIBLE
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "Error -----------------------> ")
        })
        viewModel.getAllIssueList()
        progressBar.visibility = View.GONE
    }

    override fun onClick(id: Int, title: String, body: String, CommentNumber: Int) {
        val intent = Intent(this, CommentListActivity::class.java)
        intent.putExtra("CommentId",id)
        intent.putExtra("title",title)
        intent.putExtra("body",body)
        intent.putExtra("CNum",CommentNumber)
        startActivity(intent)
    }



    override fun OnYesClickListner() {
        if(Network.isNetworkConnected(this)){
            FetchDataFromServerInOtherWay()
            customDialog.dialog.dismiss()
        }else{
            customDialog.dialog
            progressBar.visibility = View.GONE
        }
    }

    override fun OnNoClickListner() {
        exitProcess(-1)
    }
}

