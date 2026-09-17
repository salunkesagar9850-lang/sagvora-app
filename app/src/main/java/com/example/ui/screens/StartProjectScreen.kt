package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ProjectInquiryEntity
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeader
import com.example.ui.components.WhatsAppCTAButton
import com.example.ui.theme.AccentSuccess
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.InquiryFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartProjectScreen(
    formState: InquiryFormState,
    submissionSuccess: Boolean,
    lastSubmittedInquiry: ProjectInquiryEntity?,
    onFieldChange: (fullName: String?, companyName: String?, email: String?, phoneWhatsApp: String?, selectedService: String?, projectDescription: String?, estimatedBudget: String?, preferredContactMethod: String?) -> Unit,
    onSubmit: () -> Unit,
    onContactWhatsApp: () -> Unit,
    onDismissSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val availableServices = listOf(
        "AI Solutions",
        "AI Advertising",
        "AI Filmmaking",
        "App Development",
        "Website Development",
        "Digital Innovation",
        "Custom Technology"
    )

    val budgetTiers = listOf(
        "$1k - $5k",
        "$5k - $15k",
        "$15k - $50k",
        "$50k+",
        "Flexible / Exploring"
    )

    val contactMethods = listOf("WhatsApp", "Email", "Phone Call")

    if (submissionSuccess) {
        // Confirmation Screen
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
                .testTag("submission_success_view"),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
                border = BorderStroke(1.dp, CyanNeon.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(AccentSuccess.copy(alpha = 0.15f))
                            .border(1.dp, AccentSuccess, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Success",
                            tint = AccentSuccess,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Thank you!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your project request has been received.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = CyanNeon,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Our engineering and creative team will review your specifications and reach out via your preferred method (${lastSubmittedInquiry?.preferredContactMethod ?: "WhatsApp"}).",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Summary Box
                    if (lastSubmittedInquiry != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF090D17))
                                .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
                                .padding(14.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Client: ${lastSubmittedInquiry.fullName}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "Service: ${lastSubmittedInquiry.selectedService}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = CyanBright
                                )
                                Text(
                                    text = "Budget: ${lastSubmittedInquiry.estimatedBudget}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextMuted
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onDismissSuccess,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CyanNeon, contentColor = Color(0xFF07090F)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("done_submission_button")
                    ) {
                        Text("Done", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    WhatsAppCTAButton(
                        onClick = onContactWhatsApp,
                        label = "Also Send to WhatsApp Directly"
                    )
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .testTag("start_project_form_root")
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            SectionHeader(
                tag = "Client Inquiries",
                title = "Start a Project",
                subtitle = "Tell us about your project or vision. We review inquiries quickly and provide actionable technology consultations."
            )

            Spacer(modifier = Modifier.height(18.dp))

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Full Name
                    FormLabel(text = "Full Name *")
                    OutlinedTextField(
                        value = formState.fullName,
                        onValueChange = { onFieldChange(it, null, null, null, null, null, null, null) },
                        placeholder = { Text("e.g. Alex Morgan", color = TextMuted) },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = CyanNeon)
                        },
                        isError = formState.errors.containsKey("fullName"),
                        supportingText = {
                            formState.errors["fullName"]?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        singleLine = true,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_full_name")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Company / Brand Name
                    FormLabel(text = "Company / Brand Name")
                    OutlinedTextField(
                        value = formState.companyName,
                        onValueChange = { onFieldChange(null, it, null, null, null, null, null, null) },
                        placeholder = { Text("e.g. Acme Corp / Studio Name", color = TextMuted) },
                        leadingIcon = {
                            Icon(Icons.Default.Business, contentDescription = null, tint = TextMuted)
                        },
                        singleLine = true,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_company_name")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Email
                    FormLabel(text = "Email Address *")
                    OutlinedTextField(
                        value = formState.email,
                        onValueChange = { onFieldChange(null, null, it, null, null, null, null, null) },
                        placeholder = { Text("e.g. alex@company.com", color = TextMuted) },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = CyanNeon)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isError = formState.errors.containsKey("email"),
                        supportingText = {
                            formState.errors["email"]?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        singleLine = true,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_email")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Phone / WhatsApp
                    FormLabel(text = "Phone / WhatsApp *")
                    OutlinedTextField(
                        value = formState.phoneWhatsApp,
                        onValueChange = { onFieldChange(null, null, null, it, null, null, null, null) },
                        placeholder = { Text("e.g. +1 (555) 019-2834", color = TextMuted) },
                        leadingIcon = {
                            Icon(Icons.Default.Phone, contentDescription = null, tint = CyanNeon)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        isError = formState.errors.containsKey("phoneWhatsApp"),
                        supportingText = {
                            formState.errors["phoneWhatsApp"]?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        singleLine = true,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_phone")
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Select Service
                    FormLabel(text = "Select Service *")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        availableServices.forEach { serviceName ->
                            val isSelected = formState.selectedService == serviceName
                            ChoiceChip(
                                label = serviceName,
                                isSelected = isSelected,
                                onClick = { onFieldChange(null, null, null, null, serviceName, null, null, null) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Project Description
                    FormLabel(text = "Project Description *")
                    OutlinedTextField(
                        value = formState.projectDescription,
                        onValueChange = { onFieldChange(null, null, null, null, null, it, null, null) },
                        placeholder = {
                            Text(
                                "Describe your objectives, timeline, deliverables, or technical questions...",
                                color = TextMuted
                            )
                        },
                        isError = formState.errors.containsKey("projectDescription"),
                        supportingText = {
                            formState.errors["projectDescription"]?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        minLines = 4,
                        maxLines = 6,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_project_description")
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Estimated Budget
                    FormLabel(text = "Estimated Budget")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        budgetTiers.forEach { tier ->
                            val isSelected = formState.estimatedBudget == tier
                            ChoiceChip(
                                label = tier,
                                isSelected = isSelected,
                                onClick = { onFieldChange(null, null, null, null, null, null, tier, null) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Preferred Contact Method
                    FormLabel(text = "Preferred Contact Method")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        contactMethods.forEach { method ->
                            val isSelected = formState.preferredContactMethod == method
                            ChoiceChip(
                                label = method,
                                isSelected = isSelected,
                                onClick = { onFieldChange(null, null, null, null, null, null, null, method) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // Submit Button
                    Button(
                        onClick = onSubmit,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CyanNeon,
                            contentColor = Color(0xFF07090F)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("submit_project_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.RocketLaunch,
                                contentDescription = null,
                                tint = Color(0xFF07090F),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Submit Project Request",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Direct WhatsApp Option
                    WhatsAppCTAButton(
                        onClick = onContactWhatsApp,
                        label = "Contact on WhatsApp"
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun FormLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = TextPrimary,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun ChoiceChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (isSelected) CyanNeon else Color(0xFF141C2E)
    val textColor = if (isSelected) Color(0xFF07090F) else TextSecondary
    val borderStroke = if (isSelected) null else BorderStroke(1.dp, BorderGlass)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .then(if (borderStroke != null) Modifier.border(borderStroke, RoundedCornerShape(12.dp)) else Modifier)
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = textColor
        )
    }
}

@Composable
private fun customTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = CyanNeon,
    unfocusedBorderColor = BorderGlass,
    focusedContainerColor = Color(0xFF0B101D),
    unfocusedContainerColor = Color(0xFF0B101D),
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    cursorColor = CyanNeon
)
