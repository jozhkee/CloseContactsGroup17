package com.example.closecontactsgroup17

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Contact(val name: String, val phoneNumber: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListScreen()
        }
    }
}

@Composable
fun ContactListScreen() {
    val contacts = listOf(
        Contact("Tiago Alexandre Mendes", "+351 912 345 678"),
        Contact("Beatriz Sofia Figueiredo", "+351 925 876 543"),
        Contact("Mateus Henrique Oliveira", "+351 933 210 987"),
        Contact("Valentina Maria Guerreiro", "+351 964 555 123"),
        Contact("Gonçalo Nuno Vasconcelos", "+351 918 777 444"),
        Contact("Inês Filipa Cavaco", "+351 929 000 111")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(contacts) { contact ->
            ContactItem(contact = contact)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { dialPhoneNumber(context, contact.phoneNumber) }
    ) {
        Text(text = contact.name, fontSize = 18.sp)
        Text(text = contact.phoneNumber, fontSize = 14.sp)
    }
}

fun dialPhoneNumber(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}