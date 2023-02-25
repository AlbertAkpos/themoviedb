package com.alberto.themoviedb.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.themoviedb.data.interfaces.IRepository
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.helper.ErrorHandler
import com.alberto.themoviedb.helper.Event
import com.alberto.themoviedb.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieVM @Inject constructor (private val repository: IRepository) : ViewModel() {

    var currentPage = 0

    val movieState = MutableLiveData<Event<ResultState<List<Domain.Movie>>>>()


    fun fetchMovies() {
        currentPage++
        val handler = CoroutineExceptionHandler { _, throwable ->
            //When there is exception, reduce the current page
            currentPage--
            throwable.printStackTrace()
            val message = ErrorHandler.handleException(throwable)
            movieState.postValue(Event(ResultState.Error(message)))
        }

        viewModelScope.launch(handler) {
            movieState.postValue(Event(ResultState.Loading()))
            val response = repository.fetchMovies(currentPage)
            movieState.postValue(Event(ResultState.Success(response)))
        }
    }
}