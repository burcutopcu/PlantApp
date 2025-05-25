package com.burcutopcu.plantapp.data.localmodel.questions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val uri: String,
    val order: Int
)