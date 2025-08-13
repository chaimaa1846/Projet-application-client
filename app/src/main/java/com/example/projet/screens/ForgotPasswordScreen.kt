package com.example.projet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var currentStep by remember { mutableStateOf(1) } // 1: Phone, 2: Code, 3: New Password
    var phoneNumber by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var countdown by remember { mutableStateOf(0) }

    // Countdown timer for resend code
    LaunchedEffect(countdown) {
        if (countdown > 0) {
            delay(1000)
            countdown--
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background avec dégradé identique au profil
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF6D4C41),
                            Color(0xFF5D4037),
                            Color(0xFF4E342E)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar
            TopAppBar(
                title = {
                    Text(
                        "Mot de passe oublié",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Retour",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )



            // Main content card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    when (currentStep) {
                        1 -> PhoneNumberStep(
                            phoneNumber = phoneNumber,
                            onPhoneNumberChange = { phoneNumber = it },
                            errorMessage = errorMessage,
                            successMessage = successMessage,
                            isLoading = isLoading,
                            onSendCode = {
                                if (phoneNumber.isNotEmpty() && phoneNumber.length >= 8) {
                                    isLoading = true
                                    errorMessage = ""
                                    // Simulation d'envoi de code
                                    kotlinx.coroutines.MainScope().launch {
                                        delay(2000)
                                        isLoading = false
                                        successMessage = "Code envoyé avec succès!"
                                        delay(1000)
                                        currentStep = 2
                                        countdown = 60
                                        successMessage = ""
                                    }
                                } else {
                                    errorMessage = "Veuillez entrer un numéro de téléphone valide"
                                }
                            }
                        )

                        2 -> VerificationCodeStep(
                            verificationCode = verificationCode,
                            onCodeChange = { verificationCode = it },
                            phoneNumber = phoneNumber,
                            errorMessage = errorMessage,
                            isLoading = isLoading,
                            countdown = countdown,
                            onVerifyCode = {
                                if (verificationCode.length == 6) {
                                    isLoading = true
                                    errorMessage = ""
                                    // Simulation de vérification
                                    kotlinx.coroutines.MainScope().launch {
                                        delay(1500)
                                        isLoading = false
                                        if (verificationCode == "159753") {
                                            currentStep = 3
                                        } else {
                                            errorMessage = "Code incorrect. Veuillez réessayer."
                                        }
                                    }
                                } else {
                                    errorMessage = "Veuillez entrer un code à 6 chiffres"
                                }
                            },
                            onResendCode = {
                                if (countdown == 0) {
                                    countdown = 60
                                    successMessage = "Code renvoyé!"
                                    kotlinx.coroutines.MainScope().launch {
                                        delay(2000)
                                        successMessage = ""
                                    }
                                }
                            }
                        )

                        3 -> NewPasswordStep(
                            newPassword = newPassword,
                            confirmPassword = confirmPassword,
                            onNewPasswordChange = { newPassword = it },
                            onConfirmPasswordChange = { confirmPassword = it },
                            showPassword = showPassword,
                            showConfirmPassword = showConfirmPassword,
                            onTogglePasswordVisibility = { showPassword = !showPassword },
                            onToggleConfirmPasswordVisibility = { showConfirmPassword = !showConfirmPassword },
                            errorMessage = errorMessage,
                            isLoading = isLoading,
                            onResetPassword = {
                                errorMessage = ""
                                if (newPassword.length < 6) {
                                    errorMessage = "Le mot de passe doit contenir au moins 6 caractères"
                                } else if (newPassword != confirmPassword) {
                                    errorMessage = "Les mots de passe ne correspondent pas"
                                } else {
                                    isLoading = true
                                    // Simulation de réinitialisation
                                    kotlinx.coroutines.MainScope().launch {
                                        delay(2000)
                                        isLoading = false
                                        // Retour à l'écran de connexion
                                        navController.popBackStack()
                                    }
                                }
                            }
                        )
                    }

                    // Messages d'erreur et de succès
                    if (errorMessage.isNotEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Red.copy(alpha = 0.1f)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = errorMessage,
                                color = Color.Red.copy(alpha = 0.9f),
                                modifier = Modifier.padding(12.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    if (successMessage.isNotEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Green.copy(alpha = 0.1f)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = successMessage,
                                color = Color.Green.copy(alpha = 0.9f),
                                modifier = Modifier.padding(12.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}



@Composable
fun PhoneNumberStep(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    errorMessage: String,
    successMessage: String,
    isLoading: Boolean,
    onSendCode: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Icon(
            Icons.Default.Phone,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Récupération du mot de passe",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Entrez votre numéro de téléphone pour recevoir un code de vérification",
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        ForgotPasswordTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = "Numéro de téléphone",
            enabled = !isLoading,
            keyboardType = KeyboardType.Phone
        )

        Button(
            onClick = onSendCode,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    "Envoyer le code",
                    color = Color(0xFF6D4C41),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun VerificationCodeStep(
    verificationCode: String,
    onCodeChange: (String) -> Unit,
    phoneNumber: String,
    errorMessage: String,
    isLoading: Boolean,
    countdown: Int,
    onVerifyCode: () -> Unit,
    onResendCode: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Icon(
            Icons.Default.Lock,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Code de vérification",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Nous avons envoyé un code à 6 chiffres au numéro $phoneNumber",
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        ForgotPasswordTextField(
            value = verificationCode,
            onValueChange = { if (it.length <= 6) onCodeChange(it) },
            label = "Code de vérification",
            enabled = !isLoading,
            keyboardType = KeyboardType.Number
        )

        Button(
            onClick = onVerifyCode,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    "Vérifier le code",
                    color = Color(0xFF6D4C41),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        OutlinedButton(
            onClick = onResendCode,
            modifier = Modifier.fillMaxWidth(),
            enabled = countdown == 0,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = Brush.horizontalGradient(listOf(Color.White, Color.White))
            )
        ) {
            Text(
                if (countdown > 0) "Renvoyer dans ${countdown}s" else "Renvoyer le code",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun NewPasswordStep(
    newPassword: String,
    confirmPassword: String,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    showConfirmPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    errorMessage: String,
    isLoading: Boolean,
    onResetPassword: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Icon(
            Icons.Default.Lock,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Nouveau mot de passe",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Créez un nouveau mot de passe sécurisé pour votre compte",
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        ForgotPasswordTextField(
            value = newPassword,
            onValueChange = onNewPasswordChange,
            label = "Nouveau mot de passe",
            enabled = !isLoading,
            isPassword = !showPassword
        )

        ForgotPasswordTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmer le mot de passe",
            enabled = !isLoading,
            isPassword = !showConfirmPassword
        )

        Button(
            onClick = onResetPassword,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    "Réinitialiser le mot de passe",
                    color = Color(0xFF6D4C41),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = if (enabled) Color.White.copy(alpha = 0.8f)
                else Color.White.copy(alpha = 0.5f)
            )
        },
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(
            color = Color.White
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = if (enabled) Color.White.copy(alpha = 0.1f)
            else Color.Transparent,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
            disabledBorderColor = Color.Transparent,
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
            disabledLabelColor = Color.White.copy(alpha = 0.5f),
            disabledTextColor = Color.White.copy(alpha = 0.7f)
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}