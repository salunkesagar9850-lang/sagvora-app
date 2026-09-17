package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.ProjectInquiryEntity
import com.example.data.model.CompanyConfig
import com.example.data.model.ProjectItem
import com.example.data.model.ServiceItem
import com.example.data.repository.SagvoraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    data object Splash : Screen()
    data object Home : Screen()
    data object Services : Screen()
    data class ServiceDetail(val serviceId: String) : Screen()
    data object Projects : Screen()
    data class ProjectDetail(val projectId: String) : Screen()
    data class StartProject(val initialServiceId: String? = null) : Screen()
    data object About : Screen()
    data object Contact : Screen()
    data object Settings : Screen()
    data object InquiriesAdmin : Screen()
}

data class InquiryFormState(
    val fullName: String = "",
    val companyName: String = "",
    val email: String = "",
    val phoneWhatsApp: String = "",
    val selectedService: String = "AI Solutions",
    val projectDescription: String = "",
    val estimatedBudget: String = "$5,000 - $15,000",
    val preferredContactMethod: String = "WhatsApp",
    val errors: Map<String, String> = emptyMap()
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = SagvoraRepository(application)

    // Reactive State
    val config: StateFlow<CompanyConfig> = repository.config
    val projects: StateFlow<List<ProjectItem>> = repository.projects
    val services: List<ServiceItem> = repository.services
    val inquiries: StateFlow<List<ProjectInquiryEntity>> = repository.allInquiries
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Navigation & Screen Stack
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Splash)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _selectedNavTab = MutableStateFlow(0) // 0: Home, 1: Services, 2: Projects, 3: Start, 4: Contact/More
    val selectedNavTab: StateFlow<Int> = _selectedNavTab.asStateFlow()

    // Filter
    private val _selectedProjectCategory = MutableStateFlow("All")
    val selectedProjectCategory: StateFlow<String> = _selectedProjectCategory.asStateFlow()

    // Form
    private val _formState = MutableStateFlow(InquiryFormState())
    val formState: StateFlow<InquiryFormState> = _formState.asStateFlow()

    private val _submissionSuccess = MutableStateFlow(false)
    val submissionSuccess: StateFlow<Boolean> = _submissionSuccess.asStateFlow()

    private val _lastSubmittedInquiry = MutableStateFlow<ProjectInquiryEntity?>(null)
    val lastSubmittedInquiry: StateFlow<ProjectInquiryEntity?> = _lastSubmittedInquiry.asStateFlow()

    // Active details
    private val _activeService = MutableStateFlow<ServiceItem?>(null)
    val activeService: StateFlow<ServiceItem?> = _activeService.asStateFlow()

    private val _activeProject = MutableStateFlow<ProjectItem?>(null)
    val activeProject: StateFlow<ProjectItem?> = _activeProject.asStateFlow()

    // Dark theme override (defaults to dark-first)
    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    // UI feedback
    private val _uiNotice = MutableStateFlow<String?>(null)
    val uiNotice: StateFlow<String?> = _uiNotice.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
        when (screen) {
            is Screen.Home -> _selectedNavTab.value = 0
            is Screen.Services -> _selectedNavTab.value = 1
            is Screen.Projects -> _selectedNavTab.value = 2
            is Screen.StartProject -> _selectedNavTab.value = 3
            is Screen.Contact, is Screen.About, is Screen.Settings -> _selectedNavTab.value = 4
            else -> {}
        }
    }

    fun selectNavTab(index: Int) {
        _selectedNavTab.value = index
        when (index) {
            0 -> _currentScreen.value = Screen.Home
            1 -> _currentScreen.value = Screen.Services
            2 -> _currentScreen.value = Screen.Projects
            3 -> _currentScreen.value = Screen.StartProject()
            4 -> _currentScreen.value = Screen.Contact
        }
    }

    fun openServiceDetail(serviceId: String) {
        _activeService.value = repository.getService(serviceId)
        _currentScreen.value = Screen.ServiceDetail(serviceId)
    }

    fun openProjectDetail(project: ProjectItem) {
        _activeProject.value = project
        _currentScreen.value = Screen.ProjectDetail(project.id)
    }

    fun requestService(serviceTitle: String) {
        _formState.value = _formState.value.copy(selectedService = serviceTitle)
        _currentScreen.value = Screen.StartProject(initialServiceId = serviceTitle)
        _selectedNavTab.value = 3
    }

    fun setProjectCategory(category: String) {
        _selectedProjectCategory.value = category
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    fun updateFormField(
        fullName: String? = null,
        companyName: String? = null,
        email: String? = null,
        phoneWhatsApp: String? = null,
        selectedService: String? = null,
        projectDescription: String? = null,
        estimatedBudget: String? = null,
        preferredContactMethod: String? = null
    ) {
        val current = _formState.value
        _formState.value = current.copy(
            fullName = fullName ?: current.fullName,
            companyName = companyName ?: current.companyName,
            email = email ?: current.email,
            phoneWhatsApp = phoneWhatsApp ?: current.phoneWhatsApp,
            selectedService = selectedService ?: current.selectedService,
            projectDescription = projectDescription ?: current.projectDescription,
            estimatedBudget = estimatedBudget ?: current.estimatedBudget,
            preferredContactMethod = preferredContactMethod ?: current.preferredContactMethod,
            errors = emptyMap() // clear errors when editing
        )
    }

    fun submitProjectRequest(): Boolean {
        val form = _formState.value
        val errors = mutableMapOf<String, String>()

        if (form.fullName.trim().isEmpty()) {
            errors["fullName"] = "Please enter your full name"
        }
        if (form.email.trim().isEmpty()) {
            errors["email"] = "Please enter your email address"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(form.email.trim()).matches()) {
            errors["email"] = "Please enter a valid email format"
        }
        if (form.phoneWhatsApp.trim().isEmpty()) {
            errors["phoneWhatsApp"] = "Please enter your phone or WhatsApp number"
        }
        if (form.projectDescription.trim().length < 10) {
            errors["projectDescription"] = "Please provide a brief description (at least 10 characters)"
        }

        if (errors.isNotEmpty()) {
            _formState.value = form.copy(errors = errors)
            return false
        }

        viewModelScope.launch {
            val entity = ProjectInquiryEntity(
                fullName = form.fullName.trim(),
                companyName = form.companyName.trim().ifEmpty { "Individual / Not Specified" },
                email = form.email.trim(),
                phoneWhatsApp = form.phoneWhatsApp.trim(),
                selectedService = form.selectedService,
                projectDescription = form.projectDescription.trim(),
                estimatedBudget = form.estimatedBudget,
                preferredContactMethod = form.preferredContactMethod
            )
            repository.submitInquiry(entity)
            _lastSubmittedInquiry.value = entity
            _submissionSuccess.value = true
        }

        return true
    }

    fun dismissSubmissionSuccess() {
        _submissionSuccess.value = false
        // Reset form
        _formState.value = InquiryFormState()
    }

    fun updateCompanyConfig(newConfig: CompanyConfig) {
        repository.updateConfig(newConfig)
        _uiNotice.value = "Settings updated successfully"
    }

    fun deleteInquiry(id: Int) {
        viewModelScope.launch {
            repository.deleteInquiry(id)
        }
    }

    fun clearNotice() {
        _uiNotice.value = null
    }
}
