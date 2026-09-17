package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.local.AppDatabase
import com.example.data.local.ProjectInquiryEntity
import com.example.data.model.CompanyConfig
import com.example.data.model.ProjectCatalog
import com.example.data.model.ProjectItem
import com.example.data.model.ServiceCatalog
import com.example.data.model.ServiceItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SagvoraRepository(context: Context) {

    private val database = AppDatabase.getInstance(context)
    private val inquiryDao = database.inquiryDao()
    private val prefs: SharedPreferences =
        context.getSharedPreferences("sagvora_company_config", Context.MODE_PRIVATE)

    // Reactive inquiries from Room
    val allInquiries: Flow<List<ProjectInquiryEntity>> = inquiryDao.getAllInquiries()

    // Services
    val services: List<ServiceItem> = ServiceCatalog.services

    // Dynamic Projects (allows adding/editing projects without redesigning the app)
    private val _projects = MutableStateFlow(ProjectCatalog.defaultProjects)
    val projects: StateFlow<List<ProjectItem>> = _projects.asStateFlow()

    // Dynamic Config (editable WhatsApp number, email, social links)
    private val _config = MutableStateFlow(loadConfigFromPrefs())
    val config: StateFlow<CompanyConfig> = _config.asStateFlow()

    private fun loadConfigFromPrefs(): CompanyConfig {
        return CompanyConfig(
            whatsappNumber = prefs.getString("whatsapp_number", "+15550192834") ?: "+15550192834",
            email = prefs.getString("email", "contact@sagvora.com") ?: "contact@sagvora.com",
            websiteUrl = prefs.getString("website_url", "https://sagvora.com") ?: "https://sagvora.com",
            instagramUrl = prefs.getString("instagram_url", "https://instagram.com/sagvora.ai") ?: "https://instagram.com/sagvora.ai",
            linkedinUrl = prefs.getString("linkedin_url", "https://linkedin.com/company/sagvora") ?: "https://linkedin.com/company/sagvora",
            youtubeUrl = prefs.getString("youtube_url", "https://youtube.com/@sagvora") ?: "https://youtube.com/@sagvora"
        )
    }

    fun updateConfig(newConfig: CompanyConfig) {
        prefs.edit().apply {
            putString("whatsapp_number", newConfig.whatsappNumber)
            putString("email", newConfig.email)
            putString("website_url", newConfig.websiteUrl)
            putString("instagram_url", newConfig.instagramUrl)
            putString("linkedin_url", newConfig.linkedinUrl)
            putString("youtube_url", newConfig.youtubeUrl)
            apply()
        }
        _config.value = newConfig
    }

    suspend fun submitInquiry(inquiry: ProjectInquiryEntity): Long {
        return inquiryDao.insertInquiry(inquiry)
    }

    suspend fun deleteInquiry(id: Int) {
        inquiryDao.deleteInquiry(id)
    }

    fun addProject(project: ProjectItem) {
        val current = _projects.value.toMutableList()
        current.add(0, project)
        _projects.value = current
    }

    fun getService(id: String): ServiceItem? {
        return ServiceCatalog.getServiceById(id)
    }
}
