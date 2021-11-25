package com.example.gitbug.ViewModal.CommentViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitbug.Repository.BugRepository
import com.example.gitbug.Repository.CommentRepository


class CommentViewModelFactory constructor(private val repository: CommentRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            CommentViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}