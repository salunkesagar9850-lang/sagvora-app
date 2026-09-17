package com.example.data.model

data class CompanyConfig(
    val companyName: String = "SAGVORA",
    val tagline: String = "Imagine. Create. Transform.",
    val founderName: String = "Sagar Salunke",
    val founderRole: String = "Founder & CEO",
    val heroTitle: String = "AI & Technology, Built for What's Next.",
    val heroSubtitle: String = "We create intelligent digital experiences, AI-powered solutions and innovative technology for businesses and creators.",
    val whatsappNumber: String = "+91 9226553511",
    val email: String = "sagvoraofficial@gmail.com",
    val websiteUrl: String = "https://sagvoraofficial.website3.me",
    val instagramUrl: String = "https://www.instagram.com/sagvoraofficial?stkn=MXVyNnczemllM2huMA==",
    val linkedinUrl: String = "https://linkedin.com/company/sagvora",
    val youtubeUrl: String = "https://www.youtube.com/channel/UCaoh6O4PppVAKpJYL4Rv-aA",
    val officeLocation: String = "Global AI & Technology Studio"
) {
    fun getWhatsAppIntentUrl(customMessage: String? = null): String {
        val cleanNumber = whatsappNumber.replace(Regex("[^0-9]"), "")
        val message = customMessage ?: "Hello SAGVORA, I would like to discuss a project."
        val encodedMessage = java.net.URLEncoder.encode(message, "UTF-8")
        return "https://wa.me/$cleanNumber?text=$encodedMessage"
    }
}
