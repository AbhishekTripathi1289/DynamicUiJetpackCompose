package com.codingwithmitch.daggerhiltplayground.util




sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception? = null,  val message: String? = null) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Empty : DataState<Nothing>()

}