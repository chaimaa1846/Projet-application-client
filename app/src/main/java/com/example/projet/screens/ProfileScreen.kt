package com.example.projet.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("Utilisateur") }
    var email by remember { mutableStateOf("user@example.com") }
    var phone by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isEditing by remember { mutableStateOf(false) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) imageUri = uri
        }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background avec dégradé
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

        // Contenu principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar avec bouton retour
            TopAppBar(
                title = {
                    Text(
                        "Mon Profil",
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
                actions = {
                    IconButton(onClick = { isEditing = !isEditing }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = if (isEditing) "Annuler" else "Modifier",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // Section Photo de profil
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Photo de profil avec effet d'ombre
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.1f))
                        .clickable { imagePickerLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Photo de profil",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Photo par défaut",
                                tint = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                    // Indicateur d'ajout/modification de photo
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Modifier photo",
                            tint = Color(0xFF6D4C41),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Badge de statut
                if (!isEditing) {
                    Card(
                        modifier = Modifier,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.15f)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {

                    }
                }
            }

            // Section des informations
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
                    Text(
                        text = "Informations personnelles",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    ModernTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Nom complet",
                        enabled = isEditing
                    )

                    ModernTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Adresse email",
                        enabled = isEditing
                    )

                    ModernTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = "Numéro de téléphone",
                        enabled = isEditing
                    )


                }
            }

            // Boutons d'action
            if (isEditing) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Bouton Annuler
                    OutlinedButton(
                        onClick = { isEditing = false },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(listOf(Color.White, Color.White))
                        )
                    ) {
                        Text("Annuler")
                    }

                    // Bouton Sauvegarder
                    Button(
                        onClick = {
                            isEditing = false
                            // Logique de sauvegarde
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Sauvegarder",
                            color = Color(0xFF6D4C41),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    isPassword: Boolean = false,
    maxLines: Int = 1
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
        maxLines = maxLines,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(12.dp)
    )
}