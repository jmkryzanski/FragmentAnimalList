package com.example.fragmentanimallist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PositionViewModel: ViewModel(){

    // Version-1
    // A variable to keep track and pass position of the selected list element
    //var position = 0

    // Version-2 (Alternative to the above)
    // With mutablelivedata version
    val position = MutableLiveData<Int>(0)

    fun setPosition(positionPassed: Int) {
        position.value = positionPassed
    }

    fun getPosition(): Int? {
        return position.value
    }

}