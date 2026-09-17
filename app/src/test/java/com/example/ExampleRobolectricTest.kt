package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CompanyConfig
import com.example.data.model.ProjectCatalog
import com.example.data.model.ServiceCatalog
import com.example.data.repository.SagvoraRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read app name from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("SAGVORA", appName)
  }

  @Test
  fun `verify service catalog completeness`() {
    val services = ServiceCatalog.services
    assertEquals(6, services.size)
    assertNotNull(ServiceCatalog.getServiceById("ai_solutions"))
    assertNotNull(ServiceCatalog.getServiceById("ai_advertising"))
    assertNotNull(ServiceCatalog.getServiceById("ai_filmmaking"))
    assertNotNull(ServiceCatalog.getServiceById("app_development"))
    assertNotNull(ServiceCatalog.getServiceById("website_development"))
    assertNotNull(ServiceCatalog.getServiceById("digital_innovation"))
  }

  @Test
  fun `verify company config whatsapp formatting`() {
    val config = CompanyConfig(whatsappNumber = "+15550192834")
    val url = config.getWhatsAppIntentUrl("Hello SAGVORA")
    assertTrue(url.contains("5550192834"))
    assertTrue(url.contains("Hello+SAGVORA") || url.contains("Hello%20SAGVORA"))
  }

  @Test
  fun `verify project catalog categories`() {
    assertTrue(ProjectCatalog.categories.contains("AI Advertising"))
    assertTrue(ProjectCatalog.categories.contains("AI Films"))
    assertTrue(ProjectCatalog.categories.contains("Apps"))
    assertTrue(ProjectCatalog.categories.contains("Websites"))
    assertTrue(ProjectCatalog.categories.contains("AI Experiments"))
  }
}
