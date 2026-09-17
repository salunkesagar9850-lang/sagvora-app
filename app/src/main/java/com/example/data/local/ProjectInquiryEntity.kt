package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_inquiries")
data class ProjectInquiryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val companyName: String,
    val email: String,
    val phoneWhatsApp: String,
    val selectedService: String,
    val projectDescription: String,
    val estimatedBudget: String,
    val preferredContactMethod: String,
    val createdAt: Long = System.currentTimeMillis(),
    val status: String = "Received"
)
