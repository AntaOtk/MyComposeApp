package com.example.mycomposeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.R
import com.example.mycomposeapp.domain.Contact

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetailsPreview()
        }
    }

    @Composable
    fun ContactDetails(contact: Contact) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowImage(contact)
            Text(
                style = MaterialTheme.typography.h6,
                text = "${contact.name} ${contact.surname.orEmpty()}")
            FamilyShow(contact.isFavorite, contact.familyName)
            InfoRow( R.string.phone, contact.phone)
            InfoRow( R.string.address ,contact.address)
            if (!contact.email.isNullOrEmpty()) InfoRow( R.string.email ,contact.email)
        }
    }

    @Composable
    fun FamilyShow(isFavorite: Boolean, familyName: String) {
        Row {
            Text(
                style = MaterialTheme.typography.h5,
                text = familyName
            )
            if (isFavorite) Image(
                modifier = Modifier.padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )
        }

    }

    @Composable
    fun ShowImage(contact: Contact) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp)
        ) {
            if (contact.imageRes != null) {
                ImagePreview(contact.imageRes)
            } else RoundInitials(contact.name.take(1) + contact.familyName.take(1))
        }
    }

    @Composable
    fun ImagePreview(imageRes: Int) {
        Image(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            painter = painterResource(imageRes),
            contentDescription = null
        )
    }

    @Composable
    fun RoundInitials(initials: String) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                tint = Color.LightGray
            )
            Text(
                style = MaterialTheme.typography.h5,
                text = initials
            )
        }
    }

    @Composable
    fun InfoRow(text: Int, content: String) {
        Row ( modifier = Modifier.padding(vertical = 8.dp)){
            Text(
                modifier = Modifier.weight(1F),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                text = getString(text)+":")
            Text(
                modifier = Modifier.weight(1F)
                    .padding(start = 8.dp),
                text = content)
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreview() {
        ContactDetails(
            contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                isFavorite = true,
                email = "lucashin@ya.ru"
            )
        )
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreviewSecond() {
        ContactDetails(
            contact = Contact(
                name = "Василий",
                familyName = "Кузякин",
                phone = "+792321",
                address = "г. Рязань, улица Ленина, д. 3, кв. 2",
                imageRes = R.drawable.car,
            )
        )
    }
}