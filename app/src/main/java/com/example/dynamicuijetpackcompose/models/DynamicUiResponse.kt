package com.example.dynamicuijetpackcompose.models


/*Model class corresponding to our local json*/
data class DynamicUiResponse(
	val form: List<FormItem>
)

data class Action(
	val headers: Map<String, String>,
	val endpoint: String,
	val method: String? = null,
	val type: String? = null
)


data class Validation(
	val required: Boolean,
	val pattern: String,
	val minLength: Int
)

data class FormItem(
	val action: Action,
	val label: String,
	val type: String? = null,
	val dropdown_content: ArrayList<String>,
	val validation: Validation,
	val altText: String? = null,
	val imageUrl: String? = null,
	val hint: String,
	val text: String,
	val imageHeight: Int
)

