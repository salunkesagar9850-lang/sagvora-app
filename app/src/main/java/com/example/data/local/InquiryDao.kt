package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InquiryDao {
    @Query("SELECT * FROM project_inquiries ORDER BY createdAt DESC")
    fun getAllInquiries(): Flow<List<ProjectInquiryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInquiry(inquiry: ProjectInquiryEntity): Long

    @Query("DELETE FROM project_inquiries WHERE id = :id")
    suspend fun deleteInquiry(id: Int)

    @Query("DELETE FROM project_inquiries")
    suspend fun clearAll()
}
