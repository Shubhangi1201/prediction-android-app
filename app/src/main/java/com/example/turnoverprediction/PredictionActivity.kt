package com.example.turnoverprediction

import android.net.http.HttpException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.turnoverprediction.api.PostRequest
import com.example.turnoverprediction.api.ResultResponse
import com.example.turnoverprediction.api.RetorfitInstance
import com.example.turnoverprediction.databinding.ActivityPredictionBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PredictionActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPredictionBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val postRequest = PostRequest("John Doe")

        binding.submit.setOnClickListener{
            checkValidations()
        }
        RetorfitInstance.api.postData(postRequest).enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.isSuccessful) {
                    val postResponse = response.body()
                    val result = postResponse?.result
                    Log.d("abc", "$result")


                    // do something with the result
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.d("abc", "${t.message.toString()}")

            }

        })

        }

    private fun checkValidations(){
        binding.apply {
            if(!SatisfactionLevelEt.text.toString().isNullOrEmpty()){
                if(!lastEvaluationEt.text.toString().isNullOrEmpty()){
                    if(!noOfProjects.text.toString().isNullOrEmpty()){
                        if(!AverageMonthlyHours.text.toString().isNullOrEmpty()){
                            if(!timeSpendCompany.text.toString().isNullOrEmpty()){
                                if(!workAccident.text.toString().isNullOrEmpty()){
                                    if(!promotionLastYear.text.toString().isNullOrEmpty()){
//                                        GetResult.getFinalResult()
                                        val satisfaction = SatisfactionLevelEt.text.toString()
                                        val evaluation = lastEvaluationEt.text.toString()
                                        val noofproject = noOfProjects.text.toString()
                                        val averagehours = AverageMonthlyHours.text.toString()
                                        val timespend = timeSpendCompany.text.toString()
                                        val workaccident = workAccident.text.toString()
                                        val promotion = promotionLastYear.text.toString()
                                        val workAccidentValue = if (workaccident.equals("yes", ignoreCase = true)) true else false
                                        val promotionValue = if (promotion.equals("yes", ignoreCase = true)) true else if (promotion.equals("no", ignoreCase = true)) false else throw IllegalArgumentException("promotion must be either 'yes' or 'no'")
//                                          GetResult.predictEmployeeTurnover()
                                        val result = predictEmployeeTurnover(satisfaction, evaluation, noofproject, averagehours, timespend, workAccidentValue, promotionValue)

                                        if(result == true){
                                            resultTV.visibility = View.VISIBLE
                                            resultTV.setTextColor(ContextCompat.getColor(this@PredictionActivity, R.color.truecolor))
                                            resultTV.text = "Employee will leave the company"
                                        }else{
                                            resultTV.visibility = View.VISIBLE
                                            resultTV.setTextColor(ContextCompat.getColor(this@PredictionActivity, R.color.falsecolor))
                                            resultTV.text = "Employee will stay in company"
                                        }
                                        Toast.makeText(applicationContext, "${result.toString()}", Toast.LENGTH_SHORT)
                                            .show()
                                       
                                    }else{
                                        lastEvaluationEt.setError("This field cannot be empty")
                                    }
                                }else{
                                    lastEvaluationEt.setError("This field cannot be empty")
                                }
                            }else{
                                lastEvaluationEt.setError("This field cannot be empty")
                            }
                        }else{
                            lastEvaluationEt.setError("This field cannot be empty")
                        }
                    }else{
                        lastEvaluationEt.setError("This field cannot be empty")
                    }
                }else{
                    lastEvaluationEt.setError("This field cannot be empty")
                }
            }else{
                SatisfactionLevelEt.setError("This feild cannot be empty")
            }
        }
    }






    fun predictEmployeeTurnover(
        satisfactionLevel: String,
        lastEvaluation: String,
        numberOfProjects: String,
        averageMonthlyHours: String,
        timeSpentCompany: String,
        workAccident: Boolean,
        promotionInLastFiveYears: Boolean
    ): Boolean {
        Log.d("shubhangi", "insde predict function")
        // Define some thresholds and weights based on general knowledge
        val highSatisfactionLevel = 0.5
        val lowLastEvaluation = 0.5
        val manyProjects = 3
        val highAverageMonthlyHours = 150
        val longTimeSpentCompany = 5


        // Convert the input parameters to the appropriate data types
        val satisfactionLevelValue = satisfactionLevel.toDoubleOrNull() ?: 0.0
        val lastEvaluationValue = lastEvaluation.toDoubleOrNull() ?: 0.0
        val numberOfProjectsValue = numberOfProjects.toIntOrNull() ?: 0
        val averageMonthlyHoursValue = averageMonthlyHours.toDoubleOrNull() ?: 0.0
        val timeSpentCompanyValue = timeSpentCompany.toIntOrNull() ?: 0


        if(satisfactionLevelValue<highSatisfactionLevel && lastEvaluationValue<lowLastEvaluation && numberOfProjectsValue>manyProjects && averageMonthlyHoursValue>highAverageMonthlyHours && workAccident && !promotionInLastFiveYears){
            return true
        }else if(satisfactionLevelValue<highSatisfactionLevel && lastEvaluationValue>lowLastEvaluation && numberOfProjectsValue>manyProjects && !promotionInLastFiveYears){
            return true
        }else{
            return false
        }

    }


}