package com.example.dynamicuijetpackcompose.utils

import com.example.dynamicuijetpackcompose.models.Validation


/*Extension functions*/
fun String.isDataInValid(validation: Validation): Boolean
{

   return if(validation.required && this.isEmpty())
    {
        return true
    }
    else if(this.length < validation.minLength)
    {
        return true
    }
    else
    {
        if(validation.pattern != null && validation.pattern.isNotEmpty())
        {
            !isValidText(this, validation.pattern)
        }
        else{
            false
        }
    }

}

fun isValidText(text: String, pattern: String): Boolean {
    // Add your custom validation rules here
    return text.matches(Regex(pattern))
}