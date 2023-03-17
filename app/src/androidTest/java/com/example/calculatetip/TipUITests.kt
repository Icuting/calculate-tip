package com.example.calculatetip


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.calculatetip.ui.theme.CalculateTipTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat


class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_no_round() {
        composeTestRule.setContent {
            CalculateTipTheme {
                TipCalculateScreen()
            }
        }
        composeTestRule.onNodeWithText("Cost of service")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip (%)")
            .performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists("No node with this text was found.")
    }

    @Test
    fun calculate_8_percent_with_round() {
        composeTestRule.setContent {
            CalculateTipTheme {
                TipCalculateScreen()
            }
        }
        composeTestRule.onNodeWithText("Cost of service")
            .performTextInput("197")
        composeTestRule.onNodeWithText("Tip (%)")
            .performTextInput("8")
        composeTestRule.onNodeWithTag("switch_around")
            .performClick()
        val expectedTip = NumberFormat.getCurrencyInstance().format(16)
        composeTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists("No node with this text was found.")
    }
}