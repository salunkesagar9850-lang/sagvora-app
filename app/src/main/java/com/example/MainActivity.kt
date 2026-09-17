package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.SagvoraBottomNav
import com.example.ui.components.SagvoraTopBar
import com.example.ui.components.launchUrlSafely
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProjectsScreen
import com.example.ui.screens.ServiceDetailScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.StartProjectScreen
import com.example.ui.theme.SagvoraTheme
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            SagvoraTheme(darkTheme = isDarkTheme) {
                SagvoraApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SagvoraApp(viewModel: MainViewModel) {
    val context = LocalContext.current
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedNavTab by viewModel.selectedNavTab.collectAsState()
    val config by viewModel.config.collectAsState()
    val projects by viewModel.projects.collectAsState()
    val inquiries by viewModel.inquiries.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val submissionSuccess by viewModel.submissionSuccess.collectAsState()
    val lastSubmittedInquiry by viewModel.lastSubmittedInquiry.collectAsState()
    val activeService by viewModel.activeService.collectAsState()
    val selectedProjectCategory by viewModel.selectedProjectCategory.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val uiNotice by viewModel.uiNotice.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiNotice) {
        uiNotice?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearNotice()
            }
        }
    }

    // Android Hardware / Gesture Back Navigation Handling
    BackHandler(enabled = currentScreen !is Screen.Home && currentScreen !is Screen.Splash) {
        when (currentScreen) {
            is Screen.ServiceDetail -> viewModel.navigateTo(Screen.Services)
            is Screen.ProjectDetail -> viewModel.navigateTo(Screen.Projects)
            is Screen.About, is Screen.Settings, is Screen.InquiriesAdmin -> viewModel.navigateTo(Screen.Contact)
            else -> viewModel.navigateTo(Screen.Home)
        }
    }

    if (currentScreen is Screen.Splash) {
        SplashScreen(
            onSplashFinished = {
                viewModel.navigateTo(Screen.Home)
            }
        )
    } else {
        Scaffold(
            topBar = {
                SagvoraTopBar(
                    onSettingsClick = { viewModel.navigateTo(Screen.Settings) },
                    onWhatsAppClick = {
                        val whatsappUrl = config.getWhatsAppIntentUrl("Hello SAGVORA, I would like to discuss a project.")
                        launchUrlSafely(context, whatsappUrl, "WhatsApp")
                    }
                )
            },
            bottomBar = {
                SagvoraBottomNav(
                    selectedIndex = selectedNavTab,
                    onTabSelected = { index ->
                        viewModel.selectNavTab(index)
                    }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AnimatedContent(
                    targetState = currentScreen,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "screen_transition"
                ) { screen ->
                    when (screen) {
                        is Screen.Home -> {
                            HomeScreen(
                                config = config,
                                services = viewModel.services,
                                latestProjects = projects,
                                onExploreServices = { viewModel.selectNavTab(1) },
                                onStartProject = { viewModel.selectNavTab(3) },
                                onServiceClick = { serviceId -> viewModel.openServiceDetail(serviceId) },
                                onProjectClick = { project -> viewModel.openProjectDetail(project) },
                                onWhatsAppClick = {
                                    val url = config.getWhatsAppIntentUrl("Hello SAGVORA, I would like to discuss a project.")
                                    launchUrlSafely(context, url, "WhatsApp")
                                }
                            )
                        }

                        is Screen.Services -> {
                            ServicesScreen(
                                services = viewModel.services,
                                onLearnMore = { serviceId -> viewModel.openServiceDetail(serviceId) },
                                onRequestService = { serviceTitle -> viewModel.requestService(serviceTitle) }
                            )
                        }

                        is Screen.ServiceDetail -> {
                            val service = activeService ?: viewModel.services.first()
                            ServiceDetailScreen(
                                service = service,
                                onBack = { viewModel.navigateTo(Screen.Services) },
                                onInquireService = { serviceTitle -> viewModel.requestService(serviceTitle) },
                                onWhatsAppContact = { customMessage ->
                                    val url = config.getWhatsAppIntentUrl(customMessage)
                                    launchUrlSafely(context, url, "WhatsApp")
                                }
                            )
                        }

                        is Screen.Projects -> {
                            ProjectsScreen(
                                projects = projects,
                                selectedCategory = selectedProjectCategory,
                                onCategorySelected = { cat -> viewModel.setProjectCategory(cat) },
                                onStartProjectWithCategory = { cat ->
                                    viewModel.requestService(cat)
                                }
                            )
                        }

                        is Screen.ProjectDetail -> {
                            // Can also open Projects with modal, or route here
                            ProjectsScreen(
                                projects = projects,
                                selectedCategory = selectedProjectCategory,
                                onCategorySelected = { cat -> viewModel.setProjectCategory(cat) },
                                onStartProjectWithCategory = { cat -> viewModel.requestService(cat) }
                            )
                        }

                        is Screen.StartProject -> {
                            StartProjectScreen(
                                formState = formState,
                                submissionSuccess = submissionSuccess,
                                lastSubmittedInquiry = lastSubmittedInquiry,
                                onFieldChange = { fullName, company, email, phone, service, desc, budget, contactMethod ->
                                    viewModel.updateFormField(
                                        fullName = fullName,
                                        companyName = company,
                                        email = email,
                                        phoneWhatsApp = phone,
                                        selectedService = service,
                                        projectDescription = desc,
                                        estimatedBudget = budget,
                                        preferredContactMethod = contactMethod
                                    )
                                },
                                onSubmit = {
                                    val valid = viewModel.submitProjectRequest()
                                    if (!valid) {
                                        Toast.makeText(context, "Please check required fields", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                onContactWhatsApp = {
                                    val serviceText = formState.selectedService
                                    val msg = "Hello SAGVORA, I would like to discuss a project regarding $serviceText."
                                    val url = config.getWhatsAppIntentUrl(msg)
                                    launchUrlSafely(context, url, "WhatsApp")
                                },
                                onDismissSuccess = {
                                    viewModel.dismissSubmissionSuccess()
                                    viewModel.navigateTo(Screen.Home)
                                }
                            )
                        }

                        is Screen.About -> {
                            AboutScreen(
                                onStartProject = { viewModel.selectNavTab(3) }
                            )
                        }

                        is Screen.Contact -> {
                            ContactScreen(
                                config = config,
                                onStartProject = { viewModel.selectNavTab(3) },
                                onOpenSettings = { viewModel.navigateTo(Screen.Settings) },
                                onOpenAbout = { viewModel.navigateTo(Screen.About) }
                            )
                        }

                        is Screen.Settings -> {
                            SettingsScreen(
                                config = config,
                                inquiries = inquiries,
                                isDarkTheme = isDarkTheme,
                                onToggleTheme = { viewModel.toggleTheme() },
                                onSaveConfig = { newConfig -> viewModel.updateCompanyConfig(newConfig) },
                                onDeleteInquiry = { id -> viewModel.deleteInquiry(id) },
                                onBack = { viewModel.navigateTo(Screen.Contact) },
                                onNavigateAbout = { viewModel.navigateTo(Screen.About) },
                                onNavigateContact = { viewModel.navigateTo(Screen.Contact) }
                            )
                        }

                        else -> {
                            HomeScreen(
                                config = config,
                                services = viewModel.services,
                                latestProjects = projects,
                                onExploreServices = { viewModel.selectNavTab(1) },
                                onStartProject = { viewModel.selectNavTab(3) },
                                onServiceClick = { serviceId -> viewModel.openServiceDetail(serviceId) },
                                onProjectClick = { project -> viewModel.openProjectDetail(project) },
                                onWhatsAppClick = {
                                    val url = config.getWhatsAppIntentUrl()
                                    launchUrlSafely(context, url, "WhatsApp")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
