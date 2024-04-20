package com.example.turnoverprediction

import android.util.Log

object GetResult {
    fun getFinalResult(){
        Log.d("ab", "get final result")
    }fun predictEmployeeTurnover(
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
        val highAverageMonthlyHours = 100
        val longTimeSpentCompany = 5
        val workAccidentWeight = 0.2
        val promotionWeight = 0.1


        // Convert the input parameters to the appropriate data types
        val satisfactionLevelValue = satisfactionLevel.toDoubleOrNull() ?: 0.0
        val lastEvaluationValue = lastEvaluation.toDoubleOrNull() ?: 0.0
        val numberOfProjectsValue = numberOfProjects.toIntOrNull() ?: 0
        val averageMonthlyHoursValue = averageMonthlyHours.toDoubleOrNull() ?: 0.0
        val timeSpentCompanyValue = timeSpentCompany.toIntOrNull() ?: 0
        val score =
            if (satisfactionLevelValue > highSatisfactionLevel) 0.5 else 0.0 + if (lastEvaluationValue < lowLastEvaluation) 0.5 else 0.0 + if (numberOfProjectsValue > manyProjects) 0.5 else 0.0 + if (averageMonthlyHoursValue > highAverageMonthlyHours) 0.5 else 0.0 + if (timeSpentCompanyValue > longTimeSpentCompany) 0.5 else 0.0 + if (workAccident) workAccidentWeight else 0.0 + if (promotionInLastFiveYears) -promotionWeight else 0.0

        // Return a prediction based on the score
        return score >= 1.0

    }
}